package com.laboratoriotec.archivostexto;

import java.io.IOError;
import java.io.InputStreamReader;

public class ArchivosTexto {
    String nombreArchivo;

    private ArchivosTexto(String archivo){
        nombreArchivo=archivo;
    }

    public String abrirArchivo(String [] archivos,String archivo){
        String contenido="";

        if (this.existe(archivos,"notas.txt")){
            try{
                InputStreamReader inputStreamReader = new InputStreamReader (("notas.txt"));
            }
            catch ( IOError e ){

            }


        }

        return  contenido;
    }

    private boolean existe(String [] archivos,String archivo){

        return false;
    }
}
