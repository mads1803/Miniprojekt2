package com.example.madsstoltenborg.rejsedagbog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
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

public class RedigerNote extends AppCompatActivity implements View.OnClickListener{
    private Storage storage;
    private static final int REQUEST_GET_MAP_LOCATION = 0;
    private LatLng lokation;
    private Dagbogsnote redigeringsNote = null;

    public static final String EDIT_ID = "Edit_id";
    private int id;
    private Intent intent;

    //UI References
    private EditText edit_fromDateEtxt;

    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;

    private SimpleDateFormat dateFormatter;

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
        final TextView dato = findViewById(R.id.edit_fromDateEtxt);


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
                finish();
            }
        });
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        findViewsById();

        setDateTimeField();

        edit_fromDateEtxt.setOnClickListener(this);

    }

    private void findViewsById() {
        edit_fromDateEtxt = (EditText) findViewById(R.id.etxt_fromdate);
        edit_fromDateEtxt.setInputType(InputType.TYPE_NULL);
        edit_fromDateEtxt.requestFocus();

    }

    private void setDateTimeField() {
        edit_fromDateEtxt.setOnClickListener(this);


        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                edit_fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


    }

    @Override
    public void onClick(View view) {
        if(view == edit_fromDateEtxt) {
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

        }
    }

}
