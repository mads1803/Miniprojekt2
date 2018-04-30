package com.example.madsstoltenborg.rejsedagbog;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

public class Weblink extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weblink);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Weblink");


        WebView myWebView = (WebView) findViewById(R.id.note_webview);
        //TODO: Fixe HTTP her
        String newWeblink;
        String url;
        try {
            url = getIntent().getStringExtra("weblink");

            String[] web = url.split("www");

            newWeblink = "http://www" + web[1];
            Log.d("DEMO", "onCreate: LINK: " + url);
        } catch (Exception e) {

            Context context = getApplicationContext();
            CharSequence text = "Invalid URL, here is a an Example";
            newWeblink = "http://www.example.com";
            int duration = Toast.LENGTH_SHORT;
            Log.d("DEMO", "onCreate: LINK " + newWeblink);
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }

        myWebView.loadUrl(newWeblink);
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
