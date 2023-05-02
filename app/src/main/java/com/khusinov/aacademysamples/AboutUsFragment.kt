package com.khusinov.aacademysamples

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khusinov.aacademysamples.databinding.FragmentAboutUsBinding


class AboutUsFragment : Fragment(R.layout.fragment_about_us) {
    val binding by viewBinding{ FragmentAboutUsBinding.bind(it)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.apply {

            telegram.setOnClickListener {
                val telegram = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/aplusacademyielts"))
                telegram.setPackage("org.telegram.messenger")
                startActivity(telegram)
            }

            programmer.setOnClickListener {
                val telegram = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/xusinov"))
                telegram.setPackage("org.telegram.messenger")
                startActivity(telegram)
            }
            instagram.setOnClickListener {
                val telegram = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/aplusacademy_ielts/"))
                startActivity(telegram)
            }
            izzat.setOnClickListener {
                val telegram = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/IzzatAbdullaev"))
                telegram.setPackage("org.telegram.messenger")
                startActivity(telegram)
            }
        }
    }

}