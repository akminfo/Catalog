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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenevo on 19-05-2018.
 */

public class AddCouponAPI extends BaseAPI {

    private Context mContext;
    private NetworkCallback mCallback;
    SharedPreferences.Editor editor;
    JSONObject jObj1 = null;
    String tele, cusname, emailid, phn;
    int otp;
    String cus_id;

    public AddCouponAPI(Context context, NetworkCallback callback) {
        super(context);
        this.mContext = context;
        this.mCallback = callback;

    }

    public void processAddCoupon(final String s1, final String s2, final String s3,final String s10, final String s4, final String s5, final String s6, final String s7,final String s8,final String s9) {
        showProgressDialog();


        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.ADDCOUPONURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    jObj1 = new JSONObject(response);
                    System.out.println("loginresponse" + response);

                    hideProgressDialog();
//
                    mCallback.updateScreen(response, Config.TAG_ADD_COUPON_LIST);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(Config.TAG_COUPONS_LIST, "Error: " + error.getMessage());
                error.printStackTrace();
                mCallback.updateScreen("ERROR", Config.TAG_ADD_COUPON_LIST);
                hideProgressDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put(Config.PARAM_CATEGORY_NAME, s1);
                params.put(Config.PARAM_CODE, s2);
                params.put(Config.PARAM_TYPE, s3);
                params.put(Config.PARAM_CUSTOMER_GROUP, s10);
                params.put(Config.PARAM_DISCOUNT, s4);
                params.put(Config.PARAM_TOTAL, s5);
                params.put(Config.PARAM_START, s6);
                params.put(Config.PARAM_END, s7);
                params.put(Config.PARAM_USER_COUPON, s8);
                params.put(Config.PARAM_USER_CUSTOMER, s9);


                return params;
            }

        };

        // Adding request to request queue
        AppController ctrl = AppController.getInstance();
        ctrl.addToRequestQueue(strReq, Config.TAG_ADD_COUPON_LIST);
    }
}

