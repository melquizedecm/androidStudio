package com.example.touristapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class ViewCategoria extends AppCompatActivity {

    CarouselView carouselView;

    int[] sampleimages={R.drawable.costac1,R.drawable.costac2,R.drawable.costac3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_categoria);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);


        carouselView = (CarouselView)findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleimages.length);

        carouselView.setImageListener(imageListener);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleimages[position]);
        }
    };
}
