package com.ACDAT.ejercicio6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.net.FileNameMap;

public class Menu extends Activity {

    Spinner spinner;
    String[] datos={" -- ","TV azteca","El reforma", ""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);

        //final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.canales,android.R.layout.simple_spinner_item);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,datos);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Intent noticiasActivity = new Intent(Menu.this,UltimasNoticiasActivity.class);
                switch (i){
                    case 0:
                        Toast.makeText(getApplicationContext(),datos[i].toString(),Toast.LENGTH_LONG).show();

                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(),datos[i].toString(),Toast.LENGTH_LONG).show();
                        noticiasActivity.putExtra("link","http://www.aztecanoticias.com.mx/rss/mexico.xml");
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(),datos[i].toString(),Toast.LENGTH_LONG).show();
                        noticiasActivity.putExtra("link","http://www.reforma.com/rss/ciencia.xml");
                        break;
                }
                startActivity(noticiasActivity);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


       /* spinner.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Toast.makeText(getApplicationContext(),datos[i].toString(),Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(),datos[i].toString(),Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(),datos[i].toString(),Toast.LENGTH_LONG).show();
                        break;
                }
              /*  Intent noticiasActivity = new Intent(Menu.this,UltimasNoticiasActivity.class);
                noticiasActivity.putExtra("link",datos[0]);
                startActivity(noticiasActivity);
            }
        });*/

/*        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
*/


    }
}
