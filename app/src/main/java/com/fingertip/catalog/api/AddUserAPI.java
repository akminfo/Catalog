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
 * Created by lenevo on 23-05-2018.
 */

public class AddUserAPI extends BaseAPI {

    private Context mContext;
    private NetworkCallback mCallback;
    SharedPreferences.Editor editor;
    JSONObject jObj1 = null;
    String tele, cusname, emailid, phn;
    int otp;
    String cus_id;

    public AddUserAPI(Context context, NetworkCallback callback) {
        super(context);
        this.mContext = context;
        this.mCallback = callback;

    }

    public void processAddUser(final String s1, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9,final String s10) {
        showProgressDialog();

        System.out.println("statt"+s2);
        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.ADDUSERURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    jObj1 = new JSONObject(response);
                    System.out.println("loginresponse" + response);

                    hideProgressDialog();
//
                    mCallback.updateScreen(response, Config.TAG_ADD_USERLIST);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(Config.TAG_ADD_USERLIST, "Error: " + error.getMessage());
                error.printStackTrace();
                mCallback.updateScreen("ERROR", Config.TAG_ADD_USERLIST);
                hideProgressDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put(Config.PARAM_USER_NAME, s1);
                params.put(Config.PARAM_USER_GROUP, s2);
                params.put(Config.PARAM_FIRST_NAME, s3);
                params.put(Config.PARAM_LAST_NAME, s4);
                params.put(Config.PARAM_GENDER, s5);
                params.put(Config.PARAM_EMAIL,s6);
                params.put(Config.PARAM_MOBILE,s7);
                params.put(Config.PARAM_USER_PASS,s8);
                params.put(Config.PARAM_CATEGORY_STATUS,s9);
                params.put(Config.PARAM_GSTIN,s10);


                return params;
            }

        };

        // Adding request to request queue
        AppController ctrl = AppController.getInstance();
        ctrl.addToRequestQueue(strReq, Config.TAG_ADD_USERLIST);
    }
}


