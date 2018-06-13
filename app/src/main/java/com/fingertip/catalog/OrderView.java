package com.fingertip.catalog;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fingertip.catalog.adapter.OrderListAdapter;
import com.fingertip.catalog.adapter.OrderViewAdapter;
import com.fingertip.catalog.api.OrdersAPI;
import com.fingertip.catalog.api.OrdersViewAPI;
import com.fingertip.catalog.api.SearchOrderAPI;
import com.fingertip.catalog.callback.Config;
import com.fingertip.catalog.callback.CustomTabLayout;
import com.fingertip.catalog.callback.CustomTypefaceSpan;
import com.fingertip.catalog.interfaces.NetworkCallback;
import com.fingertip.catalog.model.OrderViewBO;
import com.fingertip.catalog.model.OrdersBO;
import com.fingertip.catalog.parsar.OrderListParsar;
import com.fingertip.catalog.parsar.OrderViewParsar;
import com.fingertip.catalog.parsar.SearchOrderParsar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lenevo on 28-05-2018.
 */

public class OrderView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,NetworkCallback {
    TextView textView;
    ArrayList<Integer> tcontact = new ArrayList<Integer>();
    private CustomTabLayout tabLayout;
    private ViewPager viewPager;
    HorizontalScrollView horizontalScrollView1,horizontalScrollView2;
    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10,textView11,textView12;
    LinearLayoutManager listLayoutManager,linearLayoutManager1;
    Typeface typeface,typeface1,typeface2,typeface3;
    Menu nav_Menu;
    LinearLayout linearLayout,linearLayout1;
Button button1,button2;
    Map orderMap = null;
    Map searchMap = null;
    HorizontalScrollView horizontalScrollView;
    String type;
    OrderViewAdapter orderListAdapter;
Spinner spinner;
    EditText percentagetext;
    NavigationView navigationView;
    int mPaddingSeparator;
    ArrayAdapter<String> spinnerArrayAdapter1;
EditText editText,editText1;
    CheckBox checkBox;
    Button savebutton;
    ArrayList<String> ord_array = new ArrayList<String>();
    private List<OrderViewBO> ordersBOList;
    LinearLayout linearLayout2;
    ScrollView scrollingView;
    TextView textheader;
TextView tbs1,tbs2,tbs3,tbs4,tbs5,tbs6,tbs7,tbs8,tbs9,tbs10,tbs11,tbs12,tbs13,tbs14,tbs15;
    RecyclerView recyclerView;
    ImageView view1,view2,view3,view4;
    TextView text1,text2,text3,text4,text5;
    public static TextView text6;
    MenuItem dashboard,catalog,products,categories,customers,sales,orders,coupon,admin,settings,logouttext,reports,cus1,cusgroup,use1,usegr;
    int flag=1;
    int flag3=1;
    int flag1=1;
    String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17;
    int flag2=1;
    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        setTitle("");

spinner=(Spinner)findViewById(R.id.spin1);
        textView = (TextView) findViewById(R.id.toolbar_title);
        textView1=(TextView)findViewById(R.id.t1);
        textView2=(TextView)findViewById(R.id.t2);
        textView3=(TextView)findViewById(R.id.t3);
        textView4=(TextView)findViewById(R.id.t4);
        textView5=(TextView)findViewById(R.id.t5);
        textView6=(TextView)findViewById(R.id.t6);
        textView7=(TextView)findViewById(R.id.t7);
        textView8=(TextView)findViewById(R.id.t8);
        textView9=(TextView)findViewById(R.id.t9);
        textView10=(TextView)findViewById(R.id.t10);
        textView11=(TextView)findViewById(R.id.t11);
        textView12=(TextView)findViewById(R.id.t12);
        imageView1=(ImageView)findViewById(R.id.sign1);
        imageView2=(ImageView)findViewById(R.id.sign2);
        imageView3=(ImageView)findViewById(R.id.sign3);
        imageView4=(ImageView)findViewById(R.id.sign4);
        imageView5=(ImageView)findViewById(R.id.sign5);
        imageView6=(ImageView)findViewById(R.id.sign6);
        imageView7=(ImageView)findViewById(R.id.sign7);
        imageView8=(ImageView)findViewById(R.id.sign8);
        imageView9=(ImageView)findViewById(R.id.sign9);
        imageView10=(ImageView)findViewById(R.id.sign10);
        text1=(TextView)findViewById(R.id.ordv1);
        linearLayout=(LinearLayout)findViewById(R.id.lay1);
        linearLayout1=(LinearLayout)findViewById(R.id.lay2);
        text2=(TextView)findViewById(R.id.ordv2);
        text3=(TextView)findViewById(R.id.ordv3);
        text4=(TextView)findViewById(R.id.ordv4);
        text5=(TextView)findViewById(R.id.ordv5);
        text6=(TextView)findViewById(R.id.totalprice);
        tbs1=(TextView)findViewById(R.id.tb1);
        tbs2=(TextView)findViewById(R.id.tb2);
        tbs3=(TextView)findViewById(R.id.tb3);
        tbs4=(TextView)findViewById(R.id.tb4);
        tbs5=(TextView)findViewById(R.id.tb5);
        tbs6=(TextView)findViewById(R.id.tb6);
        tbs7=(TextView)findViewById(R.id.tb7);
        tbs8=(TextView)findViewById(R.id.tb8);
        tbs9=(TextView)findViewById(R.id.tb9);
        tbs10=(TextView)findViewById(R.id.tb10);
        tbs11=(TextView)findViewById(R.id.tb11);
        tbs12=(TextView)findViewById(R.id.tb12);
        tbs13=(TextView)findViewById(R.id.tb13);
        tbs14=(TextView)findViewById(R.id.tb14);
        tbs15=(TextView)findViewById(R.id.total);
horizontalScrollView1=(HorizontalScrollView)findViewById(R.id.h1);
        horizontalScrollView2=(HorizontalScrollView)findViewById(R.id.h2);

