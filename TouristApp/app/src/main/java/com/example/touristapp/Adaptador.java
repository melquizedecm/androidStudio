package com.example.touristapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    public Adaptador(Context context, ArrayList<Entidad> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    private Context context;
    private ArrayList<Entidad> listItems;

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Entidad Item = (Entidad) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
        TextView titulo = (TextView) convertView.findViewById(R.id.categoryItemName);
        ImageView imgFoto = (ImageView) convertView.findViewById(R.id.categoryItemImage);
        titulo.setText(Item.getTitulo());
        imgFoto.setImageResource(Item.getImgFoto());
        return convertView;
    }
}
