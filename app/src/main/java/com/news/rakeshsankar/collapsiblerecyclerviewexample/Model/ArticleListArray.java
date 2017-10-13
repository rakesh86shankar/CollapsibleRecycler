package com.news.rakeshsankar.collapsiblerecyclerviewexample.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ArticleListArray implements Parcelable {
    public static final Creator<ArticleListArray> CREATOR = new Creator<ArticleListArray>() {
        @Override
        public ArticleListArray createFromParcel(Parcel in) {
            return new ArticleListArray(in);
        }

        @Override
        public ArticleListArray[] newArray(int size) {
            return new ArticleListArray[size];
        }
    };
    List<Article> articlesList = new ArrayList<Article>();


    public ArticleListArray() {
        // initialization
        articlesList = new ArrayList<Article>();
    }

    protected ArticleListArray(Parcel in) {
        articlesList = in.createTypedArrayList(Article.CREATOR);
    }

    public List<Article> getArticlesList() {
        return articlesList;
    }

    public void setArticlesList(List<Article> articlesList) {
        this.articlesList = articlesList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(articlesList);
    }
}
