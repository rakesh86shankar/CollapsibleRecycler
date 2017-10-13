package com.news.rakeshsankar.collapsiblerecyclerviewexample.Views;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.news.rakeshsankar.collapsiblerecyclerviewexample.Adapters.GenreAdapter;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Listeners.NetworkInterfaceListener;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Model.NewsPaperGenre;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Model.NewsPaperList;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Model.NewsPaperObject;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Network.Request.NewsPaperListRequest;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity implements NetworkInterfaceListener<NewsPaperList> {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<NewsPaperGenre> genres = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL_LIST));
        layoutManager = new LinearLayoutManager(this);
        new NewsPaperListRequest(getApplicationContext(),MainActivity.this,this).loadRequest();
    }

    public void downloadData(NewsPaperList newsPaperList){
        HashMap<String,NewsPaperGenre> newsPaperCollection = new HashMap<>();
        List<NewsPaperObject> newsPaperList1 =  newsPaperList.getSources();

        for(int i = 0 ; i <newsPaperList1.size() ; i++){
            NewsPaperObject newsPaperObject = newsPaperList1.get(i);
            NewsPaperGenre value = newsPaperCollection.get(newsPaperObject.getCategory());
            if (value != null) {
                value.getPaperObjects().add(newsPaperObject);
            } else {
                List<NewsPaperObject> newsPaperType = new ArrayList<>() ;
                newsPaperType.add(newsPaperObject);
                NewsPaperGenre newGenre = new NewsPaperGenre(newsPaperObject.getCategory(),newsPaperType);
                newsPaperCollection.put(newsPaperObject.getCategory(),newGenre);
            }
        }
        for ( Map.Entry<String, NewsPaperGenre> entry : newsPaperCollection.entrySet()) {
            String key = entry.getKey();
            NewsPaperGenre genre = entry.getValue();
            genres.add(genre);
        }
        updateView();
    }

    public void updateView(){
        //instantiate your adapter with the list of genres
        GenreAdapter adapter = new GenreAdapter(genres,MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onNetworkResponseReceived(NewsPaperList newsPaperList) {
        Log.v("On NetworkResponseReceived>>>","sucess");
        downloadData(newsPaperList);
    }

    @Override
    public void onNetworkFailure(String errorResponse) {
        Log.v("On NetworkFailure>>",""+errorResponse);
    }
}
