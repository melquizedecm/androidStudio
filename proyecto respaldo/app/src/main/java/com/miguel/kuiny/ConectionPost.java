package com.miguel.kuiny;

import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

    /**
     * Created by Miguel Pech Mena on 05/03/2019.
     */

    class ConnectionPost extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection httpURLConnection=null;
            URL url=null;
            try {
                url = new URL(strings[0]);
            }catch (MalformedURLException e){
                e.printStackTrace();
            }
            try {
                httpURLConnection = (HttpURLConnection) url.openConnection();
                ////para configurar por post/////

                httpURLConnection.setReadTimeout(15000);
                httpURLConnection.setConnectTimeout (10000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);


                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                writer.write(strings[1]);
                writer.flush();
                writer.close();
                os.close();

                //////fin de configurar post

                httpURLConnection.connect();
                int code=httpURLConnection.getResponseCode();
                if(code== HttpURLConnection.HTTP_OK){

                    InputStream in=httpURLConnection.getInputStream ();
                    BufferedReader reader= new BufferedReader (new InputStreamReader (in));
                    StringBuilder result= new StringBuilder ();
                    String Line=" ";
                    while((Line=reader.readLine())!=null){
                        result.append(Line);
                    }
                    return result.toString();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
    }
