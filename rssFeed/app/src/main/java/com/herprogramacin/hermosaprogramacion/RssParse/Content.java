package com.herprogramacin.hermosaprogramacion.RssParse;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Creado por Hermosa Programación.
 *
 * Clase que representa la etiqueta <media:content> del feed
 */

@Root(name="content", strict = false)
public class Content {

    @Attribute(name="url")
    private String url;

    public Content() {
    }

    public Content(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
