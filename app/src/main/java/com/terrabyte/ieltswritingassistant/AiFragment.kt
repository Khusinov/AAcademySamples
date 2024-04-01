package com.terrabyte.ieltswritingassistant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.webkit.WebViewClient
import com.terrabyte.ieltswritingassistant.databinding.FragmentAiBinding

class AiFragment : Fragment(R.layout.fragment_ai) {
    val binding by viewBinding { FragmentAiBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.visibility = View.VISIBLE
        binding.webView.visibility = View.GONE


        setupUI()


    }

    private fun setupUI() {
        binding.apply {

            webView.webViewClient = CustomWebViewClient()
            webView.loadUrl("https://www.oxfordlearnersdictionaries.com/")
            webView.settings.javaScriptEnabled = true
            webView.settings.setSupportZoom(true)

            progressBar.visibility = View.GONE
            webView.visibility = View.VISIBLE

        }


    }


}