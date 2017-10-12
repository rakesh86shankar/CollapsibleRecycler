package com.news.rakeshsankar.collapsiblerecyclerviewexample;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by rakesh sankar on 9/9/2017.
 */

public class NewsPaperGenre extends ExpandableGroup<NewsPaperObject> {
    private String title;

    public List<NewsPaperObject> getPaperObjects() {
        return paperObjects;
    }

    public void setPaperObjects(List<NewsPaperObject> paperObjects) {
        this.paperObjects = paperObjects;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private List<NewsPaperObject> paperObjects;

    public NewsPaperGenre(String papertitle, List<NewsPaperObject> items) {
        super(papertitle, items);
        title = papertitle;
        paperObjects = items;
    }

}
