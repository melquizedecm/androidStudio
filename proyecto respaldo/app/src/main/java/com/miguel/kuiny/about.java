package com.miguel.kuiny;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class about extends AppCompatActivity {
    WebView myWebView;
    String url="http://laboratoriotec.com/kuiny/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        myWebView = findViewById(R.id.webview);

       final WebSettings ajustesVisorWeb = myWebView.getSettings();
       ajustesVisorWeb.setJavaScriptEnabled(true);
       myWebView.loadUrl(url);
    }
}
