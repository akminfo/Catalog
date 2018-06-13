package com.fingertip.catalog;

import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fingertip.catalog.adapter.CustomerGroupAdapter;
import com.fingertip.catalog.adapter.EditProDataAdapter;
import com.fingertip.catalog.adapter.ProDataCatAdapter;
import com.fingertip.catalog.adapter.ProductListAdapter;
import com.fingertip.catalog.api.AddCouponAPI;
import com.fingertip.catalog.api.CustomerGroupAPI;
import com.fingertip.catalog.callback.Config;
import com.fingertip.catalog.callback.CustomTypefaceSpan;
import com.fingertip.catalog.fragments.EditProDataFragment;
import com.fingertip.catalog.interfaces.NetworkCallback;
import com.fingertip.catalog.model.CategoryBO;
import com.fingertip.catalog.model.CustomerGroupBO;
import com.fingertip.catalog.parsar.CategoryListParsar;
import com.fingertip.catalog.parsar.CustomerGroupListParsar;
import com.fingertip.catalog.parsar.CustomerListParsar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by lenevo on 04-05-2018.
 */

public class AddCoupon extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,NetworkCallback {
    Menu nav_Menu;
    Spinner spinner1;
    private List<CustomerGroupBO> customerGroupBOList;
    ArrayAdapter<String> spinnerArrayAdapter1;
    ArrayList<String> typearray = new ArrayList<String>();
    ArrayList<String> cusgroupname = new ArrayList<String>();
    ArrayList<String> cusgroupid = new ArrayList<String>();
    NavigationView navigationView;
    String cusname,cusid;
    LinearLayoutManager listLayoutManager;
    Map cusgroupMap=null;
    LinearLayout linearLayout2;
    TextView textheader;
 public static    CustomerGroupAdapter customerGroupAdapter;

    int mPaddingSeparator;

    LinearLayout linearLayout, linearLayout1;

    RecyclerView recyclerView;

