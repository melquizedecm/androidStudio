package es.hol.melqui.usersdatabase;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    EditText firstNameEdit;
    EditText lastNameEdit;
    EditText numberPhoneEdit;
    Button getButton;
    private Button setButton;
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        ////////RELACIONAR LOS CONTROLES /////
        firstNameEdit = (EditText) findViewById (R.id.txtNombre);
        lastNameEdit = (EditText) findViewById (R.id.txtApellido);
        numberPhoneEdit = (EditText) findViewById (R.id.txtTelefono);
        getButton = (Button) findViewById (R.id.button);
        setButton = (Button) findViewById (R.id.buttonGuardar);
        searchButton = (Button) findViewById (R.id.button2);

        //////CONSULTAR DATOS///////

        getButton.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                ClassConnection connection = new ClassConnection ();
                try {
                    String response = connection.execute("https://melquizedecm.000webhostapp.com/movilII/controller/getUsers.php").get ();

                    /////LEER EN FORMATO JSON

                    JSONArray jsonArray = new JSONArray (response);
                    JSONObject jsonObject = jsonArray.getJSONObject (19);

                        String firstName = jsonObject.getString ("first_name");
                        String lastName = jsonObject.getString ("last_name");
                        String numberPhone = jsonObject.getString ("number_phone");

                        String firstNameDecrypted= decrypter(firstName);
                        String lastNameDecrypted= decrypter(lastName);
                        String numberPhoneDecrypted= decrypter(numberPhone);

                        firstNameEdit.setText (firstNameDecrypted);
                        lastNameEdit.setText (lastNameDecrypted);
                        numberPhoneEdit.setText (numberPhoneDecrypted);

                } catch (InterruptedException e) {
                    e.printStackTrace ( );
                } catch (ExecutionException e) {
                    e.printStackTrace ( );
                } catch (JSONException e) {
                    e.printStackTrace ( );
                }

            }
        });


        setButton.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                ClassConnectionPost post = new ClassConnectionPost ();
                String response = post.doInBackground("https://melquizedecm.000webhostapp.com/movilII/controller/setUsersPost.php");
            }
        });
        ///////GUARDAR DATOS/////

      /*  setButton.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                String firstName=firstNameEdit.getText ().toString ();
                String lastName= lastNameEdit.getText ().toString ();
                String numberPhone= numberPhoneEdit.getText ().toString ();

                String firstNameEncrypted = encrypter(firstName);
                String lastNameEncrypted= encrypter (lastName);
                String numberPhoneEncrypted= encrypter (numberPhone);


                ClassConnection connection = new ClassConnection ( );
                try {
                    String response = connection.execute ("https://melquizedecm.000webhostapp.com/movilII/controller/setUsers.php?firstName="+firstNameEncrypted +"&lastName="+lastNameEncrypted+"&numberPhone="+numberPhoneEncrypted).get ( );
                    if (response != null) {
                        Toast.makeText (getApplicationContext ( ), "Guardado exitoso", Toast.LENGTH_LONG).show ( );
                        firstNameEdit.setText ("");
                        lastNameEdit.setText ("");
                        numberPhoneEdit.setText ("");

                    }
                } catch (InterruptedException e1) {
                    e1.printStackTrace ( );
                } catch (ExecutionException e1) {
                    e1.printStackTrace ( );
                }

            }

        });
*/

        //////BUSCAR DATOS///////

        searchButton.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                ClassConnection connection = new ClassConnection ();
                try {
                    String datoBuscar=firstNameEdit.getText ().toString ();

                    String datoEncriptado=encrypter (datoBuscar);

                    String response = connection.execute("https://melquizedecm.000webhostapp.com/movilII/controller/searchUsers.php?firstName="+datoEncriptado).get ();

                    /////LEER EN FORMATO JSON

                    JSONArray jsonArray = new JSONArray (response);
                    JSONObject jsonObject = jsonArray.getJSONObject (0);

                    String firstName = jsonObject.getString ("first_name");
                    String lastName = jsonObject.getString ("last_name");
                    String numberPhone = jsonObject.getString ("number_phone");

                    String firstNameDecrypted= decrypter(firstName);
                    String lastNameDecrypted= decrypter(lastName);
                    String numberPhoneDecrypted= decrypter(numberPhone);

                    firstNameEdit.setText (firstNameDecrypted);
                    lastNameEdit.setText (lastNameDecrypted);
                    numberPhoneEdit.setText (numberPhoneDecrypted);

                } catch (InterruptedException e) {
                    e.printStackTrace ( );
                } catch (ExecutionException e) {
                    e.printStackTrace ( );
                } catch (JSONException e) {
                    e.printStackTrace ( );
                }

            }
        });

        }

    private String decrypter(String message) {
        char arrayMessage[] =message.toCharArray ();
        for (int i=0; i<= arrayMessage.length-1;i++){
            arrayMessage[i]=(char) (arrayMessage[i]-((char)3));
        }
        return String.valueOf (arrayMessage);
    }

    private String encrypter(String message) {
        char arrayMessage[] =message.toCharArray ();
        for (int i=0; i<= arrayMessage.length-1;i++){
            arrayMessage[i]=(char) (arrayMessage[i]+((char)3));
        }
        return String.valueOf (arrayMessage);
    }
}