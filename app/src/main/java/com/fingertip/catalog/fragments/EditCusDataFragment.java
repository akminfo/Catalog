package com.fingertip.catalog.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.fingertip.catalog.EditCustomer;
import com.fingertip.catalog.R;

import java.util.ArrayList;

/**
 * Created by lenevo on 19-05-2018.
 */

public class EditCusDataFragment  extends Fragment {

    Typeface typeface,typeface1,typeface2;
    public static EditText editText,editText1,editText2,editText3,editText4;

    TextView textView;
    ArrayList<String> statusarray = new ArrayList<String>();
    ArrayAdapter<String> spinnerArrayAdapter1;
    Spinner spinner1;
    Button button;

    public EditCusDataFragment() {
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
        return inflater.inflate(R.layout.edit_cus_data, container, false);

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);



    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        editText=(EditText) getView().findViewById(R.id.address1);
        editText1=(EditText) getView().findViewById(R.id.pincode);
        editText2=(EditText)getView().findViewById(R.id.statetext);
        editText3=(EditText)getView().findViewById(R.id.citytext);
        editText4=(EditText)getView().findViewById(R.id.countrytext);
        System.out.println("adressss"+EditCustomer.s10+EditCustomer.s12+EditCustomer.s13+EditCustomer.s11+EditCustomer.s14);
editText.setText(EditCustomer.s10);
        editText1.setText(EditCustomer.s12);
        editText2.setText(EditCustomer.s13);
        editText3.setText(EditCustomer.s11);
        editText4.setText(EditCustomer.s14);


        typeface = Typeface.createFromAsset(getActivity().getAssets(),"robotoRegular.ttf");
        typeface1 = Typeface.createFromAsset(getActivity().getAssets(),"robotoBold.ttf");


        editText.setTypeface(typeface);
        editText1.setTypeface(typeface);
        editText2.setTypeface(typeface);
        editText3.setTypeface(typeface);
        editText4.setTypeface(typeface);




    }



}




