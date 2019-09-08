package com.example.touristapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Categorias extends AppCompatActivity {

    private ListView lItems;
    private Adaptador adaptador;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_categorias);
        lItems= (ListView) findViewById(R.id.items);
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
        Intent intent= new Intent(this,subCategorias.class);
        startActivity(intent);
    }

    private ArrayList<Entidad> GetArrayItems(){
        ArrayList<Entidad> listItems = new ArrayList<>();
        listItems.add(new Entidad(R.drawable.rest,"Restaurants"));
        listItems.add(new Entidad(R.drawable.hotel,"Hoteles"));
        listItems.add(new Entidad(R.drawable.sitio,"Lugares de interes"));
        listItems.add(new Entidad(R.drawable.rest,"Categoria1"));
        listItems.add(new Entidad(R.drawable.rest,"Categoria2"));
        listItems.add(new Entidad(R.drawable.rest,"Categoria3"));
        listItems.add(new Entidad(R.drawable.rest,"Categoria4"));
        listItems.add(new Entidad(R.drawable.rest,"Categoria5"));
        listItems.add(new Entidad(R.drawable.rest,"Categoria6"));
        listItems.add(new Entidad(R.drawable.rest,"Categoria7"));
        listItems.add(new Entidad(R.drawable.rest,"Categoria8"));
        listItems.add(new Entidad(R.drawable.rest,"Categoria9"));
        return listItems;
    }

    public void selectSubCategory(View view) {
        Toast.makeText(getApplicationContext(),"hola",Toast.LENGTH_LONG).show();
    }
}
