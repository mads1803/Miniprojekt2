package com.example.madsstoltenborg.rejsedagbog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
    private Dagbogsnote redigeringsNote = null;

    public static final String EDIT_ID = "Edit_id";
    private int id;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rediger_note);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        // Set toolbar text
        getSupportActionBar().setTitle("Rediger note");
        Util.showSnackBar(RedigerNote.this, "Redigér note");

        storage = Storage.getInstance();

        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.note_rediger);
        final TextView titel = findViewById(R.id.rediger_note_titel);
        final TextView beskrivelse = findViewById(R.id.rediger_note_beskrivelse);
        final TextView weblink = findViewById(R.id.rediger_note_weblink);
        final TextView dato = findViewById(R.id.rediger_note_dato);


        id = (int) getIntent().getExtras().get(EDIT_ID);
        NoteCursorWrapper cursor = storage.getNote(id);

        if (cursor.moveToFirst()) {
            redigeringsNote = cursor.getDagbogsNote();
        }

        titel.setText(redigeringsNote.getTitel());
        beskrivelse.setText(redigeringsNote.getBeskrivelse());
        weblink.setText(redigeringsNote.getWeblink());
        dato.setText(redigeringsNote.getDato());
        final int rejseId = (int) redigeringsNote.getRejse_id();
        lokation = redigeringsNote.getLokation();


        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sTitel = titel.getText().toString();
                String sBeskrivelse = beskrivelse.getText().toString();
                String sweblink = weblink.getText().toString();
                String sDato = dato.getText().toString();

                storage.updateDagbogsNote(id, sTitel, rejseId, sBeskrivelse, lokation, sweblink, sDato);

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


    public void onClickKort(View view) {
        intent = new Intent(this, Destination.class);

        if (lokation != null) {
            Log.d("DEMO", "onClickKort: " + lokation);
            double latitude = lokation.latitude;
            double longitude = lokation.longitude;

            intent.putExtra("latitude", latitude).putExtra("longitude", longitude).putExtra("edit", "redigerer");
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
            tvLokation.setText("" + lokation);

        }
    }
}