    String type = "Type";
    ImageView view1,view2,view3,view4;
    MenuItem dashboard,catalog,products,categories,customers,sales,orders,coupon,admin,settings,logouttext,reports,cus1,cusgroup,use1,usegr;
    int flag=1;
    int flag2=1;
    int flag3=1;
    int flag1=1;
    Button button, button1, button2;
    TextView textView, textView1, textView4, textView2, textView3;
    EditText editText, editText1, editText2, editText3, editText4, editText5;
    Calendar myCalendar4, myCalendar5;
    Typeface typeface, typeface1, typeface2, typeface3;
CheckBox checkBox1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coupon);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), "robotoRegular.ttf");
//        fontChanger.replaceFonts((ViewGroup)this.findViewById(android.R.id.content));
        checkBox1=(CheckBox)findViewById(R.id.checkgroup);
        setSupportActionBar(toolbar);
        setTitle("");
        spinner1 = (Spinner) findViewById(R.id.spinner4);
        typearray.add("Type");
        typearray.add("Percentage");
        typearray.add("Fixed");
        recyclerView=(RecyclerView)findViewById(R.id.cusgrouplist) ;
        spinnerArrayAdapter1 = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_textview1, typearray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter1.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(spinnerArrayAdapter1);
        linearLayout = (LinearLayout) findViewById(R.id.datestartlay);
        linearLayout1 = (LinearLayout) findViewById(R.id.dateendlay);


        editText = (EditText) findViewById(R.id.coupnametext);
        editText1 = (EditText) findViewById(R.id.coupcode);
        editText2 = (EditText) findViewById(R.id.disctext);
        editText3 = (EditText) findViewById(R.id.amttext);
        editText4 = (EditText) findViewById(R.id.userpercoup);
        editText5 = (EditText) findViewById(R.id.userpercus);

        textView4 = (TextView) findViewById(R.id.toolbar_title);

        textView = (TextView) findViewById(R.id.generalcoupon);

        textView2 = (TextView) findViewById(R.id.datestart);
        textView3 = (TextView) findViewById(R.id.dateend);
        button = (Button) findViewById(R.id.savecoupon);

        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "robotoRegular.ttf");
        typeface1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "robotoLight.ttf");
        typeface2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "robotoMedium.ttf");
        typeface3 = Typeface.createFromAsset(getApplicationContext().getAssets(), "robotoBold.ttf");
        textView.setTypeface(typeface);

        textView2.setTypeface(typeface);
        textView3.setTypeface(typeface);
        textView4.setTypeface(typeface1);
        editText.setTypeface(typeface);
        editText1.setTypeface(typeface);
        editText2.setTypeface(typeface);
        editText3.setTypeface(typeface);
        editText4.setTypeface(typeface);
        editText5.setTypeface(typeface);
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
//        reports = nav_Menu.findItem(R.id.report);
        cus1 = nav_Menu.findItem(R.id.cust1);
        use1 = nav_Menu.findItem(R.id.user1);
        usegr = nav_Menu.findItem(R.id.usegroup);
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

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(AddCoupon.this, MyProfile.class);
                startActivity(intent);

            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                type = spinner1.getSelectedItem().toString();
                if (type.equals("Percentage")) {
                    type = "P";
                } else if (type.equals("Fixed")) {
                    type = "F";
                } else {
                    type = "Type";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        myCalendar4 = Calendar.getInstance();
        myCalendar5 = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date4 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar4.set(Calendar.YEAR, year);
                myCalendar4.set(Calendar.MONTH, monthOfYear);
                myCalendar4.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel4();
            }

        };
        linearLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                hide4();
                new DatePickerDialog(AddCoupon.this, date4, myCalendar4
                        .get(Calendar.YEAR), myCalendar4.get(Calendar.MONTH),
                        myCalendar4.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        final DatePickerDialog.OnDateSetListener date5 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar5.set(Calendar.YEAR, year);
                myCalendar5.set(Calendar.MONTH, monthOfYear);
                myCalendar5.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel5();
            }

        };
        linearLayout1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                hide4();
                new DatePickerDialog(AddCoupon.this, date5, myCalendar5
                        .get(Calendar.YEAR), myCalendar5.get(Calendar.MONTH),
                        myCalendar5.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                View v=null;
                if(b) {

                    customerGroupAdapter= new CustomerGroupAdapter(AddCoupon.this,AddCoupon.this,customerGroupBOList,AddCoupon.this,true);
                    customerGroupAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(customerGroupAdapter);
                    CustomerGroupAdapter.catgroup_list.clear();
//                    ArrayList<ProductBO> actorList = productListAdapter.getSelectActorList();
//                    for(int i=0;i<actorList.size();i++) {
//                        System.out.println("selectedall"+actorList.get(i).toString());
//                    }
                }
                else {
                    customerGroupAdapter = new CustomerGroupAdapter(AddCoupon.this, AddCoupon.this,customerGroupBOList, AddCoupon.this, false);
                    recyclerView.setAdapter(customerGroupAdapter);
                }


            }
        });
        checkBox1.setTypeface(typeface);
        getCustomerGroupList();


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

    private void updateLabel4() {

        // String myFormat = "MM/dd/yy";
        String myFormat = "yyyy-MM-dd";//In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        textView2.setText(sdf.format(myCalendar4.getTime()));
    }

    private void updateLabel5() {

        // String myFormat = "MM/dd/yy";
        String myFormat = "yyyy-MM-dd";//In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        textView3.setText(sdf.format(myCalendar5.getTime()));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (id == R.id.dash) {
            Intent i = new Intent(AddCoupon.this, MainActivity.class);
            startActivity(i);
            AddCoupon.this.overridePendingTransition(R.anim.righttoleft,
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
            Intent i = new Intent(AddCoupon.this, ProductList.class);
            startActivity(i);
            AddCoupon.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);


        }
        else if (id == R.id.categories) {
            Intent i = new Intent(AddCoupon.this, Category.class);
            startActivity(i);
            AddCoupon.this.overridePendingTransition(R.anim.righttoleft,
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
            AddCoupon.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.cust1) {
            Intent intent=new Intent(this,CustomerList.class);
            startActivity(intent);
            AddCoupon.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.cusgroup) {
            Intent intent=new Intent(this,CustomerGroup.class);
            startActivity(intent);
            AddCoupon.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.user1) {
            Intent intent=new Intent(this,UsersList.class);
            startActivity(intent);
            AddCoupon.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.usegroup) {
            Intent intent=new Intent(this,UserGroup.class);
            startActivity(intent);
            AddCoupon.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.coupon) {
            Intent intent=new Intent(this,CouponList.class);
            startActivity(intent);
            AddCoupon.this.overridePendingTransition(R.anim.righttoleft,
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
            AddCoupon.this.overridePendingTransition(R.anim.left_in,
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
            AddCoupon.this.overridePendingTransition(R.anim.righttoleft,
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

    public void addCoupon(View view) {
        StringBuilder builder = new StringBuilder(CustomerGroupAdapter.catgroup_list.toString());
        builder.deleteCharAt(0);
        builder.deleteCharAt(builder.length() - 1);
        System.out.println("cusslength" + builder.length());


        if (!(editText.getText().toString().isEmpty())) {
            if (!(editText1.getText().toString().isEmpty())) {
                if (!(type.equals("Type"))) {


                    if (!(editText2.getText().toString().isEmpty())) {
                        if (!(editText3.getText().toString().isEmpty())) {
                            if (!(textView2.getText().toString().contains("Date Start"))) {
                                if (!(textView3.getText().toString().contains("Date End"))) {
                                    if (!(editText4.getText().toString().isEmpty())) {
                                        if (!(editText5.getText().toString().isEmpty())) {
                                            if (builder.length() != 0) {

                                                AddCouponAPI addCouponAPI = new AddCouponAPI(this, this);
                                                addCouponAPI.processAddCoupon(editText.getText().toString(), editText1.getText().toString(), type, String.valueOf(builder).replaceAll(" ",""), editText2.getText().toString(), editText3.getText().toString(), textView2.getText().toString(), textView3.getText().toString(), editText4.getText().toString(), editText5.getText().toString());
                                            }
                                            else if(checkBox1.isChecked())
                                            {
                                                AddCouponAPI addCouponAPI = new AddCouponAPI(this, this);
                                                addCouponAPI.processAddCoupon(editText.getText().toString(), editText1.getText().toString(), type,"5", editText2.getText().toString(), editText3.getText().toString(), textView2.getText().toString(), textView3.getText().toString(), editText4.getText().toString(), editText5.getText().toString());


                                            }
                                            else {
                                                Toast.makeText(getApplicationContext(), "Please select customer group", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                    else {
                                        Toast.makeText(getApplicationContext(), "Please enter user per customer", Toast.LENGTH_SHORT).show();

                                    }
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Please enter user per coupon", Toast.LENGTH_SHORT).show();

                                }
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Please enter ending date", Toast.LENGTH_SHORT).show();

                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Please enter starting date", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Please enter total amount", Toast.LENGTH_SHORT).show();

                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please enter discount", Toast.LENGTH_SHORT).show();

                }


            }
            else {
                Toast.makeText(getApplicationContext(), "Please enter type", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(getApplicationContext(), "Please enter coupon code", Toast.LENGTH_SHORT).show();

        }
    }
    else

    {
        Toast.makeText(getApplicationContext(), "Please enter coupon name", Toast.LENGTH_SHORT).show();

    }


}

    @Override
    public void updateScreen(String data, String tag) {
        try {
            if (tag.compareTo(Config.TAG_ADD_COUPON_LIST) == 0) {
                Log.d("datahoome", data);
                JSONObject jsonObject=new JSONObject(data);
                if(jsonObject.getString("status").equals("Success"))
                {
                    Toast.makeText(getApplicationContext(),"Coupon added succesfully",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddCoupon.this, CouponList.class);
                    startActivity(intent);
                    AddCoupon.this.overridePendingTransition(R.anim.left_in,
                            R.anim.right_out);

                }


            }
            if (tag.compareTo(Config.TAG_CUSTOMER_GROUP_LIST) == 0) {
                Log.d("datahoome", data);
                cusgroupMap = CustomerGroupListParsar.parseGroups(data);
                setCusgroupList();

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void setCusgroupList()
    {
        try {

            customerGroupBOList = (List<CustomerGroupBO>)cusgroupMap.get("cusgroup_list");
            Log.d("grr", cusgroupMap.toString());


            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            customerGroupAdapter= new CustomerGroupAdapter(this,this,customerGroupBOList,this,false);
            listLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            listLayoutManager.scrollToPosition(0);
            recyclerView.setLayoutManager(listLayoutManager);
            recyclerView.setAdapter(customerGroupAdapter);
            recyclerView.setNestedScrollingEnabled(false);


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void updateScreen1() {

    }
    public void getCustomerGroupList()
    {
        CustomerGroupAPI customerGroupAPI=new CustomerGroupAPI(this,this);
        customerGroupAPI.processCustomerGroupList();
    }
}

