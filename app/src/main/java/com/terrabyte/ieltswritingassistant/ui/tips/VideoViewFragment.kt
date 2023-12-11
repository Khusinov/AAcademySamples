package com.terrabyte.ieltswritingassistant.ui.tips

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import com.terrabyte.ieltswritingassistant.R
import com.terrabyte.ieltswritingassistant.databinding.FragmentVideoViewBinding
import com.terrabyte.ieltswritingassistant.viewBinding

class VideoViewFragment : Fragment() {
    private val binding by viewBinding { FragmentVideoViewBinding.bind(it) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.apply {

            val mediaController = MediaController(requireContext())
            mediaController.setAnchorView(binding.videoView)

            val onlineUri = Uri.parse("")
            videoView.setMediaController(mediaController)
            videoView.setVideoURI(onlineUri)
            videoView.requestFocus()
            videoView.start()


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video_view, container, false)
    }

}