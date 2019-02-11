package com.example.ana.dispensador;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.UUID;

public class MenuActivity extends AppCompatActivity {

    private TextView cuba,paloma, whisky, charro, cubacagua;
    Handler bluetoothIn;
    final int handlerState = 0;
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private StringBuilder DataStringIN = new StringBuilder();
    private palomaActivity.ConnectedThread MyConexionBT;
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static String address = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        cuba = (TextView) findViewById(R.id.cuba);
        paloma = (TextView) findViewById(R.id.paloma);
        whisky = (TextView) findViewById(R.id.whisky);
        charro = (TextView) findViewById(R.id.charro);
        cubacagua = (TextView) findViewById(R.id.cubacagua);

        bluetoothIn = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (msg.what == handlerState) {
                    String readMessage = (String) msg.obj;
                    DataStringIN.append(readMessage);

                    int endOfLineIndex = DataStringIN.indexOf("#");

                    if (endOfLineIndex > 0) {
                        String dataInPrint = DataStringIN.substring(0, endOfLineIndex);
                        Toast.makeText(getBaseContext(), "Bluetooth: " + dataInPrint, Toast.LENGTH_LONG).show();
                        DataStringIN.delete(0, DataStringIN.length());
                    }
                }
            }
        };

        cuba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, DeviceListActivity.class);
                startActivity(intent);
                finish();
            }
        });
        paloma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, DeviceListActivity2.class);
                startActivity(intent);
                finish();
            }
        });
        whisky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, DeviceListActivity3.class);
                startActivity(intent);
                finish();
            }
        });
        charro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, DeviceListActivity4.class);
                startActivity(intent);
                finish();
            }
        });
        cubacagua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, DeviceListActivity5.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
