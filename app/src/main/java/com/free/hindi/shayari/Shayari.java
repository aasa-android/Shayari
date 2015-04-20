package com.free.hindi.shayari;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by sneha on 28/3/15.
 */
public class Shayari extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
      //  ParseObject.registerSubclass(Love.class);
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "n71sLiC6CFquE4T5wi6bpM9DYriJSPkvNNhNBiwe", "vcEdMbyWjdGaVuQOxf0Uf0O6ds3Ec0NXlF6zaFxp");

    }
}
