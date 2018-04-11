package cn.xunger.and.wanandroid.web

import android.content.Context
import android.content.Intent
import android.os.Build
import android.webkit.*
import cn.xunger.and.wanandroid.R
import cn.xunger.and.wanandroid.framework.CommonActivity
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : CommonActivity() {

    companion object {
        fun startNewsDetailActivity(context: Context, url: String) {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("Url", url)
            context.startActivity(intent)
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_web_view
    }

    override fun initView() {
        webview.settings.setAppCacheEnabled(true)
        webview.settings.javaScriptEnabled = true
        webview.settings.domStorageEnabled = true
        webview.settings.databaseEnabled = true
        webview.settings.cacheMode = WebSettings.LOAD_DEFAULT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webview.settings.mixedContentMode = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE
        }

        webview.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView?, title: String?) {
                setTitle(title)
            }
        }

        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(request?.url.toString())
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
    }

    override fun initData() {
        val url = intent.getStringExtra("Url")
        webview.loadUrl(url)
    }

}
