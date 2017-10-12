package com.news.rakeshsankar.collapsiblerecyclerviewexample;

/**
 * Created by rakesh sankar on 9/12/2017.
 */

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

interface APIInterface {

    @GET("/?sources=techcrunch&apiKey=be9c63bd0fa94e8b85836fed535d73d0")
    Call<NewsPaperList> doGetNewPaperResources();


    @GET("/sources")
    public Call<NewsPaperList> doGetNewPaperResourcesSource();//List<Example>

}
