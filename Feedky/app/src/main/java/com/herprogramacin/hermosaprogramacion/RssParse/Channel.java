package com.herprogramacin.hermosaprogramacion.RssParse;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Creado por Hermosa Programación.
 *
 * Clase que representa la etiqueta <channel> del feed
 */

@Root(name = "channel", strict = false)
public class Channel {


    @ElementList(inline = true)
    private List<Item> items;

    public Channel() {
    }

    public Channel(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }
}
