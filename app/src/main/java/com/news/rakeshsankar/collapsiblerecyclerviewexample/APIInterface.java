package com.news.rakeshsankar.collapsiblerecyclerviewexample;

/**
 * Created by rakesh sankar on 9/12/2017.
 */

import com.news.rakeshsankar.collapsiblerecyclerviewexample.Model.NewsPaperList;

import retrofit2.Call;
import retrofit2.http.GET;

interface APIInterface {

    @GET("/?sources=techcrunch&apiKey=be9c63bd0fa94e8b85836fed535d73d0")
    Call<NewsPaperList> doGetNewPaperResources();


    @GET("/sources")
    public Call<NewsPaperList> doGetNewPaperResourcesSource();//List<Example>

}
