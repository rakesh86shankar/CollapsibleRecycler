package com.news.rakeshsankar.collapsiblerecyclerviewexample;

import android.app.Application;
import android.content.Context;

/**
 * Created by rakesh sankar on 10/16/2017.
 */

public class NewsApp extends Application {
    private static NewsApp instance;

    public static NewsApp getInstance() {
        return instance;
    }

    private static void setInstance(NewsApp app) {
        instance = app;
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }

}
