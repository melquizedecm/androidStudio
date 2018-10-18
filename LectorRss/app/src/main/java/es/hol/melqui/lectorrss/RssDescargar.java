package es.hol.melqui.lectorrss;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by melqui on 2/14/18.
 */

public class RssDescargar {
    public static String getRSSfromURL(String url){

        HttpURLConnection connection;
        URL direccion;
        InputStream data;
        File xml;
        FileOutputStream fileOut;
        final int bufferSize=1024*2;
        byte[] buffer;
        try{
            direccion=new URL(url);
            connection=(HttpURLConnection) direccion.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.connect();

            data= connection.getInputStream();
            xml=new File("/EXTERNAL_MEMORY/rss.xml");
            fileOut=new FileOutputStream(xml);

            int bytesEscritura;
            buffer=new byte[bufferSize];
            while((bytesEscritura=data.read(buffer))>0){
                fileOut.write(buffer,0,bytesEscritura);
            }
            data.close();
            fileOut.close();
            connection.disconnect();
        }
        catch (Exception e){

        }
        return null;
    }
}
