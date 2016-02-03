package com.teenvan.frs;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParsePush;

/**
 * Created by navneet on 27/09/15.
 */
public class FRSApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "mlfCDX70HHQdbk9C7HfgogIuYn8O0Ef4TtB0Fllm",
                "NHdLk8WWMIggWn1aoHbI7Dq59AvmpkfZAVclG4E9");

    }
}
