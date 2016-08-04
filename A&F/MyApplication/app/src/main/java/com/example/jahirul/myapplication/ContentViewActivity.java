package com.example.jahirul.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class ContentViewActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_view);
        Bundle b = getIntent().getExtras();
        webView = (WebView) findViewById(R.id.wvContent);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(b.getString("product_Content"));

    }
}
