package com.project.chatapp.util;

import android.app.Application;
import android.os.Bundle;

import com.parse.Parse;
import com.parse.ParseObject;
import com.project.chatapp.BuildConfig;
import com.project.chatapp.R;
import com.project.chatapp.model.Message;

public class App extends Application {

    private static final String APPLICATION_ID_CHAT= BuildConfig.APPLCATION_ID_CHAT;
    private static final String CLIENT_KEY= BuildConfig.CLIENT_KEY;


    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Message.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(BuildConfig.APPLCATION_ID_CHAT)
                // if defined
                .clientKey(BuildConfig.CLIENT_KEY)
                .server(getString(R.string.back4app_server_url))
                .build())
        ;



    }




}
