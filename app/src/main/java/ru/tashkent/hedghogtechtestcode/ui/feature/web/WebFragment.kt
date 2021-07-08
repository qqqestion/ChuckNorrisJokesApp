package ru.tashkent.hedghogtechtestcode.ui.feature.web

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import ru.tashkent.hedghogtechtestcode.databinding.FragmentWebBinding
import ru.tashkent.hedghogtechtestcode.ui.core.BaseFragment

class WebFragment : BaseFragment<FragmentWebBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            getOnBackPressedCallback()
        )

        with(binding) {
            webView.webViewClient = getWebViewClient()
            savedInstanceState?.let { bundle ->
                webView.restoreState(bundle)
            } ?: run {
                webView.loadUrl("http://www.icndb.com/")
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.webView.saveState(outState)
    }

    override fun onResume() {
        super.onResume()
        binding.webView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.webView.onPause()
    }

    private fun getOnBackPressedCallback() = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (binding.webView.canGoBack()) {
                binding.webView.goBack()
            } else {
                isEnabled = false
                findNavController().popBackStack()
            }
        }
    }

    private fun getWebViewClient() = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            if (Uri.parse(url).host == "www.icndb.com") {
                return false
            }
            Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                startActivity(this)
            }
            return true
        }
    }
}