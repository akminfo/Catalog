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
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fingertip.catalog.callback.Config;
import com.fingertip.catalog.callback.CustomTabLayout;
import com.fingertip.catalog.callback.CustomTypefaceSpan;
import com.fingertip.catalog.fragments.EditCusDataFragment;
import com.fingertip.catalog.fragments.EditCusGeneralFragment;
import com.fingertip.catalog.fragments.EditCusImageFargment;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenevo on 19-05-2018.
 */

public class EditCustomer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView textView;
    private CustomTabLayout tabLayout;
    private ViewPager viewPager;
    Typeface typeface, typeface1, typeface2;
    Menu nav_Menu;

    HttpEntity resEntity;
    ProgressDialog progressDialog;
    LinearLayout linearLayout2;
    TextView textheader;
public  static  String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16;

    NavigationView navigationView;
    int mPaddingSeparator;

    Button button;

    ImageView view1,view2,view3,view4;
    MenuItem dashboard,catalog,products,categories,customers,sales,orders,coupon,admin,settings,logouttext,reports,cus1,cusgroup,use1,usegr;
    int flag=1;
    int flag2=1;
    int flag3=1;
    int flag1=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        setTitle("");
        textView = (TextView) findViewById(R.id.toolbar_title);
        button = (Button) findViewById(R.id.savecusdata4);
        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "robotoRegular.ttf");
        typeface1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "robotoLight.ttf");
        typeface2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "robotoBold.ttf");
        textView.setTypeface(typeface1);
        button.setTypeface(typeface);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (CustomTabLayout) findViewById(R.id.tabs);
        setupViewPager(viewPager);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        nav_Menu = navigationView.getMenu();
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
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
        s1=getIntent().getExtras().getString("id");
        s2=getIntent().getExtras().getString("fname");
        s3=getIntent().getExtras().getString("lname");
        s4=getIntent().getExtras().getString("gen");
        s5=getIntent().getExtras().getString("email");
        s6=getIntent().getExtras().getString("mobile");
        s7=getIntent().getExtras().getString("username");
        s8=getIntent().getExtras().getString("pass");
        s9=getIntent().getExtras().getString("status");
        s10=getIntent().getExtras().getString("address");
        s11=getIntent().getExtras().getString("city");
        s12=getIntent().getExtras().getString("pin");
        s13=getIntent().getExtras().getString("state");
        s14=getIntent().getExtras().getString("country");
        s15=getIntent().getExtras().getString("image");
        s16=getIntent().getExtras().getString("group");
        View headerview = navigationView.getHeaderView(0);
        linearLayout2 = (LinearLayout) headerview.findViewById(R.id.newlay);
        textheader = (TextView) headerview.findViewById(R.id.user_header);

        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(EditCustomer.this, MyProfile.class);
                startActivity(intent);

            }
        });

    }

    public void editCus(View view) {
        System.out.println("cusgroup"+EditCusGeneralFragment.cusid);
        if (!(EditCusGeneralFragment.editText.getText().toString().isEmpty())) {
            if (!(EditCusGeneralFragment.editText1.getText().toString().isEmpty())) {
                if (!(EditCusGeneralFragment.cusg.equals("Choose Customer Group"))) {
                if (!(EditCusGeneralFragment.editText2.getText().toString().isEmpty())) {
                    if (!(EditCusGeneralFragment.editText3.getText().toString().isEmpty())) {
                        if (!(EditCusGeneralFragment.editText4.getText().toString().isEmpty())) {
                            if (!(EditCusGeneralFragment.editText5.getText().toString().isEmpty())) {

                                    if (!(EditCusDataFragment.editText.getText().toString().isEmpty())) {
                                        if (!(EditCusDataFragment.editText1.getText().toString().isEmpty())) {
                                            if (!(EditCusDataFragment.editText2.getText().toString().isEmpty())) {
                                                if (!(EditCusDataFragment.editText3.getText().toString().isEmpty())) {
                                                    if (!(EditCusDataFragment.editText4.getText().toString().isEmpty())) {

                                                        progressDialog = ProgressDialog.show(this, "", "Uploading files to server.....", false);
                                                        Thread thread = new Thread(new Runnable() {
                                                            public void run() {
                                                                doFileUpload();
                                                                runOnUiThread(new Runnable() {
                                                                    public void run() {
                                                                        if (progressDialog.isShowing())
                                                                            progressDialog.dismiss();
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        thread.start();
                                                    } else {
                                                        Toast.makeText(getApplicationContext(), "Please enter country", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Toast.makeText(getApplicationContext(), "Please enter city", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(getApplicationContext(), "Please enter state", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Please enter pincode", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Please enter address", Toast.LENGTH_SHORT).show();
                                    }

                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Please enter username", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter mobile no", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                }
            }
                else {
                    Toast.makeText(getApplicationContext(),"Please select customer group",Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(getApplicationContext(), "Please enter last name", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please enter first name", Toast.LENGTH_SHORT).show();
        }

    }

    private void setupViewPager(ViewPager viewPager) {
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new EditCusGeneralFragment(), "General");
        adapter.addFrag(new EditCusDataFragment(), "Data");
        adapter.addFrag(new EditCusImageFargment(), "Image");
        viewPager.setAdapter(adapter);
        viewPager.setPadding(0, 0, 0, 0);
//        viewPager.setCurrentItem(1);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (id == R.id.dash) {
            Intent i = new Intent(EditCustomer.this, MainActivity.class);
            startActivity(i);
            EditCustomer.this.overridePendingTransition(R.anim.righttoleft,
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
            Intent i = new Intent(EditCustomer.this, ProductList.class);
            startActivity(i);
            EditCustomer.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);


        }
        else if (id == R.id.categories) {
            Intent i = new Intent(EditCustomer.this, Category.class);
            startActivity(i);
            EditCustomer.this.overridePendingTransition(R.anim.righttoleft,
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
            EditCustomer.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.cust1) {
            Intent intent=new Intent(this,CustomerList.class);
            startActivity(intent);
            EditCustomer.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.cusgroup) {
            Intent intent=new Intent(this,CustomerGroup.class);
            startActivity(intent);
            EditCustomer.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.user1) {
            Intent intent=new Intent(this,UsersList.class);
            startActivity(intent);
            EditCustomer.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.usegroup) {
            Intent intent=new Intent(this,UserGroup.class);
            startActivity(intent);
            EditCustomer.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.coupon) {
            Intent intent=new Intent(this,CouponList.class);
            startActivity(intent);
            EditCustomer.this.overridePendingTransition(R.anim.righttoleft,
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
            EditCustomer.this.overridePendingTransition(R.anim.left_in,
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
            EditCustomer.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }


        return true;


    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private void setupTabIcons() {

        String[] tabtext = {
                "Contest",
                "News",
                "Invite"
        };

        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);

                        // int tabIconColor = ContextCompat.getColor(NewHomeActivity.this, R.color.white);

                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        // int tabIconColor = ContextCompat.getColor(NewHomeActivity.this, R.color.unselect);

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        finish();

    }

    private void doFileUpload() {


        String urlString = "http://fingertipmail.com/catalog_store2508/admin/rest/api/Customer/update";
        try {

            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(urlString);


            MultipartEntity reqEntity = new MultipartEntity();
            reqEntity.addPart(Config.PARAM_CATEGORY_ID, new StringBody(s1));
            System.out.println("cusssss"+s1);
            reqEntity.addPart(Config.PARAM_FIRST_NAME, new StringBody(EditCusGeneralFragment.editText.getText().toString()));
            reqEntity.addPart(Config.PARAM_LAST_NAME, new StringBody(EditCusGeneralFragment.editText1.getText().toString()));
            reqEntity.addPart(Config.PARAM_EMAIL, new StringBody(EditCusGeneralFragment.editText2.getText().toString()));
            reqEntity.addPart(Config.PARAM_USER_PASS, new StringBody(EditCusGeneralFragment.editText5.getText().toString()));
            reqEntity.addPart(Config.PARAM_USER_NAME, new StringBody(EditCusGeneralFragment.editText4.getText().toString()));
            reqEntity.addPart(Config.PARAM_MOBILE, new StringBody(EditCusGeneralFragment.editText3.getText().toString()));
            reqEntity.addPart(Config.PARAM_GENDER, new StringBody(EditCusGeneralFragment.gen));
            System.out.println("cusssss"+EditCusGeneralFragment.cusid);
            reqEntity.addPart(Config.PARAM_CUSTOMER_GROUP, new StringBody(EditCusGeneralFragment.cusid));
            reqEntity.addPart(Config.PARAM_CATEGORY_STATUS, new StringBody(EditCusGeneralFragment.flag));
            reqEntity.addPart(Config.PARAM_ADDRESS, new StringBody(EditCusDataFragment.editText.getText().toString()));
            reqEntity.addPart(Config.PARAM_CITY, new StringBody(EditCusDataFragment.editText3.getText().toString()));
            reqEntity.addPart(Config.PARAM_PINCODE, new StringBody(EditCusDataFragment.editText1.getText().toString()));
            reqEntity.addPart(Config.PARAM_COUNTRY_ID, new StringBody(EditCusDataFragment.editText4.getText().toString()));
            reqEntity.addPart(Config.PARAM_ZONE_ID, new StringBody(EditCusDataFragment.editText2.getText().toString()));


            if (EditCusImageFargment.fileBody != null) {
                reqEntity.addPart(Config.PARAM_IMAGE, EditCusImageFargment.fileBody);
                reqEntity.addPart(Config.PARAM_FEATURED_FLAG, new StringBody("1"));
            } else if (EditCusImageFargment.selectedPath1 != null) {
                File file1 = new File(EditCusImageFargment.selectedPath1);
                FileBody bin1 = new FileBody(file1);
                reqEntity.addPart(Config.PARAM_IMAGE, bin1);
                reqEntity.addPart(Config.PARAM_FEATURED_FLAG, new StringBody("1"));
            }
            else {
                reqEntity.addPart(Config.PARAM_IMAGE, new StringBody(s15));
                reqEntity.addPart(Config.PARAM_FEATURED_FLAG, new StringBody("0"));
            }


//                        for(int i=0; i < img_list.size();i++){
//                            FileBody fileBody1 = new FileBody(img_list.get(i));
//                            System.out.println("filesize"+img_list.get(i).toString());
//                            reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE, fileBody1);
//                        }

            post.setEntity(reqEntity);
            HttpResponse response = client.execute(post);
            resEntity = response.getEntity();
            final String response_str = EntityUtils.toString(resEntity);
            System.out.println("resss"+response_str);
            if (resEntity != null) {
                Log.i("RESPONSE", response_str);
                runOnUiThread(new Runnable() {
                    public void run() {
                        try {

                            Toast.makeText(getApplicationContext(), "Customer Updated successfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(EditCustomer.this, CustomerList.class);
                            startActivity(intent);
                            EditCustomer.this.overridePendingTransition(R.anim.left_in,
                                    R.anim.right_out);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception ex) {
            Log.e("Debug", "error: " + ex.getMessage(), ex);
        }

    }

}


