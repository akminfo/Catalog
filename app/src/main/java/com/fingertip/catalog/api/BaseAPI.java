package com.fingertip.catalog.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;

import com.fingertip.catalog.R;


/**
 * Created by spidey on 5/23/2016.
 */
public class BaseAPI {

    private ProgressDialog pDialog;

    public BaseAPI(Context context){
        try {
//
            pDialog = new ProgressDialog(context, R.style.MyTheme);

            pDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            pDialog.setCancelable(false);
            pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }




    protected void showProgressDialog() {
        if(pDialog!=null) {
            if (!pDialog.isShowing())
                pDialog.show();
        }

    }

    protected void hideProgressDialog() {
        if (pDialog != null) {
            if (pDialog.isShowing())
                pDialog.hide();
//        pDialog=null;
        }
    }
}
