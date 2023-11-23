package com.terrabyte.ieltswritingassistant.ui.mistakes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.terrabyte.ieltswritingassistant.R
import com.terrabyte.ieltswritingassistant.databinding.FragmentMistakesBinding
import com.terrabyte.ieltswritingassistant.model.Tip
import com.terrabyte.ieltswritingassistant.viewBinding
import com.yandex.mobile.ads.banner.AdSize
import com.yandex.mobile.ads.banner.BannerAdEventListener
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import com.yandex.mobile.ads.common.MobileAds


class MistakesFragment : Fragment(R.layout.fragment_mistakes) {
    val binding by viewBinding { FragmentMistakesBinding.bind(it) }
    private val TAG = "MistakesFragment"
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

        }

        val mistakeList = ArrayList<Tip>()

        val db = Firebase.firestore
        db.collection("mistake").get().addOnSuccessListener { result ->
            for (document in result) {
                Log.d("Galdi bi", "${document.id} => ${document.data}")

                //  mistake and tip is the same model classes
                val tip = Tip(
                    document.data["tipBody"].toString(), document.data["imageUrl"].toString()
                )
                mistakeList.add(tip)
            }
            callIt(mistakeList)
        }.addOnFailureListener { exception ->
            Log.w(TAG, "setupUI: Error getting documents.  ", exception)

        }

    }

    private fun callIt(mistakeList: java.util.ArrayList<Tip>) {
        binding.apply {
            pBar.visibility = View.GONE
            rv.visibility = View.VISIBLE

            val recyclerView = rv
            val adapter = MistakesAdapter()
            recyclerView.adapter = adapter
            adapter.submitList(mistakeList)

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