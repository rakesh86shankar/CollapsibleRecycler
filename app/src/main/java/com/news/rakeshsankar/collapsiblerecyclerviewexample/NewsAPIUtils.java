package com.news.rakeshsankar.collapsiblerecyclerviewexample;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by rakesh sankar on 10/23/2017.
 */

public class NewsAPIUtils {

    public static void alertDialogShow(Context context, String message)
    {
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setMessage(message);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public static void alertToast(String message)
    {

        Context applicationContext = NewsApp.getInstance().getApplicationContext();
        Toast.makeText(applicationContext, message,Toast.LENGTH_LONG).show();
        //Toast.makeText(SecondActivity.this,"Network Error:"+errorResponse,Toast.LENGTH_LONG).show();
    }
}
