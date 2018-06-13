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
 * Created by lenevo on 11-05-2018.
 */

public class PercentageupdateAPI extends BaseAPI {

    private Context mContext;
    private NetworkCallback mCallback;
    SharedPreferences.Editor editor;
    JSONObject jObj1 = null;
    String tele, cusname, emailid, phn;
    int otp;
    String cus_id;

    public PercentageupdateAPI(Context context, NetworkCallback callback) {
        super(context);
        this.mContext = context;
        this.mCallback = callback;

    }

    public void processUpdatePercent(final String proid, final String percentage) {
        showProgressDialog();

        System.out.println("idsss"+proid+percentage);
        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.PERCENTAGEUPDATEURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    jObj1 = new JSONObject(response);
                    System.out.println("loginresponse" + response);
                    if(jObj1.getString("status").equals("successfully updated"))
                    {
                        Toast.makeText(mContext, "Price updated successfully", Toast.LENGTH_SHORT).show();
                    }

                    hideProgressDialog();
//
                    mCallback.updateScreen(response, Config.TAG_PERCENTAGE_LIST);

                } catch (JSONException e) {
                    hideProgressDialog();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(Config.TAG_PERCENTAGE_LIST, "Error: " + error.getMessage());
                error.printStackTrace();
                mCallback.updateScreen("ERROR", Config.TAG_PERCENTAGE_LIST);
                hideProgressDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put(Config.PARAM_CATEGORY_ID, String.valueOf(proid));
                params.put(Config.PARAM_PERCENTAGE, percentage);



                return params;
            }

        };

        // Adding request to request queue
        AppController ctrl = AppController.getInstance();
        ctrl.addToRequestQueue(strReq, Config.TAG_PERCENTAGE_LIST);
    }
}

