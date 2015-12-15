package com.free.hindi.shayari;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class ShowShayariActivity extends ActionBarActivity {

    TextView showShayari;
    protected List<ParseObject> mShayari;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.free.hindi.shayari.R.layout.activity_show_shayari);

        Toolbar toolbar = (Toolbar) findViewById(com.free.hindi.shayari.R.id.app_bar);
        setSupportActionBar(toolbar);


        AdView mAdView = (AdView) findViewById(com.free.hindi.shayari.R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        showShayari = (TextView) findViewById(com.free.hindi.shayari.R.id.textView);

        ParseQuery<ParseObject> query = ParseQuery.getQuery(ShayarilistActivity.mimage);
        query.whereEqualTo("objectId", ShayarilistActivity.sid);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                if (e == null) {
                    mShayari = parseObjects;
                    String[] username = new String[mShayari.size()];

                    for (ParseObject message : mShayari) {
                        username[i] = message.getString("Shayari");

                        showShayari.setText(username[i]);
                        i++;
                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.free.hindi.shayari.R.menu.menu_show_shayari, menu);
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
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.sneha.shayari"));
                startActivity(intent);
                break;
            case R.id.action_share:
                sharePost();
                break;
            case R.id.action_home:
                intent = new Intent(ShowShayariActivity.this, IndexActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                ShowShayariActivity.this.finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sharePost() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, showShayari.getText());
        startActivity(Intent.createChooser(shareIntent, getString(com.free.hindi.shayari.R.string.share_title)));

    }
}