package com.news.rakeshsankar.collapsiblerecyclerviewexample.Views;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by rakesh sankar on 10/13/2017.
 */

public class BaseActivity extends AppCompatActivity {
    ProgressBarHandler progressBarHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("Base Activity","OKKOKOMK");
        progressBarHandler = new ProgressBarHandler(this);
    }

    public void showToastDialog(String message){
        Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                BaseActivity.this.finish();
            }
        }, 2000);
    }

    public ProgressBarHandler getProgressBar(){
        return progressBarHandler;
    }

    public void showProgressDialog(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(progressBarHandler!=null)
                    progressBarHandler.show();
            }
        },500);
    }

    public void dismissProgressDialog(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(progressBarHandler!=null)
                    progressBarHandler.hide();
            }
        }, 500);
    }

}
