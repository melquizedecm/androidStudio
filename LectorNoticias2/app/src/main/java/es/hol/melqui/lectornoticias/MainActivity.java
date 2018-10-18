package es.hol.melqui.lectornoticias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //atributos

    ListView listViewNoticias;
    URL url;
    ArrayList<Noticias> noticias;
    String[] titulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewNoticias=(ListView) findViewById(R.id.listViewUltimasNoticias);
        noticias=new ArrayList<Noticias>();

        ///accedemos al url
        try{
            url=new URL("http://ep00.epimg.net/rss/tags/ultimas_noticias.xml");
            leerDatos();

        } catch (MalformedURLException e){
            e.printStackTrace();
        }
    }

    private void leerDatos() {
        XmlPullParserFactory factory;
        XmlPullParser xml;
        int evento;

        boolean booleanTitle;
        boolean booleanLink;
        boolean booleanPubDate;
        boolean booleanContent;

        ArrayList<String> titles;
        ArrayList<String> links;
        ArrayList<String> pubDates;
        ArrayList<String> contentEncodeds;
        int contador;

        titles= new ArrayList<String>();
        links= new ArrayList<String>();
        pubDates= new ArrayList<String>();
        contentEncodeds = new ArrayList<String>();
        contador=0;

        booleanTitle=false;
        booleanLink=false;
        booleanPubDate=false;
        booleanContent=false;

        try{
            factory= XmlPullParserFactory.newInstance();
            xml=factory.newPullParser();
            xml.setInput(url.openStream(),"UTF-8");
            evento=xml.getEventType();
            while(evento!=XmlPullParser.END_DOCUMENT){
                switch (evento){

                    case XmlPullParser.START_TAG:
                        if(xml.getName().equals("title")){
                            contador++;
                            if (contador>2){
                                booleanTitle=true;
                            }
                        }
                        if(xml.getName().equals("link")){
                            booleanLink=true;
                        }
                        if (xml.getName().equals("pubDate")){
                            booleanPubDate = true;
                        }
                        if (xml.getName().equals("content:encoded")){
                            booleanContent = true;
                        }
                        break;

                    case XmlPullParser.TEXT:
                        if (booleanTitle){
                            titles.add(xml.getText());
                        }
                        if(booleanLink){
                            links.add(xml.getText());
                        }
                        if (booleanPubDate){
                            pubDates.add(xml.getText());
                        }
                        if (booleanContent){
                            contentEncodeds.add(xml.getText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (xml.getName().equals("title")){
                            booleanTitle = false;
                        }
                        if (xml.getName().equals("link")){
                            booleanLink = false;
                        }
                        if (xml.getName().equals("pubDate")){
                            booleanPubDate = false;
                        }
                        if (xml.getName().equals("content:encoded")){
                            booleanContent = false;
                        }
                        break;
                }
                evento=xml.next();
            }

            ///inializar los titulos y generar el arreglo a presentar
            titulos= new String[contentEncodeds.size()];
            for (int i=0; i<contentEncodeds.size();i++){
                noticias.add(new Noticias(titles.get(i),contentEncodeds.get(i),pubDates.get(i), links.get(i)));
                titulos[i]=titles.get(i);
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
