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
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.fingertip.catalog.EditCustomerGroup;
import com.fingertip.catalog.R;
import com.fingertip.catalog.api.DeleteCustomerGroupAPI;
import com.fingertip.catalog.holder.CheckdatacatHolder;
import com.fingertip.catalog.holder.CustomerGroupHolder;
import com.fingertip.catalog.interfaces.NetworkCallback;
import com.fingertip.catalog.model.CustomerGroupBO;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lenevo on 22-05-2018.
 */

public class CustomerGroupListAdapter extends RecyclerView.Adapter<CustomerGroupHolder> implements NetworkCallback{
    private List<CustomerGroupBO> itemList3;
    private Context context;
    public  static ArrayList<String> catgroup_list = new ArrayList<String>();
    public  static ArrayList<String> catgroup_list_name = new ArrayList<String>();
    private NetworkCallback mCallback;
    Activity activity;
    SharedPreferences pref45;
    ArrayList<CheckBox> checkArray = new ArrayList<CheckBox>();
    private int selectedPosition = -1;
    String user_type,saveduser;
    public  static ArrayList<String> dose_list2 = new ArrayList<String>();
    private Boolean flag;
    private int stat;
    public CustomerGroupListAdapter(Activity activity, Context context, List<CustomerGroupBO> itemList, NetworkCallback mCallback) {
        this.itemList3 = itemList;
        this.context = context;
        this.activity=activity;
        this.mCallback=mCallback;
        this.flag=flag;
    }


    @Override
    public CustomerGroupHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView =  null;

        layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cus_group_adapter, null);

        CustomerGroupHolder rcv = new CustomerGroupHolder(layoutView);

            pref45 = context.getSharedPreferences("Login", MODE_PRIVATE);

            saveduser = pref45.getString("user", null);
            user_type = pref45.getString("usertype", null);






        return rcv;
    }


    @Override
    public void onBindViewHolder(final CustomerGroupHolder holder, int position) {

        Typeface typeFace1 = Typeface.createFromAsset(context.getAssets(), "robotoRegular.ttf");
        final CustomerGroupBO customerGroupBO = itemList3.get(position);

        holder.textView.setText(customerGroupBO.getName());
if(customerGroupBO.getStatus().equals("1"))
{
    holder.textView1.setText("Active");
}
else {
    holder.textView1.setText("Inactive");
}

        holder.textView.setTypeface(typeFace1);
        holder.textView1.setTypeface(typeFace1);



holder.deleteGroup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        DeleteCustomerGroupAPI deleteCustomerGroupAPI=new DeleteCustomerGroupAPI(context,mCallback);
        deleteCustomerGroupAPI.processDelete(customerGroupBO.getId());
        mCallback.updateScreen1();

    }
});
        holder.editGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, EditCustomerGroup.class);
                intent.putExtra("id",customerGroupBO.getId());
                intent.putExtra("name",customerGroupBO.getName());
                intent.putExtra("status",customerGroupBO.getStatus());
                context.startActivity(intent);

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





}





