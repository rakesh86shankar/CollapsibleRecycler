package com.news.rakeshsankar.collapsiblerecyclerviewexample.Network.Request;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Listeners.NetworkInterfaceListener;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Model.ArticleList;

import static com.news.rakeshsankar.collapsiblerecyclerviewexample.Views.SecondActivity.APIKey;

/**
 * Created by rakesh sankar on 10/13/2017.
 */

public class ArticleRequest extends BaseRequest {
    String url ;
    Response.ErrorListener errorListener = null;
    NetworkInterfaceListener networkInterfaceListener;
    Activity currentActivity;

    public ArticleRequest(Context context, final Activity currentActivity , NetworkInterfaceListener networkInterfaceListener) {
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

    public void loadRequest(String newsPaper,String sortBy){
        url =  "https://newsapi.org/v1/articles?source="+ newsPaper+ "&sortBy=latest&apiKey=" + APIKey;
        GSONRequest<ArticleList> articleListGSONRequest = new GSONRequest(Request.Method.GET,ArticleList.class,
                null,url,errorListener,networkInterfaceListener);
        requestQueue.add(articleListGSONRequest);
    }
}
