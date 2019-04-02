package com.weiweizhang.latte_core.delegates.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.weiweizhang.latte_core.delegates.LatteDelegate;
import com.weiweizhang.latte_core.delegates.web.WebDelegate;
import com.weiweizhang.latte_core.delegates.web.WebDelegateImpl;

public class Router {
    private Router() {
    }

    private static class Holder{
        private static final Router INSTANCE= new Router();
    }

    public static Router getInstance() {
        return Holder.INSTANCE;
    }

    public final boolean handleWebUrl(WebDelegate delegate, String url) {
        if (url.contains("tel:")) {
            callPhone(delegate.getContext(), url);
            return true;
        }

        final LatteDelegate topDelegate = delegate.getTopDelegate();
        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);
        topDelegate.getSupportDelegate().start(webDelegate);
        return true;
    }

    private void callPhone(Context context, String uri) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context, intent, null);
    }

    public final void loadPage(WebDelegate delegate, String url) {
        if(URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url) ) {
            loadWebPage(delegate.getWebView(), url);
        } else {
            loadLocalPage(delegate.getWebView(), url);
        }
    }

    private void loadWebPage(WebView webView, String url){
        if(webView != null) {
            webView.loadUrl(url);
        } else {
            throw new NullPointerException("webview is null");
        }
    }

    private void loadLocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/" + url);
    }
}
