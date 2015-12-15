package com.free.hindi.shayari;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.startapp.android.publish.StartAppAd;


public class IndexActivity extends ActionBarActivity implements View.OnClickListener{

    ImageView mBewafa, mDard, mLove, mRomantic;
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.free.hindi.shayari.R.layout.activity_index);

        AdView mAdView = (AdView) findViewById(com.free.hindi.shayari.R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mBewafa = (ImageView) findViewById(com.free.hindi.shayari.R.id.bewafa);
        mDard = (ImageView) findViewById(com.free.hindi.shayari.R.id.dard);
        mLove = (ImageView) findViewById(com.free.hindi.shayari.R.id.love);
        mRomantic = (ImageView) findViewById(com.free.hindi.shayari.R.id.romantic);

        mBewafa.setOnClickListener(this);
        mDard.setOnClickListener(this);
        mLove.setOnClickListener(this);
        mRomantic.setOnClickListener(this);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3297319032078557/8221249620");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });

        requestNewInterstitial();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(IndexActivity.this, ShayarilistActivity.class);
        switch (view.getId()){
            case R.id.bewafa:
                intent.putExtra("image", "bewafa");
                startActivity(intent);
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                break;
            case R.id.dard:
                intent.putExtra("image", "dard");
                startActivity(intent);
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                break;
            case R.id.love:
                intent.putExtra("image", "love");
                startActivity(intent);
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                break;
            case R.id.romantic:
                intent.putExtra("image", "romantic");
                startActivity(intent);
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                break;
        }
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
}
