package com.khusinov.aacademysamples.ui.tips


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.khusinov.aacademysamples.R
import com.khusinov.aacademysamples.databinding.FragmentTipsBinding
import com.khusinov.aacademysamples.model.TaskOne
import com.khusinov.aacademysamples.model.Tip
import com.khusinov.aacademysamples.viewBinding


class TipsFragment : Fragment(R.layout.fragment_tips) {

    val binding by viewBinding { FragmentTipsBinding.bind(it) }
    private val TAG = "TipsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        requireActivity().window.statusBarColor = requireContext().getColor(R.color.green)

        binding.apply {

            icBack.setOnClickListener {
                findNavController().navigate(R.id.action_tipsFragment_to_homeFragment)
            }

            val tipList = ArrayList<Tip>()

            val db = Firebase.firestore
            db.collection("tip").get().addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Galdi bi", "${document.id} => ${document.data}")

                    val tip = Tip(
                        document.data["tipBody"].toString(),
                        document.data["imageUrl"].toString()
                    )
                    tipList.add(tip)

                }
                callIt()
            }.addOnFailureListener { exception ->
                Log.w(TAG, "setupUI: Error getting documents.  ", exception)
            }
        }
    }

    private fun callIt() {

    }


}