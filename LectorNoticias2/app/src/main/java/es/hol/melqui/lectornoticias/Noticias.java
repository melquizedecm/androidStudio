package es.hol.melqui.lectornoticias;

import java.io.Serializable;

/**
 * Created by melqui on 2/19/18.
 */

public class Noticias implements Serializable {
    ///atributos
    private String titulo;
    private String descripcion;
    private String fechaPublicacion;
    private String url;

    //Constructor
    public Noticias(String titulo,String descripcion,
                    String fechaPublicacion,String url){
        this.titulo= titulo;
        this.descripcion=descripcion;
        this.fechaPublicacion=fechaPublicacion;
        this.url=url;
    }

    public String getTitulo(){
        return this.titulo;
    }

    public String getDescripcion(){
        return  this.descripcion;
    }

    public String getFechaPublicacion(){
        return this.fechaPublicacion;
    }

    public String getUrl(){
        return this.url;
    }

}
