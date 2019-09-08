package com.miguel.kuiny;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;


public class mostraractividadesempresas extends AppCompatActivity {
    ListView list;
    ArrayList arrayList,idactividad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostraractividadesempresas);
        list = findViewById(R.id.listview2);
        arrayList = new ArrayList<>();
        idactividad = new ArrayList<>();
        arrayList=getIntent().getStringArrayListExtra("arreglo");
        idactividad=getIntent().getStringArrayListExtra("arregloid");

        //arrayList=getIntent().getStringArrayListExtra("arreglo");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(arrayAdapter);
    }
}
