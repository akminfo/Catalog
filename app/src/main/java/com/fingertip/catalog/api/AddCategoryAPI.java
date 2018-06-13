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
 * Created by lenevo on 09-05-2018.
 */

public class AddCategoryAPI extends BaseAPI {

    private Context mContext;
    private NetworkCallback mCallback;
    SharedPreferences.Editor editor;
    JSONObject jObj1 = null;
    String tele, cusname, emailid, phn;
    int otp;
    String cus_id;

    public AddCategoryAPI(Context context, NetworkCallback callback) {
        super(context);
        this.mContext = context;
        this.mCallback = callback;

    }

    public void processAddCategory(final String catname, final String catdesc, final String metatitle, final String metadesc, final String metakey, final String sort, final String top, final String status) {
        showProgressDialog();


        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.ADDCATEGORYURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    jObj1 = new JSONObject(response);
                    System.out.println("loginresponse" + response);

                    hideProgressDialog();
//
                    mCallback.updateScreen(response, Config.TAG_ADD_CATEGORY_LIST);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(Config.TAG_ADD_CATEGORY_LIST, "Error: " + error.getMessage());
                error.printStackTrace();
                mCallback.updateScreen("ERROR", Config.TAG_ADD_CATEGORY_LIST);
                hideProgressDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put(Config.PARAM_CATEGORY_NAME, catname);
                params.put(Config.PARAM_CATEGORY_DESC, catdesc);
                params.put(Config.PARAM_CATEGORY_META_TITLE, metatitle);
                params.put(Config.PARAM_CATEGORY_META_DESC, metadesc);
                params.put(Config.PARAM_CATEGORY_META_KEY, metakey);
                params.put(Config.PARAM_CATEGORY_SORT_ORDER, sort);
                params.put(Config.PARAM_CATEGORY_TOP, top);
                params.put(Config.PARAM_CATEGORY_STATUS, status);


                return params;
            }

        };

        // Adding request to request queue
        AppController ctrl = AppController.getInstance();
        ctrl.addToRequestQueue(strReq, Config.TAG_ADD_CATEGORY_LIST);
    }
}
