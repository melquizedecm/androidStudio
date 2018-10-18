package com.ACDAT.ejercicio6;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.crypto.spec.PSource;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ACDAT.ejercicio6.Noticia.Noticia;


public class UltimasNoticiasActivity extends Activity {

	// Campos gráficos
	private ListView listvNoticias;	// Campo gráfico que muestra una lista con noticias
	
	// Otros campos
	private  URL url;
	private ArrayList<Noticia> noticias;
	private String[] titulos;
	private String urlParameter="http://ep00.epimg.net/rss/tags/ultimas_noticias.xml";

	/**
	 * Al iniciar la actividad
	 */
    @SuppressLint({ "NewApi", "NewApi", "NewApi" })
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultimas_noticias);

        //obtención del link
		Bundle parameterLink = getIntent().getExtras();
		if(parameterLink!=null){
			urlParameter= parameterLink.getString("link").toString();
			//Toast.makeText(getApplicationContext(),parameterLink.getString("link"),Toast.LENGTH_LONG).show();
		}


        // Inicialización de campos gráficos
        listvNoticias = (ListView) findViewById(R.id.listvUltimasNoticias);
        
        // Inicialización de otros campos
        noticias = new ArrayList<Noticia>();

        // Permisos para acceder a internet
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        // Inicializa la url desde donde se obtiene el fichero xml
        try {
		//	url = new URL("http://ep00.epimg.net/rss/tags/ultimas_noticias.xml");
		//	url= new URL("http://www.reforma.com/rss/ciencia.xml");
        //  url= new URL("http://www.aztecanoticias.com.mx/rss/mexico.xml");
        //    url= new URL("http://rss.televisa.com/noticieros/index.xml");
			url = new URL(urlParameter);
			
			leerDatos();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // Crea el adaptador con los titulos de noticias
        ArrayAdapter adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,titulos);

        
        // Establece el adaptador
        listvNoticias.setAdapter(adaptador);
        

        listvNoticias.setOnItemClickListener(new OnItemClickListener() {

        	/**
        	 * Muestra la noticia seleccionada
        	 */
			public void onItemClick(AdapterView<?> adaptador, View vista, int posicion,
					long arg3) {
				
				// Crea un intent con la noticia y la envía a NoticiaActivity para mostrarla
				Intent intent = new Intent (UltimasNoticiasActivity.this, NoticiaActivity.class);
				intent.putExtra("NOTICIA", noticias.get(posicion));
				startActivity(intent);

			}
		});

    }

    /**
     * Lee los datos del fichero xml
     */
    public void leerDatos(){
    	
    	// CAMPOS ////////////////////////////////////////////////////////////////
    	
    	XmlPullParserFactory factory;
    	XmlPullParser xml;			// Almacena el fichero xml
    	int evento;					// Almacena el evento producido
    	
    	boolean title;				// Almacena si se ha leido el título
    	boolean link;				// Almacena si se ha leido el link
    	boolean pubDate;			// Almacena si se ha leido la fecha de publicación
    	boolean contentEncoded;		// Almacena si se ha leido el contenido
    	
    	ArrayList<String> titles;			// Almacena los títutlos de la noticias
    	ArrayList<String> links;			// Almacena los links de las noticias
    	ArrayList<String> pubDates;			// Almacena las fechas de publicación de las noticias
    	ArrayList<String> contentEncodeds;	// Almacena el contenido de las noticias
    	
    	int contador;				// Controla que no salga los dos primeros titulos
    	int contadorDescription;
    	
    	// Inicializa los arrays
    	titles = new ArrayList<String>();
    	links = new ArrayList<String>();
    	pubDates = new ArrayList<String>();
    	contentEncodeds = new ArrayList<String>();
    	
    	// Inicializa el contador
    	contador = 0;
    	contadorDescription=0;
    	
    	// Inicializa los valores booleanos
    	title = false;
    	link = false;
    	pubDate = false;
    	contentEncoded = false;
    	
    	try {
    	
    		// Obtiene el fichero xml
			factory = XmlPullParserFactory.newInstance();
			xml = factory.newPullParser();
			xml.setInput(url.openStream(),"UTF-8");
			
			// Obtiene el evento producido
			evento = xml.getEventType();
			
			// Recorre el fichero xl
			while (evento != XmlPullParser.END_DOCUMENT){
				
				switch (evento) {
				
				case XmlPullParser.START_TAG:


					if (xml.getName().equals("title")){
						
						// Incrementa el contador de titulos
						contador++;
						
						// Controla que no aparezca los dos primeros títulos
						if (contador > 2){
							
							title = true;

						}
					}
					
					
					if (xml.getName().equals("link")){
						
						link = true;
					}
					
					
					if (xml.getName().equals("pubDate")){
						
						pubDate = true;
					}
					
					
					if (xml.getName().equals("description")){
						contadorDescription++;
						if (contadorDescription>1) {
                            contentEncoded = true;
                        }
					}
					
					break;
				
					
				case XmlPullParser.TEXT:
					
					if (title){
						titles.add(xml.getText());
					}
					
					if (link){
						links.add(xml.getText());
					}
					
					if (pubDate){
						pubDates.add(xml.getText());
					}
					
					if (contentEncoded){
						contentEncodeds.add(xml.getText());
					}
					
					
					break;
					
				case XmlPullParser.END_TAG:
					
					if (xml.getName().equals("title")){
						title = false;
					}

					if (xml.getName().equals("link")){
						link = false;
					}

					if (xml.getName().equals("pubDate")){
						pubDate = false;
					}

					if (xml.getName().equals("description")){
						contentEncoded = false;
					}

					break;

				}
				
				evento = xml.next();
			}
			
			
			// Almacena la noticias////////////////////////////////////////////////////////////////////////////////////
			
			// Inicializa el array de titulos de noticias
			titulos = new String[contentEncodeds.size()];
			
			// Recorre el contenido
			for (int i = 0; i < contentEncodeds.size(); i++) {
				
				// Almacena las noticas
				noticias.add(new Noticia(titles.get(i), contentEncodeds.get(i), pubDates.get(i), links.get(i)));
				
				// Almacena el titutlo de las noticas
				titulos[i] = titles.get(i);
			}
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	// FIN EJECUCIÓN //////////////////////////////////////////////////////////////
    }
}
