package com.fingertip.catalog;

import android.app.ProgressDialog;
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
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.fingertip.catalog.api.AddUserAPI;
import com.fingertip.catalog.api.EditUserAPI;
import com.fingertip.catalog.api.UserGroupAPI;
import com.fingertip.catalog.callback.AppController;
import com.fingertip.catalog.callback.Config;
import com.fingertip.catalog.callback.CustomTabLayout;
import com.fingertip.catalog.callback.CustomTypefaceSpan;
import com.fingertip.catalog.interfaces.NetworkCallback;

import org.apache.http.HttpEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lenevo on 23-05-2018.
 */

public class EditUser extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,NetworkCallback {
    TextView textView,textView2;
    private CustomTabLayout tabLayout;
    private ViewPager viewPager;
    RadioGroup radioGroup;
    String cus,status;
    Typeface typeface, typeface1,typeface2;
    int arraylength1=-1;
    String cusid,cusname1,cusname;
    Menu nav_Menu;
    Spinner spinner1,spinner2;
    ArrayList<String> cus_array = new ArrayList<String>();
    ArrayList<String> sta_array = new ArrayList<String>();
    HttpEntity resEntity;
    String gen;
    LinearLayout linearLayout2;
    TextView textheader;
    ArrayList<String> customergroup  = new ArrayList<String>();
    JSONObject jsonObject=null;
    ProgressDialog progressDialog;
    ArrayAdapter<String> spinnerArrayAdapter1;
    ArrayAdapter<String> spinnerArrayAdapter2;
    EditText editText,editText1,editText2,editText3,editText4,editText5,editText6,editText7;

    NavigationView navigationView;
    int mPaddingSeparator;

    Button button;


    String type;
String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11;
    RadioButton radioButton,radioButton1;
    ImageView view1,view2,view3,view4;
    MenuItem dashboard,catalog,products,categories,customers,sales,orders,coupon,admin,settings,logouttext,reports,cus1,cusgroup,use1,usegr;
    int flag=1;
    int flag3=1;
    int flag1=1;
    int flag2=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        setTitle("");
        spinner1=(Spinner)findViewById(R.id.spinneruser);
        spinner2=(Spinner)findViewById(R.id.userstatus);
        textView = (TextView) findViewById(R.id.toolbar_title);
        textView2 = (TextView) findViewById(R.id.generaluser);
        button=(Button)findViewById(R.id.saveuser);
        editText=(EditText)findViewById(R.id.username1);
        editText1=(EditText)findViewById(R.id.userfirstname);
        editText2=(EditText)findViewById(R.id.userlastname);
        editText3=(EditText)findViewById(R.id.useremail);
        editText4=(EditText)findViewById(R.id.usermobile);
        editText5=(EditText)findViewById(R.id.userpassword);
        editText6=(EditText)findViewById(R.id.usergstin);
        radioButton=(RadioButton)findViewById(R.id.male);
        radioButton1=(RadioButton)findViewById(R.id.female);

