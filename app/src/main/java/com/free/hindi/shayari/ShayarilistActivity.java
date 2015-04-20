package com.free.hindi.shayari;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.startapp.android.publish.StartAppAd;

import adapters.ListAdapter;


public class ShayarilistActivity extends ActionBarActivity {

    ListView list;
    ListAdapter adapter;
    protected static String sid;
    public static String mimage;
    private StartAppAd startAppAd = new StartAppAd(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.free.hindi.shayari.R.layout.activity_shayarilist);
        StartAppAd.showSlider(this);

        AdView mAdView = (AdView) findViewById(com.free.hindi.shayari.R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

          mimage = getIntent().getStringExtra("image");

        Toolbar toolbar = (Toolbar) findViewById(com.free.hindi.shayari.R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(mimage.toUpperCase() + " Shayari");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list = (ListView) findViewById(com.free.hindi.shayari.R.id.listView);

        adapter = new ListAdapter(this, new ParseQueryAdapter.QueryFactory<ParseObject>() {

            public ParseQuery<ParseObject> create() {
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(mimage);

                return query;
            }
        }, mimage);
        adapter.setTextKey("shayari_name");

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ShayarilistActivity.this, ShowShayariActivity.class);
                sid = adapter.getItem(position).getObjectId();
                System.out.println(" set on item click listener call" + adapter.getItem(position).getObjectId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        startAppAd.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shayarilist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Intent intent;
        switch (id){
            case R.id.action_more_apps:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:AASA"));
                startActivity(intent);
                break;
            case R.id.action_rate_us:
                intent = new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=com.sneha.shayari"));
                startActivity(intent);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
