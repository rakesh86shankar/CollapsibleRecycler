package com.news.rakeshsankar.collapsiblerecyclerviewexample;

import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

/**
 * Created by rakesh sankar on 10/9/2017.
 */

public class ThirdActivity extends AppCompatActivity {
    FragmentStatePagerAdapter adapterViewPager;
    int position;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_holder);
        Bundle b = getIntent().getExtras();
        ArticleListArray articleListArray = b.getParcelable("Arrays");
        position = b.getInt("position");
        List<Article> articleList = articleListArray.getArticlesList();
        System.out.println(articleListArray.articlesList.size());
        ViewPager vpPager = (ViewPager) findViewById(R.id.viewPager);
        adapterViewPager = new WebViewAdapter(getSupportFragmentManager(), articleList);
        vpPager.setAdapter(adapterViewPager);
        vpPager.setCurrentItem(position, true);
        adapterViewPager.notifyDataSetChanged();
    }


}
