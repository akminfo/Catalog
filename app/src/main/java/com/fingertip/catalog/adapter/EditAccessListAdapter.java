package com.fingertip.catalog.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.fingertip.catalog.R;
import com.fingertip.catalog.holder.CheckdatacatHolder;
import com.fingertip.catalog.interfaces.NetworkCallback;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lenevo on 23-05-2018.
 */

public class EditAccessListAdapter extends RecyclerView.Adapter<CheckdatacatHolder> implements CompoundButton.OnCheckedChangeListener{

    private Context context;
    public  static ArrayList<String> access_list = new ArrayList<String>();
    public  static ArrayList<String> catgroup_list_name = new ArrayList<String>();
    private NetworkCallback mCallback;
    Activity activity;
    SharedPreferences pref45;
    ArrayList<CheckBox> checkArray = new ArrayList<CheckBox>();
    private int selectedPosition = -1;
    String user_type,saveduser;
    public  static ArrayList<String> dose_list2 = new ArrayList<String>();
    private Boolean flag;
    List<String> n = new ArrayList<String>();
    ArrayList<String> itemList3 = new ArrayList<String>();
    private int stat;
    public static SparseBooleanArray mCheckStates;
    public EditAccessListAdapter(Activity activity, Context context, ArrayList itemList,List n) {
        this.itemList3 = itemList;
        this.context = context;
        this.activity=activity;
        this.n=n;

        mCheckStates = new SparseBooleanArray(n.size());

    }


    @Override
    public CheckdatacatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView =  null;

        layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_datacat, null);

        CheckdatacatHolder rcv = new CheckdatacatHolder(layoutView);

        pref45 = context.getSharedPreferences("Login", MODE_PRIVATE);

        saveduser = pref45.getString("user", null);
        user_type = pref45.getString("usertype", null);




        return rcv;
    }


    @Override
    public void onBindViewHolder(final CheckdatacatHolder holder, final int position) {

        Typeface typeFace1 = Typeface.createFromAsset(context.getAssets(), "robotoRegular.ttf");

        holder.checkBox.setText(itemList3.get(position).toString());
        holder.checkBox.setChecked(mCheckStates.get(position, false));
        holder.checkBox.setOnCheckedChangeListener(this);
        holder.checkBox.setTag(position);
        holder.checkBox.setTypeface(typeFace1);
        for (int i = 0; i < itemList3.size(); i++) {

try {
    if (itemList3.get(position).toString().equals(n.get(i).toString())) {
        System.out.println("eaqq" + n.get(i).toString());
        holder.checkBox.setChecked(true);
    }
}
catch (Exception e)
{
    e.printStackTrace();
}

        }
//
//






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






public boolean isChecked(int position) {
        return mCheckStates.get(position, false);
        }

public void setChecked(int position, boolean isChecked) {
        mCheckStates.put(position, isChecked);
        }

public void toggle(int position) {
        setChecked(position, !isChecked(position));
        }




@Override
public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mCheckStates.put((Integer) buttonView.getTag(), isChecked);

        }

        }















