package com.example.madsstoltenborg.rejsedagbog;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class NoteData extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {
private Intent intent = getIntent();
private GoogleMap mMap;
public static final String NOTE_ID = "Note_id";
private int id;
private Storage storage;
private Dagbogsnote note = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_data);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


        storage = Storage.getInstance();

        id = (int)getIntent().getExtras().get(NOTE_ID);
        NoteCursorWrapper cursor = storage.getNote(id);

        if(cursor.moveToFirst()){
             note = cursor.getDagbogsNote();
        }
        // Set toolbar text
        getSupportActionBar().setTitle(note.getTitel());
        
        TextView beskrivelse = findViewById(R.id.selected_beskrivelse);
        beskrivelse.setText(note.getBeskrivelse());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                myToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawer.setScrimColor(getResources().getColor(android.R.color.transparent));


    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        LatLng position = note.getLokation();
        MarkerOptions marker = new MarkerOptions().position(position).title(note.getTitel());
        mMap.addMarker(marker);
        pointToPosition(position);
    }

    private void pointToPosition(LatLng position) {
        //Build camera position
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(position)
                .zoom(5).build();
        //Zoom in and animate the camera.
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public void onClickWebView(View view){
        Intent intent = new Intent(this, Weblink.class);
        intent.putExtra("weblink", note.getWeblink());
        startActivity(intent);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        Intent intent = null;

        switch(id) {
            case R.id.nav_start:
                Intent startIntent = new Intent(this, MainActivity.class);
                startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(startIntent);
                break;
            case R.id.nav_kort:
                Intent mapIntent = new Intent (this, Destination.class);
                startActivity(mapIntent);
                break;
            case R.id.nav_favouritter:
                Util.showSnackBar(NoteData.this, "Ikke Implementeret",0);
                break;
            case R.id.nav_kalender:
                Util.showSnackBar(NoteData.this, "Ikke Implementeret",0);
                break;
            case R.id.nav_info:
                Util.showSnackBar(NoteData.this, "Ikke Implementeret",0);
                break;
            case R.id.nav_send:
                Util.showSnackBar(NoteData.this, "Ikke Implementeret",0);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.rejse_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.rejse_cancel:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
