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
 * Created by lenevo on 24-05-2018.
 */

public class EditSettingsAPI extends BaseAPI {

    private Context mContext;
    private NetworkCallback mCallback;
    SharedPreferences.Editor editor;
    JSONObject jObj1 = null;
    String tele, cusname, emailid, phn;
    int otp;
    String cus_id;

    public EditSettingsAPI(Context context, NetworkCallback callback) {
        super(context);
        this.mContext = context;
        this.mCallback = callback;

    }

    public void processEditSettings(final String s1, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8) {
        showProgressDialog();

        System.out.println("statt"+s1+s2+s3+s4+s5+s6+s7+s8);
        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.EDITSETTINGSURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    jObj1 = new JSONObject(response);
                    System.out.println("loginresponse" + response);

                    hideProgressDialog();
//
                    mCallback.updateScreen(response, Config.TAG_EDIT_SETTINGSINFO);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(Config.TAG_EDIT_SETTINGSINFO, "Error: " + error.getMessage());
                error.printStackTrace();
                mCallback.updateScreen("ERROR", Config.TAG_EDIT_SETTINGSINFO);
                hideProgressDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Config.PARAM_STORE_NAME, s1);
                params.put(Config.PARAM_STORE_OWNER, s2);
                params.put(Config.PARAM_STORE_ADDRESS, s3);
                params.put(Config.PARAM_STORE_NUMBER, s4);
                params.put(Config.PARAM_STORE_EMAIL, s5);
                params.put(Config.PARAM_STORE_PAN, s6);
                params.put(Config.PARAM_STORE_GSTIN,s7);
                params.put(Config.PARAM_STORE_INVOICE,s8);



                return params;
            }

        };

        // Adding request to request queue
        AppController ctrl = AppController.getInstance();
        ctrl.addToRequestQueue(strReq, Config.TAG_EDIT_SETTINGSINFO);
    }
}


