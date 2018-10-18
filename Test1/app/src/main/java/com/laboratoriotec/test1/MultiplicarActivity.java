package com.laboratoriotec.test1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MultiplicarActivity extends AppCompatActivity {

    EditText edit1;
    EditText edit2;
    Button botonMultiplicar;
    TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_multiplicar);

        edit1 = (EditText) findViewById (R.id.editText1);
        edit2 =(EditText) findViewById (R.id.editText2);
        botonMultiplicar=(Button) findViewById (R.id.buttonMultiplicar);
        textViewResultado =(TextView) findViewById (R.id.textViewResultado);


        botonMultiplicar.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                ///obtenemos los valores
                float numero1= Float.valueOf (edit1.getText ().toString ());
                float numero2=Float.valueOf (edit2.getText ().toString ());
                /// multiplicamos
                float resultado= numero1*numero2;
                /// imprimimos el resultado.
                textViewResultado.setText (String.valueOf (resultado));
            }
        });


    }
}
