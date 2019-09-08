package com.miguel.kuiny;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class detalleact extends AppCompatActivity {

    TextView nombre, horas, fechas, descipciones, limites, actividad, direccion;
    Button participar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalleact);

        actividad = findViewById(R.id.idactividad);
        nombre = findViewById(R.id.tvactividad);
        horas = findViewById(R.id.tvhora);
        fechas = findViewById(R.id.tvfecha);
        descipciones = findViewById(R.id.tvdescripcion);
        direccion = findViewById(R.id.tvdireccion);
        limites = findViewById(R.id.tvlimite);
        participar = findViewById(R.id.btparticipar);

        final String id_act = getIntent().getStringExtra("idactividad");
        String titulo = getIntent().getStringExtra("contenido");
        String hora = getIntent().getStringExtra("hora");
        String fecha = getIntent().getStringExtra("fecha");
        String descripcion = getIntent().getStringExtra("descripcion");
        final String limite = getIntent().getStringExtra("limit");
        String dir = getIntent().getStringExtra("direct");
        // String id =getIntent().getStringExtra("id");

        actividad.setText(id_act);
        nombre.setText(titulo);
        horas.setText(hora);
        fechas.setText(fecha);
        descipciones.setText(descripcion);
        direccion.setText(dir);
        limites.setText(limite);

        //  empresa.setText(id);

        participar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClassConection conexion = new ClassConection();
                String user = consultar(v);
                try {

                    String resp = conexion.execute("http://kuinuysolutions.000webhostapp.com/kuiny/config/controllers/insert_act_asig.php?username=" + user + "&identificacion=" + id_act).get();
                    if (resp != null) {
                        Intent(v);
                        // sendEmail();
                        Toast.makeText(getApplicationContext(), "Usted esta participando en esta actividad", Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "Una vez participando en la actividad no se podra registrar en esta misma", Toast.LENGTH_LONG).show();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public String consultar(View v) {
        DataBase baseDatosAdmin = new DataBase(this, "user", null, 1);
        SQLiteDatabase bd = baseDatosAdmin.getWritableDatabase();

        Cursor tabla = bd.rawQuery("SELECT * FROM user", null);
        tabla.moveToLast();
        String user = (tabla.getString(0));
        tabla.close();
        bd.close();
        return user;
    }

    public void Intent(View v) {
        Intent i = new Intent(this, mostraractividad.class);
        startActivity(i);
    }
}
