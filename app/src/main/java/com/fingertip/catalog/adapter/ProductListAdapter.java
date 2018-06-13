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
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.fingertip.catalog.EditProduct;
import com.fingertip.catalog.R;
import com.fingertip.catalog.api.DeleteProductAPI;
import com.fingertip.catalog.api.UpdatePriceAPI;
import com.fingertip.catalog.api.UpdateQuantityAPI;
import com.fingertip.catalog.callback.Config;
import com.fingertip.catalog.holder.ProductListHolder;
import com.fingertip.catalog.interfaces.NetworkCallback;
import com.fingertip.catalog.interfaces.UpdateCallback;
import com.fingertip.catalog.model.ProductBO;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lenevo on 10-05-2018.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListHolder> implements NetworkCallback,UpdateCallback {
    private List<ProductBO> itemList3;
    private Context context;
UpdateCallback updateCallback;
    private NetworkCallback mCallback;
    Activity activity;
    SharedPreferences pref45;
    String user_type,saveduser;
    StringBuilder builder1,builder;
private Boolean flag;
    public  static ArrayList<String> dose_list = new ArrayList<String>();
    private int stat;
    public ProductListAdapter(Activity activity, Context context, List<ProductBO> itemList, NetworkCallback mCallback,Boolean flag) {
        this.itemList3 = itemList;
        this.context = context;
        this.activity=activity;
        this.mCallback=mCallback;
        this.flag=flag;
    }


    @Override
    public ProductListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView =  null;

        layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.productlist_adapter, null);



        ProductListHolder rcv;  rcv = new ProductListHolder(layoutView);
        if(flag) {
            rcv.checkBox.setChecked(true);
            rcv.checkBox.setClickable(false);
        }
        else {
            rcv.checkBox.setChecked(false);
            rcv.checkBox.setClickable(true);
        }


        try {

            pref45 = context.getSharedPreferences("Login", MODE_PRIVATE);
            saveduser = pref45.getString("id", null);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return rcv;
    }


    @Override
    public void onBindViewHolder(final ProductListHolder holder, final int position) {

        Typeface typeFace1 = Typeface.createFromAsset(context.getAssets(), "robotoRegular.ttf");
        final ProductBO productBO = itemList3.get(position);


        if(!productBO.getImage().isEmpty()) {

            UrlImageViewHelper.setUrlDrawable(holder.imageView, Config.IMAGEURL+productBO.getImage());

        }
        else {
            UrlImageViewHelper.setUrlDrawable(holder.imageView,null);
            holder.imageView.setImageResource(R.drawable.noimage);
        }
        holder.textView.setText(productBO.getName());
try {
    builder = new StringBuilder(productBO.getCat());

    builder.deleteCharAt(productBO.getCat().length() - 1);
}
catch (Exception e)
{
    e.printStackTrace();
}



        holder.textView1.setText(builder);
        holder.textView2.setText(productBO.getSku());
        holder.editText.setText(productBO.getPrice());
        holder.editText1.setText(productBO.getQuantity());
        holder.textView.setTypeface(typeFace1);
        holder.textView1.setTypeface(typeFace1);
        holder.textView2.setTypeface(typeFace1);

        holder.editText.setTypeface(typeFace1);
        holder.editText1.setTypeface(typeFace1);
holder.updatePrice.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        System.out.println("productid"+productBO.getId());
        UpdatePriceAPI updatePriceAPI =new UpdatePriceAPI(context,ProductListAdapter.this);
        updatePriceAPI.processUpdate(productBO.getId(),holder.editText.getText().toString());


    }
});
        holder.updateQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("productid"+productBO.getId());
                UpdateQuantityAPI updateQuantityAPI =new UpdateQuantityAPI(context,ProductListAdapter.this);
                updateQuantityAPI.processUpdateQuant(productBO.getId(),holder.editText1.getText().toString());


            }
        });
        updateCallback=new UpdateCallback() {
            @Override
            public void updateScreen2(String data) {
                System.out.println("data is");
            }
        };



        holder.editCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(context, EditCategory.class);
//                intent.putExtra("cat_id",categoryBO.getId());
//                intent.putExtra("cat_name",categoryBO.getCatname());
//                intent.putExtra("cat_sort",categoryBO.getSort());
//                intent.putExtra("cat_desc",categoryBO.getDesc());
//                intent.putExtra("meta_title",categoryBO.getMeta_title());
//                intent.putExtra("meta_desc",categoryBO.getMeta_decs());
//                intent.putExtra("meta_key",categoryBO.getMeta_key());
//                intent.putExtra("top",categoryBO.getTop());
//                intent.putExtra("status",categoryBO.getStatus());
//                context.startActivity(intent);
            }
        });
        holder.deleteCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DeleteCategoryAPI deleteCategoryAPI=new DeleteCategoryAPI(context,mCallback);
