package com.example.madsstoltenborg.rejsedagbog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Storage storage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDBHelper.setApplicationContext(this);

        storage = Storage.getInstance();
        if(doesDataBaseExist(this, "RejseDagbog")==false){
            Log.v("Database", "Fail");
        }


        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Rejser");

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


        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab_opretRejse);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OpretRejse.class));
            }
        });

    initList();


    }
    @Override
    public void onResume(){
        super.onResume();
        initList();
    }

    private static boolean doesDataBaseExist(Context context, String dbName){
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        Intent intent = null;

        switch(id) {
            case R.id.nav_start:
                Intent startIntent = new Intent(this, MainActivity.class);
                startActivity(startIntent);
                break;
            case R.id.nav_kort:
                Intent mapIntent = new Intent (this, Destination.class);
                break;
            case R.id.nav_favouritter:
                Toast.makeText(this, R.string.ikke_implemeretet, Toast.LENGTH_SHORT);
                break;
            case R.id.nav_kalender:
                Toast.makeText(MainActivity.this, R.string.ikke_implemeretet, Toast.LENGTH_SHORT);
                break;
            case R.id.nav_info:
                Toast.makeText(this, R.string.ikke_implemeretet, Toast.LENGTH_SHORT);
                break;
            case R.id.nav_send:
                Toast.makeText(this, R.string.ikke_implemeretet, Toast.LENGTH_SHORT);
                break;




        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initList(){
        ListView lvRejser = (ListView)findViewById(R.id.rejse_options);

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {


            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Log.v("Adapter", "clicked + id");

                Intent intent = new Intent(MainActivity.this, Note.class);
                intent.putExtra(Note.REJSE_ID, (int) id);
                //intent.putExtra(DrinkActivity.EXTRA_DRINKID, (int) id)
                startActivity(intent);

            }
        };
        View.OnClickListener btnlistener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast t = Toast.makeText(MainActivity.this, R.string.ikke_implemeretet, Toast.LENGTH_SHORT);
                t.show();
                Snackbar snackbar = Snackbar.make(findViewById(R.id.Root), R.string.ikke_implemeretet, Snackbar.LENGTH_SHORT);
            }
        };




        Cursor cursor = storage.getRejse();
        RejseAdapter adapter = new RejseAdapter(this, cursor, 0, btnlistener);




        lvRejser.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(view.getId()==R.id.visRejseKortBtn){
                    Log.v("ClickListener", "button click");
                    Snackbar myScack = Snackbar.make(findViewById(R.id.rejse_options), "Ikke implementeret", Snackbar.LENGTH_SHORT);
                    myScack.show();
                }
                Log.v("Adapter", "clicked + id");

                Intent intent = new Intent(MainActivity.this, Note.class);
                intent.putExtra(Note.REJSE_ID, (int) id);
                //intent.putExtra(DrinkActivity.EXTRA_DRINKID, (int) id)
                startActivity(intent);

            }
        });
        lvRejser.setAdapter(adapter);

    }

}
