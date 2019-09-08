package com.miguel.kuiny;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class mostraractividad extends AppCompatActivity {

    ArrayList<String> arrayList,descripcion, hora,fecha,idEmpresa,idActividad,direccion,limite;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostraractividad);
        list = findViewById(R.id.listview);

        arrayList = new ArrayList<>();
        descripcion = new ArrayList<>();
        hora = new ArrayList<>();
        fecha = new ArrayList<>();
        descripcion = new ArrayList<>();
        idEmpresa = new ArrayList<>();
        direccion = new ArrayList<>();
        idActividad = new ArrayList<>();
        limite = new ArrayList<>();


        ClassConection conexion = new ClassConection();
        try {
            String resp = "";

            resp=conexion.execute("http://kuinuysolutions.000webhostapp.com/kuiny/config/controllers/consult_actividades.php").get();

            /////Leer formato JSON
            JSONArray jsonArray = new JSONArray(resp);
            for (int i = 0; i < jsonArray.length() ;i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                 if(jsonObject.getString("proceso").equals("1")){

                   idActividad.add(jsonObject.getString("id_act"));
                   arrayList.add(jsonObject.getString("nombre_actividad"));
                   hora.add(jsonObject.getString("hora"));
                   fecha.add(jsonObject.getString("fecha"));
                   descripcion.add(jsonObject.getString("descripcion"));
                   direccion.add(jsonObject.getString("direccion"));
                   idEmpresa.add(jsonObject.getString("id_empresa"));
                   limite.add(jsonObject.getString("limite"));
                  }
            }

            /////Mostrar en los datos
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
            list.setAdapter(arrayAdapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    detalleActividad(view,position);

                }
            });

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), (e.toString()), Toast.LENGTH_LONG).show();
        }

    }
    public void detalleActividad(View v, int position){
        Intent intent = new Intent(this, detalleact.class);

        String ida= idActividad.get(position);
        intent.putExtra("idactividad",ida);
        String texto= arrayList.get(position);
        intent.putExtra("contenido",texto);
        String hor= hora.get(position);
        intent.putExtra("hora",hor);
        String fe= fecha.get(position);
        intent.putExtra("fecha",fe);
        String desc= descripcion.get(position);
        intent.putExtra("descripcion",desc);
        String direc= direccion.get(position);
        intent.putExtra("direct",direc);
        String ide= idEmpresa.get(position);
        intent.putExtra("id",ide);
        String li= limite.get(position);
        intent.putExtra("limit",li);
        startActivity(intent);
    }
}
