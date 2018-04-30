package com.example.madsstoltenborg.rejsedagbog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class OpretNote extends AppCompatActivity implements View.OnClickListener{
    private Storage storage;
    private static final int REQUEST_GET_MAP_LOCATION = 0;
    private LatLng lokation;
    private String noteNavn = "";
    public static final String REJSE_ID = "Rejse_id";
    private int id;

    //UI References
    private EditText note_fromDateEtxt2;

    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;

    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opret_note);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        // Set toolbar text
        getSupportActionBar().setTitle("Opret note");

        id = (int) getIntent().getExtras().get("Rejse_id");

        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.note_opret);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView titel = findViewById(R.id.note_titel);
                String sTitel = titel.getText().toString();

                noteNavn = sTitel;

                TextView beskrivelse = findViewById(R.id.note_beskrivelse);
                String sBeskrivelse = beskrivelse.getText().toString();

                TextView weblink = findViewById(R.id.note_weblink);
                String sWeblink = beskrivelse.getText().toString();

                note_fromDateEtxt2 = (EditText) findViewById(R.id.note_fromDateEtxt2);
                String dato = note_fromDateEtxt2.getText().toString();

                storage.insertDagbogsNote(sTitel, id, sBeskrivelse, lokation, sWeblink, dato);

                finish();
            }
        });
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        findViewsById();

        setDateTimeField();

        note_fromDateEtxt2.setOnClickListener(this);

    }

    private void findViewsById() {
        note_fromDateEtxt2 = (EditText) findViewById(R.id.note_fromDateEtxt2);
        note_fromDateEtxt2.setInputType(InputType.TYPE_NULL);
        note_fromDateEtxt2.requestFocus();

    }

    private void setDateTimeField() {
        note_fromDateEtxt2.setOnClickListener(this);


        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                note_fromDateEtxt2.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


    }

    @Override
    public void onClick(View view) {
        if (view == note_fromDateEtxt2) {
            fromDatePickerDialog.show();
        }
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
        Intent intent = new Intent(this, Destination.class);
        if (lokation != null) {

            double latitude = lokation.latitude;
            double longitude = lokation.longitude;

            intent.putExtra("latitude", latitude).putExtra("longitude", longitude).putExtra("noteNavn", noteNavn);
        }

        startActivityForResult(intent, REQUEST_GET_MAP_LOCATION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_GET_MAP_LOCATION && resultCode == Activity.RESULT_OK) {
            double latitude = data.getDoubleExtra("latitude", 0);
            double longitude = data.getDoubleExtra("longitude", 0);

            lokation = new LatLng(latitude, longitude);
        }
    }
}
