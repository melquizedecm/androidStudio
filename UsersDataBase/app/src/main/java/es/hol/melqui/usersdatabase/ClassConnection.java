package es.hol.melqui.usersdatabase;

import android.os.AsyncTask;
import android.os.HandlerThread;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by melqui on 3/15/18.
 */

public class ClassConnection extends AsyncTask<String,String,String> {

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection httpURLConnection=null;
        URL url =null;
        try {
            url = new URL (strings[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace ( );
        }

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection ();
            httpURLConnection.connect ();
            int code =httpURLConnection.getResponseCode ();
            if (code == HttpURLConnection.HTTP_OK){
                InputStream in = new BufferedInputStream (httpURLConnection.getInputStream ());
                BufferedReader reader  = new BufferedReader (new InputStreamReader (in));
                String line="";
                StringBuffer buffer = new StringBuffer ();
                while ((line=reader.readLine ())!=null){
                    buffer.append (line);
                }
                return buffer.toString ();
            }


        } catch (IOException e) {
            e.printStackTrace ( );
        }


        return null;
    }
}
