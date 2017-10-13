package com.news.rakeshsankar.collapsiblerecyclerviewexample;

import android.content.Context;

import com.android.volley.RequestQueue;

/**
 * Created by rakesh sankar on 10/13/2017.
 */

public class BaseRequest {
    RequestQueue requestQueue;
    String url;
    Context appContext;

    BaseRequest(Context context){
        this.url = url;
        appContext = context.getApplicationContext();
        requestQueue = NetworkLoader.getInstance(context).getRequestQueue();
    }
}
