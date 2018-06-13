package com.fingertip.catalog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fingertip.catalog.adapter.AccessListAdapter;
import com.fingertip.catalog.adapter.CustomerGroupAdapter;
import com.fingertip.catalog.adapter.EditAccessListAdapter;
import com.fingertip.catalog.adapter.EditModifyListAdapter;
import com.fingertip.catalog.adapter.ModiAdapter;
import com.fingertip.catalog.api.AddUserGroupAPI;
import com.fingertip.catalog.api.EditUserGroupAPI;
import com.fingertip.catalog.callback.Config;
import com.fingertip.catalog.callback.CustomTypefaceSpan;
import com.fingertip.catalog.interfaces.NetworkCallback;
import com.fingertip.catalog.model.CustomerGroupBO;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by lenevo on 23-05-2018.
 */

public class EditUserGroup extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,NetworkCallback {
    Menu nav_Menu;
    StringBuilder checkedcontacts;
    StringBuilder checkedcontacts1;
    Spinner spinner1;
    public static List<String> access = new ArrayList<String>();
    public static List<String> modify = new ArrayList<String>();
    String s1,s2,s3,s4,s5,s6;
    private List<CustomerGroupBO> customerGroupBOList;
    ArrayAdapter<String> spinnerArrayAdapter1;
    ArrayList<String> typearray = new ArrayList<String>();
    ArrayList<String> access_array = new ArrayList<String>();
    ArrayList<String> mod_array = new ArrayList<String>();
    NavigationView navigationView;
    String cusname,cusid;
    LinearLayoutManager listLayoutManager,listLayoutManager1;
    Map cusgroupMap=null;
    public static CustomerGroupAdapter customerGroupAdapter;

    int mPaddingSeparator;

    EditAccessListAdapter accessListAdapter;
    EditModifyListAdapter modiAdapter;

    LinearLayout linearLayout, linearLayout1;

    RecyclerView recyclerView,recyclerView1;

    String type = "Status";
    ImageView view1,view2,view3,view4;
    MenuItem dashboard,catalog,products,categories,customers,sales,orders,coupon,admin,settings,logouttext,reports,cus1,cusgroup,use1,usegr;
    int flag=1;
    int flag3=1;
    int flag1=1;
    int flag2=1;

    Button button, button1, button2;
    TextView textView, textView1, textView4, textView2, textView3;
    EditText editText, editText1, editText2, editText3, editText4, editText5;
    Calendar myCalendar4, myCalendar5;
    public static ArrayList<String> acc_list = new ArrayList<String>();
    public static ArrayList<String> mod_list = new ArrayList<String>();
    Typeface typeface, typeface1, typeface2, typeface3;
    CheckBox checkBox1;
    SharedPreferences pref45;LinearLayout linearLayout2;
    TextView textheader;

    String saveduser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_usergroup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), "robotoRegular.ttf");
