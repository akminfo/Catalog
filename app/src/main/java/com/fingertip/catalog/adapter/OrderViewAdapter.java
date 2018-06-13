package com.fingertip.catalog.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fingertip.catalog.OrderView;
import com.fingertip.catalog.R;
import com.fingertip.catalog.api.DeleteOrderAPI;
import com.fingertip.catalog.api.OrderQuantityAPI;
import com.fingertip.catalog.callback.Config;
import com.fingertip.catalog.holder.OrderListHolder;
import com.fingertip.catalog.holder.OrderViewHolder;
import com.fingertip.catalog.interfaces.NetworkCallback;
import com.fingertip.catalog.model.OrderViewBO;
import com.fingertip.catalog.model.OrdersBO;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import org.json.JSONObject;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lenevo on 28-05-2018.
 */

public class OrderViewAdapter extends RecyclerView.Adapter<OrderViewHolder> implements NetworkCallback {
    private List<OrderViewBO> itemList3;
    private Context context;

    private NetworkCallback mCallback;
    Activity activity;
    SharedPreferences pref45;
    String user_type,saveduser;

    private int stat;
    public OrderViewAdapter(Activity activity, Context context, List<OrderViewBO> itemList, NetworkCallback mCallback) {
        this.itemList3 = itemList;
        this.context = context;
        this.activity=activity;
        this.mCallback=mCallback;
    }


    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView =  null;

        layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderadapter, null);

        OrderViewHolder rcv = new OrderViewHolder(layoutView);
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
    public void onBindViewHolder(final OrderViewHolder holder, int position) {
        Typeface typeFace1 = Typeface.createFromAsset(context.getAssets(), "robotoRegular.ttf");
        final OrderViewBO orderViewBO = itemList3.get(position);
        holder.textView.setText(orderViewBO.getName());
        holder.textView1.setText(orderViewBO.getSku());
        holder.editText.setText(orderViewBO.getQuant());
        holder.textView2.setText(orderViewBO.getUnit());
        holder.textView3.setText(orderViewBO.getTotal());


        if(!orderViewBO.getImage().isEmpty()) {

            UrlImageViewHelper.setUrlDrawable(holder.imgpro, orderViewBO.getImage());

        }
        else {
            UrlImageViewHelper.setUrlDrawable(holder.imgpro,null);
            holder.imgpro.setImageResource(R.drawable.noimage);
        }


        holder.textView.setTypeface(typeFace1);
        holder.textView1.setTypeface(typeFace1);
        holder.textView2.setTypeface(typeFace1);
        holder.textView3.setTypeface(typeFace1);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(holder.editText.getText().toString().isEmpty())) {
                    OrderQuantityAPI orderQuantityAPI = new OrderQuantityAPI(context, OrderViewAdapter.this);
                    orderQuantityAPI.processUpdateQuantity(orderViewBO.getOrderid(), orderViewBO.getProid(), holder.editText.getText().toString(), orderViewBO.getUnit(), orderViewBO.getTotal(), OrderView.text6.getText().toString());
               mCallback.updateScreen1();
                }
                else {
                    Toast.makeText(context, "Please enter quantity", Toast.LENGTH_SHORT).show();
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
        try {
            if (tag.compareTo(Config.TAG_ORDERUPDATELIST) == 0) {
                Log.d("datahoome1", data);
                JSONObject json=new JSONObject(data);
                if(json.getString("status").equals("Success")) {

                   OrderView.text6.setText(json.getString("order_total"));
                }

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void updateScreen1() {

    }



}



