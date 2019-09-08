package com.miguel.kuiny;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class actividades extends AppCompatActivity {

    EditText act, desc, limite, direc,hora,minuto,fecha;
    TextView fecha2;
    Button enviar,solicitar;
    ArrayList<String> arrayList,idactividad;
    ListView list;
    CheckBox c1;

    private int anio1,mes1,dia1,anio2,mes2,dia2;
    static final int DATE_ID=0;
    Calendar c = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades);

        /*******Array list para los datos de una sola empresa*********/
        arrayList = new ArrayList<>();
       idactividad = new ArrayList<>();
        /***************************************************************/
        anio2 =c.get(Calendar.YEAR);
        mes2 = c.get(Calendar.MONTH);
        dia2 = c.get(Calendar.DAY_OF_MONTH);
        //EditText de los datos que se reciben//
        enviar = findViewById(R.id.regact);
        act = findViewById(R.id.nomacti);
      c1 = findViewById(R.id.cbactivo);
        desc = findViewById(R.id.descripcion);
        direc = findViewById(R.id.etdireccion);
        limite = findViewById(R.id.limitepersonas);
        fecha2 = findViewById(R.id.editFecha);
        //Boton de olicitud de datos en la base//
        solicitar= findViewById(R.id.btso);
        //TextView para la demostracion y funcionalidad del selector de hora y fecha//
        hora = findViewById(R.id.etHora);
        minuto = findViewById(R.id.etMinutos);
        fecha = findViewById(R.id.etFecha);
        //accion del boton de aceptar
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_ID);
            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (act.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Se requiere el dato: Nombre de la actividad", Toast.LENGTH_SHORT).show();
                } else {
                    if (hora.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Se requiere el dato: Hora", Toast.LENGTH_SHORT).show();
                      } else {
                        if (minuto.getText().toString().isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Se requiere el dato: Minuto", Toast.LENGTH_SHORT).show();
                        } else {
                            if (fecha2.getText().toString().isEmpty()) {
                                Toast.makeText(getApplicationContext(), "Se requiere el dato: Fecha", Toast.LENGTH_SHORT).show();
                            }else {
                                    if (desc.getText().toString().isEmpty()) {
                                        Toast.makeText(getApplicationContext(), "Se requiere los datos: Descripcion de la actividad", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (limite.getText().toString().isEmpty()) {
                                            Toast.makeText(getApplicationContext(), "Se requiere los datos: Limite de personas", Toast.LENGTH_SHORT).show();
                                        } else {
                                            if (direc.getText().toString().isEmpty()) {
                                                Toast.makeText(getApplicationContext(), "Se requiere los datos:Dirección", Toast.LENGTH_SHORT).show();
                                            } else {
                                                if (c1.isChecked() == true) {
                                                String fechas = fecha2.getText().toString();
                                                String h = hora.getText().toString();
                                                String m = minuto.getText().toString();
                                                String hor = (h+":"+m+":00");
                                                String acti = act.getText().toString();
                                                String descri = desc.getText().toString();
                                                String direccion = direc.getText().toString();
                                                String limites = limite.getText().toString();
                                                int proceso = 1;
                                                ClassConection conexion = new ClassConection();
                                                String user = consultar(v);

                                                try {
                                                    String resp = conexion.execute("http://kuinuysolutions.000webhostapp.com/kuiny/config/controllers/insert_actividad.php?actividad=" + acti + "&ho=" + hor + "&fec=" + fechas + "&proc=" + proceso + "&desc=" + descri + "&procesito=" + limites + "&direc=" + direccion + "&emp=" + user).get();
                                                    if (resp != null) {
                                                        hora.setText("");
                                                        minuto.setText("");
                                                        act.setText("");
                                                        desc.setText("");
                                                        limite.setText("");
                                                        direc.setText("");
                                                        fecha2.setText("");
                                                        Toast.makeText(getApplicationContext(), "Guardado con exito", Toast.LENGTH_LONG).show();
                                                    } else {
                                                        Toast.makeText(getApplicationContext(), "Conexion fallida", Toast.LENGTH_LONG).show();
                                                    }
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                } catch (ExecutionException e) {
                                                    e.printStackTrace();
                                                }
                                                } else {
                                                    Toast.makeText(getApplicationContext(), "Seleccione la casilla para continuar", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                }
            }
        });
        solicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = consultar(view);
                ClassConection conexion = new ClassConection();
                try {
                    String resp = "";

                    resp = conexion.execute("http://kuinuysolutions.000webhostapp.com/kuiny/config/controllers/respaldo.php?id="+id).get();

                    /////Leer formato JSON
                    JSONArray jsonArray = new JSONArray(resp);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        arrayList.add(jsonObject.getString("nombre_actividad"));
                       // idactividad.add(jsonObject.getString("id_act"));
                    }

                    actualizar(view);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private DatePickerDialog.OnDateSetListener date =
            new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            anio1 = year;
            mes1 = month;
            dia1 = dayOfMonth;
            fecha2.setText(anio1+"/"+"0"+(mes1+1)+"/"+dia1);
        }
    };
    protected Dialog onCreateDialog (int id) {
        switch (id) {
            case DATE_ID:
                return new DatePickerDialog(this, date, anio2, mes2, dia2);
        }
        return null;
    }
    public void actualizar(View v){
        Intent ven=new Intent(this,mostraractividadesempresas.class);
        ArrayList<String> array = arrayList;
        ven.putExtra("arreglo",array);
      /*  ArrayList<String> arregloid = idactividad;
        ven.putExtra("arregloid",arregloid);*/
        startActivity(ven);
    }

    public String consultar(View v){
        DataBase baseDatosAdmin = new DataBase(this, "user",null,1);
        SQLiteDatabase bd=baseDatosAdmin.getWritableDatabase();

        Cursor tabla= bd.rawQuery("SELECT * FROM user",null);
        tabla.moveToLast();
        String user=(tabla.getString(0));
        tabla.close();
        bd.close();
        return user;
    }
}
