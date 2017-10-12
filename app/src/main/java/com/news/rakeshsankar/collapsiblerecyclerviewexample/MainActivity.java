package com.news.rakeshsankar.collapsiblerecyclerviewexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
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
        downloadData();
    }

    public void downloadData(){

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String url = "https://newsapi.org/v1/sources";
        final JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // TODO Auto-generated method stub
                Log.v("JSON Response Volley",response.toString());
                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();
                NewsPaperList newsPaperList = mGson.fromJson(String.valueOf(response),NewsPaperList.class);
                Log.v("NewsPaperObject>>",newsPaperList.getSources().toString());

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
                Log.v("Toc check","ok");
                Log.v("NewPaperList check",""+newsPaperList1.size());

                for ( Map.Entry<String, NewsPaperGenre> entry : newsPaperCollection.entrySet()) {
                    String key = entry.getKey();
                    NewsPaperGenre genre = entry.getValue();
                    genres.add(genre);
                    // do something with key and/or tab
                }
                updateView();

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.v("JSON Response Volley error",error.toString());
            }
        });
        queue.add(jsObjRequest);
    }

    public void updateView(){
        //instantiate your adapter with the list of genres
        GenreAdapter adapter = new GenreAdapter(genres,MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}
