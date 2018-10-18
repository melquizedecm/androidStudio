package com.laboratoriotec.controlesu2;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RadioButton radioButton1;
    RadioButton radioButton2;
    CheckBox checkBox1;
    CheckBox checkBox2;
    Switch switch1;
    CheckedTextView checkedTextView1;
    CheckedTextView checkedTextView2;
    Spinner spinner1;
    EditText edit1;
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        radioButton1 = (RadioButton) findViewById (R.id.radioButton);
        radioButton2 = (RadioButton) findViewById (R.id.radioButton2);
        checkBox1 = (CheckBox) findViewById (R.id.checkBox);
        checkBox2 = (CheckBox) findViewById (R.id.checkBox2);
        switch1 =(Switch) findViewById (R.id.switch1);
        checkedTextView1 = (CheckedTextView) findViewById (R.id.checkedTextView);
        checkedTextView2 =(CheckedTextView) findViewById (R.id.checkedTextView2);
        listView = (ListView) findViewById (R.id.listView);



        spinner1 =(Spinner) findViewById (R.id.spinner);
        String [] opciones={"Tacos","Hamburguesa","Pizza","Nachos"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String> (this,android.R.layout.select_dialog_multichoice,opciones);
        spinner1.setAdapter (adapter);



        checkBox1.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {

                if (checkBox1.isChecked ()){
                    guardar (view);
                }
                else{
                    Toast.makeText (getApplicationContext (),"Desactivado Check1",Toast.LENGTH_LONG).show ();
                }
                if (switch1.isChecked ()){
                    Toast.makeText (getApplicationContext (),"el switch esta activado",Toast.LENGTH_LONG).show ();
                }



            }
        });


        /////CONTROL DE ARCHIVOS///
        edit1 = (EditText) findViewById (R.id.editText);
        ArrayList <String> productos= new ArrayList<String> ();
        String [] archivos = fileList ();
        if (existe (archivos,"notas.txt")){
            try {
                InputStreamReader archivo= new InputStreamReader (openFileInput ("notas.txt"));
                BufferedReader br = new BufferedReader (archivo);
                String linea = br.readLine();
                productos.add (linea);
                String contenido ="";
                while ((linea!=null)){
                    contenido = contenido + linea + "\n";
                    linea= br.readLine ();
                    productos.add (linea);
                }
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1,productos);
                listView.setAdapter (adapter1);
                br.close ();
                archivo.close ();
                edit1.setText (contenido);
            } catch (java.io.IOException e) {
                e.printStackTrace ( );
            }
        }

    }

    private boolean existe(String[] archivos,String archivoBuscar){
        for (int i=0; i<archivos.length; i++) {
            if (archivoBuscar.equals (archivos[i])){
                return true;
            }
        }
        return false;
    }

    public void guardar(View v){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter (openFileOutput ("notas.txt", Activity.MODE_PRIVATE));
            archivo.write(edit1.getText().toString());
            archivo.flush ();
            archivo.close ();
        } catch (java.io.IOException e) {
            e.printStackTrace ( );
        }
        Toast.makeText (this,"El archivo se guardo",Toast.LENGTH_LONG).show ();
    }


}
