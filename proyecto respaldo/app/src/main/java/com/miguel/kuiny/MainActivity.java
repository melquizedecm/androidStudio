package com.miguel.kuiny;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.net.ConnectivityManager.TYPE_WIFI;

public class MainActivity extends AppCompatActivity {
Button nosotros,empresa,usuario,face;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nosotros = findViewById(R.id.button4);
        empresa = findViewById(R.id.comenzar);
        usuario = findViewById(R.id.button2);
        face = findViewById(R.id.facebook);

    }
    private BroadcastReceiver networkStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = manager.getActiveNetworkInfo();
            onNetworkChange(ni);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void onPause() {
        unregisterReceiver(networkStateReceiver);
        super.onPause();
    }
    private void onNetworkChange(NetworkInfo networkInfo) {
        if (networkInfo != null) {
            Toast.makeText(getApplicationContext(), "Conectado", Toast.LENGTH_LONG).show();
            if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                Log.d("MainActivity", "CONECTADO");
                nosotros.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        about(v);
                    }
                });
                empresa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loginempresa(v);
                    }
                });
                usuario.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loginusuario(v);
                    }
                });
                face.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String facebookId = "fb://page/266575420960729";
                        String urlPage = "https://www.facebook.com/Kuiny-266575420960729/?modal=admin_todo_tour";

                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookId )));
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Aplicación no instalada.", Toast.LENGTH_SHORT).show();
                            //Abre url de pagina.
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlPage)));
                        }
                    }
                });
            }
        }else{
            Log.d("MainActivity", "DESCONECTADO");
            Toast.makeText(getApplicationContext(), "No esta conectado a una red", Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(), "Conecte una red de datos o wifi", Toast.LENGTH_LONG).show();
            nosotros.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Conecte una red de datos o wifi", Toast.LENGTH_SHORT).show();
                }
            });
            empresa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Conecte una red de datos o wifi", Toast.LENGTH_SHORT).show();
                }
            });
            usuario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Conecte una red de datos o wifi", Toast.LENGTH_SHORT).show();
                }
            });
            face.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Conecte una red de datos o wifi", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    public void loginempresa(View v){
        Intent ven=new Intent(this,loginempresa.class);
        startActivity(ven);
    }
    public void loginusuario(View v){
        Intent ven2=new Intent(this,loginusuarios.class);
        startActivity(ven2);
    }
    public void about(View v){
        Intent ven4=new Intent(this,about.class);
        startActivity(ven4);
    }

}
