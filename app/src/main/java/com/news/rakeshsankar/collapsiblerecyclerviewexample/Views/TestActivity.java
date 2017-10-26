package com.news.rakeshsankar.collapsiblerecyclerviewexample.Views;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.ContentLoadingProgressBar;

import com.news.rakeshsankar.collapsiblerecyclerviewexample.R;

/**
 * Created by rakesh sankar on 10/23/2017.
 */

public class TestActivity extends BaseActivity {
    ProgressBarHandler progressBarHandler;
    ContentLoadingProgressBar contentLoadingProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_holder);
        //contentLoadingProgressBar = new ContentLoadingProgressBar(TestActivity.this);
        progressBarHandler = new ProgressBarHandler(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBarHandler.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBarHandler.hide();
            }
        }, 5000);
    }
}
