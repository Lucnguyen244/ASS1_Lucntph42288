package com.example.ass1_lucntph42288.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import com.example.ass1_lucntph42288.R;

public class MyWeb extends Fragment {

    public MyWeb() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_webview, container, false);

        WebView webView = view.findViewById(R.id.wv_webview);
        webView.setWebViewClient(new WebViewClient());

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Bật hỗ trợ JavaScript nếu cần

        // Load trang web
        webView.loadUrl("https://laodong.vn/");

        return view;
    }
}
