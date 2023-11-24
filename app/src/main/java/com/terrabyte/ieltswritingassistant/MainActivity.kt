package com.terrabyte.ieltswritingassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.terrabyte.ieltswritingassistant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        com.yandex.mobile.ads.common.MobileAds.initialize(this) { }




    }
}