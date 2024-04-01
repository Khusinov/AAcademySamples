package com.terrabyte.ieltswritingassistant.ui.vocabularies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.terrabyte.ieltswritingassistant.R
import com.terrabyte.ieltswritingassistant.databinding.FragmentVocabulariesBinding
import com.terrabyte.ieltswritingassistant.model.VideoLessons
import com.terrabyte.ieltswritingassistant.viewBinding

class VocabulariesFragment : Fragment() {

    val binding by viewBinding { FragmentVocabulariesBinding.bind(it) }
    private val TAG = "TipsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vocabularies, container, false)
    }

    private fun setupUI() {

        binding.pBar.visibility = View.VISIBLE
        binding.rv.visibility = View.GONE

        requireActivity().window.statusBarColor = requireContext().getColor(R.color.yellow)

        binding.apply {


            val videosList = ArrayList<VideoLessons>()

            val db = Firebase.firestore
            db.collection("vocabularies").get().addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Galdi bi", "${document.id} => ${document.data}")

                    val video = VideoLessons(
                        document.data["id"].toString(),
                        document.data["topicName"].toString(),
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
            var adapter = VocabularyAdapter()
            recyclerView.adapter = adapter
            adapter.submitList(list)

        }
    }


}