        editText=(EditText)findViewById(R.id.ed1);
        editText1=(EditText)findViewById(R.id.ed2);
        button1=(Button)findViewById(R.id.conf);
        button2=(Button)findViewById(R.id.saveorder);

scrollingView=(ScrollView) findViewById(R.id.scr1);

//        textView2=(TextView)findViewById(R.id.sortorder);
//        textView3=(TextView)findViewById(R.id.stat);
//        textView4=(TextView)findViewById(R.id.act);
        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "robotoRegular.ttf");
        typeface1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "robotoLight.ttf");
        typeface2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "robotoBold.ttf");
        typeface3 = Typeface.createFromAsset(getApplicationContext().getAssets(), "robotoMedium.ttf");
        textView.setTypeface(typeface1);
        textView1.setTypeface(typeface);
        textView2.setTypeface(typeface);
        textView3.setTypeface(typeface);
        textView4.setTypeface(typeface);
        textView5.setTypeface(typeface);
        textView6.setTypeface(typeface);
        textView7.setTypeface(typeface);
        textView8.setTypeface(typeface);
        textView9.setTypeface(typeface);
        textView10.setTypeface(typeface);
        textView11.setTypeface(typeface);
        textView12.setTypeface(typeface);
        tbs1.setTypeface(typeface2);
        tbs2.setTypeface(typeface2);
        tbs3.setTypeface(typeface2);
        tbs4.setTypeface(typeface2);
        tbs5.setTypeface(typeface2);
        tbs6.setTypeface(typeface2);
        tbs7.setTypeface(typeface2);
        tbs8.setTypeface(typeface2);
        tbs9.setTypeface(typeface2);
        tbs10.setTypeface(typeface2);
        tbs11.setTypeface(typeface2);
        tbs12.setTypeface(typeface2);
        tbs13.setTypeface(typeface2);
        tbs14.setTypeface(typeface2);
        tbs15.setTypeface(typeface2);
