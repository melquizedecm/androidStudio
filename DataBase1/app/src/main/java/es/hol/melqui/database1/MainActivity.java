package es.hol.melqui.database1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        Button boton =(Button) findViewById(R.id.button);
        textView =(TextView) findViewById(R.id.textView);

        boton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                ingresar(view);
                consultar(view);
            }
        });
    }

    public void ingresar(View v){
        DataBase dataBaseInicial= new DataBase(this, "bdPrueba",null,1);
        SQLiteDatabase dataBase=dataBaseInicial.getWritableDatabase();
        //////obtenemos el dato
        String dato=editText.getText().toString();

        ////generamos el registro a insertar
        ContentValues registro = new ContentValues();
        registro.put("dato",dato);
        dataBase.insert("prueba",null,registro);
        dataBase.close();

        // regresamos a cero//
        editText.setText("");
        Toast.makeText(this,"Dato guardado",Toast.LENGTH_LONG).show();
    }

    public void consultar(View v){
        DataBase dataBaseInicial= new DataBase(this, "bdPrueba",null,1);
        SQLiteDatabase dataBase=dataBaseInicial.getWritableDatabase();
        //// table
        Cursor tabla= dataBase.rawQuery("SELECT * FROM prueba",null);
        tabla.moveToFirst();
       // textView.setText(tabla.getString(1));
        do{
            textView.setText(textView.getText()+"--" + tabla.getString(1));
        } while(tabla.moveToNext());
        dataBase.close();
        tabla.close();
    }
}
