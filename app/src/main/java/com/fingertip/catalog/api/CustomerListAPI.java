package com.fingertip.catalog.api;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.fingertip.catalog.callback.AppController;
import com.fingertip.catalog.callback.Config;
import com.fingertip.catalog.interfaces.NetworkCallback;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lenevo on 18-05-2018.
 */

public class CustomerListAPI extends BaseAPI {

    private Context mContext;
    private NetworkCallback mCallback;
    SharedPreferences.Editor editor;
    JSONObject jObj1=null;
    String tele,cusname,emailid,phn;
    int otp;
    String cus_id;
    public CustomerListAPI(Context context, NetworkCallback callback) {
        super(context);
        this.mContext = context;
        this.mCallback = callback;

    }

    public void processCustomersList() {
        showProgressDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.CUSTOMERLISTURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    jObj1 = new JSONObject(response);
                    System.out.println("loginresponse"+response);

                    hideProgressDialog();
//
                    mCallback.updateScreen(response, Config.TAG_CUSTOMERS_LIST);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(Config.TAG_CUSTOMERS_LIST,"Error: " + error.getMessage());
                error.printStackTrace();
                mCallback.updateScreen("ERROR", Config.TAG_CUSTOMERS_LIST);
                hideProgressDialog();
            }
        }) {



        };

        // Adding request to request queue
        AppController ctrl = AppController.getInstance();
        ctrl.addToRequestQueue(strReq, Config.TAG_CUSTOMERS_LIST);
    }
}