//                deleteCategoryAPI.processDelete(categoryBO.getId());
//                mCallback.updateScreen1();
            }
        });

//        holder.checkBox.setChecked(itemList3.get(position).isSelected());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dose_list.add(productBO.getId());
                    for(int i=0;i<dose_list.size();i++)
                    {
                        System.out.println("dopse items"+dose_list.get(i).toString());
                    }
//                    boolean isSelected = ((CheckBox)buttonView).isChecked();
//                    itemList3.get(position).setSelected(isSelected);
                }
                else {
//                    boolean isSelected = ((CheckBox)buttonView).isChecked();
//                    itemList3.get(position).setSelected(isSelected);
                    dose_list.remove(productBO.getId());
                    for(int i=0;i<dose_list.size();i++)
                    {
                        System.out.println("dopse items"+dose_list.get(i).toString());
                    }
                }
            }
        });
        holder.editCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    builder1 = new StringBuilder(productBO.getCat_id());
                    builder1.deleteCharAt(productBO.getCat_id().length() - 1);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                System.out.println("catds"+productBO.getCat_id()+builder1);
                Intent intent=new Intent(context, EditProduct.class);
                intent.putExtra("pro_id",productBO.getId());
                intent.putExtra("name",productBO.getName());
                intent.putExtra("cat_ids",String.valueOf(builder1));
                intent.putExtra("desc",productBO.getDesc());
                intent.putExtra("key_feat",productBO.getKeyfeat());
                intent.putExtra("meta_name",productBO.getMeta_title());
                intent.putExtra("meta_desc",productBO.getMeta_desc());
                intent.putExtra("meta_key",productBO.getMeta_title());
                intent.putExtra("sku",productBO.getSku());
                intent.putExtra("quantity",holder.editText1.getText().toString());
                intent.putExtra("price",holder.editText.getText().toString());
                intent.putExtra("sort",productBO.getSort());
                intent.putExtra("status",productBO.getStatus());
                intent.putExtra("image",productBO.getImage());
                intent.putExtra("count",productBO.getImg_count());
                intent.putExtra("proimage1",productBO.getImage1());
                intent.putExtra("proimage2",productBO.getImage2());
                intent.putExtra("proimage3",productBO.getImage3());
                intent.putExtra("proimage4",productBO.getImage4());
                intent.putExtra("proimage5",productBO.getImage5());
                intent.putExtra("proimage6",productBO.getImage6());
                intent.putExtra("proimage7",productBO.getImage7());
                intent.putExtra("proimage8",productBO.getImage8());
                intent.putExtra("proimage9",productBO.getImage9());
                intent.putExtra("proimage10",productBO.getImage10());
                intent.putExtra("sort1",productBO.getImgsort1());
                intent.putExtra("sort2",productBO.getImgsort2());
                intent.putExtra("sort3",productBO.getImgsort3());
                intent.putExtra("sort4",productBO.getImgsort4());
                intent.putExtra("sort5",productBO.getImgsort5());
                intent.putExtra("sort6",productBO.getImgsort6());
                intent.putExtra("sort7",productBO.getImgsort7());
                intent.putExtra("sort8",productBO.getImgsort8());
                intent.putExtra("sort9",productBO.getImgsort9());
                intent.putExtra("sort10",productBO.getImgsort10());
                intent.putExtra("img_id1",productBO.getImg1_id1());
                intent.putExtra("img_id2",productBO.getImg1_id2());
                intent.putExtra("img_id3",productBO.getImg1_id3());
                intent.putExtra("img_id4",productBO.getImg1_id4());
                intent.putExtra("img_id5",productBO.getImg1_id5());
                intent.putExtra("img_id6",productBO.getImg1_id6());
                intent.putExtra("img_id7",productBO.getImg1_id7());
                intent.putExtra("img_id8",productBO.getImg1_id8());
                intent.putExtra("img_id9",productBO.getImg1_id9());
                intent.putExtra("img_id10",productBO.getImg1_id10());



                context.startActivity(intent);
            }
        });
        holder.deleteCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteProductAPI deleteProductAPI=new DeleteProductAPI(context,ProductListAdapter.this);
                deleteProductAPI.processDelete(productBO.getId());
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
        try
        {
            if (tag.compareTo(Config.TAG_UPDATE_PRICE_LIST) == 0) {
                Log.d("datahoome", data);
                JSONObject jsonObject=new JSONObject(data);
                if(jsonObject.getString("status").equals("Success"))
                {

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
    public void deleteCat(String catid)
    {


    }


    @Override
    public void updateScreen2(String data) {

    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    public ArrayList<ProductBO> getSelectActorList(){
        ArrayList<ProductBO> list = new ArrayList<>();
        for(int i=0;i<itemList3.size();i++){
            if(itemList3.get(i).isSelected())
                list.add(itemList3.get(i));
        }
        return list;
    }
public void deleteProduct(String id)
{}
}



