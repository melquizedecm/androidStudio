package com.example.touristapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class subCategorias extends AppCompatActivity {

    private ListView lItems;
    private Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_categorias);
        //setContentView(R.layout.activity_categorias);
        lItems= (ListView) findViewById(R.id.subitems);
        adaptador = new Adaptador(this,GetArrayItems());
        lItems.setAdapter(adaptador);
        lItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sub();
            }
        });
    }

    public void sub(){
        Intent intent= new Intent(this,ViewCategoria.class);
        startActivity(intent);
    }

    private ArrayList<Entidad> GetArrayItems(){
        ArrayList<Entidad> listItems = new ArrayList<>();
        listItems.add(new Entidad(R.drawable.hotel2,"Progreso beach hotel"));
        listItems.add(new Entidad(R.drawable.hotel1,"Playa linda"));
        listItems.add(new Entidad(R.drawable.hotel,"hotel1"));
        listItems.add(new Entidad(R.drawable.hotel,"hotel2"));
        listItems.add(new Entidad(R.drawable.hotel,"hotel3"));
        listItems.add(new Entidad(R.drawable.hotel,"hotel4"));
        listItems.add(new Entidad(R.drawable.hotel,"hotel5"));
        listItems.add(new Entidad(R.drawable.hotel,"hotel6"));
        listItems.add(new Entidad(R.drawable.hotel,"hotel7"));
        listItems.add(new Entidad(R.drawable.hotel,"hotel8"));
        listItems.add(new Entidad(R.drawable.hotel,"hotel9"));


        return listItems;
    }
}
