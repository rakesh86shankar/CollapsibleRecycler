package com.news.rakeshsankar.collapsiblerecyclerviewexample;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

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
    NetworkInterfaceListener<T> listener;

    public GSONRequest(int method,Class<T> clazz, Map<String, String> headers,
                       String url, Response.ErrorListener listener,NetworkInterfaceListener<T> sucessListener) {
        super(method, url, listener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = sucessListener;
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
    }


}
