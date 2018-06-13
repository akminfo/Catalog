package com.fingertip.catalog.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.fingertip.catalog.R;
import com.fingertip.catalog.api.CustomerGroupAPI;
import com.fingertip.catalog.callback.AppController;
import com.fingertip.catalog.callback.Config;
import com.fingertip.catalog.interfaces.NetworkCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenevo on 04-05-2018.
 */

public class CustomerGeneralFragment extends Fragment implements NetworkCallback {
    Typeface typeface,typeface1,typeface2;
   public  static EditText editText,editText1,editText2,editText3,editText4,editText5;
    Button button;
    RadioGroup  radioGroup;

   public static String flag;
    ArrayList<String> statusarray = new ArrayList<String>();
    ArrayList<String> customergroup;
    Spinner spinner2;
    ArrayAdapter<String> spinnerArrayAdapter2;
public static String gen="M";
public static String title="Status";
    String cusname,cusname1;
    public static String cusid;
    public static String cusg;

    TextView textView;

    RadioButton radioButton,radioButton1,radioButton2;
    int arraylength1=-1;
    JSONObject jsonObject=null;
    Spinner spinner1;

    public CustomerGeneralFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.customer_general, container, false);

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);



    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
      final Spinner  spinner1=(Spinner)getView().findViewById(R.id.spinner5);
        customergroup  = new ArrayList<String>();
          spinner2=(Spinner)getView().findViewById(R.id.spinner8);
        statusarray.add("Status");
        statusarray.add("Enable");
        statusarray.add("Disable");
        customergroup.add("Choose Customer Group");
        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(getActivity(), R.layout.spinner_textview1, statusarray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter1.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(spinnerArrayAdapter1);

       spinnerArrayAdapter2 = new ArrayAdapter<String>(getActivity(), R.layout.spinner_textview1, customergroup); //selected item will look like a spinner set from XML
        spinnerArrayAdapter2.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(spinnerArrayAdapter2);

        editText=(EditText) getView().findViewById(R.id.firstname);
        editText1=(EditText) getView().findViewById(R.id.lastname);
        editText2=(EditText)getView().findViewById(R.id.email);
        editText3=(EditText)getView().findViewById(R.id.mobile);
        editText4=(EditText)getView().findViewById(R.id.username);
        editText5=(EditText)getView().findViewById(R.id.password);
        textView=(TextView)getView().findViewById(R.id.cusgender);
        radioButton=(RadioButton)getView().findViewById(R.id.male);
        radioButton1=(RadioButton)getView().findViewById(R.id.female);



        typeface = Typeface.createFromAsset(getActivity().getAssets(),"robotoRegular.ttf");
        typeface1 = Typeface.createFromAsset(getActivity().getAssets(),"robotoBold.ttf");
        typeface2 = Typeface.createFromAsset(getActivity().getAssets(),"robotoMedium.ttf");
        editText.setTypeface(typeface);
        editText1.setTypeface(typeface);
        editText2.setTypeface(typeface);
        editText3.setTypeface(typeface);
        editText4.setTypeface(typeface);
        editText5.setTypeface(typeface);
        textView.setTypeface(typeface2);

        radioButton.setTypeface(typeface);
        radioButton1.setTypeface(typeface);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                title=spinner1.getSelectedItem().toString();
                if(title.equals("Enable"))
                {
                    flag="1";
                }
                else {
                    flag="0";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                    cusg = spinner2.getSelectedItem().toString();
                    if (position != 0) {
                        getCusId(cusg);
                        System.out.println("iddd" + cusid);
                    }

            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        radioGroup = (RadioGroup)getView().findViewById(R.id.radiogroupgender);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {

                    case R.id.male:
                        int selectedId = radioGroup.getCheckedRadioButtonId();
                        radioButton2 = (RadioButton)getView().findViewById(selectedId);
                        gen="M";

                        break;
                    case R.id.female:
                        int selectedId1 = radioGroup.getCheckedRadioButtonId();
                        radioButton2 = (RadioButton)getView().findViewById(selectedId1);
                        gen="F";

                        break;

                }
            }
        });
        getCustomerGroup();


    }
    public void getCustomerGroup()
    {
        CustomerGroupAPI customerGroupAPI=new CustomerGroupAPI(getActivity(),CustomerGeneralFragment.this);
        customerGroupAPI.processCustomerGroupList();
    }
    public void getCusId(final String custext)
    {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.CUSTOMERGROUPURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    jsonObject = new JSONObject(response);

                    JSONArray dataJsonArr1 = null;

                    System.out.println("dataresponse" + response);
                    dataJsonArr1 = jsonObject.getJSONArray("customergroup_info");

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
                VolleyLog.d(Config.TAG_CUSTOMER_GROUP_LIST,"Error: " + error.getMessage());
                error.printStackTrace();

            }
        }) {



        };

        // Adding request to request queue
        AppController ctrl = AppController.getInstance();
        ctrl.addToRequestQueue(strReq, Config.TAG_CUSTOMER_GROUP_LIST);
    }

    @Override
    public void updateScreen(String data, String tag) {
        try {
            if (tag.compareTo(Config.TAG_CUSTOMER_GROUP_LIST) == 0) {
                Log.d("datahoome", data);
                JSONObject jsonObject = new JSONObject(data);

                JSONArray dataJsonArr1 = null;
                dataJsonArr1 = jsonObject.getJSONArray("customergroup_info");

                arraylength1 = dataJsonArr1.length();
                Log.e("responeeee", dataJsonArr1.toString() + "");
                for (int j = 0; j < dataJsonArr1.length(); j++)

                {    //JSONObject c = dataJsonArr.getJSONObject(i);
                    JSONObject elem1 = dataJsonArr1.getJSONObject(j);
                    cusname = elem1.getString("name");


                        cusid = elem1.getString("id");
                        customergroup.add(cusname);
                    spinner2.setAdapter(spinnerArrayAdapter2);




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


