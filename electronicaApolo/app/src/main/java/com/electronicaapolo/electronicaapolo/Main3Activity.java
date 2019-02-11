package com.electronicaapolo.electronicaapolo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        WebView webView;

        webView=(WebView) findViewById(R.id.webView);
        webView.loadUrl("http://www.electronicaapolo.com/matriz/");
    }
}