        spinnerArrayAdapter1 = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_textview1, cus_array); //selected item will look like a spinner set from XML
        spinnerArrayAdapter1.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        //  spinner1.setAdapter(spinnerArrayAdapter1);
        spinnerArrayAdapter2 = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_textview1, sta_array); //selected item will look like a spinner set from XML
        spinnerArrayAdapter2.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);


        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "robotoRegular.ttf");
        typeface1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "robotoLight.ttf");
        typeface2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "robotoBold.ttf");
        textView.setTypeface(typeface1);
        textView2.setTypeface(typeface);
        editText.setTypeface(typeface);
        radioButton.setTypeface(typeface);
        radioButton1.setTypeface(typeface);
        editText1.setTypeface(typeface);
        editText2.setTypeface(typeface);
        editText3.setTypeface(typeface);
        editText4.setTypeface(typeface);
        editText5.setTypeface(typeface);
        editText6.setTypeface(typeface);

        button.setTypeface(typeface);

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
      //  reports = nav_Menu.findItem(R.id.report);
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
        radioGroup = (RadioGroup)findViewById(R.id.radiogroupgender);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {

                    case R.id.male:
                        int selectedId = radioGroup.getCheckedRadioButtonId();
                        radioButton = (RadioButton)findViewById(selectedId);
                        gen="M";

                        break;
                    case R.id.female:
                        int selectedId1 = radioGroup.getCheckedRadioButtonId();
                        radioButton = (RadioButton)findViewById(selectedId1);
                        gen="F";

                        break;

                }
            }
        });
        radioButton1.setTypeface(typeface);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                status=spinner2.getSelectedItem().toString();
                if(status.equals("Enable"))
                {
                    type="1";
                }
                else {
                    type="0";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                cus = spinner1.getSelectedItem().toString();

                    if(!(cus.equals("Choose User Group"))) {


                        getCusId(cus);

                    }

                    System.out.println("iddd" + cusid);


            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        try
        {
            s1=getIntent().getExtras().getString("id",null);
            s2=getIntent().getExtras().getString("name",null);
            s3=getIntent().getExtras().getString("usergroup",null);
            s4=getIntent().getExtras().getString("first",null);
            s5=getIntent().getExtras().getString("last",null);
            s6=getIntent().getExtras().getString("gender",null);
            s7=getIntent().getExtras().getString("email",null);
            s8=getIntent().getExtras().getString("mobile",null);
            s9=getIntent().getExtras().getString("pass",null);
            s10=getIntent().getExtras().getString("status",null);
            s11=getIntent().getExtras().getString("gstin",null);
            editText.setText(s2);
            editText1.setText(s4);
            editText2.setText(s5);
            editText3.setText(s7);
            editText4.setText(s8);
            editText5.setText(s9);
            editText6.setText(s11);
            if(s6.equals("M"))
            {
                gen="M";
            }
            else {
                gen="F";
            }
            if(s10.equals("1"))
            {

                type="1";
                sta_array.add("Enable");
                sta_array.add("Disable");
            }
            else {

                type="0";
                sta_array.add("Disable");
                sta_array.add("Enable");
            }
            spinner2.setAdapter(spinnerArrayAdapter2);

            View headerview = navigationView.getHeaderView(0);
            linearLayout2 = (LinearLayout) headerview.findViewById(R.id.newlay);
            textheader = (TextView) headerview.findViewById(R.id.user_header);

            linearLayout2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawer.closeDrawer(GravityCompat.START);
                    Intent intent = new Intent(EditUser.this, MyProfile.class);
                    startActivity(intent);

                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if(s3.equals("0"))
        {
            cus_array.add("Choose User Group");
            getAllUsers1();
        }
        else {
            getUserGroup();

        }


    }
    public void addCus(View view)
    {


    }
    public void getAllUsers1() {



        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.USERGROUPURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    jsonObject = new JSONObject(response);

                    JSONArray dataJsonArr1 = null;

                    System.out.println("dataresponse" + response);
                    dataJsonArr1 = jsonObject.getJSONArray("usergroup_info");

                    arraylength1 = dataJsonArr1.length();
                    Log.e("responeeee", dataJsonArr1.toString() + "");
                    for (int j = 0; j < dataJsonArr1.length(); j++)

                    {    //JSONObject c = dataJsonArr.getJSONObject(i);
                        JSONObject elem1 = dataJsonArr1.getJSONObject(j);

                        cusname1 = elem1.getString("name");
//                            cusid = elem1.getString("id");
                        cus_array.add(cusname1);
                        spinner1.setAdapter(spinnerArrayAdapter1);


                    }


//
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(Config.TAG_USER_GROUP_LIST, "Error: " + error.getMessage());
                error.printStackTrace();

            }
        })  {


        };

        // Adding request to request queue
        AppController ctrl = AppController.getInstance();
        ctrl.addToRequestQueue(strReq, Config.TAG_USER_GROUP_LIST);

    }



    public void getAllUsers(final String s) {



        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.USERGROUPURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    jsonObject = new JSONObject(response);

                    JSONArray dataJsonArr1 = null;

                    System.out.println("dataresponse" + response);
                    dataJsonArr1 = jsonObject.getJSONArray("usergroup_info");

                    arraylength1 = dataJsonArr1.length();
                    Log.e("responeeee", dataJsonArr1.toString() + "");
                    for (int j = 0; j < dataJsonArr1.length(); j++)

                    {    //JSONObject c = dataJsonArr.getJSONObject(i);
                        JSONObject elem1 = dataJsonArr1.getJSONObject(j);
                        if(!(elem1.getString("name").equals(s))) {
                            cusname1 = elem1.getString("name");
                            //cusid = elem1.getString("id");
                            cus_array.add(cusname1);
                            spinner1.setAdapter(spinnerArrayAdapter1);
                        }

                    }


//
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(Config.TAG_USER_GROUP_LIST, "Error: " + error.getMessage());
                error.printStackTrace();

            }
        })  {


        };

        // Adding request to request queue
        AppController ctrl = AppController.getInstance();
        ctrl.addToRequestQueue(strReq, Config.TAG_USER_GROUP_LIST);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (id == R.id.dash) {
            Intent i = new Intent(EditUser.this, MainActivity.class);
            startActivity(i);
            EditUser.this.overridePendingTransition(R.anim.righttoleft,
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
            Intent i = new Intent(EditUser.this, ProductList.class);
            startActivity(i);
            EditUser.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);


        }
        else if (id == R.id.categories) {
            Intent i = new Intent(EditUser.this, Category.class);
            startActivity(i);
            EditUser.this.overridePendingTransition(R.anim.righttoleft,
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
            EditUser.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.cust1) {
            Intent intent=new Intent(this,CustomerList.class);
            startActivity(intent);
            EditUser.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.cusgroup) {
            Intent intent=new Intent(this,CustomerGroup.class);
            startActivity(intent);
            EditUser.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.user1) {
            Intent intent=new Intent(this,UsersList.class);
            startActivity(intent);
            EditUser.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.usegroup) {
            Intent intent=new Intent(this,UserGroup.class);
            startActivity(intent);
            EditUser.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.coupon) {
            Intent intent=new Intent(this,CouponList.class);
            startActivity(intent);
            EditUser.this.overridePendingTransition(R.anim.righttoleft,
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
            EditUser.this.overridePendingTransition(R.anim.left_in,
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
            EditUser.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }


        return true;


    }


    @Override
    public void updateScreen(String data, String tag) {
        try {
            if (tag.compareTo(Config.TAG_USER_GROUP_LIST) == 0) {
                Log.d("datahoome", data);
                JSONObject jsonObject = new JSONObject(data);

                JSONArray dataJsonArr1 = null;
                dataJsonArr1 = jsonObject.getJSONArray("usergroup_info");

                arraylength1 = dataJsonArr1.length();
                Log.e("responeeee", dataJsonArr1.toString() + "");
                for (int j = 0; j < dataJsonArr1.length(); j++)

                {    //JSONObject c = dataJsonArr.getJSONObject(i);
                    JSONObject elem1 = dataJsonArr1.getJSONObject(j);
                    cusname = elem1.getString("name");


                    if(s3.equals(elem1.getString("id"))) {
                        cusname = elem1.getString("name");
                        //cusid = elem1.getString("id");
                        cus_array.add(cusname);
                        spinner1.setAdapter(spinnerArrayAdapter1);
                        getAllUsers(cusname);

                    }

                }


            }
            if (tag.compareTo(Config.TAG_EDIT_USERLIST) == 0) {
                JSONObject jsonObject = new JSONObject(data);
if(jsonObject.getString("status").equals("Success"))
{
    Toast.makeText(getApplicationContext(),"User updated successfully",Toast.LENGTH_SHORT).show();
    Intent intent = new Intent(EditUser.this,UsersList.class);
    startActivity(intent);
    EditUser.this.overridePendingTransition(R.anim.left_in,
            R.anim.right_out);

}
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateScreen1() {

    }


    @Override
    public void onBackPressed() {


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        finish();

    }

    public void getUserGroup()
    {
        UserGroupAPI userGroupAPI=new UserGroupAPI(this,this);
        userGroupAPI.processUserGroupList();
    }
    public void getCusId(final String custext)
    {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.USERGROUPURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    jsonObject = new JSONObject(response);

                    JSONArray dataJsonArr1 = null;

                    System.out.println("dataresponse" + response);
                    dataJsonArr1 = jsonObject.getJSONArray("usergroup_info");

                    arraylength1 = dataJsonArr1.length();
                    Log.e("responeeee", dataJsonArr1.toString() + "");
                    for (int j = 0; j < dataJsonArr1.length(); j++)

                    {    //JSONObject c = dataJsonArr.getJSONObject(i);
                        JSONObject elem1 = dataJsonArr1.getJSONObject(j);

                        cusname1 = elem1.getString("name");


                        if(custext.equals(cusname1)) {
                            cusid = elem1.getString("id");

                        }

                    }


//
                } catch (Exception e) {
                    e.printStackTrace();
                }

//

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(Config.TAG_USER_GROUP_LIST,"Error: " + error.getMessage());
                error.printStackTrace();

            }
        }) {



        };

        // Adding request to request queue
        AppController ctrl = AppController.getInstance();
        ctrl.addToRequestQueue(strReq, Config.TAG_USER_GROUP_LIST);
    }
    public void editUser(View view)
    {
        if(!(editText.getText().toString()).isEmpty())
        {
            if(!(cus.equals("Choose User Group")))
            {
                if(!(editText3.getText().toString()).isEmpty())
                {
                    if(!(editText5.getText().toString()).isEmpty()) {

                            EditUserAPI editUserAPI = new EditUserAPI(this, this);
                            editUserAPI.processEditUser(s1,editText.getText().toString(), cusid, editText1.getText().toString(), editText2.getText().toString(), gen, editText3.getText().toString(), editText4.getText().toString(), editText5.getText().toString(), type, editText6.getText().toString());

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Please enter password",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please enter email",Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Please select user group",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Please enter username",Toast.LENGTH_SHORT).show();
        }
    }


}


