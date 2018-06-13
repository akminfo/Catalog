package com.fingertip.catalog;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.fingertip.catalog.api.LoginAPI;
import com.fingertip.catalog.api.UserGroupAPI;
import com.fingertip.catalog.callback.AppController;
import com.fingertip.catalog.callback.Config;
import com.fingertip.catalog.interfaces.NetworkCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lenevo on 30-04-2018.
 */

public class LoginActivity extends Activity implements NetworkCallback{
    Spinner spinner;
    EditText editText,editText1;
    SharedPreferences.Editor editor;
String title;
    String cusname,cusid,cusname1;
    JSONObject jsonObject=null;
    ArrayAdapter<String> spinnerArrayAdapter1;
    ArrayList<String> levelarray = new ArrayList<String>();
int arraylength1=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        spinner=(Spinner)findViewById(R.id.levelspinner);
        levelarray.add("Please select level");



        editText=(EditText)findViewById(R.id.username);
        editText1=(EditText)findViewById(R.id.password);

        spinnerArrayAdapter1 = new ArrayAdapter<String>(LoginActivity.this, R.layout.spinnertextview, levelarray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinnerArrayAdapter1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                title=spinner.getSelectedItem().toString();
                if (position != 0) {
                    getCusId(title);
                    System.out.println("iddd" + cusid);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        getUserGroup();

    }
    public void getCusId(final String custext)
    {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.USERGROUPURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    jsonObject = new JSONObject(response);

                    JSONArray dataJsonArr1 = null;

                    System.out.println("dataresponse" + response);
                    dataJsonArr1 = jsonObject.getJSONArray("usergroup_info");

                    arraylength1 = dataJsonArr1.length();
                    Log.e("responeeee", dataJsonArr1.toString() + "");
                    for (int j = 0; j < dataJsonArr1.length(); j++)

                    {    //JSONObject c = dataJsonArr.getJSONObject(i);
                        JSONObject elem1 = dataJsonArr1.getJSONObject(j);

                        cusname1 = elem1.getString("name");


                        if(custext.equals(cusname1)) {
                            cusid = elem1.getString("id");

                        }

                    }


//
                } catch (Exception e) {
                    e.printStackTrace();
                }

//

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(Config.TAG_USER_GROUP_LIST,"Error: " + error.getMessage());
                error.printStackTrace();

            }
        }) {



        };

        // Adding request to request queue
        AppController ctrl = AppController.getInstance();
        ctrl.addToRequestQueue(strReq, Config.TAG_CUSTOMER_GROUP_LIST);
    }
    public void gotoLogin(View view)
    {
        if(!(editText.getText().toString().isEmpty())) {
            if (!(editText1.getText().toString().isEmpty())) {
                if (!(title.equals("Please select level"))) {
                    LoginAPI loginAPI = new LoginAPI(this, this);
                    loginAPI.processLogin(editText.getText().toString(), editText1.getText().toString(), title);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Please select level",Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(getApplicationContext(),"Please enter password",Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(),"Please enter username",Toast.LENGTH_SHORT).show();
        }

    }
    public void getUserGroup()
    {
        UserGroupAPI userGroupAPI=new UserGroupAPI(this,this);
        userGroupAPI.processUserGroupList();
    }

    @Override
    public void updateScreen(String data, String tag) {
        try {
            if (tag.compareTo(Config.TAG_LOGIN_LIST) == 0) {
                Log.d("response",tag);
                JSONObject jsonObject=new JSONObject(data);
                if(jsonObject.getString("success").equals("true"))
                {
                    SharedPreferences pref14 = getApplicationContext().getSharedPreferences("Login", MODE_PRIVATE);
                    editor = pref14.edit();
                    editor.putBoolean("isFirstTime", false);
                    editor.putString("id", jsonObject.getString("id"));

                    editor.commit();
                    Toast.makeText(getApplicationContext(),"Loggedin successfully",Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(this,MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.overridePendingTransition(R.anim.righttoleft,
                            R.anim.lefttoright);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Wrong username or password",Toast.LENGTH_SHORT).show();
                }

            }
            if (tag.compareTo(Config.TAG_USER_GROUP_LIST) == 0) {
                Log.d("datahoome", data);
                try {
                    JSONObject jsonObject = new JSONObject(data);

                    JSONArray dataJsonArr1 = null;
                    dataJsonArr1 = jsonObject.getJSONArray("usergroup_info");

                    arraylength1 = dataJsonArr1.length();
                    Log.e("responeeee", dataJsonArr1.toString() + "");
                    for (int j = 0; j < dataJsonArr1.length(); j++)

                    {    //JSONObject c = dataJsonArr.getJSONObject(i);
                        JSONObject elem1 = dataJsonArr1.getJSONObject(j);
                        cusname = elem1.getString("name");


                        cusid = elem1.getString("id");
                        levelarray.add(cusname);
                        spinner.setAdapter(spinnerArrayAdapter1);


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void updateScreen1() {

    }


}