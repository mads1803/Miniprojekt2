package com.example.madsstoltenborg.rejsedagbog;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

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
        fab1.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick (View v) {
               startActivity(new Intent(MainActivity.this, OpretRejse.class));


            }
        });

        ListView lvRejser = (ListView) findViewById(R.id.list_options);
        //SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,  new int[]{android.R.id.text1});
        lvRejser.setAdapter(adapter);

        AdapterView.OnClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemclick(<AdapterView<?> listView, View itemView, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(MainActivity.this, ContactsContract.CommonDataKinds.Note.class);
                    startActivity(intent);
                }

            }
        };

    }

    @Override
    public void onResume(){
        super.onResume();
        initList();
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


    }



}
