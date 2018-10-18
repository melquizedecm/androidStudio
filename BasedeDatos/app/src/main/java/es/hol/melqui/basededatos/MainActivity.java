package es.hol.melqui.basededatos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //atributos

    private EditText editText;
    private Button button;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=(EditText) findViewById(R.id.editText);
        button =(Button) findViewById(R.id.button);
        textView= (TextView) findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingresar(view);
                consultar(view);
            }
        });

    }

    public void ingresar(View v){
        DataBase baseDatosAdmin = new DataBase(this, "bdPrueba",null,1);
        SQLiteDatabase bd=baseDatosAdmin.getWritableDatabase();
        //recuperar el dato a guardar
        String dato = editText.getText().toString();
        // generar el registro a guardar
        ContentValues  registro = new ContentValues();
        registro.put("dato",dato);

        ///insertamos el registro en la Base de Datos

        bd.insert("prueba",null,registro);
        bd.close();
    }


    public void consultar(View v){
        DataBase baseDatosAdmin = new DataBase(this, "bdPrueba",null,1);
        SQLiteDatabase bd=baseDatosAdmin.getWritableDatabase();

        Cursor tabla= bd.rawQuery("SELECT * FROM prueba",null);
        tabla.moveToFirst();
        textView.setText("");
        do{
           textView.setText(textView.getText()+ "--"+tabla.getString(1));
        }while(tabla.moveToNext());
        tabla.close();
        bd.close();
    }
}
