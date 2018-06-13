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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fingertip.catalog.adapter.CategoryListAdapter;
import com.fingertip.catalog.adapter.ProductListAdapter;
import com.fingertip.catalog.api.CategoryListAPI;
import com.fingertip.catalog.api.PercentageupdateAPI;
import com.fingertip.catalog.api.ProductAPI;
import com.fingertip.catalog.api.SearchAPI;
import com.fingertip.catalog.api.UpdatePriceAPI;
import com.fingertip.catalog.callback.Config;
import com.fingertip.catalog.callback.CustomTabLayout;
import com.fingertip.catalog.callback.CustomTypefaceSpan;
import com.fingertip.catalog.fragments.EditProDataFragment;
import com.fingertip.catalog.fragments.EditProImageFragment;
import com.fingertip.catalog.fragments.ImageFragment;
import com.fingertip.catalog.interfaces.NetworkCallback;
import com.fingertip.catalog.model.CategoryBO;
import com.fingertip.catalog.model.ProductBO;
import com.fingertip.catalog.parsar.CategoryListParsar;
import com.fingertip.catalog.parsar.ProductListParsar;
import com.fingertip.catalog.parsar.SearchParsar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lenevo on 10-05-2018.
 */

public class ProductList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,NetworkCallback {
    TextView textView;
    ArrayList<Integer> tcontact = new ArrayList<Integer>();
    private CustomTabLayout tabLayout;
    int arraylength=-1;
    StringBuilder checkedcontacts;
    ArrayList<String> productlistid = new ArrayList<String>();
    private ViewPager viewPager;
    TextView textView1,textView2,textView3,textView4;
    LinearLayoutManager listLayoutManager,linearLayoutManager1;
    Typeface typeface,typeface1,typeface2;
    Menu nav_Menu;
    TextView textView5;
    Map proMap = null;
    String s14;
    Map searchMap = null;
    HorizontalScrollView horizontalScrollView;
    ProductListAdapter productListAdapter;
    ImageView view1,view2,view3,view4;
   EditText percentagetext;
    NavigationView navigationView;

    MenuItem dashboard,catalog,products,categories,customers,sales,orders,coupon,admin,settings,logouttext,reports,cus1,cusgroup,use1,usegr;
    int flag=1;
    int flag3=1;
    int flag1=1;
    int flag2=1;
    int mPaddingSeparator;

    CheckBox checkBox;
    LinearLayout linearLayout2;
    TextView textheader;
    Button savebutton;
    private List<ProductBO> productBOList;
String id;

    RecyclerView recyclerView;
TextView textView6;

    public static ArrayList<String> pro_list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        setTitle("");
        savebutton=(Button)findViewById(R.id.persave);
        horizontalScrollView=(HorizontalScrollView)findViewById(R.id.h1);
        percentagetext=(EditText)findViewById(R.id.percentage);
        textView=(TextView)findViewById(R.id.toolbar_title);
        textView5=(TextView)findViewById(R.id.t1);
        textView6=(TextView)findViewById(R.id.noprod);
//        textView2=(TextView)findViewById(R.id.sortorder);
//        textView3=(TextView)findViewById(R.id.stat);
//        textView4=(TextView)findViewById(R.id.act);
        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "robotoRegular.ttf");
        typeface1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "robotoLight.ttf");
        typeface2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "robotoBold.ttf");
        textView.setTypeface(typeface1);
        textView5.setTypeface(typeface);
        savebutton.setTypeface(typeface2);
