package com.example.madsstoltenborg.rejsedagbog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
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


        final Cursor cursor = storage.getRejse();

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0) {
                    Intent intent = new Intent(MainActivity.this, Note.class);;
                    startActivity(intent);
                }
            }
        };

        ListView listView = (ListView) findViewById(R.id.rejse_options);
        listView.setOnItemClickListener(itemClickListener);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, storage.getRejse(), new String[] {"REJSENAVN"}, new int[]{android.R.id.text1});
        listView.setAdapter(adapter);



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
            case R.id.action_settings:
                //showAddProductDialog();
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initList(){
        ListView lvRejser = (ListView)findViewById(R.id.rejse_options);


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = (int) v.getTag();
                if(v.getId() == R.id.visRejseKortBtn) {
                    //Intent intent = new Intent(MainActivity.this, VisRejsekort_activity.class);
                    //startActivity(intent);
                }else{
                    Intent intent = new Intent(MainActivity.this, Note.class);
                    startActivity(intent);

                }
            }
        };

        Cursor cursor = storage.getRejse();
        RejseAdapter adapter = new RejseAdapter(this, cursor, 0, listener);
        lvRejser.setAdapter(adapter);



    }




}
