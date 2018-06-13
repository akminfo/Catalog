package com.fingertip.catalog.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.fingertip.catalog.EditProduct;
import com.fingertip.catalog.R;

/**
 * Created by lenevo on 16-05-2018.
 */

public class EditProGeneralFragment extends Fragment {
    Typeface typeface,typeface1,typeface2;
    public static EditText editText,editText1,editText2,editText3,editText4,editText5;
    Button button;
    public EditProGeneralFragment() {
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
        return inflater.inflate(R.layout.edit_progeneral, container, false);

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
        editText4=(EditText)getView().findViewById(R.id.prtokeyfeat);
        editText5=(EditText)getView().findViewById(R.id.prometakey);

        typeface = Typeface.createFromAsset(getActivity().getAssets(),"robotoRegular.ttf");
        typeface1 = Typeface.createFromAsset(getActivity().getAssets(),"robotoBold.ttf");
        typeface2 = Typeface.createFromAsset(getActivity().getAssets(),"robotoMedium.ttf");
        editText.setTypeface(typeface);
        editText1.setTypeface(typeface);
        editText2.setTypeface(typeface);
        editText3.setTypeface(typeface);
        editText4.setTypeface(typeface);
        editText5.setTypeface(typeface);
        editText.setText(EditProduct.s11);
        editText1.setText(EditProduct.s12);
        editText2.setText(EditProduct.s13);
        editText3.setText(EditProduct.s14);
        editText4.setText(EditProduct.s15);
        editText5.setText(EditProduct.s16);








    }



}

