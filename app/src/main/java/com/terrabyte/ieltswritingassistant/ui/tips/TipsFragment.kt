package com.terrabyte.ieltswritingassistant.ui.tips


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.terrabyte.ieltswritingassistant.R
import com.terrabyte.ieltswritingassistant.databinding.FragmentTipsBinding
import com.terrabyte.ieltswritingassistant.model.VideoLessons
import com.terrabyte.ieltswritingassistant.viewBinding


class TipsFragment : Fragment(R.layout.fragment_tips) {

    val binding by viewBinding { FragmentTipsBinding.bind(it) }
    private val TAG = "TipsFragment"
    private val adapter by lazy { TipsAdapter(this::onItemClicked) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()

    }

    private fun setupUI() {

        binding.pBar.visibility = View.VISIBLE
        binding.rv.visibility = View.GONE

        requireActivity().window.statusBarColor = requireContext().getColor(R.color.blue2)


        binding.apply {


            val videosList = ArrayList<VideoLessons>()

            val db = Firebase.firestore
            db.collection("video_lessons").get().addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Galdi bi", "${document.id} => ${document.data}")

                    var video = VideoLessons(
                        document.data["id"].toString(),
                        document.data["name"].toString(),
                        document.data["type"].toString(),
                        document.data["videoUrl"].toString()
                    )
                    videosList.add(video)

                }
                callIt(videosList)
            }.addOnFailureListener { exception ->
                Log.w(TAG, "setupUI: Error getting documents.  ", exception)
            }
        }
    }

    private fun callIt(list: List<VideoLessons>) {


        binding.apply {
            pBar.visibility = View.GONE
            rv.visibility = View.VISIBLE

            val recyclerView = rv
            recyclerView.adapter = adapter
            adapter.submitList(list)


        }
    }

    private fun onItemClicked(videoLessons: VideoLessons) {
        val bundle = Bundle()
        bundle.putString("videoUrl", videoLessons.videoUrl) // Example: passing a String
        bundle.putString("lessonId", videoLessons.id) // Example: passing a String

        findNavController().navigate(R.id.action_tipsFragment_to_videoViewFragment, bundle)
    }

}