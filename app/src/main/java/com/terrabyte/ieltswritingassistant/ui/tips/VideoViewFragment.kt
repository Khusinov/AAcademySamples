package com.terrabyte.ieltswritingassistant.ui.tips

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.util.MimeTypes
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.terrabyte.ieltswritingassistant.R
import com.terrabyte.ieltswritingassistant.databinding.FragmentVideoViewBinding
import com.terrabyte.ieltswritingassistant.model.VideoFiles
import com.terrabyte.ieltswritingassistant.viewBinding
import java.io.File

class VideoViewFragment : Fragment(R.layout.fragment_video_view) {
    private val binding by viewBinding { FragmentVideoViewBinding.bind(it) }
    private var videoUrl = ""
    private var lessonId = ""
    private var player: ExoPlayer? = null
    private val isPlaying get() = player?.isPlaying ?: false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoUrl = requireArguments().getString("videoUrl")!!
        lessonId = requireArguments().getString("lessonId")!!
        setupUI()
        initializePlayer()
    }

    private fun setupUI() {
        binding.apply {

            val files = VideoFiles("", "")

            val db = Firebase.firestore
            db.collection("lessonsFiles").get().addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Galdi bi", "${document.id} => ${document.data}")

                    val file = VideoFiles(
                        document.data["id"].toString(),
                        document.data["filePath"].toString()
                    )

                    if (file.id == lessonId) {
                        files.filePath = file.filePath
                    }
                    break
                }
            }.addOnFailureListener { exception ->
                Log.w("TAG", "setupUI: Error getting documents.  ", exception)
            }

            lessonsFile.setOnClickListener {
                if (files.filePath.isNotEmpty()) {
                    val storage = FirebaseStorage.getInstance()
                    val storageRef = storage.reference.child(files.filePath)

                    val localFile = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), files.filePath)

                    storageRef.getFile(localFile).addOnSuccessListener {
                        Toast.makeText(requireContext(), "PDF downloaded successfully", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Log.d("TAG", "setupUI: ${it.message} ")
                        Toast.makeText(requireContext(), "Not found any file", Toast.LENGTH_SHORT).show()
                    }

                } else
                    Toast.makeText(requireContext(), "Wait, it's finding", Toast.LENGTH_SHORT)
                        .show()
            }
        }
    }

    private fun initializePlayer() {
        player = ExoPlayer.Builder(requireContext()) // <- context
            .build()

        // create a media item.
        val mediaItem = MediaItem.Builder()
            .setUri(videoUrl)
            .setMimeType(MimeTypes.APPLICATION_MP4)
            .build()

        // Create a media source and pass the media item
        val mediaSource = ProgressiveMediaSource.Factory(
            DefaultDataSource.Factory(requireContext()) // <- context
        )
            .createMediaSource(mediaItem)

        // Finally assign this media source to the player
        player!!.apply {
            setMediaSource(mediaSource)
            playWhenReady = true // start playing when the exoplayer has setup
            seekTo(0, 0L) // Start from the beginning
            prepare() // Change the state from idle.
        }.also {
            // Do not forget to attach the player to the view
            binding.playerView.player = it
        }
    }

}