text1.setTypeface(typeface3);
        text2.setTypeface(typeface3);
        text3.setTypeface(typeface3);
        text4.setTypeface(typeface3);
        text4.setTypeface(typeface3);
        text5.setTypeface(typeface);
        button1.setTypeface(typeface);
        button2.setTypeface(typeface);
        recyclerView = (RecyclerView) findViewById(R.id.ordlist);
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

        logouttext = nav_Menu.findItem(R.id.logout1);
      //  reports = nav_Menu.findItem(R.id.report);
        use1 = nav_Menu.findItem(R.id.user1);
        usegr = nav_Menu.findItem(R.id.usegroup);
        cus1 = nav_Menu.findItem(R.id.cust1);
        cusgroup = nav_Menu.findItem(R.id.cusgroup);
        SpannableString mNewTitle = new SpannableString(dashboard.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", typeface), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        dashboard.setTitle(mNewTitle);
        SpannableString mNewTitle1 = new SpannableString(catalog.getTitle());
        mNewTitle1.setSpan(new CustomTypefaceSpan("", typeface), 0, mNewTitle1.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        catalog.setTitle(mNewTitle1);
        SpannableString mNewTitle2 = new SpannableString(products.getTitle());
        mNewTitle2.setSpan(new CustomTypefaceSpan("", typeface), 0, mNewTitle2.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        products.setTitle(mNewTitle2);
        SpannableString mNewTitle3 = new SpannableString(categories.getTitle());
        mNewTitle3.setSpan(new CustomTypefaceSpan("", typeface), 0, mNewTitle3.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        categories.setTitle(mNewTitle3);
        SpannableString mNewTitle4 = new SpannableString(customers.getTitle());
        mNewTitle4.setSpan(new CustomTypefaceSpan("", typeface), 0, mNewTitle4.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        customers.setTitle(mNewTitle4);
        SpannableString mNewTitle5 = new SpannableString(sales.getTitle());
        mNewTitle5.setSpan(new CustomTypefaceSpan("", typeface), 0, mNewTitle5.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        sales.setTitle(mNewTitle5);
        SpannableString mNewTitle6 = new SpannableString(orders.getTitle());
        mNewTitle6.setSpan(new CustomTypefaceSpan("", typeface), 0, mNewTitle6.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        orders.setTitle(mNewTitle6);
        SpannableString mNewTitle7 = new SpannableString(coupon.getTitle());
        mNewTitle7.setSpan(new CustomTypefaceSpan("", typeface), 0, mNewTitle7.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        coupon.setTitle(mNewTitle7);
        SpannableString mNewTitle8 = new SpannableString(admin.getTitle());
        mNewTitle8.setSpan(new CustomTypefaceSpan("", typeface), 0, mNewTitle8.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        admin.setTitle(mNewTitle8);
        SpannableString mNewTitle9 = new SpannableString(settings.getTitle());
        mNewTitle9.setSpan(new CustomTypefaceSpan("", typeface), 0, mNewTitle9.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        settings.setTitle(mNewTitle9);
        SpannableString mNewTitle10 = new SpannableString(logouttext.getTitle());
        mNewTitle10.setSpan(new CustomTypefaceSpan("", typeface), 0, mNewTitle10.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        logouttext.setTitle(mNewTitle10);
//        SpannableString mNewTitle11 = new SpannableString(reports.getTitle());
//        mNewTitle11.setSpan(new CustomTypefaceSpan("", typeface), 0, mNewTitle11.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//        reports.setTitle(mNewTitle11);

        SpannableString mNewTitle12 = new SpannableString(cus1.getTitle());
        mNewTitle12.setSpan(new CustomTypefaceSpan("", typeface), 0, mNewTitle12.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        cus1.setTitle(mNewTitle12);
        SpannableString mNewTitle13 = new SpannableString(cusgroup.getTitle());
        mNewTitle13.setSpan(new CustomTypefaceSpan("", typeface), 0, mNewTitle13.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        cusgroup.setTitle(mNewTitle13);

        SpannableString mNewTitle14 = new SpannableString(use1.getTitle());
        mNewTitle14.setSpan(new CustomTypefaceSpan("", typeface), 0, mNewTitle14.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        use1.setTitle(mNewTitle14);
        SpannableString mNewTitle15 = new SpannableString(usegr.getTitle());
        mNewTitle15.setSpan(new CustomTypefaceSpan("", typeface), 0, mNewTitle15.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
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

            /**
             * Called when a drawer has settled in a completely closed state.
             */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getActionBar().setTitle(mTitle);
            }

            /**
             * Called when a drawer has settled in a completely open state.
             */
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
        spinnerArrayAdapter1 = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_textview2, ord_array); //selected item will look like a spinner set from XML
        spinnerArrayAdapter1.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        View headerview = navigationView.getHeaderView(0);
        linearLayout2 = (LinearLayout) headerview.findViewById(R.id.newlay);
        textheader = (TextView) headerview.findViewById(R.id.user_header);

        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(OrderView.this, MyProfile.class);
                startActivity(intent);

            }
        });
        try {

s1=getIntent().getExtras().getString("comments",null);
            if(s1.isEmpty()) {
                textView1.setText("No comments");
                System.out.println("commenrts" + s1);

            }
            else {
                textView1.setText(s1);
            }
            s16=getIntent().getExtras().getString("id",null);
            s2=getIntent().getExtras().getString("inv_id",null);
            s3=getIntent().getExtras().getString("date",null);
            s4=getIntent().getExtras().getString("status",null);
            s5=getIntent().getExtras().getString("name",null);
            s6=getIntent().getExtras().getString("email",null);
            s7=getIntent().getExtras().getString("mobile",null);
            s8=getIntent().getExtras().getString("address",null);
            s9=getIntent().getExtras().getString("country",null);
            s10=getIntent().getExtras().getString("state",null);
            s11=getIntent().getExtras().getString("city",null);
            s12=getIntent().getExtras().getString("pin",null);
            s13=getIntent().getExtras().getString("shipping_charge",null);
            s14=getIntent().getExtras().getString("approved",null);
            s15=getIntent().getExtras().getString("remark",null);
            s17=getIntent().getExtras().getString("total",null);
      if(s14.equals("0"))
      {
          ord_array.add("Not Approved");
      }
      else {
          ord_array.add("Approved");
      }
            textView2.setText(s2);
            textView3.setText(s3);
            textView4.setText(s4);
            textView5.setText(s5);
            textView6.setText(s6);
            textView7.setText(s7);
            textView8.setText(s8);
            textView9.setText(s9);
            textView10.setText(s10);
            textView11.setText(s11);
            textView12.setText(s12);
            text6.setText(s17);
            editText.setText(s13);
            editText1.setText(s15);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        spinner.setAdapter(spinnerArrayAdapter1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                type = spinner.getSelectedItem().toString();
                if (type.equals("Approved")) {
                    type = "1";
                } else if (type.equals("Not Approved")) {
                    type = "0";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        getViewOrders();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void setOrdersList()
    {
        try {
            ordersBOList = (List<OrderViewBO>)orderMap.get("orderview_list");
            Log.d("grr", orderMap.toString());
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            orderListAdapter= new OrderViewAdapter(this,this, ordersBOList,this);
            listLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(listLayoutManager);
            recyclerView.setAdapter(orderListAdapter);
            recyclerView.setNestedScrollingEnabled(false);
            listLayoutManager.scrollToPositionWithOffset(0,0);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

//    public void setSearchList()
//    {
//        try {
//            productBOList.clear();
//            productBOList = (List<ProductBO>)searchMap.get("search_list");
//            Log.d("grr", searchMap.toString());
//            recyclerView.setHasFixedSize(true);
//
//            recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//            productListAdapter= new ProductListAdapter(this,this, productBOList,this,false);
//            listLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//            recyclerView.setLayoutManager(listLayoutManager);
//            listLayoutManager.scrollToPosition(0);
//            recyclerView.setAdapter(productListAdapter);
//            recyclerView.setNestedScrollingEnabled(false);
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (id == R.id.dash) {
            Intent i = new Intent(OrderView.this, MainActivity.class);
            startActivity(i);
            OrderView.this.overridePendingTransition(R.anim.righttoleft,
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
            Intent i = new Intent(OrderView.this, ProductList.class);
            startActivity(i);
            OrderView.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);


        }
        else if (id == R.id.categories) {
            Intent i = new Intent(OrderView.this, Category.class);
            startActivity(i);
            OrderView.this.overridePendingTransition(R.anim.righttoleft,
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

            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.cust1) {
            Intent intent=new Intent(this,CustomerList.class);
            startActivity(intent);
            OrderView.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.cusgroup) {
            Intent intent=new Intent(this,CustomerGroup.class);
            startActivity(intent);
            OrderView.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.user1) {
            Intent intent=new Intent(this,UsersList.class);
            startActivity(intent);
            OrderView.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.usegroup) {
            Intent intent=new Intent(this,UserGroup.class);
            startActivity(intent);
            OrderView.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.coupon) {
            Intent intent=new Intent(this,CouponList.class);
            startActivity(intent);
            OrderView.this.overridePendingTransition(R.anim.righttoleft,
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
            OrderView.this.overridePendingTransition(R.anim.left_in,
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
            OrderView.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }


        return true;


    }



    @Override
    public void updateScreen(String data, String tag) {
        try {
            if (tag.compareTo(Config.TAG_ORDERSVIEWLIST) == 0) {
                Log.d("datahoome", data);
                orderMap = OrderViewParsar.parseOrderViewList(data);
                setOrdersList();

            }
            if (tag.compareTo(Config.TAG_ORDERUPDATELIST) == 0) {
                Log.d("datahoome1", data);

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void updateScreen1() {
        getViewOrders();

    }
public void getViewOrders()
{
    OrdersViewAPI ordersViewAPI=new OrdersViewAPI(this,this);
    ordersViewAPI.processOrdersView(s16);
}
public void hide1(View view)
{
    imageView1.setVisibility(View.GONE);
    imageView2.setVisibility(View.VISIBLE);
    linearLayout.setVisibility(View.GONE);
}
    public void hide2(View view)
    {
        imageView2.setVisibility(View.GONE);
        imageView1.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
    }
    public void hide3(View view)
    {
        imageView3.setVisibility(View.GONE);
        imageView4.setVisibility(View.VISIBLE);
        linearLayout1.setVisibility(View.GONE);
    }
    public void hide4(View view)
    {
        linearLayout1.setVisibility(View.VISIBLE);
        imageView4.setVisibility(View.GONE);
        imageView3.setVisibility(View.VISIBLE);
    }
    public void hide5(View view)
    {
        horizontalScrollView1.setVisibility(View.GONE);
        imageView5.setVisibility(View.GONE);
        imageView6.setVisibility(View.VISIBLE);
    }
    public void hide6(View view)
    {
        horizontalScrollView1.setVisibility(View.VISIBLE);
        imageView6.setVisibility(View.GONE);
        imageView5.setVisibility(View.VISIBLE);
    }
    public void hide7(View view)
    {
        horizontalScrollView2.setVisibility(View.GONE);
        imageView7.setVisibility(View.GONE);
        imageView8.setVisibility(View.VISIBLE);
    }
    public void hide8(View view)
    {
        horizontalScrollView2.setVisibility(View.VISIBLE);
        imageView8.setVisibility(View.GONE);
        imageView7.setVisibility(View.VISIBLE);
    }
    public void hide9(View view)
    {
        scrollingView.setVisibility(View.GONE);
        imageView9.setVisibility(View.GONE);
        imageView10.setVisibility(View.VISIBLE);
    }
    public void hide10(View view)
    {
        scrollingView.setVisibility(View.VISIBLE);
        imageView10.setVisibility(View.GONE);
        imageView9.setVisibility(View.VISIBLE);
    }

}

