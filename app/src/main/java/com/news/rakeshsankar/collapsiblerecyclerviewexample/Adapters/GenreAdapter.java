package com.news.rakeshsankar.collapsiblerecyclerviewexample.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.news.rakeshsankar.collapsiblerecyclerviewexample.Model.NewsPaperGenre;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Model.NewsPaperObject;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.R;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Views.NewPaperHolder;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Views.NewPaperTypeHolder;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Views.SecondActivity;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by rakesh sankar on 9/9/2017.
 */

public class GenreAdapter extends ExpandableRecyclerViewAdapter<NewPaperTypeHolder,NewPaperHolder> {
    Activity currentActivity;

    public GenreAdapter(List<? extends ExpandableGroup> groups,Activity currentActivity) {
        super(groups);
        this.currentActivity = currentActivity;
    }

    @Override
    public NewPaperTypeHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_newspapergenre, parent, false);
        return  new NewPaperTypeHolder(view);
    }

    @Override
    public NewPaperHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_newspaper, parent, false);
        return new NewPaperHolder(view);
    }

    @Override
    public void onBindChildViewHolder(NewPaperHolder holder, final int flatPosition, ExpandableGroup group, int childIndex) {
        final NewsPaperObject newsPaper = ((NewsPaperGenre) group).getItems().get(childIndex);
        holder.setNewsPaperName(newsPaper.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("On Child Item Clicked>>>",""+flatPosition);
                List<String> sortBy = newsPaper.getSortBysAvailable();
                String[] sortByArray = sortBy.toArray(new String[sortBy.size()]);
                sortByDialog(sortByArray,newsPaper.getId());


            }
        });

    }

    @Override
    public void onBindGroupViewHolder(NewPaperTypeHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setNewsPaperGenreTitle(group);
    }


    public void sortByDialog(String sortBy[], final String newsPaperName){
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(currentActivity);
        builderSingle.setIcon(R.drawable.ic_banjo);
        builderSingle.setTitle("Sort By:-");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(currentActivity, android.R.layout.select_dialog_singlechoice);
        for(String s : sortBy)
            arrayAdapter.add(s);


        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String sortByType = arrayAdapter.getItem(which);
                Intent myIntnent = new Intent(currentActivity,SecondActivity.class);
                myIntnent.putExtra("NewsPaper", newsPaperName);
                myIntnent.putExtra("SortBy", sortByType);
                currentActivity.startActivity(myIntnent);

            }
        });
        builderSingle.show();
    }

}
