package com.example.madsstoltenborg.rejsedagbog;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import static com.example.madsstoltenborg.rejsedagbog.NoteData.NOTE_ID;
import static com.example.madsstoltenborg.rejsedagbog.RedigerNote.EDIT_ID;

public class Note extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public static final String REJSE_ID = "Rejse_id";
    private Storage storage;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        initList();
    }

    @Override
    public void onResume(){
        super.onResume();
        initList();
    }

    public  void initList(){
        storage = Storage.getInstance();

        id = (int)getIntent().getExtras().get(REJSE_ID);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        // Set toolbar text
        getSupportActionBar().setTitle("Mine Noter");

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


        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab_opretNote);
        fab1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                Intent intent = new Intent(Note.this, OpretNote.class);
                intent.putExtra("Rejse_id", id);
                startActivity(intent);
            }
        });

        ListView listView = (ListView) findViewById(R.id.note_options);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, storage.getDagbogsNote(id), new String[] {"TITEL"}, new int[]{android.R.id.text1});
        listView.setAdapter(adapter);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Note.this, NoteData.class);
                intent.putExtra(NOTE_ID, (int) l);
                startActivity(intent);
            }
        };
        listView.setOnItemClickListener(itemClickListener);

        AdapterView.OnItemLongClickListener itemLongClickListener = new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Note.this, RedigerNote.class);
                intent.putExtra(EDIT_ID, (int) l);
                startActivity(intent);

                return true;
            }
        };
        listView.setOnItemLongClickListener(itemLongClickListener);

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

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
                Util.showSnackBar(Note.this, "Ikke Implementeret",0);
                break;
            case R.id.nav_kalender:
                Util.showSnackBar(Note.this, "Ikke Implementeret",0);
                break;
            case R.id.nav_info:
                Util.showSnackBar(Note.this, "Ikke Implementeret",0);
                break;
            case R.id.nav_send:
                Util.showSnackBar(Note.this, "Ikke Implementeret",0);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
