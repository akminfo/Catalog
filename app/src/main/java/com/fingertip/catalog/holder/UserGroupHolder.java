package com.fingertip.catalog.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fingertip.catalog.R;

/**
 * Created by lenevo on 23-05-2018.
 */

public class UserGroupHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public TextView textView1;
    public TextView textView2;
    public TextView textView3;
    public TextView textView4;


    public ImageView editGroup;
    public ImageView deleteGroup;


    public UserGroupHolder(View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.usename);

        editGroup=(ImageView)itemView.findViewById(R.id.edituse);
        deleteGroup=(ImageView)itemView.findViewById(R.id.deluse);



    }
}

