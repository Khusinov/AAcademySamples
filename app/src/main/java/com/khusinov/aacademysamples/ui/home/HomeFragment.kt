package com.khusinov.aacademysamples.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.android.gms.ads.*
import com.khusinov.aacademysamples.R
import com.khusinov.aacademysamples.databinding.FragmentHomeBinding
import com.khusinov.aacademysamples.viewBinding


class HomeFragment : Fragment(R.layout.fragment_home) {
    val binding by viewBinding { FragmentHomeBinding.bind(it) }
    private val TAG = "HomeFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        admob()
    }

    private fun setupUI() {
        binding.apply {

//
//            MobileAds.initialize(requireContext()) {}
//
//            val mAdView = binding.adView
//            val adRequest = AdRequest.Builder().build()
//            mAdView.loadAd(adRequest)
//
//            val adView = AdView(requireContext())
//            adView.adUnitId = "ca-app-pub-8982515368836053/8205991526"


        }

// TODO: Add adView to your view hierarchy.

        requireActivity().window.statusBarColor = requireContext().getColor(R.color.white)

        binding.task1.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_taskOneFragment)
        }
        binding.task2.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_taskTwoFragment)
        }
        binding.tips.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_tipsFragment)
        }
        binding.commonMistakes.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_mistakesFragment)
        }
        binding.shareBtn.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Hey, Check out this great app:\nhttps://play.google.com/store/apps/details?id=uz.khusinov.aacademysamples"
            )
            intent.type = "text/plain"
            //
            startActivity(Intent.createChooser(intent, "Share To:"))
        }
        binding.aiService.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_aiFragment)
        }
        binding.menu.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_aboutUsFragment)
        }
    }

    private fun admob() {

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
