package com.news.rakeshsankar.collapsiblerecyclerviewexample;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by rakesh sankar on 10/3/2017.
 */

public class NewsPaperArticlesAdapter extends RecyclerView.Adapter<NewsPaperArticlesAdapter.ViewHolder> {
    List<Article> articleList;
    ImageLoader mImageLoader;
    RecyclerViewClickListener clickListener;

    public NewsPaperArticlesAdapter(List<Article> articleList, RequestQueue requestQueue, RecyclerViewClickListener clickListener) {
        this.articleList = articleList;
        mImageLoader = new ImageLoader(requestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.article_list_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Article article = articleList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.recyclerViewListClicked(v, position);
            }
        });
        try{
            if(article.getUrlToImage() !=null){
                Log.v("Image URL>>",article.getUrlToImage());
                if(holder.imageView!=null)
                    holder.imageView.setImageUrl(article.getUrlToImage(),mImageLoader);
            }

        }catch (Exception e){
            Log.v("Exception loading Image View>>",e.getMessage());
        }

            holder.txtHeaderTitle.setText(article.getTitle());
            holder.txtFooterDesc.setText(article.getDescription());



    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public NetworkImageView imageView;
        public TextView txtHeaderTitle;
        public TextView txtFooterDesc;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            imageView = (NetworkImageView)v.findViewById(R.id.imageView);
            txtHeaderTitle = (TextView) v.findViewById(R.id.titleView);
            txtFooterDesc = (TextView) v.findViewById(R.id.descView);
        }


    }
}
