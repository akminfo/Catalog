package com.fingertip.catalog.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
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

public class EditProDataAdapter extends RecyclerView.Adapter<CheckdatacatHolder> implements NetworkCallback,CompoundButton.OnCheckedChangeListener {
    private List<CategoryBO> itemList3;
    private Context context;
    public  static ArrayList<String> cat_list = new ArrayList<String>();
    private NetworkCallback mCallback;
    Activity activity;
    SharedPreferences pref45;
    String s1,s2;
    String user_type,saveduser;
    public  static ArrayList<String> dose_list2 = new ArrayList<String>();
    public static SparseBooleanArray mCheckStates;
    private int stat;
    List<String> n3 = new ArrayList<String>();
    ArrayList<String> n = new ArrayList<String>();
    ArrayList<String> n1 = new ArrayList<String>();
    ArrayList<String> n2 = new ArrayList<String>();
    public EditProDataAdapter(Activity activity, Context context, List<CategoryBO> itemList,ArrayList n,ArrayList n1,ArrayList n2,NetworkCallback mCallback,List n3) {
        this.itemList3 = itemList;
        this.context = context;
        this.activity=activity;
        this.mCallback=mCallback;
        this.n=n;
        this.n1=n1;
        this.n2=n2;
        this.n3=n3;
        mCheckStates = new SparseBooleanArray(n2.size());

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
        holder.checkBox.setTag(position);
        holder.checkBox.setChecked(mCheckStates.get(position, false));
        holder.checkBox.setOnCheckedChangeListener(this);
        holder.checkBox.setText(n1.get(position).toString());
        holder.checkBox.setTypeface(typeFace1);
        System.out.println("arrays"+n.toString());
        System.out.println("arrays"+n2.toString());
        for (int i = 0; i < n3.size(); i++) {

            if(categoryBO.getId().equals(n3.get(i).toString()))
            {
                System.out.println("eaqq"+n3.get(i).toString());
                holder.checkBox.setChecked(true);
            }

        }
//        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked) {
//                    cat_list.add(categoryBO.getId());
//                    for(int i=0;i<cat_list.size();i++)
//                    {
//                        System.out.println("dopse items"+cat_list.get(i).toString());
//                    }
////
//                }
//                else {
//                    cat_list.remove(categoryBO.getId());
//                    for(int i=0;i<cat_list.size();i++)
//                    {
//                        System.out.println("cat items"+cat_list.get(i).toString());
//                    }
//                }
//            }
//        });
//        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked) {
//                    dose_list2.add(categoryBO.getId());
//                    for(int i=0;i<dose_list2.size();i++)
//                    {
//                        System.out.println("dopse items"+dose_list2.get(i).toString());
//                    }
////                    boolean isSelected = ((CheckBox)buttonView).isChecked();
////                    itemList3.get(position).setSelected(isSelected);
//                }
//                else {
////                    boolean isSelected = ((CheckBox)buttonView).isChecked();
////                    itemList3.get(position).setSelected(isSelected);
//                    dose_list2.remove(categoryBO.getId());
//                    for(int i=0;i<dose_list2.size();i++)
//                    {
//                        System.out.println("dopse items"+dose_list2.get(i).toString());
//                    }
//                }
//            }
//        });



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



