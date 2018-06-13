package com.fingertip.catalog.api;

import android.content.Context;
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
 * Created by lenevo on 19-05-2018.
 */

public class DeleteOrderAPI extends BaseAPI {

    private Context mContext;
    private NetworkCallback mCallback;

    JSONObject jObj1=null;

    public DeleteOrderAPI(Context context, NetworkCallback callback) {
        super(context);
        this.mContext = context;
        this.mCallback = callback;

    }

    public void processDelete(final String catid) {
        showProgressDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.DELETEORDERURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    jObj1 = new JSONObject(response);
                    System.out.println("loginresponse"+response);

                    if(jObj1.getString("status").equals("Successfully deleted"))
                    {
                        Toast.makeText(mContext, "Order deleted successfully", Toast.LENGTH_SHORT).show();
                    }

                    hideProgressDialog();
//
                    mCallback.updateScreen(response, Config.TAG_DELETE_ORDERS_LIST);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(Config.TAG_DELETE_ORDERS_LIST,"Error: " + error.getMessage());
                error.printStackTrace();
                mCallback.updateScreen("ERROR", Config.TAG_DELETE_ORDERS_LIST);
                hideProgressDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Config.PARAM_CATEGORY_ID,catid);

                return params;
            }

        };

        // Adding request to request queue
        AppController ctrl = AppController.getInstance();
        ctrl.addToRequestQueue(strReq, Config.TAG_DELETE_ORDERS_LIST);
    }
}



