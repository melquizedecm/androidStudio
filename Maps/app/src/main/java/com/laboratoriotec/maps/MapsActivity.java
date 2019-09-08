package com.laboratoriotec.maps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    float latitud;
    float longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager ( )
                .findFragmentById (R.id.map);
        mapFragment.getMapAsync (this);
        latitud= Float.valueOf ( getIntent ().getStringExtra ("latitud"));
        longitud= Float.valueOf ( getIntent ().getStringExtra ("longitud"));
        Toast.makeText (getApplicationContext (),String.valueOf (latitud), Toast.LENGTH_LONG).show ();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener (new GoogleMap.OnMapClickListener ( ) {
            @Override
            public void onMapClick(LatLng latLng) {
                Toast.makeText (getApplicationContext (),latLng.toString (),Toast.LENGTH_LONG).show ();
            }
        });

        // Add a marker in Sydney and move the camera
        LatLng merida = new LatLng (21.2812753, -89.6716374);
        mMap.addMarker(new MarkerOptions()
                .position(merida)
                .title("Visita Progreso")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.marcador)));

        CameraUpdate camUpd1 = CameraUpdateFactory.newLatLngZoom(merida, 15);

        mMap.moveCamera(camUpd1);

    }
}
