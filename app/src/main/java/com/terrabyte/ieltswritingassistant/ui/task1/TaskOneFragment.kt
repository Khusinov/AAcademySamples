package com.terrabyte.ieltswritingassistant.ui.task1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.terrabyte.ieltswritingassistant.R
import com.terrabyte.ieltswritingassistant.databinding.FragmentTask1Binding
import com.terrabyte.ieltswritingassistant.model.TaskOne
import com.terrabyte.ieltswritingassistant.viewBinding

class TaskOneFragment : Fragment(R.layout.fragment_task1) {

    val binding by viewBinding { FragmentTask1Binding.bind(it) }
    private val TAG = "TaskOneFragment"


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        admob()
    }

    private fun setupUI() {

        val taskOneList = ArrayList<TaskOne>()
        binding.pBar.visibility = View.VISIBLE
        binding.rv.visibility = View.GONE

        val db = Firebase.firestore
        db.collection("question")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Galdi bi", "${document.id} => ${document.data}")

                    val taskOne = TaskOne(
                        document.data["body"].toString(),
                        document.data["imageUrl"].toString(),
                        document.data["date"].toString(),
                        document.data["sample"].toString(),
                        document.data["question"].toString(),
                        document.data["vocabulary"].toString(),
                        document.data["author"].toString(),
                        document.data["score"].toString(),
                        document.data["grammar"].toString(),
                    )

                    taskOneList.add(taskOne)
                }
                callIt(taskOneList)

            }.addOnFailureListener { exception ->
                Log.w(TAG, "setupUI: Error getting documents.  ", exception)

            }

        requireActivity().window.statusBarColor = requireContext().getColor(R.color.red)

        binding.apply {


            icon.setOnClickListener {

            }

        }
    }

    private fun callIt(taskOneList: ArrayList<TaskOne>) {
        binding.apply {
            pBar.visibility = View.GONE
            rv.visibility = View.VISIBLE

            val recyclerView = rv
            val adapter = TaskOneAdapter()
            recyclerView.adapter = adapter
            adapter.submitList(taskOneList)

            adapter.onClick = {
                findNavController().navigate(
                    R.id.action_taskOneFragment_to_taskOneMoreFragment,
                    bundleOf("task" to it)
                )
            }
        }
    }

    private fun admob() {
        Log.d(TAG, "admob: 130")

        MobileAds.initialize(requireContext()) {}
        val mAdView: AdView = binding.adView

        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)

        mAdView.adListener = object : AdListener() {

            override fun onAdClicked() {
                Log.d(TAG, "onAdClicked: ")
            }

            override fun onAdClosed() {
                Log.d(TAG, "onAdClosed: ")
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d(TAG, "onAdFailedToLoad: ")
            }

            override fun onAdImpression() {
                Log.d(TAG, "onAdImpression: ")
            }

            override fun onAdLoaded() {
                Log.d(TAG, "onAdLoaded: ")
            }

            override fun onAdOpened() {
                Log.d(TAG, "onAdOpened: ")
            }

        }
    }

}