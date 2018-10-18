package es.hol.melqui.lectornoticias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

/**
 * Created by melqui on 2/19/18.
 */

public class Noticia extends AppCompatActivity {

        private TextView txtTitulo;				// Campo gráfico que muestra el títutlo
        private TextView txtDescripcion;		// Campo gráfico que muestra la descripción
        private TextView txtFecha;				// Campo gráfico que muestra la fecha
        private TextView txtLink;				// Campo gráfico que muestra el link


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.noticia);

            // Inicialización de campos gráficos
            txtTitulo = (TextView) findViewById(R.id.txtTitulo);
            txtDescripcion = (TextView) findViewById(R.id.txtDescripcion);
            txtFecha = (TextView) findViewById(R.id.txtFecha);
            txtLink = (TextView) findViewById(R.id.txtLink);

            // Obtiene la noticia y la muestra
            obtenerNoticia();
        }

        /**
         * Obtiene la noticia y la muestra
         */
        public void obtenerNoticia(){

            // Recibe el intent con la noticia
            Intent intent = getIntent();
            Noticias noticia = (Noticias) intent.getSerializableExtra("DETALLE DE NOTICIA");

            // Muestra el contenido de la noticia
            txtTitulo.setText(noticia.getTitulo());
            txtDescripcion.setText(noticia.getDescripcion());
            txtFecha.setText(noticia.getFechaPublicacion());
            txtLink.setText(noticia.getUrl());
        }

}
