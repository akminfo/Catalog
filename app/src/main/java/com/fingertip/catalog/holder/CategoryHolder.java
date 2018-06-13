package com.fingertip.catalog.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fingertip.catalog.R;

/**
 * Created by lenevo on 08-05-2018.
 */

public class CategoryHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public TextView textView1;
    public TextView textView2;
    public TextView textView3;

    public ImageView editCategory;
    public ImageView deleteCategory;


    public CategoryHolder(View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.catname);
        textView1 = (TextView) itemView.findViewById(R.id.sortorder);
        textView2 = (TextView) itemView.findViewById(R.id.status);

        editCategory = (ImageView) itemView.findViewById(R.id.editcat);
        deleteCategory = (ImageView) itemView.findViewById(R.id.delcat);


    }
}



