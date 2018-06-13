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
import com.fingertip.catalog.parsar.CategoryListParsar;

import org.json.JSONObject;

/**
 * Created by lenevo on 03-05-2018.
 */

public class GeneralCatFragment extends Fragment implements NetworkCallback {
    Typeface typeface,typeface1,typeface2;
    EditText editText,editText1,editText2,editText3,editText4,editText5;
    Button button;
    String s1,s2,s3;
    public GeneralCatFragment() {
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
        return inflater.inflate(R.layout.general2, container, false);

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
        button=(Button)getView().findViewById(R.id.savepro);
        typeface = Typeface.createFromAsset(getActivity().getAssets(),"robotoRegular.ttf");
        typeface1 = Typeface.createFromAsset(getActivity().getAssets(),"robotoBold.ttf");
        typeface2 = Typeface.createFromAsset(getActivity().getAssets(),"robotoMedium.ttf");
        editText.setTypeface(typeface);
        editText1.setTypeface(typeface);
        editText2.setTypeface(typeface);
        editText3.setTypeface(typeface);
        editText4.setTypeface(typeface);
        button.setTypeface(typeface1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditCategoryAPI editCategoryAPI=new EditCategoryAPI(getActivity(),GeneralCatFragment.this);
                editCategoryAPI.processEditCategory(EditCategory.s1,editText.getText().toString(),editText1.getText().toString(),editText2.getText().toString(),editText3.getText().toString(),editText4.getText().toString(),s1,s2,s3,"1");

            }
        });

        getData();




    }




    public void getData()
    {
        EditCategoryAPI editCategoryAPI=new EditCategoryAPI(getActivity(),GeneralCatFragment.this);
        editCategoryAPI.processEditCategory2(EditCategory.s1,"0");
    }
    @Override
    public void updateScreen(String data, String tag) {
        try {
            if (tag.compareTo(Config.TAG_EDIT_CATEGORY_LIST) == 0) {
                Log.d("datahoome", data);
                JSONObject jsonObject=new JSONObject(data);
                if(jsonObject.getString("update_status").equals("Success"))
                {
                    Toast.makeText(getActivity(), "Data updated successfully", Toast.LENGTH_SHORT).show();
                    getData();
                }
                else {
                    Toast.makeText(getActivity(), "Data failed to update", Toast.LENGTH_SHORT).show();
                }

            }
            if (tag.compareTo(Config.TAG_EDIT_CATEGORY_LIST2) == 0) {
                Log.d("datahoome", data);
                JSONObject jsonObject=new JSONObject(data);

                   editText.setText(jsonObject.getString("name"));
                    editText1.setText(jsonObject.getString("description"));
                    editText2.setText(jsonObject.getString("meta_title"));
                    editText3.setText(jsonObject.getString("meta_description"));
                    editText4.setText(jsonObject.getString("meta_keyword"));
                s1=jsonObject.getString("sort_order");
                s2=jsonObject.getString("top");

                s3=jsonObject.getString("status");






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

