package com.fingertip.catalog.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.fingertip.catalog.EditCategory;
import com.fingertip.catalog.R;
import com.fingertip.catalog.api.DeleteCategoryAPI;
import com.fingertip.catalog.holder.CategoryHolder;
import com.fingertip.catalog.holder.CheckdatacatHolder;
import com.fingertip.catalog.interfaces.NetworkCallback;
import com.fingertip.catalog.model.CategoryBO;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lenevo on 11-05-2018.
 */

public class ProDataCatAdapter extends RecyclerView.Adapter<CheckdatacatHolder> implements NetworkCallback {
    private List<CategoryBO> itemList3;
    private Context context;
    public  static ArrayList<String> cat_list = new ArrayList<String>();
    private NetworkCallback mCallback;
    Activity activity;
    SharedPreferences pref45;
    String user_type,saveduser;
    public  static ArrayList<String> dose_list2 = new ArrayList<String>();

    private int stat;
    public ProDataCatAdapter(Activity activity, Context context, List<CategoryBO> itemList,NetworkCallback mCallback) {
        this.itemList3 = itemList;
        this.context = context;
        this.activity=activity;
        this.mCallback=mCallback;
    }


    @Override
    public CheckdatacatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView =  null;

        layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_datacat, null);

        CheckdatacatHolder rcv = new CheckdatacatHolder(layoutView);
        try {

            pref45 = context.getSharedPreferences("Login", MODE_PRIVATE);

            saveduser = pref45.getString("user", null);
            user_type = pref45.getString("usertype", null);



        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return rcv;
    }


    @Override
    public void onBindViewHolder(CheckdatacatHolder holder, int position) {
        Typeface typeFace1 = Typeface.createFromAsset(context.getAssets(), "robotoRegular.ttf");
        final CategoryBO categoryBO = itemList3.get(position);
       holder.checkBox.setText(categoryBO.getCatname());
        holder.checkBox.setTypeface(typeFace1);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    cat_list.add(categoryBO.getId());
                    for(int i=0;i<cat_list.size();i++)
                    {
                        System.out.println("dopse items"+cat_list.get(i).toString());
                    }
//
                }
                else {
                    cat_list.remove(categoryBO.getId());
                    for(int i=0;i<cat_list.size();i++)
                    {
                        System.out.println("cat items"+cat_list.get(i).toString());
                    }
                }
            }
        });
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dose_list2.add(categoryBO.getId());
                    for(int i=0;i<dose_list2.size();i++)
                    {
                        System.out.println("dopse items"+dose_list2.get(i).toString());
                    }
//                    boolean isSelected = ((CheckBox)buttonView).isChecked();
//                    itemList3.get(position).setSelected(isSelected);
                }
                else {
//                    boolean isSelected = ((CheckBox)buttonView).isChecked();
//                    itemList3.get(position).setSelected(isSelected);
                    dose_list2.remove(categoryBO.getId());
                    for(int i=0;i<dose_list2.size();i++)
                    {
                        System.out.println("dopse items"+dose_list2.get(i).toString());
                    }
                }
            }
        });



    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        try {
            return this.itemList3.size();

        }
        catch (Exception e) {
            return 0;


        }
    }


    @Override
    public void updateScreen(String data, String tag) {

    }

    @Override
    public void updateScreen1() {

    }

    public void deleteCat(String catid)
    {


    }



}



