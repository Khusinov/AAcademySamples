package com.terrabyte.ieltswritingassistant.ui.task1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.terrabyte.ieltswritingassistant.R
import com.terrabyte.ieltswritingassistant.databinding.FragmentTask1Binding
import com.terrabyte.ieltswritingassistant.model.TaskOne
import com.terrabyte.ieltswritingassistant.viewBinding
import com.yandex.mobile.ads.banner.AdSize
import com.yandex.mobile.ads.banner.BannerAdEventListener
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import com.yandex.mobile.ads.common.MobileAds


class TaskOneFragment : Fragment(R.layout.fragment_task1) {

    val binding by viewBinding { FragmentTask1Binding.bind(it) }
    private val TAG = "TaskOneFragment"
    private lateinit var mBannerAdView: BannerAdView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        MobileAds.initialize(requireContext()) { }

        loadYandexAds()
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

    private fun loadYandexAds() {
        mBannerAdView = binding.yandexBanner
        mBannerAdView.setAdUnitId("R-M-2580296-1")
        binding.yandexBanner.setAdSize(AdSize.stickySize(requireContext(), 320))


        // Creating an ad targeting object.
        val adRequest = AdRequest.Builder().build()

        // Registering a listener for tracking events in the banner ad.
        mBannerAdView.setBannerAdEventListener(object : BannerAdEventListener {
            override fun onAdLoaded() {
                mBannerAdView.visibility = View.VISIBLE
                Log.d("YANDEX", "LOAD")
            }

            override fun onAdFailedToLoad(p0: AdRequestError) {
                mBannerAdView.visibility = View.INVISIBLE
                Log.d("YANDEX FAILED", p0.toString())
            }

            override fun onAdClicked() {}

            override fun onLeftApplication() {}

            override fun onReturnedToApplication() {}

            override fun onImpression(p0: ImpressionData?) {}

        })
        // Loading ads.
        mBannerAdView.loadAd(adRequest)
    }


}