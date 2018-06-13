package com.fingertip.catalog.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fingertip.catalog.R;

/**
 * Created by lenevo on 18-05-2018.
 */

public class CustomerHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public TextView textView1;
    public TextView textView2;
    public TextView textView3;
    public TextView textView4;


    public ImageView editCustomer;
    public ImageView deleteCustomer;


    public CustomerHolder(View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.cusname);
        textView1 = (TextView) itemView.findViewById(R.id.cusemail);
        textView2 = (TextView) itemView.findViewById(R.id.cususer);
        textView3 = (TextView) itemView.findViewById(R.id.cusmobile);
        textView4 = (TextView) itemView.findViewById(R.id.cusstat);
editCustomer=(ImageView)itemView.findViewById(R.id.editcus);
        deleteCustomer=(ImageView)itemView.findViewById(R.id.delcus);



    }
}



