package com.news.rakeshsankar.collapsiblerecyclerviewexample.Listeners;

/**
 * Created by rakesh sankar on 10/26/2017.
 */

public interface NetworkRequestCallBackListener<T> {
    public void onResponseReceived(T response);
}
