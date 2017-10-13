package com.news.rakeshsankar.collapsiblerecyclerviewexample;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by rakesh sankar on 10/13/2017.
 */

public class NewsPaperListRequest extends BaseRequest {
    String url = "https://newsapi.org/v1/sources";
    Response.ErrorListener errorListener = null;
    NetworkInterfaceListener networkInterfaceListener;
    Activity currentActivity;

    NewsPaperListRequest(Context context, final Activity currentActivity , NetworkInterfaceListener networkInterfaceListener) {
        super(context);
        this.networkInterfaceListener = networkInterfaceListener;
        this.currentActivity = currentActivity;
        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(currentActivity,"Network Error:"error.getMessage(),Toast.LENGTH_LONG);
            }
        };
    }

    public void loadRequest(){
        GSONRequest<NewsPaperList> newsPaperListGSONRequest = new GSONRequest(Request.Method.GET,NewsPaperList.class,
                null,url,errorListener,networkInterfaceListener);
    }
}
