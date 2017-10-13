package com.news.rakeshsankar.collapsiblerecyclerviewexample.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.news.rakeshsankar.collapsiblerecyclerviewexample.Model.Article;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Views.WebViewFragment;

import java.util.List;

/**
 * Created by rakesh sankar on 10/9/2017.
 */

public class WebViewAdapter extends FragmentStatePagerAdapter{
    List<Article> articleList;

    public WebViewAdapter(FragmentManager fragmentManager, List<Article> articleList){
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
