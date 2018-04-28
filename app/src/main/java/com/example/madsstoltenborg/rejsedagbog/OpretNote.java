package com.example.madsstoltenborg.rejsedagbog;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

public class OpretNote extends AppCompatActivity {
private Storage storage;
private static final int REQUEST_GET_MAP_LOCATION = 0;
private LatLng lokation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opret_note);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        // Set toolbar text
        getSupportActionBar().setTitle("Opret note");

        //TODO: Skal oprette en rejse
        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.note_opret);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView titel = findViewById(R.id.note_titel);
                String sTitel = titel.getText().toString();

                TextView beskrivelse = findViewById(R.id.note_beskrivelse);
                String sBeskrivelse = beskrivelse.getText().toString();

                // TODO: Tilføje den rigtige rejse og dato
                storage.insertDagbogsNote(sTitel, 0, sBeskrivelse, lokation,"www.example.com", "10-10-1001");

                finish();
            }
        });
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


    public void onClickKort(View view){
        Intent intent = new Intent(this, Destination.class);
        if(lokation != null){

            double latitude = lokation.latitude;
            double longitude = lokation.longitude;
            
            intent.putExtra("latitude", latitude).putExtra("longitude", longitude);
        }

        startActivityForResult(intent, REQUEST_GET_MAP_LOCATION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_GET_MAP_LOCATION && resultCode == Activity.RESULT_OK) {
            double latitude = data.getDoubleExtra("latitude", 0);
            double longitude = data.getDoubleExtra("longitude", 0);

            lokation = new LatLng(latitude, longitude);

            TextView tvLokation = findViewById(R.id.note_titel);
            tvLokation.setText(""+lokation);

        }
    }
}
