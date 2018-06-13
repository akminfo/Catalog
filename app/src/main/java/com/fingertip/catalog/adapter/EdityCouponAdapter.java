package com.fingertip.catalog.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
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
 * Created by lenevo on 22-05-2018.
 */

public class EdityCouponAdapter extends RecyclerView.Adapter<CheckdatacatHolder> implements NetworkCallback,CompoundButton.OnCheckedChangeListener {
private List<CustomerGroupBO> itemList3;
private Context context;
public  static ArrayList<String> editgroup_list = new ArrayList<String>();
public  static ArrayList<String> catgroup_list_name = new ArrayList<String>();
private NetworkCallback mCallback;
        Activity activity;
        SharedPreferences pref45;
        ArrayList<CheckBox> checkArray = new ArrayList<CheckBox>();
private int selectedPosition = -1;
        String user_type,saveduser;
public  static ArrayList<String> dose_list2 = new ArrayList<String>();
private Boolean flag;
   List<String> n = new ArrayList<String>();
private int stat;
        public static SparseBooleanArray mCheckStates;
public EdityCouponAdapter(Activity activity, Context context, List<CustomerGroupBO> itemList, NetworkCallback mCallback,List n, Boolean flag) {
        this.itemList3 = itemList;
        this.context = context;
        this.activity=activity;
        this.mCallback=mCallback;
        this.n=n;
        this.flag=flag;
        mCheckStates = new SparseBooleanArray(n.size());
        }


@Override
public CheckdatacatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView =  null;

        layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_datacat, null);

        CheckdatacatHolder rcv = new CheckdatacatHolder(layoutView);
        try {
        if(flag) {
        rcv.checkBox.setChecked(false);
                rcv.checkBox.setEnabled(false);
        }
        else {
                rcv.checkBox.setEnabled(true);
        }
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
public void onBindViewHolder(final CheckdatacatHolder holder, int position) {

        Typeface typeFace1 = Typeface.createFromAsset(context.getAssets(), "robotoRegular.ttf");
final CustomerGroupBO customerGroupBO = itemList3.get(position);
        try {
                holder.checkBox.setText(customerGroupBO.getName());

                holder.checkBox.setTag(position);
                holder.checkBox.setChecked(mCheckStates.get(position, false));
                holder.checkBox.setOnCheckedChangeListener(this);
                holder.checkBox.setTypeface(typeFace1);
                for (int i = 0; i < n.size(); i++) {


                        if(customerGroupBO.getId().equals(n.get(i).toString()))
                        {
                                System.out.println("eaqq"+n.get(i).toString());
                                holder.checkBox.setChecked(true);
                        }

                }
                if(flag) {
                        holder.checkBox.setChecked(false);
                }
        }
        catch (Exception e)
        {
                e.printStackTrace();
        }




//        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//@Override
//public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        if(isChecked) {
//
//                editgroup_list.add(customerGroupBO.getId());
//        for (int i = 0; i < editgroup_list.size(); i++) {
//        System.out.println("dopse items" + editgroup_list.get(i).toString());
//        }
//        }
////
//
//        else {
////
//                editgroup_list.remove(customerGroupBO.getId());
//        for(int i=0;i<editgroup_list.size();i++)
//        {
//        System.out.println("cat items"+editgroup_list.get(i).toString());
//        }
//        }
//        }
//        });
//




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




        public boolean isChecked(int position) {
                return mCheckStates.get(position, false);
        }

        public void setChecked(int position, boolean isChecked) {
                mCheckStates.put(position, isChecked);
        }

        public void toggle(int position) {
                setChecked(position, !isChecked(position));
        }




        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCheckStates.put((Integer) buttonView.getTag(), isChecked);

        }

}




