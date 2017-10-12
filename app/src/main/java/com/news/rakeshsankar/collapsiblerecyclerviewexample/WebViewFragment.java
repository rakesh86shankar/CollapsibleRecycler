package com.news.rakeshsankar.collapsiblerecyclerviewexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * Created by rakesh sankar on 10/9/2017.
 */

public class WebViewFragment extends Fragment {
    int position = 0;
    String webViewURL;

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
        webView.loadUrl(webViewURL);
        return view;
    }


}
