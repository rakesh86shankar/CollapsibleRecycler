package com.news.rakeshsankar.collapsiblerecyclerviewexample.Views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.news.rakeshsankar.collapsiblerecyclerviewexample.R;

/**
 * Created by rakesh sankar on 10/9/2017.
 */

public class WebViewFragment extends Fragment {
    int position = 0;
    String webViewURL;
    ContentLoadingProgressBar contentLoadingProgressBar;

    public static WebViewFragment newInstance(int page, String title) {
        WebViewFragment fragmentFirst = new WebViewFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("someInt", 0);
        webViewURL = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_webview_fragment, container, false);
        WebView webView = (WebView) view.findViewById(R.id.webView);
        contentLoadingProgressBar = (ContentLoadingProgressBar) view.findViewById(R.id.address_looking_up);
        contentLoadingProgressBar.show();
        webView.loadUrl(webViewURL);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                contentLoadingProgressBar.hide();
            }
        });
        return view;
    }


}
