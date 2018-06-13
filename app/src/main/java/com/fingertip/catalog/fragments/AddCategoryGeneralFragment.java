package com.fingertip.catalog.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fingertip.catalog.EditCategory;
import com.fingertip.catalog.R;
import com.fingertip.catalog.api.EditCategoryAPI;
import com.fingertip.catalog.callback.Config;
import com.fingertip.catalog.interfaces.NetworkCallback;

import org.json.JSONObject;

/**
 * Created by lenevo on 09-05-2018.
 */

public class AddCategoryGeneralFragment extends Fragment implements NetworkCallback {
    Typeface typeface,typeface1,typeface2;
   public static EditText editText,editText1,editText2,editText3,editText4,editText5;
    Button button;
    String s1,s2,s3;
    public AddCategoryGeneralFragment() {
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
        return inflater.inflate(R.layout.addcat_general, container, false);

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);



    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        editText=(EditText) getView().findViewById(R.id.proname);
        editText1=(EditText) getView().findViewById(R.id.prodesc);
        editText2=(EditText)getView().findViewById(R.id.prometaname);
        editText3=(EditText)getView().findViewById(R.id.prometadesc);
        editText4=(EditText)getView().findViewById(R.id.prometakey);

        typeface = Typeface.createFromAsset(getActivity().getAssets(),"robotoRegular.ttf");
        typeface1 = Typeface.createFromAsset(getActivity().getAssets(),"robotoBold.ttf");
        typeface2 = Typeface.createFromAsset(getActivity().getAssets(),"robotoMedium.ttf");
        editText.setTypeface(typeface);
        editText1.setTypeface(typeface);
        editText2.setTypeface(typeface);
        editText3.setTypeface(typeface);
        editText4.setTypeface(typeface);





    }




    @Override
    public void updateScreen(String data, String tag) {

    }

    @Override
    public void updateScreen1() {

    }
}

