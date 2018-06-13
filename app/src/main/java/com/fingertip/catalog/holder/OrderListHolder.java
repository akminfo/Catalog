package com.fingertip.catalog.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fingertip.catalog.R;

/**
 * Created by lenevo on 19-05-2018.
 */

public class OrderListHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public TextView textView1;
    public TextView textView2;
    public TextView textView3;
    public TextView textView4;
    public TextView textView5;
    public TextView textView6;
    public TextView textView7;


    public ImageView viewOrder;
    public ImageView deleteOrder;


    public OrderListHolder(View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.date);
        textView1 = (TextView) itemView.findViewById(R.id.serial);
        textView2 = (TextView) itemView.findViewById(R.id.orderno);
        textView3 = (TextView) itemView.findViewById(R.id.cusname1);
        textView4 = (TextView) itemView.findViewById(R.id.orderemail);
        textView5 = (TextView) itemView.findViewById(R.id.ordermob);
        textView6 = (TextView) itemView.findViewById(R.id.ordertotal);
        textView7 = (TextView) itemView.findViewById(R.id.orderstat);
        viewOrder=(ImageView)itemView.findViewById(R.id.vieworder);
        deleteOrder=(ImageView)itemView.findViewById(R.id.delorder);



    }
}
