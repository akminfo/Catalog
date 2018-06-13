package com.fingertip.catalog.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fingertip.catalog.R;

/**
 * Created by lenevo on 28-05-2018.
 */

public class OrderViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public TextView textView1;
    public TextView textView2;
    public TextView textView3;
    public ImageView button;

  public EditText editText;


    public ImageView imgpro;



    public OrderViewHolder(View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.pronametext);
        textView1 = (TextView) itemView.findViewById(R.id.skutext);
        textView2 = (TextView) itemView.findViewById(R.id.unittext);
        textView3 = (TextView) itemView.findViewById(R.id.totaltext);
     editText=(EditText)itemView.findViewById(R.id.quantitytext);
        imgpro=(ImageView)itemView.findViewById(R.id.proimage);
button=(ImageView)itemView.findViewById(R.id.savequantity);



    }
}

