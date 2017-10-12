package com.news.rakeshsankar.collapsiblerecyclerviewexample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by rakesh sankar on 10/9/2017.
 */

public class WebViewAdapter extends FragmentStatePagerAdapter{
    List<Article> articleList;

    WebViewAdapter(FragmentManager fragmentManager, List<Article> articleList){
        super(fragmentManager);
        this.articleList = articleList;
    }

    @Override
    public Fragment getItem(int position) {
        return WebViewFragment.newInstance(position, articleList.get(position).getUrl());
    }

    @Override
    public int getCount() {
        return articleList.size();
    }
}
