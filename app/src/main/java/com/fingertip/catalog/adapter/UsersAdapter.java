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
import com.fingertip.catalog.EditUser;
import com.fingertip.catalog.EditUserGroup;
import com.fingertip.catalog.R;
import com.fingertip.catalog.api.DeleteCustomerGroupAPI;
import com.fingertip.catalog.api.DeleteUserAPI;
import com.fingertip.catalog.holder.CustomerGroupHolder;
import com.fingertip.catalog.holder.UsersHolder;
import com.fingertip.catalog.interfaces.NetworkCallback;
import com.fingertip.catalog.model.CustomerGroupBO;
import com.fingertip.catalog.model.UsersBO;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lenevo on 23-05-2018.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersHolder> implements NetworkCallback {
    private List<UsersBO> itemList3;
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
    public UsersAdapter(Activity activity, Context context, List<UsersBO> itemList, NetworkCallback mCallback) {
        this.itemList3 = itemList;
        this.context = context;
        this.activity=activity;
        this.mCallback=mCallback;
        this.flag=flag;
    }


    @Override
    public UsersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView =  null;

        layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_admin_adapter, null);

        UsersHolder rcv = new UsersHolder(layoutView);

        pref45 = context.getSharedPreferences("Login", MODE_PRIVATE);

        saveduser = pref45.getString("user", null);
        user_type = pref45.getString("usertype", null);






        return rcv;
    }


    @Override
    public void onBindViewHolder(final UsersHolder holder, int position) {

        Typeface typeFace1 = Typeface.createFromAsset(context.getAssets(), "robotoRegular.ttf");
        final UsersBO usersBO = itemList3.get(position);

        holder.textView.setText(usersBO.getUsername());
        if(usersBO.getStatus().equals("1"))
        {
            holder.textView1.setText("Enabled");
        }
        else {
            holder.textView1.setText("Disabled");
        }

        holder.textView.setTypeface(typeFace1);
        holder.textView1.setTypeface(typeFace1);



        holder.deleteGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteUserAPI deleteUserAPI=new DeleteUserAPI(context,mCallback);
                deleteUserAPI.processDelete(usersBO.getId());
                mCallback.updateScreen1();

            }
        });
        holder.editGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, EditUser.class);
                intent.putExtra("id",usersBO.getId());
                intent.putExtra("name",usersBO.getUsername());
                intent.putExtra("usergroup",usersBO.getUsegr());
                intent.putExtra("first",usersBO.getFname());
                intent.putExtra("last",usersBO.getLname());
                intent.putExtra("gender",usersBO.getGender());
                intent.putExtra("email",usersBO.getEmail());
                intent.putExtra("mobile",usersBO.getMob());
                intent.putExtra("pass",usersBO.getPass());
                intent.putExtra("status",usersBO.getStatus());
                intent.putExtra("gstin",usersBO.getGstin());



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





