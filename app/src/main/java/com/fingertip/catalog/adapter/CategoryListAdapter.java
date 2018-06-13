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
import com.fingertip.catalog.R;
import com.fingertip.catalog.api.DeleteCategoryAPI;
import com.fingertip.catalog.holder.CategoryHolder;
import com.fingertip.catalog.interfaces.NetworkCallback;
import com.fingertip.catalog.model.CategoryBO;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lenevo on 08-05-2018.
 */

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryHolder> implements NetworkCallback {
    private List<CategoryBO> itemList3;
    private Context context;

    private NetworkCallback mCallback;
    Activity activity;
    SharedPreferences pref45;
    String user_type,saveduser;

    private int stat;
    public CategoryListAdapter(Activity activity, Context context, List<CategoryBO> itemList,NetworkCallback mCallback) {
        this.itemList3 = itemList;
        this.context = context;
        this.activity=activity;
        this.mCallback=mCallback;
    }


    @Override
    public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView =  null;

        layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorylist_layout, null);

        CategoryHolder rcv = new CategoryHolder(layoutView);
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
    public void onBindViewHolder(CategoryHolder holder, int position) {
        Typeface typeFace1 = Typeface.createFromAsset(context.getAssets(), "robotoRegular.ttf");
        final CategoryBO categoryBO = itemList3.get(position);
        holder.textView.setText(categoryBO.getCatname());
        holder.textView1.setText(categoryBO.getSort());
        if(categoryBO.getStatus().equals("1")) {
            holder.textView2.setText("Enable");
        }
        else {
            holder.textView2.setText("Disable");
        }




        holder.textView.setTypeface(typeFace1);
        holder.textView1.setTypeface(typeFace1);
        holder.textView2.setTypeface(typeFace1);

holder.editCategory.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(context, EditCategory.class);
        intent.putExtra("cat_id",categoryBO.getId());
        intent.putExtra("cat_name",categoryBO.getCatname());
        intent.putExtra("cat_sort",categoryBO.getSort());
        intent.putExtra("cat_desc",categoryBO.getDesc());
        intent.putExtra("meta_title",categoryBO.getMeta_title());
        intent.putExtra("meta_desc",categoryBO.getMeta_decs());
        intent.putExtra("meta_key",categoryBO.getMeta_key());
        intent.putExtra("top",categoryBO.getTop());
        intent.putExtra("status",categoryBO.getStatus());
        context.startActivity(intent);
    }
});
        holder.deleteCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteCategoryAPI deleteCategoryAPI=new DeleteCategoryAPI(context,mCallback);
                deleteCategoryAPI.processDelete(categoryBO.getId());
                mCallback.updateScreen1();
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

    public void deleteCat(String catid)
{


}



}


