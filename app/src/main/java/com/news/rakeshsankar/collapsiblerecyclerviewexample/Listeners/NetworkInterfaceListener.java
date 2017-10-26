package com.news.rakeshsankar.collapsiblerecyclerviewexample.Listeners;



/**
 * Created by rakesh sankar on 10/13/2017.
 */

public interface NetworkInterfaceListener<T> {

    public void onNetworkResponseReceived(T response);


    public void onNetworkFailure(String errorResponse);

}
