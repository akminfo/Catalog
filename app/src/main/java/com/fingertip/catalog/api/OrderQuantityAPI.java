package com.fingertip.catalog.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenevo on 28-05-2018.
 */

public class OrderQuantityAPI  extends BaseAPI {

    private Context mContext;
    private NetworkCallback mCallback;
    SharedPreferences.Editor editor;
    JSONObject jObj1=null;
    String price1;
    int otp;
    String cus_id;
    public OrderQuantityAPI(Context context, NetworkCallback callback) {
        super(context);
        this.mContext = context;
        this.mCallback = callback;

    }

    public void processUpdateQuantity(final String s1,final String s2,final String s3,final String s4,final String s5,final String s6) {
        showProgressDialog();
        System.out.println("fullrequest"+s1+s2+s3+s4+s5+s6);


        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.ORDERQUANTITYURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    jObj1 = new JSONObject(response);
                    System.out.println("loginresponse"+response);
                    if(jObj1.getString("status").equals("Success"))
                    {
                        Toast.makeText(mContext, "Quantity updated successfully", Toast.LENGTH_SHORT).show();
                    }
                    hideProgressDialog();
//
                    mCallback.updateScreen(response, Config.TAG_ORDERUPDATELIST);

                } catch (JSONException e) {
                    hideProgressDialog();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(Config.TAG_ORDERUPDATELIST,"Error: " + error.getMessage());
                error.printStackTrace();
                mCallback.updateScreen("Error", Config.TAG_ORDERUPDATELIST);
                hideProgressDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Config.PARAM_ORDER_ID, s1);
                params.put(Config.PARAM_PRODUCT_ID, s2);
                params.put(Config.PARAM_UPDATE_QUANTITY, s3);
                params.put(Config.PARAM_UPDATE_PRICE, s4);
                params.put(Config.PARAM_PRODUCT_TOTAL, s5);
                params.put(Config.PARAM_ORDER_TOTAL, s6);




                return params;
            }

        };

        // Adding request to request queue
        AppController ctrl = AppController.getInstance();
        ctrl.addToRequestQueue(strReq, Config.TAG_ORDERUPDATELIST);
    }
}



