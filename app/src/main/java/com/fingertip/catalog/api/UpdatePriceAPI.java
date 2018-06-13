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
import com.fingertip.catalog.interfaces.UpdateCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenevo on 10-05-2018.
 */

public class UpdatePriceAPI extends BaseAPI {

    private Context mContext;
    private UpdateCallback mCallback;
    SharedPreferences.Editor editor;
    JSONObject jObj1=null;
    String price1;
    int otp;
    String cus_id;
    public UpdatePriceAPI(Context context, UpdateCallback callback) {
        super(context);
        this.mContext = context;
        this.mCallback = callback;

    }

    public void processUpdate(final String proid, final String price) {
        showProgressDialog();
        System.out.println("proid"+proid+price);

        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.UPDATEPRICEURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    jObj1 = new JSONObject(response);
                    System.out.println("loginresponse"+response);
                    if(jObj1.getString("status").equals("success"))
                    {
                        price1=jObj1.getString("price");
                        Toast.makeText(mContext, "Price updated successfully", Toast.LENGTH_SHORT).show();
                    }
                    hideProgressDialog();
//
                    mCallback.updateScreen2(price1);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(Config.TAG_UPDATE_PRICE_LIST,"Error: " + error.getMessage());
                error.printStackTrace();
                mCallback.updateScreen2("");
                hideProgressDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Config.PARAM_CATEGORY_ID, proid);
                params.put(Config.PARAM_UPDATE_PRICE, price);
                return params;
            }

        };

        // Adding request to request queue
        AppController ctrl = AppController.getInstance();
        ctrl.addToRequestQueue(strReq, Config.TAG_UPDATE_PRICE_LIST);
    }
}


