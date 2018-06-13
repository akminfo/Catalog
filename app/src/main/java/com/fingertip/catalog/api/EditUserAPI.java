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

public class EditUserAPI extends BaseAPI {

    private Context mContext;
    private NetworkCallback mCallback;
    SharedPreferences.Editor editor;
    JSONObject jObj1 = null;
    String tele, cusname, emailid, phn;
    int otp;
    String cus_id;

    public EditUserAPI(Context context, NetworkCallback callback) {
        super(context);
        this.mContext = context;
        this.mCallback = callback;

    }

    public void processEditUser(final String s1, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9,final String s10,final String s11) {
        showProgressDialog();

        System.out.println("statt"+s1+s2+s3+s4+s5+s6+s7+s8+s9+s10+s11);
        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.EDITUSERURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    jObj1 = new JSONObject(response);
                    System.out.println("loginresponse" + response);

                    hideProgressDialog();
//
                    mCallback.updateScreen(response, Config.TAG_EDIT_USERLIST);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(Config.TAG_EDIT_USERLIST, "Error: " + error.getMessage());
                error.printStackTrace();
                mCallback.updateScreen("ERROR", Config.TAG_EDIT_USERLIST);
                hideProgressDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Config.PARAM_CATEGORY_ID, s1);
                params.put(Config.PARAM_USER_NAME, s2);
                params.put(Config.PARAM_USER_GROUP, s3);
                params.put(Config.PARAM_FIRST_NAME, s4);
                params.put(Config.PARAM_LAST_NAME, s5);
                params.put(Config.PARAM_GENDER, s6);
                params.put(Config.PARAM_EMAIL,s7);
                params.put(Config.PARAM_MOBILE,s8);
                params.put(Config.PARAM_USER_PASS,s9);
                params.put(Config.PARAM_CATEGORY_STATUS,s10);
                params.put(Config.PARAM_GSTIN,s11);


                return params;
            }

        };

        // Adding request to request queue
        AppController ctrl = AppController.getInstance();
        ctrl.addToRequestQueue(strReq, Config.TAG_EDIT_USERLIST);
    }
}


