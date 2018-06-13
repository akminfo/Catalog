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

import com.fingertip.catalog.EditCoupon;
import com.fingertip.catalog.R;
import com.fingertip.catalog.api.DeleteCouponAPI;
import com.fingertip.catalog.api.DeleteOrderAPI;
import com.fingertip.catalog.holder.CouponHolder;
import com.fingertip.catalog.holder.OrderListHolder;
import com.fingertip.catalog.interfaces.NetworkCallback;
import com.fingertip.catalog.model.CouponBO;
import com.fingertip.catalog.model.OrdersBO;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lenevo on 19-05-2018.
 */

public class CouponListAdapter  extends RecyclerView.Adapter<CouponHolder> implements NetworkCallback {
    private List<CouponBO> itemList3;
    private Context context;

    private NetworkCallback mCallback;
    Activity activity;
    SharedPreferences pref45;
    String user_type,saveduser;

    private int stat;
    public CouponListAdapter(Activity activity, Context context, List<CouponBO> itemList,NetworkCallback mCallback) {
        this.itemList3 = itemList;
        this.context = context;
        this.activity=activity;
        this.mCallback=mCallback;
    }


    @Override
    public CouponHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView =  null;

        layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.coup_list_adapter, null);

        CouponHolder rcv = new CouponHolder(layoutView);
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
    public void onBindViewHolder(CouponHolder holder, int position) {
        Typeface typeFace1 = Typeface.createFromAsset(context.getAssets(), "robotoRegular.ttf");
        final CouponBO couponBO = itemList3.get(position);
        holder.textView.setText(couponBO.getName());
        holder.textView1.setText(couponBO.getCode());
        holder.textView2.setText(couponBO.getDis());
        holder.textView3.setText(couponBO.getDatestart());
        holder.textView4.setText(couponBO.getDateend());
        if(couponBO.getStatus().equals("1")) {
            holder.textView5.setText("Active");
        }
        else {
            holder.textView5.setText("Deactive");
        }






        holder.textView.setTypeface(typeFace1);
        holder.textView1.setTypeface(typeFace1);
        holder.textView2.setTypeface(typeFace1);
        holder.textView3.setTypeface(typeFace1);
        holder.textView4.setTypeface(typeFace1);
        holder.textView5.setTypeface(typeFace1);



        holder.deleteCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteCouponAPI deleteCouponAPI=new DeleteCouponAPI(context,mCallback);
                deleteCouponAPI.processDelete(couponBO.getId());
                mCallback.updateScreen1();
            }
        });
        holder.editCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, EditCoupon.class);
                intent.putExtra("coup_id",couponBO.getId());
                intent.putExtra("coup_name",couponBO.getName());
                intent.putExtra("coup_code",couponBO.getCode());
                intent.putExtra("coup_type",couponBO.getType());
                intent.putExtra("coup_group",couponBO.getCusgroup());
                intent.putExtra("coup_dis",couponBO.getDis());
                intent.putExtra("coup_amt",couponBO.getTot());
                intent.putExtra("coup_start",couponBO.getDatestart());
                intent.putExtra("coup_end",couponBO.getDateend());
                intent.putExtra("coup_use_coupon",couponBO.getUser_percoupon());
                intent.putExtra("coup_use_cust",couponBO.getUser_percust());
                intent.putExtra("coup_group_id",couponBO.getCusgroup());
                intent.putExtra("status",couponBO.getStatus());
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




