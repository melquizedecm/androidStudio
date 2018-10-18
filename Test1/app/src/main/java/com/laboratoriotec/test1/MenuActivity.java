package com.laboratoriotec.test1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_menu);

    }

    public void getSumar(View view){
        String username="Juan Naal";

        Intent ventanaSumar = new Intent (this, MainActivity.class);
        ventanaSumar.putExtra ("nose", username);
        startActivity (ventanaSumar);
    }

    public void getRestar(View view) {
        Intent ventanaRestar = new Intent (this,RestaActivity.class);
        startActivity (ventanaRestar);
    }
}
