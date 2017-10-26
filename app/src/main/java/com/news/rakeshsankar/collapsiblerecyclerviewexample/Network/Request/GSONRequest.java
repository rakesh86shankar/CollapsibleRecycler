package com.news.rakeshsankar.collapsiblerecyclerviewexample.Network.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Listeners.NetworkInterfaceListener;
import com.news.rakeshsankar.collapsiblerecyclerviewexample.Listeners.NetworkRequestCallBackListener;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rakesh sankar on 10/13/2017.
 */

public class GSONRequest<T> extends Request<T>{

    private final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    private final Class<T> clazz;
    private final Map<String, String> headers;
    //private final Response.Listener<T> listener;
    private static final String ERROR_CHARSET = "UTF-8";
    private Map<String, String> responseHeaders = new HashMap<>();
    private boolean responseModified = true;
    int TIMEOUT = 2000;
    NetworkInterfaceListener<T> listener;
    NetworkRequestCallBackListener<T> networkRequestCallBackListener;
    DefaultRetryPolicy defaultRetryPolicy = new DefaultRetryPolicy(TIMEOUT,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    public GSONRequest(int method,Class<T> clazz, Map<String, String> headers,
                       String url, Response.ErrorListener listener,
                       NetworkInterfaceListener<T> sucessListener,
                       NetworkRequestCallBackListener<T> networkRequestCallBackListener) {
        super(method, url, listener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = sucessListener;
        this.networkRequestCallBackListener = networkRequestCallBackListener;
        this.setRetryPolicy(defaultRetryPolicy);
    }

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(
                    gson.fromJson(json, clazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onNetworkResponseReceived(response);
        networkRequestCallBackListener.onResponseReceived(response);
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        String message = null;
        if (volleyError instanceof NetworkError) {
            message = "Cannot connect to Internet...Please check your connection!";
        } else if (volleyError instanceof ServerError) {
            message = "The server could not be found. Please try again after some time!!";
        } else if (volleyError instanceof AuthFailureError) {
            message = "Cannot connect to Internet...Please check your connection!";
        } else if (volleyError instanceof ParseError) {
            message = "Parsing error! Please try again after some time!!";
        } else if (volleyError instanceof NoConnectionError) {
            message = "Cannot connect to Internet...Please check your connection!";
        } else if (volleyError instanceof TimeoutError) {
            message = "Connection TimeOut! Please check your internet connection.";
        }
        return super.parseNetworkError(volleyError);
    }
}
