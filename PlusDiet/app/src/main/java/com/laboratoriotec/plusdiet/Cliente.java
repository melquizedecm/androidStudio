package com.laboratoriotec.plusdiet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Cliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_cliente);

        WebView webView;

        webView=(WebView) findViewById(R.id.webView);
        webView.loadUrl("http://laboratoriotec.com/plusdiet/cliente/views/sesion.php");
    }
}
