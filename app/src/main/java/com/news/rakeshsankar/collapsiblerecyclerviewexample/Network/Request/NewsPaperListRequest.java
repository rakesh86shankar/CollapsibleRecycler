package com.news.rakeshsankar.collapsiblerecyclerviewexample.Network.Request;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Listeners.NetworkInterfaceListener;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Model.NewsPaperList;

/**
 * Created by rakesh sankar on 10/13/2017.
 */

public class NewsPaperListRequest extends BaseRequest {
    String url = "https://newsapi.org/v1/sources";
    Response.ErrorListener errorListener = null;
    NetworkInterfaceListener networkInterfaceListener;
    Activity currentActivity;

    public NewsPaperListRequest(Context context, final Activity currentActivity , NetworkInterfaceListener networkInterfaceListener) {
        super(context);
        this.networkInterfaceListener = networkInterfaceListener;
        this.currentActivity = currentActivity;
        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("Network Failure>",error.getMessage());
                Toast.makeText(currentActivity,"Network Error:"+error.getMessage(),Toast.LENGTH_LONG).show();
            }
        };
    }

    public void loadRequest(){
        GSONRequest<NewsPaperList> newsPaperListGSONRequest = new GSONRequest(Request.Method.GET,NewsPaperList.class,
                null,url,errorListener,networkInterfaceListener);
        newsPaperListGSONRequest.setRetryPolicy(defaultRetryPolicy);
        requestQueue.add(newsPaperListGSONRequest);
    }
}
