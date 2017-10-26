package com.news.rakeshsankar.collapsiblerecyclerviewexample.Network.Request;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Listeners.NetworkInterfaceListener;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Listeners.NetworkRequestCallBackListener;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Model.ArticleList;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Views.BaseActivity;

import static com.news.rakeshsankar.collapsiblerecyclerviewexample.Views.SecondActivity.APIKey;

/**
 * Created by rakesh sankar on 10/13/2017.
 */

public class ArticleRequest extends BaseRequest implements NetworkRequestCallBackListener<Object>{
    String url ;
    Response.ErrorListener errorListener = null;
    NetworkInterfaceListener networkInterfaceListener;
    BaseActivity currentActivity;

    public ArticleRequest(Context context, final BaseActivity
            currentActivity , final NetworkInterfaceListener networkInterfaceListener) {
        super(context);
        this.networkInterfaceListener = networkInterfaceListener;
        this.currentActivity = currentActivity;
        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                 //networkInterfaceListener.onNetworkFailure(error.toString());
                Log.v("ArticleRequest Failure>",error.toString());
                currentActivity.showToastDialog(error.toString());
            }
        };
    }

    public void loadRequest(String newsPaper,String sortBy){
        url =  "https://newsapi.org/v1/articles?source="+ newsPaper+ "&sortBy=latest&apiKey=" + APIKey;
        GSONRequest<ArticleList> articleListGSONRequest = new GSONRequest(Request.Method.GET,ArticleList.class,
                null,url,errorListener,networkInterfaceListener,this);
        requestQueue.add(articleListGSONRequest);
        currentActivity.showProgressDialog();
    }

    @Override
    public void onResponseReceived(Object  response) {
        currentActivity.dismissProgressDialog();
    }
}
