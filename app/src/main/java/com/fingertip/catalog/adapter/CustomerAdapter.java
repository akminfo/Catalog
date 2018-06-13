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

import com.fingertip.catalog.EditCategory;
import com.fingertip.catalog.EditCustomer;
import com.fingertip.catalog.R;
import com.fingertip.catalog.api.DeleteCategoryAPI;
import com.fingertip.catalog.api.DeleteCustomerAPI;
import com.fingertip.catalog.holder.CategoryHolder;
import com.fingertip.catalog.holder.CustomerHolder;
import com.fingertip.catalog.interfaces.NetworkCallback;
import com.fingertip.catalog.model.CategoryBO;
import com.fingertip.catalog.model.CustomerBO;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lenevo on 18-05-2018.
 */

public class CustomerAdapter extends RecyclerView.Adapter<CustomerHolder> implements NetworkCallback {
    private List<CustomerBO> itemList3;
    private Context context;

    private NetworkCallback mCallback;
    Activity activity;
    SharedPreferences pref45;
    String user_type,saveduser;

    private int stat;
    public CustomerAdapter(Activity activity, Context context, List<CustomerBO> itemList, NetworkCallback mCallback) {
        this.itemList3 = itemList;
        this.context = context;
        this.activity=activity;
        this.mCallback=mCallback;
    }


    @Override
    public CustomerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView =  null;

        layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cus_list_adapter, null);

        CustomerHolder rcv = new CustomerHolder(layoutView);
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
    public void onBindViewHolder(CustomerHolder holder, int position) {
        Typeface typeFace1 = Typeface.createFromAsset(context.getAssets(), "robotoRegular.ttf");
        final CustomerBO customerBO = itemList3.get(position);
        holder.textView.setText(customerBO.getCusfname()+" "+customerBO.getCuslname());
        holder.textView1.setText(customerBO.getCusemail());
        holder.textView2.setText(customerBO.getCususer());
        holder.textView3.setText(customerBO.getCusmobile());

        if(customerBO.getCusstatus().equals("1")) {
            holder.textView4.setText("Enable");
        }
        else {
            holder.textView4.setText("Disable");
        }




        holder.textView.setTypeface(typeFace1);
        holder.textView1.setTypeface(typeFace1);
        holder.textView2.setTypeface(typeFace1);
        holder.textView3.setTypeface(typeFace1);
        holder.textView4.setTypeface(typeFace1);
        holder.editCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         Intent intent=new Intent(context,EditCustomer.class);
                intent.putExtra("id",customerBO.getId());
                intent.putExtra("fname",customerBO.getCusfname());
                intent.putExtra("lname",customerBO.getCuslname());
                intent.putExtra("gen",customerBO.getGender());
                intent.putExtra("email",customerBO.getCusemail());
                intent.putExtra("mobile",customerBO.getCusmobile());
                intent.putExtra("username",customerBO.getCususer());
                intent.putExtra("pass",customerBO.getPass());
                intent.putExtra("status",customerBO.getCusstatus());
                intent.putExtra("address",customerBO.getAddress());
                intent.putExtra("city",customerBO.getCity());
                intent.putExtra("pin",customerBO.getPin());
                intent.putExtra("state",customerBO.getState());
                intent.putExtra("country",customerBO.getCountry());
                intent.putExtra("image",customerBO.getImage());
                intent.putExtra("group",customerBO.getCusgroup());
                context.startActivity(intent);


            }
        });
        holder.deleteCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteCustomerAPI deleteCustomerAPI=new DeleteCustomerAPI(context,CustomerAdapter.this);
                deleteCustomerAPI.processDelete(customerBO.getId());
                mCallback.updateScreen1();
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



