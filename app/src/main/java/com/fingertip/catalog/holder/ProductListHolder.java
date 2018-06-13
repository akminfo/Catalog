package com.fingertip.catalog.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fingertip.catalog.R;

/**
 * Created by lenevo on 10-05-2018.
 */

public class ProductListHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public TextView textView1;
    public TextView textView2;
    public EditText editText;
    public EditText editText1;
    public ImageView imageView;
    public ImageView editCategory;
    public ImageView deleteCategory;
    public ImageView updatePrice;
    public ImageView updateQuantity;
    public CheckBox checkBox;


    public ProductListHolder(View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.proname1);
        textView1 = (TextView) itemView.findViewById(R.id.procat1);
        textView2 = (TextView) itemView.findViewById(R.id.sku1);
        editText=(EditText)itemView.findViewById(R.id.pricecount);
        editText1=(EditText)itemView.findViewById(R.id.quantitycount);
checkBox=(CheckBox)itemView.findViewById(R.id.ch);
imageView=(ImageView)itemView.findViewById(R.id.proimage);
        editCategory = (ImageView) itemView.findViewById(R.id.editpro);
        deleteCategory = (ImageView) itemView.findViewById(R.id.delpro);
        updatePrice=(ImageView)itemView.findViewById(R.id.saveprice);
        updateQuantity=(ImageView)itemView.findViewById(R.id.savequant);


    }
}



