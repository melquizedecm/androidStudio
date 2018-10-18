package com.laboratoriotec.test1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RestaActivity extends AppCompatActivity {
    EditText edit1;
    EditText edit2;
    Button buttonResta;
    TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_resta);

        edit1 = (EditText) findViewById (R.id.editText1Resta);
        edit2 = (EditText) findViewById (R.id.editText2Resta);
        buttonResta = (Button) findViewById (R.id.button2);
        textResultado = (TextView) findViewById (R.id.textViewResultadoResta);


        buttonResta.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                float numero1= Float.valueOf (edit1.getText ().toString ());
                float numero2= Float.valueOf (edit2.getText ().toString ());
                float resultado= numero1-numero2;
                textResultado.setText (String.valueOf (resultado));
            }
        });
    }
}
