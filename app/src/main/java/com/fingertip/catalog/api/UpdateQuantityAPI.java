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
import com.fingertip.catalog.interfaces.UpdateCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenevo on 10-05-2018.
 */

public class UpdateQuantityAPI extends BaseAPI {

    private Context mContext;
    private UpdateCallback mCallback;
    SharedPreferences.Editor editor;
    JSONObject jObj1=null;
    String quantity;
    int otp;
    String cus_id;
    public UpdateQuantityAPI(Context context, UpdateCallback callback) {
        super(context);
        this.mContext = context;
        this.mCallback = callback;

    }

    public void processUpdateQuant(final String proid, final String price) {
        showProgressDialog();
        System.out.println("proid"+proid+price);

        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.UPDATEQUANTITYURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    jObj1 = new JSONObject(response);
                    System.out.println("loginresponse"+response);
                    if(jObj1.getString("status").equals("success"))
                    {
                        quantity=jObj1.getString("quantity");
                        Toast.makeText(mContext, "Quantity updated successfully", Toast.LENGTH_SHORT).show();
                    }
                    hideProgressDialog();
//
                    mCallback.updateScreen2(quantity);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(Config.TAG_UPDATE_QUANTITY_LIST,"Error: " + error.getMessage());
                error.printStackTrace();
                mCallback.updateScreen2("");
                hideProgressDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Config.PARAM_CATEGORY_ID, proid);
                params.put(Config.PARAM_UPDATE_QUANTITY, price);
                return params;
            }

        };

        // Adding request to request queue
        AppController ctrl = AppController.getInstance();
        ctrl.addToRequestQueue(strReq, Config.TAG_UPDATE_QUANTITY_LIST);
    }
}


