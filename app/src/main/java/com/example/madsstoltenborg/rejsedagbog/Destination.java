package com.example.madsstoltenborg.rejsedagbog;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class Destination extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    private double latitude;
    private double longitude;
    private String noteNavn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        // Set toolbar text
        getSupportActionBar().setTitle("Kort");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        if(getIntent().getExtras() != null){
             latitude = getIntent().getDoubleExtra("latitude", 0);
             longitude = getIntent().getDoubleExtra("longitude", 0);
             noteNavn = getIntent().getStringExtra("noteNavn");
        }
    }
    private Marker marker;
    @Override
    public void onMapLongClick(LatLng point){
        if (marker != null){
            marker.setPosition(point);
        }else {
            marker = mMap.addMarker(new MarkerOptions().position(point).title(point.toString()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        }

        setResult(Activity.RESULT_OK, new Intent().putExtra("latitude", marker.getPosition().latitude).putExtra("longitude", marker.getPosition().longitude));
    }

    /** Called when the map is ready. */
    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        MarkerOptions marker;
        if(getIntent().getExtras() != null){
            LatLng position = new LatLng(latitude, longitude);
            marker = new MarkerOptions().position(position).title(noteNavn);
            mMap.addMarker(marker);
        }

        // Set a listener for marker click.
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapLongClickListener(this);
    }

    /** Called when the user clicks a marker. */
    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.map_ok:
               finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

