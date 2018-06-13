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

/**
 * Created by lenevo on 22-05-2018.
 */

public class UserGroupAPI extends BaseAPI {

    private Context mContext;
    private NetworkCallback mCallback;
    SharedPreferences.Editor editor;
    JSONObject jObj1=null;
    String tele,cusname,emailid,phn;
    int otp;
    String cus_id;
    public UserGroupAPI(Context context, NetworkCallback callback) {
        super(context);
        this.mContext = context;
        this.mCallback = callback;

    }

    public void processUserGroupList() {
      showProgressDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.USERGROUPURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    jObj1 = new JSONObject(response);
                    System.out.println("loginresponse"+response);

                     hideProgressDialog();
//
                    mCallback.updateScreen(response, Config.TAG_USER_GROUP_LIST);

                } catch (JSONException e) {
                    hideProgressDialog();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(Config.TAG_USER_GROUP_LIST,"Error: " + error.getMessage());
                error.printStackTrace();
                mCallback.updateScreen("ERROR", Config.TAG_USER_GROUP_LIST);
                Toast.makeText(mContext,"Bad internet connection",Toast.LENGTH_SHORT).show();
              hideProgressDialog();
            }
        }) {



        };

        // Adding request to request queue
        AppController ctrl = AppController.getInstance();
        ctrl.addToRequestQueue(strReq, Config.TAG_USER_GROUP_LIST);
    }
}





