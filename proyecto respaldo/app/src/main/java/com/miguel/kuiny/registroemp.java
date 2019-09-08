package com.miguel.kuiny;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class registroemp extends AppCompatActivity {

    String cad = "se han aceptado terminos y condiciones.", noacept ="No se aceptaron terminos y condiciones \nIntente marcando la casilla de: Acepto terminos y condiciones";
    CheckBox c1;
    EditText nombre, rf, respo, tel, corre, usuario, cont;
    Button ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registroemp);
        c1 = findViewById(R.id.chec);
        //textview de los datos que se reciben//
        ac = findViewById(R.id.acept);
        nombre = findViewById(R.id.nombreempresa);
        rf = findViewById(R.id.rfc);
        respo = findViewById(R.id.encargado);
        tel = findViewById(R.id.telefono);
        corre = findViewById(R.id.correoempresa);
        usuario = findViewById(R.id.userempresa);
        cont = findViewById(R.id.contrasenia);
//accion del boton de aceptar
        ac.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (nombre.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Se requiere el dato: Nombre de empresa", Toast.LENGTH_LONG).show();
                }else{
                    if (rf.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Se requiere el dato: RFC", Toast.LENGTH_LONG).show();
                    }else {
                        if(respo.getText().toString().isEmpty()){
                            Toast.makeText(getApplicationContext(), "Se requiere el dato: Encargado de la actividad", Toast.LENGTH_LONG).show();
                        }else{
                            if (tel.getText().toString().isEmpty()){
                                Toast.makeText(getApplicationContext(), "Se requiere el dato: Telefono", Toast.LENGTH_LONG).show();
                            }else{
                                if(corre.getText().toString().isEmpty()){
                                    Toast.makeText(getApplicationContext(), "Se requiere el dato: Correo", Toast.LENGTH_LONG).show();
                                }else{
                                    if (usuario.getText().toString().isEmpty()){
                                        Toast.makeText(getApplicationContext(), "Se requiere el dato: Usuario", Toast.LENGTH_LONG).show();
                                    }else{
                                        if (cont.getText().toString().isEmpty()){
                                            Toast.makeText(getApplicationContext(), "Se requiere el dato: Contraseña", Toast.LENGTH_LONG).show();
                                            }else{
                                            Toast.makeText(getApplicationContext(), "Datos completos", Toast.LENGTH_LONG).show();


                                            if (c1.isChecked() == true) {


                                                String nom = nombre.getText().toString();
                                                String rfc = rf.getText().toString();
                                                String res = respo.getText().toString();
                                                String cel = tel.getText().toString();
                                                String correo = corre.getText().toString();
                                                String usu = usuario.getText().toString();
                                                String contra = cont.getText().toString();

                                                ClassConection conexion = new ClassConection();
                                                try {
                                                    String resp = conexion.execute("http://kuinuysolutions.000webhostapp.com/kuiny/config/controllers/insert_enterprise.php?emp=" + nom + "&er=" + rfc + "&nomb=" + res + "&nume=" + cel + "&corr=" + correo + "&username=" + usu + "&password=" + contra).get();
                                                    if (resp != null) {
                                                        nombre.setText("");
                                                        rf.setText("");
                                                        respo.setText("");
                                                        tel.setText("");
                                                        corre.setText("");
                                                        usuario.setText("");
                                                        cont.setText("");
                                                        Toast.makeText(getApplicationContext(), cad, Toast.LENGTH_LONG).show();
                                                        Toast.makeText(getApplicationContext(), "Guardado con exito", Toast.LENGTH_LONG).show();
                                                        salir(v);
                                                    }
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                } catch (ExecutionException e) {
                                                    e.printStackTrace();
                                                }

                                            } else {
                                                Toast.makeText(getApplicationContext(), noacept, Toast.LENGTH_LONG).show();
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
    }
    public void salir (View v){
        Intent ven = new Intent(this,loginempresa.class);
        startActivity(ven);
        Toast.makeText(getApplicationContext(), "Redireccionando", Toast.LENGTH_LONG).show();
    }
}
