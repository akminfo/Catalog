package com.fingertip.catalog.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fingertip.catalog.R;

/**
 * Created by lenevo on 19-05-2018.
 */

public class CouponHolder  extends RecyclerView.ViewHolder {
    public TextView textView;
    public TextView textView1;
    public TextView textView2;
    public TextView textView3;
    public TextView textView4;
    public TextView textView5;
    public TextView textView6;
    public TextView textView7;


    public ImageView editCoupon;
    public ImageView deleteCoupon;


    public CouponHolder(View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.coupname1);
        textView1 = (TextView) itemView.findViewById(R.id.coupcode1);
        textView2 = (TextView) itemView.findViewById(R.id.coupdis1);
        textView3 = (TextView) itemView.findViewById(R.id.coupdatestart1);
        textView4 = (TextView) itemView.findViewById(R.id.coupdateend1);
        textView5 = (TextView) itemView.findViewById(R.id.coupstat1);

        editCoupon=(ImageView)itemView.findViewById(R.id.editcoup);
        deleteCoupon=(ImageView)itemView.findViewById(R.id.delcoup);



    }
}