//        textView2.setTypeface(typeface);
//        textView3.setTypeface(typeface);
//        textView4.setTypeface(typeface);
        recyclerView=(RecyclerView)findViewById(R.id.prolist);
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
        //reports = nav_Menu.findItem(R.id.report);
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
        checkBox=(CheckBox)findViewById(R.id.checkbox1);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                View v=null;
                if(b) {

                    productListAdapter = new ProductListAdapter(ProductList.this, ProductList.this, productBOList, ProductList.this, true);
                    productListAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(productListAdapter);
//                    ArrayList<ProductBO> actorList = productListAdapter.getSelectActorList();
//                    for(int i=0;i<actorList.size();i++) {
//                        System.out.println("selectedall"+actorList.get(i).toString());
//                    }
                }
                else {
                    productListAdapter = new ProductListAdapter(ProductList.this, ProductList.this, productBOList, ProductList.this, false);
                    recyclerView.setAdapter(productListAdapter);
                }


            }
        });

       View headerview = navigationView.getHeaderView(0);
        linearLayout2 = (LinearLayout) headerview.findViewById(R.id.newlay);
        textheader = (TextView) headerview.findViewById(R.id.user_header);

        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(ProductList.this, MyProfile.class);
                startActivity(intent);

            }
        });

        getProductList();


    }
    public void goToAdd(View view) {
        Intent intent=new Intent(this,AddProduct.class);
        startActivity(intent);
        ProductList.this.overridePendingTransition(R.anim.righttoleft,
                R.anim.lefttoright);
    }
    @Override
    protected void onResume() {
        super.onResume();
        try {
            EditProduct.cat_list.clear();

            ImageFragment.count = 0;
            EditProDataFragment.id1_array.clear();
            EditProImageFragment.selectedPath1 = "NONE";
            EditProImageFragment.selectedPath2 = "NONE";
            EditProImageFragment.selectedPath3 = "NONE";
            EditProImageFragment.selectedPath4 = "NONE";
            EditProImageFragment.selectedPath5 = "NONE";
            EditProImageFragment.selectedPath6 = "NONE";
            EditProImageFragment.selectedPath7 = "NONE";
            EditProImageFragment.selectedPath8 = "NONE";
            EditProImageFragment.selectedPath9 = "NONE";
            EditProImageFragment.selectedPath10 = "NONE";
            EditProImageFragment.selectedPath11 = "NONE";
            EditProduct.cat_list1.clear();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void setProductList()
    {
        try {
            productBOList = (List<ProductBO>)proMap.get("product_list");
            Log.d("grr", proMap.toString());
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            productListAdapter= new ProductListAdapter(this,this, productBOList,this,false);
            listLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(listLayoutManager);
            recyclerView.setAdapter(productListAdapter);
            recyclerView.setNestedScrollingEnabled(false);
            listLayoutManager.scrollToPositionWithOffset(0,0);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public void setSearchList()
    {
        try {
            productBOList.clear();
            productBOList = (List<ProductBO>)searchMap.get("search_list");
            Log.d("grr", searchMap.toString());
            recyclerView.setHasFixedSize(true);

            recyclerView.setItemAnimator(new DefaultItemAnimator());

            productListAdapter= new ProductListAdapter(this,this, productBOList,this,false);
            listLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(listLayoutManager);
            listLayoutManager.scrollToPosition(0);
            recyclerView.setAdapter(productListAdapter);
            recyclerView.setNestedScrollingEnabled(false);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public void getProductList()
    {
        ProductAPI productAPI=new ProductAPI(this,this);
        productAPI.processProductList();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (id == R.id.dash) {
            Intent i = new Intent(ProductList.this, MainActivity.class);
            startActivity(i);
            ProductList.this.overridePendingTransition(R.anim.righttoleft,
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

            drawer.closeDrawer(GravityCompat.START);


        }
        else if (id == R.id.categories) {
            Intent i = new Intent(ProductList.this, Category.class);
            startActivity(i);
            ProductList.this.overridePendingTransition(R.anim.righttoleft,
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
            ProductList.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.cust1) {
            Intent intent=new Intent(this,CustomerList.class);
            startActivity(intent);
            ProductList.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.cusgroup) {
            Intent intent=new Intent(this,CustomerGroup.class);
            startActivity(intent);
            ProductList.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.user1) {
            Intent intent=new Intent(this,UsersList.class);
            startActivity(intent);
            ProductList.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.usegroup) {
            Intent intent=new Intent(this,UserGroup.class);
            startActivity(intent);
            ProductList.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.coupon) {
            Intent intent=new Intent(this,CouponList.class);
            startActivity(intent);
            ProductList.this.overridePendingTransition(R.anim.righttoleft,
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
            ProductList.this.overridePendingTransition(R.anim.left_in,
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
            ProductList.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }


        return true;


    }

    public void savePer(View view)
    {
        if(!(percentagetext.getText().toString().isEmpty())) {
            if(checkBox.isChecked())
            {
                StringBuilder builder = new StringBuilder(productlistid.toString());
                builder.deleteCharAt(0);
                builder.deleteCharAt(builder.length() - 1);
                System.out.println("buiolder"+builder);
                PercentageupdateAPI percentageupdateAPI = new PercentageupdateAPI(this, this);
              percentageupdateAPI.processUpdatePercent(String.valueOf(builder).replaceAll(" ",""), percentagetext.getText().toString());
            }
            else {
                System.out.println("dosss"+ProductListAdapter.dose_list.toString());
                StringBuilder builder1 = new StringBuilder(ProductListAdapter.dose_list.toString());
                builder1.deleteCharAt(0);
                builder1.deleteCharAt(builder1.length() - 1);
                System.out.println("buiolder"+builder1);
                PercentageupdateAPI percentageupdateAPI = new PercentageupdateAPI(this, this);
                percentageupdateAPI.processUpdatePercent(String.valueOf(builder1).replaceAll(" ",""), percentagetext.getText().toString());
            }

//            if(!(ProductListAdapter.dose_list.isEmpty())) {
//                System.out.println("arraynull" + ProductListAdapter.dose_list.toString());
//                // Do something with the empty list here.
//
//                StringBuilder builder = new StringBuilder(ProductListAdapter.dose_list.toString());
//                builder.deleteCharAt(0);
//                builder.deleteCharAt(builder.length() - 1);
////
//                pro_list.clear();
//                checkedcontacts = new StringBuilder();
//                StringBuilder builder = null;
//
//                for (int i = 0; i < productlistid.size(); i++)
//
//                {
//                    checkedcontacts.append(productlistid.toString());
//                    checkedcontacts.append(",");
//                    s14 = productlistid.get(i).toString();
//                    if ((productListAdapter.mCheckStates.get(i) == true)) {
//
//
//
//                        Log.d("aca", s14);
//                        pro_list.add(s14);
//                        builder = new StringBuilder(pro_list.toString());
//
//                        Log.d("aca1", String.valueOf(builder));
//                    }
//                    else if((productListAdapter.mCheckStates.get(i) == false))
//                    {
//                        pro_list.remove(s14);
//                        builder = new StringBuilder(pro_list.toString());
//
//                        Log.d("aca1", String.valueOf(builder));
//                    }
//                }
//                builder.deleteCharAt(0);
//                builder.deleteCharAt(builder.length() - 1);
//                System.out.println("perlist" + builder);
//

        }
        else {
            Toast.makeText(getApplicationContext(), "Please enter %age", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) ProductList.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(ProductList.this.getComponentName()));
        }

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                // This is your adapter that will be filtered


                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                if(!(query.isEmpty())) {
                    // **Here you can get the value "query" which is entered in the search box.**
                    getSearchList(query);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Please enter text",Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onBackPressed() {


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        finish();

    }

    @Override
    public void updateScreen(String data, String tag) {
        try {
            if (tag.compareTo(Config.TAG_PRODUCT_LIST) == 0) {
                Log.d("datahoome", data);
                JSONObject jsonObject=new JSONObject(data);
                JSONArray dataJsonArr = null;
                try {
                    Log.d("nbjkkj", jsonObject.toString());
                    dataJsonArr = jsonObject.getJSONArray("product_info");
                    Log.e("tagggg", dataJsonArr.toString() + "");
                    // get the array of users

                    arraylength = dataJsonArr.length();
                    for (int i = 0; i < dataJsonArr.length(); i++)

                    {    //JSONObject c = dataJsonArr.getJSONObject(i);
                        JSONObject elem = dataJsonArr.getJSONObject(i);
                        id = elem.getString("id");

                        productlistid.add(id);
                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();

                }

                proMap = ProductListParsar.parseProducts(data);
                setProductList();

            }
            if (tag.compareTo(Config.TAG_SEARCH_LIST) == 0) {
                Log.d("datahoome", data);
                productlistid.clear();
                JSONObject jsonObject=new JSONObject(data);
                JSONArray dataJsonArr = null;
                try {
                    Log.d("nbjkkj", jsonObject.toString());
                    dataJsonArr = jsonObject.getJSONArray("info");
                    Log.e("tagggg", dataJsonArr.toString() + "");
                    if(dataJsonArr.isNull(0)) {
                        textView6.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }
                    else {
                        textView6.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        // get the array of users

                        arraylength = dataJsonArr.length();
                        for (int i = 0; i < dataJsonArr.length(); i++)

                        {    //JSONObject c = dataJsonArr.getJSONObject(i);
                            JSONObject elem = dataJsonArr.getJSONObject(i);
                            id = elem.getString("id");

                            productlistid.add(id);
                        }
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();

                }
                searchMap = SearchParsar.parseProducts(data);
                setSearchList();

            }
            if (tag.compareTo(Config.TAG_PERCENTAGE_LIST) == 0) {
                Log.d("datahoome", data);
                ProductListAdapter.dose_list=null;
                getProductList();


            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void updateScreen1() {
        getProductList();
    }
    public void getSearchList(String s)
    {
        SearchAPI searchAPI=new SearchAPI(this,this);
        searchAPI.processSearch(s);
    }
}

