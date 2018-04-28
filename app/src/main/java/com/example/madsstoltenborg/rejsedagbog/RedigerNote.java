package com.example.madsstoltenborg.rejsedagbog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

public class RedigerNote extends AppCompatActivity {
private Storage storage;
private static final int REQUEST_GET_MAP_LOCATION = 0;
private LatLng lokation;
private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rediger_note);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        // Set toolbar text
        getSupportActionBar().setTitle("Rediger note");

        //TODO: Skal oprette en rejse
        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.note_rediger);

                TextView titel = findViewById(R.id.rediger_note_titel);
                final String sTitel = titel.getText().toString();

                TextView beskrivelse = findViewById(R.id.rediger_note_beskrivelse);
                final String sBeskrivelse = beskrivelse.getText().toString();

                TextView weblink = findViewById(R.id.rediger_note_weblink);
                final String sweblink = weblink.getText().toString();

              TextView dato = findViewById(R.id.rediger_note_dato);
              final String sDato = dato.getText().toString();

                //TODO ÆNDRER TIL NOTE ID
              id = (int)getIntent().getExtras().get(Note.REJSE_ID);
              NoteCursorWrapper cursor =  storage.getDagbogsNote(id);

             Dagbogsnote redigeringsNote = cursor.getDagbogsNote();
            titel.setText(redigeringsNote.getTitel());
            beskrivelse.setText(redigeringsNote.getBeskrivelse());
            weblink.setText(redigeringsNote.getWeblink());
            dato.setText(redigeringsNote.getDato());

         final int rejseId = (int) redigeringsNote.getRejse_id();


            redigeringsNote.getLokation();
            redigeringsNote.getDato();
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO get rejseID
            storage.updateDagbogsNote(id, sTitel, rejseId, sBeskrivelse, lokation,sweblink ,sDato);

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

            TextView tvLokation = findViewById(R.id.rediger_note_titel);
            tvLokation.setText(""+lokation);

        }
    }
}
