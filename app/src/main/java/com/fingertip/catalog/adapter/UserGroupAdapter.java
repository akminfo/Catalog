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

import com.fingertip.catalog.EditCustomerGroup;
import com.fingertip.catalog.EditUserGroup;
import com.fingertip.catalog.R;
import com.fingertip.catalog.api.DeleteCustomerGroupAPI;
import com.fingertip.catalog.api.DeleteUserGroupAPI;
import com.fingertip.catalog.holder.CustomerGroupHolder;
import com.fingertip.catalog.holder.UserGroupHolder;
import com.fingertip.catalog.interfaces.NetworkCallback;
import com.fingertip.catalog.model.CustomerGroupBO;
import com.fingertip.catalog.model.UserGroupBO;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lenevo on 23-05-2018.
 */

public class UserGroupAdapter  extends RecyclerView.Adapter<UserGroupHolder> implements NetworkCallback {
    private List<UserGroupBO> itemList3;
    private Context context;
    private NetworkCallback mCallback;
    Activity activity;
    SharedPreferences pref45;
    String user_type,saveduser;

    private Boolean flag;

    public UserGroupAdapter(Activity activity, Context context, List<UserGroupBO> itemList, NetworkCallback mCallback) {
        this.itemList3 = itemList;
        this.context = context;
        this.activity=activity;
        this.mCallback=mCallback;
        this.flag=flag;
    }


    @Override
    public UserGroupHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView =  null;

        layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_gr_adapter, null);

        UserGroupHolder rcv = new UserGroupHolder(layoutView);

        pref45 = context.getSharedPreferences("Login", MODE_PRIVATE);

        saveduser = pref45.getString("user", null);
        user_type = pref45.getString("usertype", null);






        return rcv;
    }


    @Override
    public void onBindViewHolder(final UserGroupHolder holder, int position) {

        Typeface typeFace1 = Typeface.createFromAsset(context.getAssets(), "robotoRegular.ttf");
        final UserGroupBO userGroupBO = itemList3.get(position);

        holder.textView.setText(userGroupBO.getUsername());

        holder.textView.setTypeface(typeFace1);




        holder.deleteGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteUserGroupAPI deleteUserGroupAPI=new DeleteUserGroupAPI(context,mCallback);
                deleteUserGroupAPI.processDelete(userGroupBO.getId());
                mCallback.updateScreen1();

            }
        });
        holder.editGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, EditUserGroup.class);
                intent.putExtra("id",userGroupBO.getId());
                intent.putExtra("name",userGroupBO.getUsername());
                intent.putExtra("access",userGroupBO.getAccess());
                intent.putExtra("modify",userGroupBO.getModify());
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






