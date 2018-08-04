package com.itshizhan.webviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mWebView = findViewById(R.id.web_view);
        //支持javascript脚本
        mWebView.getSettings().getJavaScriptEnabled();
        //在当前webview 中显示网页，不打开系统浏览器
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("https://www.baidu.com/");
    }
}
