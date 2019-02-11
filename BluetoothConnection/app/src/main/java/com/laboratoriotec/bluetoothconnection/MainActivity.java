package com.laboratoriotec.bluetoothconnection;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
/*
1 boton activar
1 boton desactivar
1 boton listar dispositivos
1 Visible al telefono
1 ListView
 */

    Button btnActivar, btnDesactivar, btnVisible, btnListarDispositivos;
    ListView lista;
    private StringBuilder DataStringIN = new StringBuilder();
    private Set<BluetoothDevice> vinculados;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDevice bluetoothDevice;
    private BluetoothSocket bluetoothSocket;
    private ArrayAdapter adapter;
    String address = "";
    final int handlerState = 0;
    static Handler bluetoothIn;
    ConnectedThread miConexion;
    EditText editTextEnviar;
    Button btnEnviar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        btnActivar = (Button) findViewById (R.id.buttonActivar);
        btnDesactivar = (Button) findViewById (R.id.buttonDesactivar);
        btnVisible = (Button) findViewById (R.id.buttonVisualizar);
        btnListarDispositivos = (Button) findViewById (R.id.buttonVerDispositivos);
        lista = (ListView) findViewById (R.id.listView);
        btnEnviar = (Button) findViewById (R.id.buttonEnviar);
        editTextEnviar = (EditText) findViewById (R.id.editText);

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

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter ( );
        if (bluetoothAdapter == null) {
            Toast.makeText (this, "Tu dispositivo no soporta bluetooth", Toast.LENGTH_LONG).show ( );
        }



    }

    public void activarBluetooth(View view) {
        if (!bluetoothAdapter.isEnabled ( )) {
            Intent encender = new Intent (BluetoothAdapter.ACTION_REQUEST_ENABLE); //preparar solicitud para encender
            startActivityForResult (encender, 0);
            Toast.makeText (this, "Bluetooth ya está Activado", Toast.LENGTH_LONG).show ( );
        }
    }

    public void desactivarBluetooth(View view) {
        bluetoothAdapter.disable ( );
        Toast.makeText (this, "Bluetooth Apagado", Toast.LENGTH_LONG).show ( );
    }

    public void visualizarBluetooth(View view) {
        Intent visible = new Intent (BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        visible.putExtra (BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 30);
        startActivity (visible);
    }

    private UUID BTMODULEUUID = UUID.fromString ("00001101-0000-1000-8000-00805F9B34FB");

    public void listarDispositivosBluetooth(View view) {
        vinculados = bluetoothAdapter.getBondedDevices ( );
        ArrayList listaDispositivos = new ArrayList ( );
        for (BluetoothDevice bt : vinculados)
            listaDispositivos.add (bt.getName ( ) + "\n" + bt.getAddress ( ));
        adapter = new ArrayAdapter (this, android.R.layout.simple_list_item_1, listaDispositivos);
        lista.setAdapter (adapter);

        lista.setOnItemClickListener (new AdapterView.OnItemClickListener ( ) {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String fila = ((TextView) view).getText ( ).toString ( );
                address = fila.substring (fila.length ( ) - 17);    //obtiene la dirección
                Toast.makeText (getApplicationContext ( ), "Conectando a ...." + address, Toast.LENGTH_LONG).show ( );

                bluetoothDevice = bluetoothAdapter.getRemoteDevice (address);  // realiza la conexion a la dirección indicada
                try {
                    String uuid = "00001101-0000-1000-8000-00805f9b34fb";
                    bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord (UUID.fromString (uuid));
                    bluetoothSocket.connect ( );
                    Toast.makeText (getApplicationContext ( ), "Conectado", Toast.LENGTH_LONG).show ( );
                } catch (IOException e) {
                    e.printStackTrace ( );
                    Toast.makeText (getApplicationContext ( ), "No se pudo conectar", Toast.LENGTH_LONG).show ( );
                    try {
                        bluetoothSocket.close ( );
                    } catch (IOException e1) {
                        e1.printStackTrace ( );
                    }
                }
                miConexion = new ConnectedThread (bluetoothSocket);
                miConexion.start ( );
            }
        });
    }


    public void enviarMensaje(View view) {
        miConexion.write (editTextEnviar.getText ( ).toString ( ));
    }


    class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try {
                tmpIn = socket.getInputStream ( );
                tmpOut = socket.getOutputStream ( );
            } catch (IOException e) {
            }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[256];
            int bytes;

            // Se mantiene en modo escucha para determinar el ingreso de datos
            while (true) {
                try {
                    bytes = mmInStream.read (buffer);
                    String readMessage = new String (buffer, 0, bytes);
                    // Envia los datos obtenidos hacia el evento via handler
                    bluetoothIn.obtainMessage (handlerState, bytes, -1, readMessage).sendToTarget ( );
                } catch (IOException e) {
                    break;
                }
            }
        }

        //Envio de trama
        public void write(String input) {
            try {
                mmOutStream.write (input.getBytes ( ));
            } catch (IOException e) {
                Toast.makeText (getBaseContext ( ), "La Conexión fallo", Toast.LENGTH_LONG).show ( );
                finish ( );
            }
        }
    }


}
