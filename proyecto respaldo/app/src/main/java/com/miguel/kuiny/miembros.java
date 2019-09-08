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

public class miembros extends AppCompatActivity {
    String cad = "Se han aceptado terminos y condiciones.", noacept ="No se aceptaron terminos y condiciones \nIntente marcando la casilla de: Acepto terminos y condiciones";
    CheckBox c1;
    EditText nombre, edad, telefono, correo, usuario, contra;
    Button aceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miembros);
        c1 = findViewById(R.id.chec);
        aceptar = findViewById(R.id.aceptarusuarios);
        nombre = findViewById(R.id.nombreempresa);
        edad = findViewById(R.id.edad);
        telefono = findViewById(R.id.rfc);
        correo = findViewById(R.id.encargado);
        usuario = findViewById(R.id.telefono);
        contra = findViewById(R.id.contra);

        aceptar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (nombre.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Se requiere el dato: Nombre del usuario", Toast.LENGTH_LONG).show();
                }else {
                    if (edad.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Se requiere el dato: Edad", Toast.LENGTH_LONG).show();
                    } else {
                        if (telefono.getText().toString().isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Se requiere el dato: Telefono", Toast.LENGTH_LONG).show();
                        } else {
                            if (correo.getText().toString().isEmpty()) {
                                Toast.makeText(getApplicationContext(), "Se requiere el dato: Correo", Toast.LENGTH_LONG).show();
                            } else {
                                if (usuario.getText().toString().isEmpty()) {
                                    Toast.makeText(getApplicationContext(), "Se requiere el dato: Usuario", Toast.LENGTH_LONG).show();
                                } else {
                                    if (contra.getText().toString().isEmpty()) {
                                        Toast.makeText(getApplicationContext(), "Se requiere el dato: Contraseña", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Datos completos", Toast.LENGTH_LONG).show();
                                        if (c1.isChecked() == true) {


                                            String nom = nombre.getText().toString();
                                            String ed = edad.getText().toString();
                                            String tel = telefono.getText().toString();
                                            String email = correo.getText().toString();
                                            String user = usuario.getText().toString();
                                            String pass = contra.getText().toString();

                                            ClassConection conexion = new ClassConection();

                                            try {
                                                String resp = conexion.execute("http://kuinuysolutions.000webhostapp.com/kuiny/config/controllers/insert_colaborador.php?nombre_colaborador=" + nom + "&edad=" + ed + "&telefono_celular=" + tel + "&email=" + email + "&username=" + user + "&password=" + pass).get();
                                                if (resp != null) {

                                                    nombre.setText("");
                                                    edad.setText("");
                                                    telefono.setText("");
                                                    correo.setText("");
                                                    usuario.setText("");
                                                    contra.setText("");
                                                    Toast.makeText(getApplicationContext(), cad, Toast.LENGTH_LONG).show();
                                                    Toast.makeText(getApplicationContext(), "Guardado con exito", Toast.LENGTH_LONG).show();
                                                    salir(v);
                                                }
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            } catch (ExecutionException e) {
                                                e.printStackTrace();
                                            }
                                            Toast.makeText(getApplicationContext(), cad, Toast.LENGTH_LONG).show();

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
        });
    }
    public void salir (View v){
        Intent ven = new Intent(this,loginusuarios.class);
        startActivity(ven);
    }
}


