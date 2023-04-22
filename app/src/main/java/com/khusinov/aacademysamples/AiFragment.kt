package com.khusinov.aacademysamples

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.khusinov.aacademysamples.databinding.FragmentAiBinding

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

            webView.webViewClient = WebViewClient()
            webView.loadUrl("http://ieltscdt.com/ielts-writing-essay-evaluation.php")
            webView.settings.javaScriptEnabled = true
            webView.settings.setSupportZoom(true)

            progressBar.visibility = View.GONE
            webView.visibility = View.VISIBLE

        }



    }


}