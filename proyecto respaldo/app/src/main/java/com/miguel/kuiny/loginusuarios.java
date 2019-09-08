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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class loginusuarios extends AppCompatActivity {
EditText user, contra;
Button but;
String userDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginusuarios);
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
                    String resp = conexion.execute("http://kuinuysolutions.000webhostapp.com/kuiny/config/controllers/login_colaborador.php", query).get();
                        if (resp.equals("[]")) {
                            Toast.makeText(getApplicationContext(), "No tiene acceso", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), "Intente de nuevo y escriba bien su usuario y contraseña", Toast.LENGTH_LONG).show();


                        } else {
                            user.setText("");
                            contra.setText("");
                            registromiem(v);
                            Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT).show();
                            ///guardar el Id o userDB.
                            userDB=usu;
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

   public void registromiem(View v){
        Intent ven=new Intent(this,mostraractividad.class);
        startActivity(ven);
    }
    public void registro(View v){
        Intent mar = new Intent(this, miembros.class);
        startActivity(mar);
    }
    public void ingresar(View v){
        DataBase baseDatosAdmin = new DataBase(this, "user",null,1);
        SQLiteDatabase bd=baseDatosAdmin.getWritableDatabase();

        // generar el registro a guardar
        ContentValues registro = new ContentValues();
        registro.put("user",userDB);

        ///insertamos el registro en la Base de Datos
        bd.insert("user",null,registro);
        bd.close();
    }
}