//        fontChanger.replaceFonts((ViewGroup)this.findViewById(android.R.id.content));

        setSupportActionBar(toolbar);
        setTitle("");
        try {

            pref45 = getApplicationContext().getSharedPreferences("Login", MODE_PRIVATE);

            saveduser = pref45.getString("id", null);
            s1=getIntent().getExtras().getString("id",null);
            s2=getIntent().getExtras().getString("name",null);
            s3=getIntent().getExtras().getString("access",null);
            s4=getIntent().getExtras().getString("modify",null);




        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        recyclerView=(RecyclerView)findViewById(R.id.accesslist) ;
        recyclerView1=(RecyclerView)findViewById(R.id.modlist) ;




        editText = (EditText) findViewById(R.id.usegrouptext);

        textView4 = (TextView) findViewById(R.id.toolbar_title);

        textView = (TextView) findViewById(R.id.acctext);
        textView2 = (TextView) findViewById(R.id.modtext);

        access_array.add("dashboard");
        access_array.add("category");
        access_array.add("product");
        access_array.add("customer");
        access_array.add("customer group");
        access_array.add("sale report");
        access_array.add("settings");
        access_array.add("remove success");
        access_array.add("invoice");
        access_array.add("coupon");
        access_array.add("user group");
        access_array.add("User");

        mod_array.add("dashboard");
        mod_array.add("category");
        mod_array.add("product");
        mod_array.add("customer");
        mod_array.add("customer group");
        mod_array.add("sale report");
        mod_array.add("settings");
        mod_array.add("remove success");
        mod_array.add("invoice");
        mod_array.add("coupon");
        mod_array.add("user group");
        mod_array.add("User");

        button = (Button) findViewById(R.id.savecoupon);

        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "robotoRegular.ttf");
        typeface1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "robotoLight.ttf");
        typeface2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "robotoMedium.ttf");
        typeface3 = Typeface.createFromAsset(getApplicationContext().getAssets(), "robotoBold.ttf");
        textView.setTypeface(typeface);
        textView2.setTypeface(typeface);


        textView4.setTypeface(typeface1);
        editText.setTypeface(typeface);
        editText.setText(s2);
        button.setTypeface(typeface3);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        nav_Menu = navigationView.getMenu();
        Resources res = this.getResources();
        dashboard = nav_Menu.findItem(R.id.dash);
        catalog = nav_Menu.findItem(R.id.cat);
        products = nav_Menu.findItem(R.id.prod);
        categories = nav_Menu.findItem(R.id.categories);
        customers = nav_Menu.findItem(R.id.cust);
        sales = nav_Menu.findItem(R.id.sales);
        orders = nav_Menu.findItem(R.id.orders);
        coupon = nav_Menu.findItem(R.id.coupon);
        admin = nav_Menu.findItem(R.id.adminman);
        settings = nav_Menu.findItem(R.id.settingspage);
        logouttext = nav_Menu.findItem(R.id.logout1);
       // reports = nav_Menu.findItem(R.id.report);
        use1 = nav_Menu.findItem(R.id.user1);
        usegr = nav_Menu.findItem(R.id.usegroup);
        cus1 = nav_Menu.findItem(R.id.cust1);
        cusgroup = nav_Menu.findItem(R.id.cusgroup);
        SpannableString mNewTitle = new SpannableString(dashboard.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("" , typeface), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        dashboard.setTitle(mNewTitle);
        SpannableString mNewTitle1 = new SpannableString(catalog.getTitle());
        mNewTitle1.setSpan(new CustomTypefaceSpan("" , typeface), 0 , mNewTitle1.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        catalog.setTitle(mNewTitle1);
        SpannableString mNewTitle2 = new SpannableString(products.getTitle());
        mNewTitle2.setSpan(new CustomTypefaceSpan("" , typeface), 0 , mNewTitle2.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        products.setTitle(mNewTitle2);
        SpannableString mNewTitle3 = new SpannableString(categories.getTitle());
        mNewTitle3.setSpan(new CustomTypefaceSpan("" , typeface), 0 , mNewTitle3.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        categories.setTitle(mNewTitle3);
        SpannableString mNewTitle4 = new SpannableString(customers.getTitle());
        mNewTitle4.setSpan(new CustomTypefaceSpan("" , typeface), 0 , mNewTitle4.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        customers.setTitle(mNewTitle4);
        SpannableString mNewTitle5 = new SpannableString(sales.getTitle());
        mNewTitle5.setSpan(new CustomTypefaceSpan("" , typeface), 0 , mNewTitle5.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        sales.setTitle(mNewTitle5);
        SpannableString mNewTitle6 = new SpannableString(orders.getTitle());
        mNewTitle6.setSpan(new CustomTypefaceSpan("" , typeface), 0 , mNewTitle6.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        orders.setTitle(mNewTitle6);
        SpannableString mNewTitle7 = new SpannableString(coupon.getTitle());
        mNewTitle7.setSpan(new CustomTypefaceSpan("" , typeface), 0 , mNewTitle7.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        coupon.setTitle(mNewTitle7);
        SpannableString mNewTitle8 = new SpannableString(admin.getTitle());
        mNewTitle8.setSpan(new CustomTypefaceSpan("" , typeface), 0 , mNewTitle8.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        admin.setTitle(mNewTitle8);
        SpannableString mNewTitle9 = new SpannableString(settings.getTitle());
        mNewTitle9.setSpan(new CustomTypefaceSpan("" , typeface), 0 , mNewTitle9.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        settings.setTitle(mNewTitle9);
        SpannableString mNewTitle10 = new SpannableString(logouttext.getTitle());
        mNewTitle10.setSpan(new CustomTypefaceSpan("" , typeface), 0 , mNewTitle10.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        logouttext.setTitle(mNewTitle10);
//        SpannableString mNewTitle11 = new SpannableString(reports.getTitle());
//        mNewTitle11.setSpan(new CustomTypefaceSpan("" , typeface), 0 , mNewTitle11.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//        reports.setTitle(mNewTitle11);

        SpannableString mNewTitle12 = new SpannableString(cus1.getTitle());
        mNewTitle12.setSpan(new CustomTypefaceSpan("" , typeface), 0 , mNewTitle12.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        cus1.setTitle(mNewTitle12);
        SpannableString mNewTitle13 = new SpannableString(cusgroup.getTitle());
        mNewTitle13.setSpan(new CustomTypefaceSpan("" , typeface), 0 , mNewTitle13.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        cusgroup.setTitle(mNewTitle13);

        SpannableString mNewTitle14 = new SpannableString(use1.getTitle());
        mNewTitle14.setSpan(new CustomTypefaceSpan("" , typeface), 0 , mNewTitle14.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        use1.setTitle(mNewTitle14);
        SpannableString mNewTitle15 = new SpannableString(usegr.getTitle());
        mNewTitle15.setSpan(new CustomTypefaceSpan("" , typeface), 0 , mNewTitle15.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        usegr.setTitle(mNewTitle15);

        mPaddingSeparator = res.getDimensionPixelOffset(R.dimen.design_navigation_separator_vertical_padding);
        view1 = (ImageView) navigationView.getMenu().findItem(R.id.cat).getActionView().findViewById(R.id.im1);
        view2 = (ImageView) navigationView.getMenu().findItem(R.id.sales).getActionView().findViewById(R.id.im2);
        view3 = (ImageView) navigationView.getMenu().findItem(R.id.cust).getActionView().findViewById(R.id.im3);
        view4 = (ImageView) navigationView.getMenu().findItem(R.id.adminman).getActionView().findViewById(R.id.im4);
        nav_Menu.findItem(R.id.prod).setVisible(false);
        nav_Menu.findItem(R.id.categories).setVisible(false);
        nav_Menu.findItem(R.id.orders).setVisible(false);
        nav_Menu.findItem(R.id.coupon).setVisible(false);
        nav_Menu.findItem(R.id.cust1).setVisible(false);
        nav_Menu.findItem(R.id.cusgroup).setVisible(false);
        nav_Menu.findItem(R.id.user1).setVisible(false);
        nav_Menu.findItem(R.id.usegroup).setVisible(false);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                System.out.println("draweropened");

                view1.setImageResource(R.drawable.arrow);
                nav_Menu.findItem(R.id.prod).setVisible(false);
                nav_Menu.findItem(R.id.categories).setVisible(false);
                nav_Menu.findItem(R.id.cust1).setVisible(false);
                nav_Menu.findItem(R.id.cusgroup).setVisible(false);
                nav_Menu.findItem(R.id.orders).setVisible(false);
                nav_Menu.findItem(R.id.coupon).setVisible(false);
                nav_Menu.findItem(R.id.user1).setVisible(false);
                nav_Menu.findItem(R.id.usegroup).setVisible(false);
                view2.setImageResource(R.drawable.arrow);
                view3.setImageResource(R.drawable.arrow);
                view4.setImageResource(R.drawable.arrow);


                // getActionBar().setTitle(mDrawerTitle);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        View headerview = navigationView.getHeaderView(0);
        linearLayout2 = (LinearLayout) headerview.findViewById(R.id.newlay);
        textheader = (TextView) headerview.findViewById(R.id.user_header);

        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(EditUserGroup.this, MyProfile.class);
                startActivity(intent);

            }
        });


        access = Arrays.asList(s3.split(","));
        modify = Arrays.asList(s4.split(","));
        System.out.println("adapter"+modify);
        System.out.println("adapter1"+access);
        accessListAdapter=new EditAccessListAdapter(this,this,access_array,access);
        modiAdapter=new EditModifyListAdapter(this,this,mod_array,modify);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        listLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(listLayoutManager);
        accessListAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(accessListAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        listLayoutManager.scrollToPositionWithOffset(0,0);


        recyclerView1.setHasFixedSize(true);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());

        listLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView1.setLayoutManager(listLayoutManager1);
        modiAdapter.notifyDataSetChanged();
        recyclerView1.setAdapter(modiAdapter);
        recyclerView1.setNestedScrollingEnabled(false);
        listLayoutManager1.scrollToPositionWithOffset(0,0);

        hide4();



    }

    public void hide4() {
        Activity a = this;
        hide3(a);
    }

    public static void hide3(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (id == R.id.dash) {
            Intent i = new Intent(EditUserGroup.this, MainActivity.class);
            startActivity(i);
            EditUserGroup.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);




        } else if (id == R.id.cat) {
            ImageView view = (ImageView) navigationView.getMenu().findItem(R.id.cat).getActionView().findViewById(R.id.im1);
            ImageView view1 = (ImageView) navigationView.getMenu().findItem(R.id.sales).getActionView().findViewById(R.id.im2);
            ImageView view2 = (ImageView) navigationView.getMenu().findItem(R.id.cust).getActionView().findViewById(R.id.im3);
            ImageView view3 = (ImageView) navigationView.getMenu().findItem(R.id.cust).getActionView().findViewById(R.id.im3);

            nav_Menu.findItem(R.id.orders).setVisible(false);
            nav_Menu.findItem(R.id.coupon).setVisible(false);
            nav_Menu.findItem(R.id.cust1).setVisible(false);
            nav_Menu.findItem(R.id.cusgroup).setVisible(false);
            nav_Menu.findItem(R.id.user1).setVisible(false);
            nav_Menu.findItem(R.id.usegroup).setVisible(false);
            view1.setImageResource(R.drawable.arrow);
            view2.setImageResource(R.drawable.arrow);
            view3.setImageResource(R.drawable.arrow);
            if(flag==1)
            {
                nav_Menu.findItem(R.id.prod).setVisible(true);
                nav_Menu.findItem(R.id.categories).setVisible(true);
                flag=0;
                view.setImageResource(R.drawable.up_arrow);
            }

            else if(flag==0)
            {


                view.setImageResource(R.drawable.down_arrow);
                nav_Menu.findItem(R.id.prod).setVisible(false);
                nav_Menu.findItem(R.id.categories).setVisible(false);



                flag=1;


            }



        }
        else if (id == R.id.prod) {
            Intent i = new Intent(EditUserGroup.this, ProductList.class);
            startActivity(i);
            EditUserGroup.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);


        }
        else if (id == R.id.categories) {
            Intent i = new Intent(EditUserGroup.this, Category.class);
            startActivity(i);
            EditUserGroup.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);

            drawer.closeDrawer(GravityCompat.START);


        }
        else if (id == R.id.cust) {
            ImageView view = (ImageView) navigationView.getMenu().findItem(R.id.cust).getActionView().findViewById(R.id.im3);
            ImageView view1 = (ImageView) navigationView.getMenu().findItem(R.id.sales).getActionView().findViewById(R.id.im2);
            ImageView view2 = (ImageView) navigationView.getMenu().findItem(R.id.cat).getActionView().findViewById(R.id.im1);
            ImageView view3 = (ImageView) navigationView.getMenu().findItem(R.id.adminman).getActionView().findViewById(R.id.im4);
            nav_Menu.findItem(R.id.orders).setVisible(false);
            nav_Menu.findItem(R.id.coupon).setVisible(false);
            nav_Menu.findItem(R.id.prod).setVisible(false);
            nav_Menu.findItem(R.id.categories).setVisible(false);
            nav_Menu.findItem(R.id.user1).setVisible(false);
            nav_Menu.findItem(R.id.usegroup).setVisible(false);
            view1.setImageResource(R.drawable.arrow);
            view2.setImageResource(R.drawable.arrow);
            view3.setImageResource(R.drawable.arrow);
            if(flag2==1)
            {
                nav_Menu.findItem(R.id.cust1).setVisible(true);
                nav_Menu.findItem(R.id.cusgroup).setVisible(true);
                flag2=0;
                view.setImageResource(R.drawable.up_arrow);
            }

            else if(flag2==0)
            {


                view.setImageResource(R.drawable.down_arrow);
                nav_Menu.findItem(R.id.cust1).setVisible(false);
                nav_Menu.findItem(R.id.cusgroup).setVisible(false);



                flag2=1;


            }

//            Intent intent=new Intent(this,CustomerList.class);
//            startActivity(intent);
//            MainActivity.this.overridePendingTransition(R.anim.righttoleft,
//                    R.anim.lefttoright);
//
//            drawer.closeDrawer(GravityCompat.START);


        }
        else if (id == R.id.sales) {
            ImageView view = (ImageView) navigationView.getMenu().findItem(R.id.cat).getActionView().findViewById(R.id.im1);
            ImageView view1 = (ImageView) navigationView.getMenu().findItem(R.id.sales).getActionView().findViewById(R.id.im2);
            ImageView view2 = (ImageView) navigationView.getMenu().findItem(R.id.cust).getActionView().findViewById(R.id.im3);
            ImageView view3 = (ImageView) navigationView.getMenu().findItem(R.id.adminman).getActionView().findViewById(R.id.im4);
            nav_Menu.findItem(R.id.prod).setVisible(false);
            nav_Menu.findItem(R.id.categories).setVisible(false);
            nav_Menu.findItem(R.id.cust1).setVisible(false);
            nav_Menu.findItem(R.id.cusgroup).setVisible(false);
            nav_Menu.findItem(R.id.user1).setVisible(false);
            nav_Menu.findItem(R.id.usegroup).setVisible(false);
            view.setImageResource(R.drawable.arrow);
            view2.setImageResource(R.drawable.arrow);
            view3.setImageResource(R.drawable.arrow);


            if(flag1==1)
            {
                nav_Menu.findItem(R.id.orders).setVisible(true);
                nav_Menu.findItem(R.id.coupon).setVisible(true);;


                flag1=0;

                view1.setImageResource(R.drawable.up_arrow);
            }

            else if(flag1==0)
            {


                view1.setImageResource(R.drawable.down_arrow);
                nav_Menu.findItem(R.id.orders).setVisible(false);
                nav_Menu.findItem(R.id.coupon).setVisible(false);



                flag1=1;


            }





        }
        else if (id == R.id.adminman) {
            ImageView view = (ImageView) navigationView.getMenu().findItem(R.id.adminman).getActionView().findViewById(R.id.im4);
            ImageView view1 = (ImageView) navigationView.getMenu().findItem(R.id.sales).getActionView().findViewById(R.id.im2);
            ImageView view2 = (ImageView) navigationView.getMenu().findItem(R.id.cat).getActionView().findViewById(R.id.im1);
            ImageView view3 = (ImageView) navigationView.getMenu().findItem(R.id.cust).getActionView().findViewById(R.id.im3);
            nav_Menu.findItem(R.id.orders).setVisible(false);
            nav_Menu.findItem(R.id.coupon).setVisible(false);
            nav_Menu.findItem(R.id.cust).setVisible(false);
            nav_Menu.findItem(R.id.cusgroup).setVisible(false);
            nav_Menu.findItem(R.id.prod).setVisible(false);
            nav_Menu.findItem(R.id.categories).setVisible(false);
            view1.setImageResource(R.drawable.arrow);
            view2.setImageResource(R.drawable.arrow);
            view3.setImageResource(R.drawable.arrow);
            if(flag3==1)
            {
                nav_Menu.findItem(R.id.user1).setVisible(true);
                nav_Menu.findItem(R.id.usegroup).setVisible(true);
                flag3=0;
                view.setImageResource(R.drawable.up_arrow);
            }

            else if(flag3==0)
            {


                view.setImageResource(R.drawable.down_arrow);
                nav_Menu.findItem(R.id.user1).setVisible(false);
                nav_Menu.findItem(R.id.usegroup).setVisible(false);



                flag3=1;


            }
        }
        else if (id == R.id.orders) {
            Intent intent=new Intent(this,MyOrders.class);
            startActivity(intent);
            EditUserGroup.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.cust1) {
            Intent intent=new Intent(this,CustomerList.class);
            startActivity(intent);
            EditUserGroup.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.cusgroup) {
            Intent intent=new Intent(this,CustomerGroup.class);
            startActivity(intent);
            EditUserGroup.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.user1) {
            Intent intent=new Intent(this,UsersList.class);
            startActivity(intent);
            EditUserGroup.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.usegroup) {
            Intent intent=new Intent(this,UserGroup.class);
            startActivity(intent);
            EditUserGroup.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.coupon) {
            Intent intent=new Intent(this,CouponList.class);
            startActivity(intent);
            EditUserGroup.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);
        }

        else if (id == R.id.logout1) {
            Toast.makeText(getApplicationContext(), "Logout Successfully", Toast.LENGTH_SHORT).show();
            SharedPreferences preferences6 = getSharedPreferences("Login", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor1 = preferences6.edit();
            editor1.clear();
            editor1.commit();
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
            EditUserGroup.this.overridePendingTransition(R.anim.left_in,
                    R.anim.right_out);
            drawer.closeDrawer(GravityCompat.START);
        }

//
//        else if (id == R.id.report) {
//
//            drawer.closeDrawer(GravityCompat.START);
//
//        }

        else if (id == R.id.settingspage) {
            Intent intent=new Intent(this,Settings.class);
            startActivity(intent);
            EditUserGroup.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }


        return true;


    }

    @Override
    public void onBackPressed() {


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        finish();

    }

    public void editUserGroup(View view) {
        acc_list.clear();
        mod_list.clear();
        checkedcontacts = new StringBuilder();
        StringBuilder builder = null;
        checkedcontacts1 = new StringBuilder();
        StringBuilder builder1 = null;
        for (int i = 0; i < access_array.size(); i++)

        {
            checkedcontacts.append(access_array.toString());
            checkedcontacts.append(",");
            s5 = access_array.get(i).toString();
            Log.d("aca", s5);
            if ((accessListAdapter.mCheckStates.get(i) == true)) {


                acc_list.add(s5);
                builder = new StringBuilder(acc_list.toString());

            }
            if ((accessListAdapter.mCheckStates.get(i) == false)) {
                acc_list.remove(s5);
                builder = new StringBuilder(acc_list.toString());

            }
        }
        builder.deleteCharAt(0);
        builder.deleteCharAt(builder.length() - 1);
        Log.d("aca2", String.valueOf(builder));
        for (int j = 0; j < mod_array.size(); j++)

        {
            checkedcontacts1.append(mod_array.toString());
            checkedcontacts1.append(",");
            s6 = mod_array.get(j).toString();
            Log.d("aca", s6);
            if ((modiAdapter.mCheckStates.get(j) == true)) {


                mod_list.add(s6);
                builder1 = new StringBuilder(mod_list.toString());


            }
            else if ((modiAdapter.mCheckStates.get(j) == false)) {
                mod_list.remove(s6);
                builder1 = new StringBuilder(mod_list.toString());
            }
        }
        builder1.deleteCharAt(0);
        builder1.deleteCharAt(builder1.length() - 1);

        if(!(editText.getText().toString().isEmpty()))
        {

            EditUserGroupAPI editUserGroupAPI=new EditUserGroupAPI(this,this);
            editUserGroupAPI.processEditUserGroup(s1,editText.getText().toString(),String.valueOf(builder).replaceAll(", ",","),String.valueOf(builder1).replaceAll(", ",","));

        }
        else {
            Toast.makeText(getApplicationContext(),"Please enter user group name",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void updateScreen(String data, String tag) {
        try {
            if (tag.compareTo(Config.TAG_EDIT_USERGROUP_LIST) == 0) {
                Log.d("datahoome", data);
                JSONObject jsonObject=new JSONObject(data);
                if(jsonObject.getString("status").equals("Success"))
                {
                    Toast.makeText(getApplicationContext(),"User Group updated successfully",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditUserGroup.this,UserGroup.class);
                    startActivity(intent);
                    EditUserGroup.this.overridePendingTransition(R.anim.left_in,
                            R.anim.right_out);
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

}




