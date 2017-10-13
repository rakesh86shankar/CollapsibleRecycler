package com.news.rakeshsankar.collapsiblerecyclerviewexample;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by rakesh sankar on 10/13/2017.
 */

public class TestActivity extends AppCompatActivity implements NetworkInterfaceListener<NewsPaperList> {

    public void loadData(){
        new NewsPaperListRequest(getApplicationContext(),this,this);
    }
    @Override
    public void onNetworkResponseReceived(NewsPaperList response) {

    }

    @Override
    public void onNetworkFailure(String errorResponse) {

    }
}
