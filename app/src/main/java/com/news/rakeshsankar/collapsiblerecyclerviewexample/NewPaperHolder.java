package com.news.rakeshsankar.collapsiblerecyclerviewexample;

import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;


/**
 * Created by rakesh sankar on 9/9/2017.
 */

public class NewPaperHolder extends ChildViewHolder {

    private TextView childTextView;
    private ImageView imageView;

    public NewPaperHolder(View itemView) {
        super(itemView);
        childTextView = (TextView) itemView.findViewById(R.id.list_item_artist_name);
        imageView = (ImageView)itemView.findViewById(R.id.list_item_genre_icon);
    }

    public void setNewsPaperName(String name) {
        childTextView.setText(name);
    }

}
