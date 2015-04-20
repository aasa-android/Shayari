package com.free.hindi.shayari;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.startapp.android.publish.StartAppAd;


public class IndexActivity extends ActionBarActivity {

    ImageView mBewafa, mDard, mLove, mRomantic;
    private StartAppAd startAppAd = new StartAppAd(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StartAppAd.showSplash(this, savedInstanceState);
        setContentView(com.free.hindi.shayari.R.layout.activity_index);
        StartAppAd.showSlider(this);

        AdView mAdView = (AdView) findViewById(com.free.hindi.shayari.R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mBewafa = (ImageView) findViewById(com.free.hindi.shayari.R.id.bewafa);
        mDard = (ImageView) findViewById(com.free.hindi.shayari.R.id.dard);
        mLove = (ImageView) findViewById(com.free.hindi.shayari.R.id.love);
        mRomantic = (ImageView) findViewById(com.free.hindi.shayari.R.id.romantic);

        mBewafa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexActivity.this, ShayarilistActivity.class);
                intent.putExtra("image", "bewafa");
                startActivity(intent);
            }
        });
        mDard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexActivity.this, ShayarilistActivity.class);
                intent.putExtra("image", "dard");
                startActivity(intent);
            }
        });
        mLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexActivity.this, ShayarilistActivity.class);
                intent.putExtra("image", "love");
                startActivity(intent);
            }
        });
        mRomantic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexActivity.this, ShayarilistActivity.class);
                intent.putExtra("image", "romantic");
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        startAppAd.onBackPressed();
        super.onBackPressed();
    }

    @Override
    public void onResume() {
        super.onResume();
        startAppAd.onResume();
    }


}
