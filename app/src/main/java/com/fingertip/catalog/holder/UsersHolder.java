package com.fingertip.catalog.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fingertip.catalog.R;

/**
 * Created by lenevo on 23-05-2018.
 */

public class UsersHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public TextView textView1;
    public TextView textView2;
    public TextView textView3;
    public TextView textView4;


    public ImageView editGroup;
    public ImageView deleteGroup;


    public UsersHolder(View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.useadminname);
        textView1 = (TextView) itemView.findViewById(R.id.useadminstat);

        editGroup=(ImageView)itemView.findViewById(R.id.editadmin);
        deleteGroup=(ImageView)itemView.findViewById(R.id.deladmin);



    }
}
