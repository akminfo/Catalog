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
import com.fingertip.catalog.OrderView;
import com.fingertip.catalog.R;
import com.fingertip.catalog.api.DeleteCategoryAPI;
import com.fingertip.catalog.api.DeleteOrderAPI;
import com.fingertip.catalog.holder.CategoryHolder;
import com.fingertip.catalog.holder.OrderListHolder;
import com.fingertip.catalog.interfaces.NetworkCallback;
import com.fingertip.catalog.model.CategoryBO;
import com.fingertip.catalog.model.OrdersBO;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lenevo on 19-05-2018.
 */

public class OrderListAdapter extends RecyclerView.Adapter<OrderListHolder> implements NetworkCallback {
    private List<OrdersBO> itemList3;
    private Context context;

    private NetworkCallback mCallback;
    Activity activity;
    SharedPreferences pref45;
    String user_type,saveduser;

    private int stat;
    public OrderListAdapter(Activity activity, Context context, List<OrdersBO> itemList,NetworkCallback mCallback) {
        this.itemList3 = itemList;
        this.context = context;
        this.activity=activity;
        this.mCallback=mCallback;
    }


    @Override
    public OrderListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView =  null;

        layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list_adapter, null);

        OrderListHolder rcv = new OrderListHolder(layoutView);
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
    public void onBindViewHolder(OrderListHolder holder, int position) {
        Typeface typeFace1 = Typeface.createFromAsset(context.getAssets(), "robotoRegular.ttf");
        final OrdersBO ordersBO = itemList3.get(position);
        holder.textView.setText(ordersBO.getDate());
        holder.textView1.setText(ordersBO.getId());
        holder.textView2.setText(ordersBO.getOrderNo());
        holder.textView3.setText(ordersBO.getCusfname()+" "+ordersBO.getCuslname());
        holder.textView4.setText(ordersBO.getEmail());
        holder.textView5.setText(ordersBO.getMobile());
        holder.textView6.setText(ordersBO.getTotal());
        holder.textView7.setText(ordersBO.getOrderstat());





        holder.textView.setTypeface(typeFace1);
        holder.textView1.setTypeface(typeFace1);
        holder.textView2.setTypeface(typeFace1);
        holder.textView3.setTypeface(typeFace1);
        holder.textView4.setTypeface(typeFace1);
        holder.textView5.setTypeface(typeFace1);
        holder.textView6.setTypeface(typeFace1);
        holder.textView7.setTypeface(typeFace1);


        holder.deleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteOrderAPI deleteOrderAPI=new DeleteOrderAPI(context,mCallback);
                deleteOrderAPI.processDelete(ordersBO.getId());
                mCallback.updateScreen1();
            }
        });

holder.viewOrder.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(context,OrderView.class);
        intent.putExtra("id",ordersBO.getId());
        intent.putExtra("comments",ordersBO.getComment());
        intent.putExtra("inv_id",ordersBO.getOrderNo());
        intent.putExtra("date",ordersBO.getDate());
        intent.putExtra("status",ordersBO.getOrderstat());
        intent.putExtra("name",ordersBO.getCusfname()+" "+ordersBO.getCuslname());
        intent.putExtra("email",ordersBO.getEmail());
        intent.putExtra("mobile",ordersBO.getMobile());
        intent.putExtra("address",ordersBO.getAddress());
        intent.putExtra("country",ordersBO.getCountry());
        intent.putExtra("state",ordersBO.getState());
        intent.putExtra("city",ordersBO.getCity());
        intent.putExtra("pin",ordersBO.getPincode());
        intent.putExtra("shipping_charge",ordersBO.getShip_charge());
        intent.putExtra("approved",ordersBO.getOrd_app());
        intent.putExtra("remark",ordersBO.getRemark());
        intent.putExtra("total",ordersBO.getTotal());
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



