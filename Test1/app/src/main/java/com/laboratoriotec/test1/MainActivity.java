package com.laboratoriotec.test1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edit1;
    EditText edit2;
    Button button;
    TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        edit1 = (EditText) findViewById (R.id.editText3);
        edit2 = (EditText) findViewById (R.id.editText4);
        button = (Button) findViewById (R.id.button);
        textResultado = (TextView) findViewById (R.id.textView4);

        String user = getIntent ().getExtras ().getString ("nose");
        Toast.makeText (getApplicationContext (),user, Toast.LENGTH_LONG).show ();

        button.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                float numero1= Float.valueOf (edit1.getText ().toString ());
                float numero2= Float.valueOf (edit2.getText ().toString ());
                float resultado= numero1+numero2;
                textResultado.setText (String.valueOf (resultado));
            }
        });


    }
}
