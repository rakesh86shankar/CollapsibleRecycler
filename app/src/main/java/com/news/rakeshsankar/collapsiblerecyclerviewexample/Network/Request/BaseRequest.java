package com.news.rakeshsankar.collapsiblerecyclerviewexample.Network.Request;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Network.NetworkLoader;

/**
 * Created by rakesh sankar on 10/13/2017.
 */

public class BaseRequest {
    RequestQueue requestQueue;
    String url;
    Context appContext;
    int TIMEOUT = 2000;
    boolean useIfModifiedSinceHeader = true;
    DefaultRetryPolicy defaultRetryPolicy = new DefaultRetryPolicy(TIMEOUT,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);


    BaseRequest(Context context){
        this.url = url;
        appContext = context.getApplicationContext();
        requestQueue = NetworkLoader.getInstance(context).getRequestQueue();
    }
}
