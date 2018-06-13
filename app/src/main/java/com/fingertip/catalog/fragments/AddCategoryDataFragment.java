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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fingertip.catalog.EditCategory;
import com.fingertip.catalog.R;
import com.fingertip.catalog.api.EditCategoryAPI;
import com.fingertip.catalog.callback.Config;
import com.fingertip.catalog.interfaces.NetworkCallback;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lenevo on 09-05-2018.
 */

public class AddCategoryDataFragment extends Fragment implements NetworkCallback {

    Typeface typeface,typeface1,typeface2;
   public static EditText editText,editText1,editText2,editText3,editText4,editText5;
    String s1,s2,s3,s4,s5;
    TextView textView;
    ArrayList<String> statusarray = new ArrayList<String>();
    ArrayAdapter<String> spinnerArrayAdapter1;
    Spinner spinner1;
    public static String flag="1";
    String title1;
    Button button;

    public AddCategoryDataFragment() {
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
        return inflater.inflate(R.layout.activity_adddatacat, container, false);

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);



    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        spinner1=(Spinner)getView().findViewById(R.id.spinner3);

            statusarray.add("Enable");
            statusarray.add("Disable");




        spinnerArrayAdapter1 = new ArrayAdapter<String>(getActivity(), R.layout.spinner_textview1, statusarray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter1.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(spinnerArrayAdapter1);

        editText1=(EditText) getView().findViewById(R.id.sort1);
        editText2=(EditText)getView().findViewById(R.id.toptext);


        typeface = Typeface.createFromAsset(getActivity().getAssets(),"robotoRegular.ttf");
        typeface1 = Typeface.createFromAsset(getActivity().getAssets(),"robotoBold.ttf");
        typeface2 = Typeface.createFromAsset(getActivity().getAssets(),"robotoMedium.ttf");

        editText1.setTypeface(typeface);
        editText2.setTypeface(typeface);



        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                title1=spinner1.getSelectedItem().toString();
                if(title1.equals("Enable"))
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
        //getData();

    }


    @Override
    public void updateScreen(String data, String tag) {

    }

    @Override
    public void updateScreen1() {

    }
}



