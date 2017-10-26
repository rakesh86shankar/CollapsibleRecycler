package com.news.rakeshsankar.collapsiblerecyclerviewexample.Network.Request;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Listeners.NetworkInterfaceListener;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Listeners.NetworkRequestCallBackListener;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Model.NewsPaperList;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Views.BaseActivity;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Views.ProgressBarHandler;

/**
 * Created by rakesh sankar on 10/13/2017.
 */

public class NewsPaperListRequest extends BaseRequest implements NetworkRequestCallBackListener<NewsPaperList> {
    String url = "https://newsapi.org/v1/sources";
    Response.ErrorListener errorListener = null;
    NetworkInterfaceListener networkInterfaceListener;
    BaseActivity currentActivity;
    ProgressBarHandler progressBarHandler;

    public NewsPaperListRequest(Context context, final BaseActivity currentActivity , NetworkInterfaceListener networkInterfaceListener) {
        super(context);
        this.networkInterfaceListener = networkInterfaceListener;
        this.currentActivity = currentActivity;
        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("NewsPaperRequest Failure>",error.toString());
                currentActivity.showToastDialog(error.toString());
            }
        };
    }

    public void loadRequest(){
        GSONRequest<NewsPaperList> newsPaperListGSONRequest = new GSONRequest(Request.Method.GET,NewsPaperList.class,
                null,url,errorListener,networkInterfaceListener,this);
        newsPaperListGSONRequest.setRetryPolicy(defaultRetryPolicy);
        requestQueue.add(newsPaperListGSONRequest);
        currentActivity.showProgressDialog();
    }



    public void onNetworkFailure(String errorResponse) {
        currentActivity.dismissProgressDialog();
    }

    @Override
    public void onResponseReceived(NewsPaperList response) {
        currentActivity.dismissProgressDialog();
    }
}
