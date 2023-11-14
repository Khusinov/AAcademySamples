package com.khusinov.ieltswritingassistant.ui.tips


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.khusinov.ieltswritingassistant.R
import com.khusinov.ieltswritingassistant.databinding.FragmentTipsBinding
import com.khusinov.ieltswritingassistant.model.Tip
import com.khusinov.ieltswritingassistant.viewBinding
import com.yandex.mobile.ads.banner.AdSize
import com.yandex.mobile.ads.banner.BannerAdEventListener
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import com.yandex.mobile.ads.common.MobileAds


class TipsFragment : Fragment(R.layout.fragment_tips) {

    val binding by viewBinding { FragmentTipsBinding.bind(it) }
    private val TAG = "TipsFragment"
    private lateinit var mBannerAdView: BannerAdView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()

        MobileAds.initialize(requireContext()) { }
        loadYandexAds()
    }

    private fun setupUI() {

        binding.pBar.visibility = View.VISIBLE
        binding.rv.visibility = View.GONE

        requireActivity().window.statusBarColor = requireContext().getColor(R.color.green)

        binding.apply {


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
                callIt(tipList)
            }.addOnFailureListener { exception ->
                Log.w(TAG, "setupUI: Error getting documents.  ", exception)
            }
        }
    }

    private fun callIt(list: List<Tip>) {


        binding.apply {
            pBar.visibility = View.GONE
            rv.visibility = View.VISIBLE

            val recyclerView = rv
            var adapter = TipsAdapter()
            recyclerView.adapter = adapter
            adapter.submitList(list)

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