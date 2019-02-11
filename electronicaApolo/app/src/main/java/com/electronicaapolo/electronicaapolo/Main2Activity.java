package com.electronicaapolo.electronicaapolo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import java.net.URI;
import java.net.URISyntaxException;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        WebView webView;

        webView=(WebView) findViewById(R.id.webView);
        webView.loadUrl("http://www.electronicaapolo.com/");
    }
}
