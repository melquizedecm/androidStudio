package com.miguel.kuiny;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class loginempresa extends AppCompatActivity {
    EditText user, contra;
    Button but;
    String usuarioDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginempresa);
        user = findViewById(R.id.etusuario11);
        contra = findViewById(R.id.etcontrasena22);
        but = findViewById(R.id.comenzar);

        but.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String usu = user.getText().toString();
                String pas = contra.getText().toString();

                ConnectionPost conexion = new  ConnectionPost();
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("usuario", usu)
                        .appendQueryParameter("password",pas);
                String query = builder.build().getEncodedQuery();
                try {
                    String resp = conexion.execute("http://kuinuysolutions.000webhostapp.com/kuiny/config/controllers/login_empresa.php", query).get();

                    if (resp.equals("[]")){
                        Toast.makeText(getApplicationContext(), "No tiene acceso", Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "Intente de nuevo y escriba bien su usuario y contraseña", Toast.LENGTH_LONG).show();

                    }else{
                        user.setText("");
                        contra.setText("");
                        registroactividad(v);
                        Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_LONG).show();
                        usuarioDB=usu;
                        ingresar(v);
                    }

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void registroactividad(View v){
        Intent ven=new Intent(this,actividades.class);
        startActivity(ven);
    }
    public void registro(View v){
        Intent mar = new Intent(this, registroemp.class);
        startActivity(mar);
    }
    public void ingresar(View v){
        DataBase baseDatosAdmin = new DataBase(this, "user",null,1);
        SQLiteDatabase bd=baseDatosAdmin.getWritableDatabase();

        // generar el registro a guardar
        ContentValues registro = new ContentValues();
        registro.put("user",usuarioDB);

        ///insertamos el registro en la Base de Datos
        bd.insert("user",null,registro);
        bd.close();
    }
}