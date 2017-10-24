package com.news.rakeshsankar.collapsiblerecyclerviewexample.Views;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by rakesh sankar on 10/23/2017.
 */

public class ProgressBarHandler {
    private ContentLoadingProgressBar mProgressBar;
    private Context mContext;

    public ProgressBarHandler(Activity currentActivity) {
        mContext = currentActivity.getApplicationContext();

        ViewGroup layout = (ViewGroup) (currentActivity).findViewById(android.R.id.content).getRootView();

        mProgressBar = new ContentLoadingProgressBar(mContext,null);
        mProgressBar.setIndeterminate(true);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        RelativeLayout rl = new RelativeLayout(mContext);

        rl.setGravity(Gravity.CENTER);
        rl.addView(mProgressBar);

        layout.addView(rl, params);

        //hide();
    }

    public void show() {
        //mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar.show();
    }

    public void hide() {
        //mProgressBar.setVisibility(View.INVISIBLE);
        mProgressBar.hide();
    }
}
