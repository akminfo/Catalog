package com.fingertip.catalog.fragments;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import com.fingertip.catalog.EditCategory;
import com.fingertip.catalog.EditProduct;
import com.fingertip.catalog.R;
import com.fingertip.catalog.adapter.EditProDataAdapter;
import com.fingertip.catalog.adapter.ProDataCatAdapter;
import com.fingertip.catalog.api.CategoryListAPI;
import com.fingertip.catalog.callback.Config;
import com.fingertip.catalog.interfaces.NetworkCallback;
import com.fingertip.catalog.model.CategoryBO;
import com.fingertip.catalog.parsar.CategoryListParsar;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by lenevo on 16-05-2018.
 */

public class EditProDataFragment extends android.support.v4.app.Fragment implements NetworkCallback{
    Spinner spinner1;
    Typeface typeface,typeface1,typeface2;
    public static EditText editText,editText1,editText2,editText3,editText4,editText5;
    ArrayAdapter<String> spinnerArrayAdapter1;
    TextView textView;
    LinearLayoutManager listLayoutManager;
    public static String title;
    public static  String flag="1";
    int arraylength=-1;
    ArrayList<String> class_array = new ArrayList<String>();
    public static ArrayList<String> id1_array = new ArrayList<String>();
    Map catMap=null;
    String class1,id1;
    StringBuilder checkedcontacts;
    public  static  EditProDataAdapter categoryListAdapter;
    RecyclerView recyclerView;
    ArrayList<String> statusarray = new ArrayList<String>();
    Button button;
    private List<CategoryBO> categoryBOList;

    public EditProDataFragment() {
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
        return inflater.inflate(R.layout.data1, container, false);

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);



    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        spinner1=(Spinner)getView().findViewById(R.id.spinner2);

  if(EditProduct.s21.equals("1")) {
      statusarray.add("Enable");
      statusarray.add("Disable");
  }
  else {
      statusarray.add("Disable");
      statusarray.add("Enable");

  }

        spinnerArrayAdapter1 = new ArrayAdapter<String>(getActivity(), R.layout.spinner_textview1, statusarray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter1.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(spinnerArrayAdapter1);
        textView=(TextView) getView().findViewById(R.id.catlist);
        editText1=(EditText) getView().findViewById(R.id.skutext);
        editText2=(EditText)getView().findViewById(R.id.quantitytext);
        editText3=(EditText)getView().findViewById(R.id.pricetext);
        editText4=(EditText)getView().findViewById(R.id.sorttext);

        recyclerView=(RecyclerView)getView().findViewById(R.id.procatlist);
        typeface = Typeface.createFromAsset(getActivity().getAssets(),"robotoRegular.ttf");
        typeface1 = Typeface.createFromAsset(getActivity().getAssets(),"robotoBold.ttf");
        typeface2 = Typeface.createFromAsset(getActivity().getAssets(),"robotoMedium.ttf");
        textView.setTypeface(typeface2);
        editText1.setTypeface(typeface);
        editText2.setTypeface(typeface);
        editText3.setTypeface(typeface);
        editText4.setTypeface(typeface);
        editText1.setText(EditProduct.s17);
        editText2.setText(EditProduct.s18);
        editText3.setText(EditProduct.s19);
        editText4.setText(EditProduct.s20);


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
        checkedcontacts = new StringBuilder();
        //  checkedcontacts=null;


        System.out.println("String split with delimiter: " + Arrays.toString(EditProduct.words));
        getCategories();
//

    }
    public void setCategoryList()
    {
        try {
            System.out.println("arrayids"+id1_array.toString());
            categoryBOList = (List<CategoryBO>)catMap.get("category_list");
            Log.d("grr", catMap.toString());


            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            categoryListAdapter= new EditProDataAdapter(getActivity(),getActivity(),categoryBOList,EditProduct.cat_list,class_array,id1_array,this,EditProduct.ed_prolist);
            listLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            listLayoutManager.scrollToPosition(0);
            recyclerView.setLayoutManager(listLayoutManager);
            recyclerView.setAdapter(categoryListAdapter);
            recyclerView.setNestedScrollingEnabled(false);


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public void getCategories()
    {

        CategoryListAPI categoryListAPI=new CategoryListAPI(getActivity(),EditProDataFragment.this);
        categoryListAPI.processCategoryList();
    }


    @Override
    public void updateScreen(String data, String tag) {
        try {
            if (tag.compareTo(Config.TAG_CATEGORY_LIST) == 0) {
                Log.d("datahoome", data);


                JSONObject jsonObject=new JSONObject(data);
                JSONArray dataJsonArr = null;
                try {
                    Log.d("nbjkkj", jsonObject.toString());
                    dataJsonArr = jsonObject.getJSONArray("categoryInfo");
                    Log.e("tagggg", dataJsonArr.toString() + "");
                    // get the array of users

                    arraylength = dataJsonArr.length();
                    for (int i = 0; i < dataJsonArr.length(); i++)

                    {    //JSONObject c = dataJsonArr.getJSONObject(i);
                        JSONObject elem = dataJsonArr.getJSONObject(i);
                        class1 = elem.getString("name");
                        id1 = elem.getString("id");
                        class_array.add(class1);
                        id1_array.add(id1);
                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();

                }

                catMap = CategoryListParsar.parseCategoryProducts(data);
                setCategoryList();


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

