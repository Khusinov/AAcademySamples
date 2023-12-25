package com.terrabyte.ieltswritingassistant.ui.home

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.terrabyte.ieltswritingassistant.R
import com.terrabyte.ieltswritingassistant.databinding.FragmentHomeBinding
import com.terrabyte.ieltswritingassistant.viewBinding
import com.yandex.mobile.ads.banner.AdSize
import com.yandex.mobile.ads.banner.BannerAdEventListener
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import com.yandex.mobile.ads.common.MobileAds


class HomeFragment : Fragment(R.layout.fragment_home) {
    val binding by viewBinding { FragmentHomeBinding.bind(it) }
    private val TAG = "HomeFragment"
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var mBannerAdView: BannerAdView




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkInternetConnection()
        setupUI()
        MobileAds.initialize(requireContext()) { }
        com.yandex.mobile.ads.common.MobileAds.initialize(requireContext()) { }



        loadYandexAds()
    }

    private fun checkInternetConnection() {
        if (!isOnline(requireContext())) {
            binding.internetConnectionTxt.visibility = View.VISIBLE
        }
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

    private fun setupUI() {
        binding.apply {

        }

        var numberOfTask1 = 0
        var numberOfTask2 = 0
        var numberOfMistakes = 0

        sharedPreferences = requireContext().getSharedPreferences("MySharedPref", MODE_PRIVATE)

        numberOfTask1 = sharedPreferences.getInt("numberOfTask1", 0)
        Log.d(TAG, "setupUI: $numberOfTask1")
        numberOfTask2 = sharedPreferences.getInt("numberOfTask2", 0)
        numberOfMistakes = sharedPreferences.getInt("numberOfMistakes", 0)


// TODO: Add adView to your view hierarchy.

        requireActivity().window.statusBarColor = requireContext().getColor(R.color.white)

        binding.apply {
            if (numberOfTask1 != 0) {
                count1.text = numberOfTask1.toString()
                count2.text = numberOfTask2.toString()
                count3.text = numberOfMistakes.toString()
            }

        }

        binding.task1.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_taskOneFragment)
        }
        binding.task2.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_taskTwoFragment)
        }
        binding.videoLessons.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_tipsFragment)
        }
        binding.commonMistakes.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_mistakesFragment)
        }
        binding.vocabularies.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_vocabulariesFragment)
        }
        binding.shareBtn.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Hey, Check out this great app:\nhttps://play.google.com/store/apps/details?id=com.khusinov.ieltswritingassistant"
            )
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share To:"))
        }
        binding.aiService.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_aiFragment)
        }
        binding.menu.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_aboutUsFragment)
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
