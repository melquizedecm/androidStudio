package com.laboratoriotec.maps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        btn = (Button) findViewById (R.id.buttonMaps);
        btn.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (getApplicationContext (),MapsActivity.class);
                intent.putExtra ("latitud","-89.223");
                intent.putExtra ("longitud", "20.25");
                startActivity (intent);
            }
        });
    }

    public void abrirMapa(View view) {

    }
}
