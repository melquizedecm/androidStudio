package com.laboratoriotec.usersrecords;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /////Declarar mis variables de los Componentes
    EditText editNombre,editPassword, editEmail;
    Button buttonAbrir, buttonGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        ///Traer el control de mis componentes hacia el código

        editNombre= (EditText) findViewById (R.id.editTextName);
        editPassword=(EditText) findViewById (R.id.editTextPassword);
        editEmail =(EditText) findViewById (R.id.editTextEmail);
        buttonAbrir=(Button) findViewById (R.id.buttonAbrir);
        buttonGuardar= (Button) findViewById (R.id.buttonGuardar);

        ///Usar mis componentes


        ///Consumir recursos





    }

    public void guardar(View view) {

        String nombre= editNombre.getText ().toString ();
        String password= editPassword.getText ().toString ();
        String email = editEmail.getText ().toString ();

        Toast.makeText (getApplicationContext (),"nombre: "+nombre +"password: "+ password, Toast.LENGTH_LONG).show ();
    }

    public void abrir(View view) {

    }

    public void calcular(View view){

    }
}
