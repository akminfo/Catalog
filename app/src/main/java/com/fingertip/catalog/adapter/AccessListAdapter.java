package com.fingertip.catalog.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.fingertip.catalog.R;
import com.fingertip.catalog.holder.CheckdatacatHolder;
import com.fingertip.catalog.interfaces.NetworkCallback;
import com.fingertip.catalog.model.CustomerGroupBO;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lenevo on 23-05-2018.
 */

public class AccessListAdapter extends RecyclerView.Adapter<CheckdatacatHolder> {

    private Context context;
    public  static ArrayList<String> access_list = new ArrayList<String>();
    public  static ArrayList<String> catgroup_list_name = new ArrayList<String>();
    private NetworkCallback mCallback;
    Activity activity;
    SharedPreferences pref45;
    ArrayList<CheckBox> checkArray = new ArrayList<CheckBox>();
    private int selectedPosition = -1;
    String user_type,saveduser;
    public  static ArrayList<String> dose_list2 = new ArrayList<String>();
    private Boolean flag;
    ArrayList<String> itemList3 = new ArrayList<String>();
    private int stat;
    public AccessListAdapter(Activity activity, Context context, ArrayList itemList) {
        this.itemList3 = itemList;
        this.context = context;
        this.activity=activity;

    }


    @Override
    public CheckdatacatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView =  null;

        layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_datacat, null);

        CheckdatacatHolder rcv = new CheckdatacatHolder(layoutView);

            pref45 = context.getSharedPreferences("Login", MODE_PRIVATE);

            saveduser = pref45.getString("user", null);
            user_type = pref45.getString("usertype", null);




        return rcv;
    }


    @Override
    public void onBindViewHolder(final CheckdatacatHolder holder, final int position) {

        Typeface typeFace1 = Typeface.createFromAsset(context.getAssets(), "robotoRegular.ttf");

        holder.checkBox.setText(itemList3.get(position).toString());

        holder.checkBox.setTag(position);
        holder.checkBox.setTypeface(typeFace1);


        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {

                    access_list.add(itemList3.get(position).toString());
                    for (int i = 0; i < access_list.size(); i++) {
                        System.out.println("dopse items" + access_list.get(i).toString());
                    }
                }
//

                else {
//
                    access_list.remove(itemList3.get(position).toString());
                    for(int i=0;i<access_list.size();i++)
                    {
                        System.out.println("cat items"+access_list.get(i).toString());
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





}





