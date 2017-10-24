package com.news.rakeshsankar.collapsiblerecyclerviewexample.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Adapters.NewsPaperArticlesAdapter;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Listeners.NetworkInterfaceListener;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Listeners.RecyclerViewClickListener;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Model.Article;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Model.ArticleList;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Model.ArticleListArray;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Network.NetworkLoader;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Network.Request.ArticleRequest;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.R;

import java.util.List;

/**
 * Created by rakesh sankar on 10/3/2017.
 */

public class SecondActivity extends BaseActivity implements RecyclerViewClickListener,NetworkInterfaceListener<ArticleList> {
    public static String APIKey = "be9c63bd0fa94e8b85836fed535d73d0";
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    RequestQueue queue ;
    List<Article> currentArticles = null;

    @Override
    public void recyclerViewListClicked(View v, int position) {
        Log.v("On Recycler View Clicked>>",""+position);
        Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
        ArticleListArray articleListArray = new ArticleListArray();
        articleListArray.setArticlesList(currentArticles);
        intent.putExtra("Arrays", articleListArray);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    @Override
       protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        recyclerView = (RecyclerView) findViewById(R.id.list_view);
        layoutManager = new LinearLayoutManager(this);
        downloadData(getIntent().getExtras().get("NewsPaper").toString(),"Latest");
    }

    private void downloadData(String newsPaper,String sortBy) {
        new ArticleRequest(getApplicationContext(),SecondActivity.this,this).loadRequest(newsPaper, sortBy);
   /*   queue = Volley.newRequestQueue(SecondActivity.this);
        String url = "https://newsapi.org/v1/articles?source=" + newsPaper*//*the-next-web*//* + "&sortBy=latest&apiKey=" + APIKey;
        //be9c63bd0fa94e8b85836fed535d73d0";
        final JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // TODO Auto-generated method stub
                Log.v("JSON Response Volley",response.toString());
                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();
                ArticleList articleList = mGson.fromJson(String.valueOf(response),ArticleList.class);
                Log.v("NewsPaperObject>>",articleList.getArticles().toString());
                List<Article> article =  articleList.getArticles();

                for(int i = 0 ; i <article.size() ; i++){
                    Log.v("Toc check",article.get(i).getTitle());

                }
                updateView(articleList.getArticles(),queue);

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.v("JSON Response Volley error",error.toString());
            }
        });
        NetworkLoader.getInstance(getApplicationContext()).addToRequestQueue(jsObjRequest,"Second");*/
    }

    public void updateView(List<Article> articles,RequestQueue queue){
        //instantiate your adapter with the list of genres
        currentArticles = articles;
        NewsPaperArticlesAdapter adapter = new NewsPaperArticlesAdapter(articles,queue,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(SecondActivity.this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onNetworkResponseReceived(ArticleList response) {
        Log.v("NewsPaperObject>>",response.getArticles().toString());
        List<Article> article =  response.getArticles();

        for(int i = 0 ; i <article.size() ; i++){
            Log.v("Toc check",article.get(i).getTitle());

        }
        updateView(response.getArticles(), NetworkLoader.getInstance(getApplicationContext()).getRequestQueue());
    }

    @Override
    public void onNetworkFailure(String errorResponse) {
        //Toast.makeText(SecondActivity.this,"Network Error:"+errorResponse,Toast.LENGTH_LONG).show();
        showToastDialog(errorResponse);
    }
}
