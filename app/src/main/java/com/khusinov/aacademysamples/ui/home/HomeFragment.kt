package com.khusinov.aacademysamples.ui.home

import android.content.Context
import android.content.Context.MODE_APPEND
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.google.android.gms.ads.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.khusinov.aacademysamples.R
import com.khusinov.aacademysamples.databinding.FragmentHomeBinding
import com.khusinov.aacademysamples.viewBinding


class HomeFragment : Fragment(R.layout.fragment_home) {
    val binding by viewBinding { FragmentHomeBinding.bind(it) }
    private val TAG = "HomeFragment"
    var numberOfTask1 = 0
    var numberOfTask2 = 0
    var numberOfMistakes = 0
    lateinit var sharedPreferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkInternetConnection()
        setupUI()
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
