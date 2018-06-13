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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fingertip.catalog.callback.Config;
import com.fingertip.catalog.callback.CustomTabLayout;
import com.fingertip.catalog.callback.CustomTypefaceSpan;
import com.fingertip.catalog.fragments.EditProDataFragment;
import com.fingertip.catalog.fragments.EditProGeneralFragment;
import com.fingertip.catalog.fragments.EditProImageFragment;

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
import java.util.Arrays;
import java.util.List;

/**
 * Created by lenevo on 16-05-2018.
 */

public class EditProduct extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView textView;
    private CustomTabLayout tabLayout;
    private ViewPager viewPager;
    Typeface typeface, typeface1;
    HttpEntity resEntity;
    int c=0;

  public static String s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23;
    public static String s31,s32,s33,s34,s35,s36,s37,s38,s39,s40;
    public static String s51,s52,s53,s54,s55,s56,s57,s58,s59,s60,s61;
    public static String s71,s72,s73,s74,s75,s76,s77,s78,s79,s80,s81;
    Menu nav_Menu;
String checkids;
    public static List<String> ed_prolist = new ArrayList<String>();
    int count = 0;
    public ArrayList<File> img_list = new ArrayList<File>();
    public  static ArrayList<String> cat_list = new ArrayList<String>();
    public  static ArrayList<String> cat_list1 = new ArrayList<String>();

    StringBuilder checkedcontacts;
   public static String[] words;
    NavigationView navigationView;
    FileBody fileBody;
    ProgressDialog progressDialog;
    public static ArrayList<String> sort_list = new ArrayList<String>();
    int mPaddingSeparator;
    FileBody bin1, bin2, bin3, bin4, bin5, bin6, bin7, bin8, bin9, bin10, bin11;

    int f=0;
    String s1;
    ImageView view1,view2,view3,view4;
    MenuItem dashboard,catalog,products,categories,customers,sales,orders,coupon,admin,settings,logouttext,reports,cus1,cusgroup,use1,usegr;
    int flag=1;

    int flag3=1;
    int flag1=1;
    int flag2=1;
    LinearLayout linearLayout2;
    TextView textheader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        setTitle("");
        textView = (TextView) findViewById(R.id.toolbar_title);
        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "robotoRegular.ttf");
        typeface1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "robotoLight.ttf");
        textView.setTypeface(typeface1);
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
        checkids=getIntent().getExtras().getString("cat_ids");
//        words = checkids.split(" ");

        s11=getIntent().getExtras().getString("name");
        s12=getIntent().getExtras().getString("desc");
        s13=getIntent().getExtras().getString("key_feat");
        s14=getIntent().getExtras().getString("meta_name");
        s15=getIntent().getExtras().getString("meta_desc");
        s16=getIntent().getExtras().getString("meta_key");
        s17=getIntent().getExtras().getString("sku");
        s18=getIntent().getExtras().getString("quantity");
        s19=getIntent().getExtras().getString("price");
        s20=getIntent().getExtras().getString("sort");
        s21=getIntent().getExtras().getString("status");
        s22=getIntent().getExtras().getString("image");
        s23=getIntent().getExtras().getString("count");
        s31=getIntent().getExtras().getString("proimage1");
        s32=getIntent().getExtras().getString("proimage2");
        System.out.println("sds"+s31+s32);
        s33=getIntent().getExtras().getString("proimage3");
        s34=getIntent().getExtras().getString("proimage4");
        s35=getIntent().getExtras().getString("proimage5");
        s36=getIntent().getExtras().getString("proimage6");
        s37=getIntent().getExtras().getString("proimage7");
        s38=getIntent().getExtras().getString("proimage8");
        s39=getIntent().getExtras().getString("proimage9");
        s40=getIntent().getExtras().getString("proimage10");

        s51=getIntent().getExtras().getString("sort1");
        s52=getIntent().getExtras().getString("sort2");
        s53=getIntent().getExtras().getString("sort3");
        s54=getIntent().getExtras().getString("sort4");
        s55=getIntent().getExtras().getString("sort5");
        s56=getIntent().getExtras().getString("sort6");
        s57=getIntent().getExtras().getString("sort7");
        s58=getIntent().getExtras().getString("sort8");
        s59=getIntent().getExtras().getString("sort9");
        s60=getIntent().getExtras().getString("sort10");
        s71=getIntent().getExtras().getString("img_id1");
        s72=getIntent().getExtras().getString("img_id2");
        s73=getIntent().getExtras().getString("img_id3");
        s74=getIntent().getExtras().getString("img_id4");
        s75=getIntent().getExtras().getString("img_id5");
        s76=getIntent().getExtras().getString("img_id6");
        s77=getIntent().getExtras().getString("img_id7");
        s78=getIntent().getExtras().getString("img_id8");
        s79=getIntent().getExtras().getString("img_id9");
        s80=getIntent().getExtras().getString("img_id10");

        s61=getIntent().getExtras().getString("pro_id");
        ed_prolist = Arrays.asList(checkids.split(","));
        System.out.println("coups" + ed_prolist.toString());
        System.out.println("words1 are"+checkids);
//        for(int i=0;i<checkids.length();i++) {
//            checkids.replaceAll(",", "");
//            cat_list.add(checkids);
//        }





       System.out.println("cars"+cat_list.toString());
        View headerview = navigationView.getHeaderView(0);
        linearLayout2 = (LinearLayout) headerview.findViewById(R.id.newlay);
        textheader = (TextView) headerview.findViewById(R.id.user_header);

        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(EditProduct.this, MyProfile.class);
                startActivity(intent);

            }
        });



    }

    public void uploadFiles(View view) {


        checkedcontacts = new StringBuilder();
        StringBuilder builder = null;
        cat_list1.clear();
        for (int i = 0; i < EditProDataFragment.id1_array.size(); i++)

        {
            checkedcontacts.append(EditProDataFragment.id1_array.toString());
            checkedcontacts.append(",");
            s14 = EditProDataFragment.id1_array.get(i).toString();
            if ((EditProDataFragment.categoryListAdapter.mCheckStates.get(i) == true)) {



                Log.d("aca", s14);
                cat_list1.add(s14);
                builder = new StringBuilder(cat_list1.toString());

                Log.d("aca1", String.valueOf(builder));
            }
            else if((EditProDataFragment.categoryListAdapter.mCheckStates.get(i) == false))
            {
                cat_list1.remove(s14);
                builder = new StringBuilder(cat_list1.toString());

                Log.d("aca1", String.valueOf(builder));
            }
        }
        builder.deleteCharAt(0);
        builder.deleteCharAt(builder.length() - 1);
//        builder.toString().trim();

//        checkedcontacts = new StringBuilder();
//
//        StringBuilder builder = null;
//
//        for (int i = 0; i < EditProDataFragment.id1_array.size(); i++)
//
//        {
//            checkedcontacts.append(EditProDataFragment.id1_array.toString());
//            checkedcontacts.append(",");
//            s14 = EditProDataFragment.id1_array.get(i).toString();
//            if ((EditProDataFragment.categoryListAdapter.mCheckStates.get(i) == true)) {
//
//
//
//                Log.d("aca", s14);
//                cat_list.add(s14);
//                builder = new StringBuilder(cat_list.toString());
//
//                Log.d("aca1", String.valueOf(builder));
//            }
//            else if((EditProDataFragment.categoryListAdapter.mCheckStates.get(i) == false))
//            {
//                cat_list.remove(s14);
//                builder = new StringBuilder(cat_list.toString());
//
//                Log.d("aca1", String.valueOf(builder));
//            }
//        }
//        builder.deleteCharAt(0);
//        builder.deleteCharAt(builder.length() - 1);
//        System.out.println("words are"+cat_list.toString());
        if (!(EditProGeneralFragment.editText.getText().toString().isEmpty())) {

            if (!(EditProDataFragment.editText1.getText().toString().isEmpty())) {
                if (!(EditProDataFragment.editText2.getText().toString().isEmpty())) {
                    if (!(EditProDataFragment.editText3.getText().toString().isEmpty())) {
//        if(!(ImageFragment.selectedPath1.trim().equalsIgnoreCase("NONE")) && !(ImageFragment.selectedPath2.trim().equalsIgnoreCase("NONE"))){
                        progressDialog = ProgressDialog.show(this, "", "Uploading files to server.....", false);
                        final StringBuilder finalBuilder = builder;
                        Thread thread = new Thread(new Runnable() {
                            public void run() {
                                doFileUpload(finalBuilder);
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
                        Toast.makeText(getApplicationContext(), "Please enter quantity", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter price", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Please enter sku", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please enter product name", Toast.LENGTH_SHORT).show();
        }

    }

    private void setupViewPager(ViewPager viewPager) {
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new EditProGeneralFragment(), "General");
        adapter.addFrag(new EditProDataFragment(), "Data");
        adapter.addFrag(new EditProImageFragment(), "Image");


        viewPager.setAdapter(adapter);
        viewPager.setPadding(0, 0, 0, 0);
//        viewPager.setCurrentItem(1);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (id == R.id.dash) {
            Intent i = new Intent(EditProduct.this, MainActivity.class);
            startActivity(i);
            EditProduct.this.overridePendingTransition(R.anim.righttoleft,
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
            Intent i = new Intent(EditProduct.this, ProductList.class);
            startActivity(i);
            EditProduct.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);


        }
        else if (id == R.id.categories) {
            Intent i = new Intent(EditProduct.this, Category.class);
            startActivity(i);
            EditProduct.this.overridePendingTransition(R.anim.righttoleft,
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
            EditProduct.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.cust1) {
            Intent intent=new Intent(this,CustomerList.class);
            startActivity(intent);
            EditProduct.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.cusgroup) {
            Intent intent=new Intent(this,CustomerGroup.class);
            startActivity(intent);
            EditProduct.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.user1) {
            Intent intent=new Intent(this,UsersList.class);
            startActivity(intent);
            EditProduct.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.usegroup) {
            Intent intent=new Intent(this,UserGroup.class);
            startActivity(intent);
            EditProduct.this.overridePendingTransition(R.anim.righttoleft,
                    R.anim.lefttoright);
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.coupon) {
            Intent intent=new Intent(this,CouponList.class);
            startActivity(intent);
            EditProduct.this.overridePendingTransition(R.anim.righttoleft,
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
            EditProduct.this.overridePendingTransition(R.anim.left_in,
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
            EditProduct.this.overridePendingTransition(R.anim.righttoleft,
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


    private void doFileUpload(StringBuilder stringBuilder) {
        try {
            System.out.println("countimages" + EditProImageFragment.selectedPath1);
            System.out.println("countimages" + EditProImageFragment.selectedPath2);
            System.out.println("countimages" + EditProImageFragment.selectedPath3);
            System.out.println("countimages" + EditProImageFragment.selectedPath4);
            if (!(EditProImageFragment.imageView1.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.proimage).getConstantState())) {
                f++;
                System.out.println("flll" + f);
                // new RegisterAsyntaskNew().execute();
            }
            if (EditProImageFragment.linearLayout2.getVisibility() == View.VISIBLE) {
                if (!(EditProImageFragment.imageView2.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.proimage).getConstantState())) {
                    f++;
                    System.out.println("flll" + f);
                    // new RegisterAsyntaskNew().execute();
                }
            }
            if (EditProImageFragment.linearLayout3.getVisibility() == View.VISIBLE) {
                if (!(EditProImageFragment.imageView3.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.proimage).getConstantState())) {
                    f++;
                    System.out.println("flll3" + f);
                    // new RegisterAsyntaskNew().execute();
                }
            }
            if (EditProImageFragment.linearLayout4.getVisibility() == View.VISIBLE) {
                if (!(EditProImageFragment.imageView4.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.proimage).getConstantState())) {
                    f++;
                    System.out.println("flll" + f);
                    // new RegisterAsyntaskNew().execute();
                }
            }
            if (EditProImageFragment.linearLayout5.getVisibility() == View.VISIBLE) {

                if (!(EditProImageFragment.imageView5.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.proimage).getConstantState())) {
                    f++;
                    System.out.println("flll" + f);
                    // new RegisterAsyntaskNew().execute();
                }
            }
            if (EditProImageFragment.linearLayout6.getVisibility() == View.VISIBLE) {
                if (!(EditProImageFragment.imageView6.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.proimage).getConstantState())) {
                    f++;
                    System.out.println("flll" + f);
                    // new RegisterAsyntaskNew().execute();
                }
            }
            if (EditProImageFragment.linearLayout7.getVisibility() == View.VISIBLE) {
                if (!(EditProImageFragment.imageView7.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.proimage).getConstantState())) {
                    f++;
                    System.out.println("flll" + f);
                    // new RegisterAsyntaskNew().execute();
                }
            }
            if (EditProImageFragment.linearLayout8.getVisibility() == View.VISIBLE) {
                if (!(EditProImageFragment.imageView8.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.proimage).getConstantState())) {
                    f++;
                    System.out.println("flll" + f);
                    // new RegisterAsyntaskNew().execute();
                }
            }
            if (EditProImageFragment.linearLayout9.getVisibility() == View.VISIBLE) {
                if (!(EditProImageFragment.imageView9.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.proimage).getConstantState())) {
                    f++;
                    System.out.println("flll" + f);
                }
                // new RegisterAsyntaskNew().execute();
            }
            if (EditProImageFragment.linearLayout10.getVisibility() == View.VISIBLE) {
                if (!(EditProImageFragment.imageView10.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.proimage).getConstantState())) {
                    f++;
                    System.out.println("flll" + f);
                    // new RegisterAsyntaskNew().execute();
                }
            }

            String urlString = "http://fingertipmail.com/catalog_store2508/admin/rest/api/Product/update";
            try {

                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(urlString);
                if (!(EditProImageFragment.selectedPath1.trim().equalsIgnoreCase("NONE"))) {
                    File file1 = new File(EditProImageFragment.selectedPath1);
                    bin1 = new FileBody(file1);
                    c++;

                }
                if (!(EditProImageFragment.selectedPath2.trim().equalsIgnoreCase("NONE"))) {
                    bin2=null;
                    File file2 = new File(EditProImageFragment.selectedPath2);
                    System.out.println("filee"+file2);
                    bin2 = new FileBody(file2);
                    img_list.add(file2);
                    sort_list.add(EditProImageFragment.editText1.getText().toString());
                    c++;
                }
                if (!(EditProImageFragment.selectedPath3.trim().equalsIgnoreCase("NONE"))) {
                    bin3=null;
                    File file3 = new File(EditProImageFragment.selectedPath3);
                    System.out.println("filee"+file3);
                    bin3 = new FileBody(file3);
                    img_list.add(file3);
                    sort_list.add(EditProImageFragment.editText2.getText().toString());
                    c++;
                }
                if (!(EditProImageFragment.selectedPath4.trim().equalsIgnoreCase("NONE"))) {
                    bin4=null;
                    File file4 = new File(EditProImageFragment.selectedPath4);
                    System.out.println("filee"+file4);
                    bin4 = new FileBody(file4);
                    img_list.add(file4);
                    sort_list.add(EditProImageFragment.editText3.getText().toString());
                    c++;
                }
                if (!(EditProImageFragment.selectedPath5.trim().equalsIgnoreCase("NONE"))) {
                    bin5=null;
                    File file5 = new File(EditProImageFragment.selectedPath5);
                    bin5 = new FileBody(file5);
                    img_list.add(file5);
                    sort_list.add(EditProImageFragment.editText4.getText().toString());
                    c++;
                }
                if (!(EditProImageFragment.selectedPath6.trim().equalsIgnoreCase("NONE"))) {
                    bin6=null;
                    File file6 = new File(EditProImageFragment.selectedPath6);
                    bin6 = new FileBody(file6);
                    img_list.add(file6);
                    sort_list.add(EditProImageFragment.editText5.getText().toString());
                    c++;
                }
                if (!(EditProImageFragment.selectedPath7.trim().equalsIgnoreCase("NONE"))) {
                    bin7=null;
                    File file7 = new File(EditProImageFragment.selectedPath7);
                    bin7 = new FileBody(file7);
                    img_list.add(file7);
                    sort_list.add(EditProImageFragment.editText6.getText().toString());
                    c++;
                }
                if (!(EditProImageFragment.selectedPath8.trim().equalsIgnoreCase("NONE"))) {
                    bin8=null;
                    File file8 = new File(EditProImageFragment.selectedPath8);
                    bin8 = new FileBody(file8);
                    img_list.add(file8);
                    sort_list.add(EditProImageFragment.editText7.getText().toString());
                    c++;
                }
                if (!(EditProImageFragment.selectedPath9.trim().equalsIgnoreCase("NONE"))) {
                    bin9=null;
                    File file9 = new File(EditProImageFragment.selectedPath9);
                    bin9 = new FileBody(file9);
                    img_list.add(file9);
                    sort_list.add(EditProImageFragment.editText8.getText().toString());
                    c++;
                }
                if (!(EditProImageFragment.selectedPath10.trim().equalsIgnoreCase("NONE"))) {
                    bin10=null;
                    File file10 = new File(EditProImageFragment.selectedPath10);
                    bin10 = new FileBody(file10);
                    img_list.add(file10);
                    sort_list.add(EditProImageFragment.editText9.getText().toString());
                    c++;
                }
                if (!(EditProImageFragment.selectedPath11.trim().equalsIgnoreCase("NONE"))) {
                    bin11=null;
                    File file11 = new File(EditProImageFragment.selectedPath11);
                    bin11 = new FileBody(file11);
                    img_list.add(file11);
                    sort_list.add(EditProImageFragment.editText10.getText().toString());
                    c++;
                }
                System.out.println();
                StringBuilder builder = new StringBuilder(cat_list.toString());
                builder.deleteCharAt(0);
                builder.deleteCharAt(builder.length() - 1);
//                        StringBuilder builder1 = new StringBuilder(sort_list.toString());
//                        builder1.deleteCharAt(0);
//                        builder1.deleteCharAt(builder1.length() - 1);
//                        StringBuilder builder3 = new StringBuilder(img_list.toString());
//                        builder3.deleteCharAt(0);
//                        builder3.deleteCharAt(builder3.length() - 1);
//
                System.out.println("couu"+count);
                MultipartEntity reqEntity = new MultipartEntity();
                reqEntity.addPart(Config.PARAM_CATEGORY_ID, new StringBody(s61));
                reqEntity.addPart(Config.PARAM_CATEGORY_NAME, new StringBody(EditProGeneralFragment.editText.getText().toString()));
                reqEntity.addPart(Config.PARAM_CATEGORY_DESC, new StringBody(EditProGeneralFragment.editText1.getText().toString()));
                reqEntity.addPart(Config.PARAM_KEY_FEATURES, new StringBody(EditProGeneralFragment.editText4.getText().toString()));
                reqEntity.addPart(Config.PARAM_CATEGORY_META_TITLE, new StringBody(EditProGeneralFragment.editText2.getText().toString()));
                reqEntity.addPart(Config.PARAM_CATEGORY_META_DESC, new StringBody(EditProGeneralFragment.editText3.getText().toString()));
                reqEntity.addPart(Config.PARAM_CATEGORY_META_KEY, new StringBody(EditProGeneralFragment.editText5.getText().toString()));
                System.out.println("dataass"+EditProDataFragment.editText1.getText().toString());
                reqEntity.addPart(Config.PARAM_SKU, new StringBody(EditProDataFragment.editText1.getText().toString()));
                reqEntity.addPart(Config.PARAM_UPDATE_QUANTITY, new StringBody(EditProDataFragment.editText2.getText().toString()));
                reqEntity.addPart(Config.PARAM_CATEGORY_CHECKBOX, new StringBody(String.valueOf(stringBuilder).replaceAll(" ", "")));
                reqEntity.addPart(Config.PARAM_UPDATE_PRICE, new StringBody(EditProDataFragment.editText3.getText().toString()));
                reqEntity.addPart(Config.PARAM_CATEGORY_SORT_ORDER, new StringBody(EditProDataFragment.editText4.getText().toString()));
                if (EditProDataFragment.title.equals("Status")) {
                    reqEntity.addPart(Config.PARAM_CATEGORY_STATUS, new StringBody(String.valueOf(stringBuilder).replaceAll(" ", "")));
                } else {
                    reqEntity.addPart(Config.PARAM_CATEGORY_STATUS, new StringBody(EditProDataFragment.flag));
                }
                System.out.println("countaa" + f);

                System.out.println("inser" + f);
                reqEntity.addPart(Config.PARAM_CATEGORY_COUNT, new StringBody(String.valueOf(f)));

                if (!(EditProImageFragment.selectedPath1.trim().equalsIgnoreCase("NONE"))) {
                    reqEntity.addPart(Config.PARAM_FEATURED_IMAGE, bin1);
                    reqEntity.addPart(Config.PARAM_FEATURED_FLAG, new StringBody("1"));

                } else {
                    reqEntity.addPart(Config.PARAM_FEATURED_IMAGE, new StringBody(s22));
                    reqEntity.addPart(Config.PARAM_FEATURED_FLAG, new StringBody("0"));

                }
                System.out.println("cimana"+bin2 +bin3+bin4);
                if (!(EditProImageFragment.selectedPath2.trim().equalsIgnoreCase("NONE"))) {
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE1, bin2);
                    reqEntity.addPart(Config.PARAM_PRODUCT_SORT1, new StringBody(EditProImageFragment.editText1.getText().toString()));
                    reqEntity.addPart(Config.PARAM_PRODUCT_FLAG_1, new StringBody("1"));
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE_ID1, new StringBody(s71));
                } else {
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE1, new StringBody(s31));
                    reqEntity.addPart(Config.PARAM_PRODUCT_FLAG_1, new StringBody("0"));
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE_ID1, new StringBody(s71));

                }
                if (!(EditProImageFragment.selectedPath3.trim().equalsIgnoreCase("NONE"))) {
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE2, bin3);
                    reqEntity.addPart(Config.PARAM_PRODUCT_SORT2, new StringBody(EditProImageFragment.editText2.getText().toString()));
                    reqEntity.addPart(Config.PARAM_PRODUCT_FLAG_2, new StringBody("1"));
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE_ID2, new StringBody(s72));
                } else {

                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE2, new StringBody(s32));
                    reqEntity.addPart(Config.PARAM_PRODUCT_FLAG_2, new StringBody("0"));
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE_ID2, new StringBody(s72));

                }
                if (!(EditProImageFragment.selectedPath4.trim().equalsIgnoreCase("NONE"))) {
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE3, bin4);
                    reqEntity.addPart(Config.PARAM_PRODUCT_SORT3, new StringBody(EditProImageFragment.editText3.getText().toString()));
                    reqEntity.addPart(Config.PARAM_PRODUCT_FLAG_3, new StringBody("1"));
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE_ID3, new StringBody(s73));
                } else {
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE3, new StringBody(s33));
                    reqEntity.addPart(Config.PARAM_PRODUCT_FLAG_3, new StringBody("0"));
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE_ID3, new StringBody(s73));

                }
                if (!(EditProImageFragment.selectedPath5.trim().equalsIgnoreCase("NONE"))) {
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE4, bin5);
                    reqEntity.addPart(Config.PARAM_PRODUCT_SORT4, new StringBody(EditProImageFragment.editText4.getText().toString()));
                    reqEntity.addPart(Config.PARAM_PRODUCT_FLAG_4, new StringBody("1"));
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE_ID4, new StringBody(s74));
                } else {
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE4, new StringBody(s34));
                    reqEntity.addPart(Config.PARAM_PRODUCT_FLAG_4, new StringBody("0"));
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE_ID4, new StringBody(s74));

                }

                if (!(EditProImageFragment.selectedPath6.trim().equalsIgnoreCase("NONE"))) {
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE5, bin6);
                    reqEntity.addPart(Config.PARAM_PRODUCT_SORT5, new StringBody(EditProImageFragment.editText5.getText().toString()));
                    reqEntity.addPart(Config.PARAM_PRODUCT_FLAG_5, new StringBody("1"));
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE_ID5, new StringBody(s75));
                } else {
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE5, new StringBody(s35));
                    reqEntity.addPart(Config.PARAM_PRODUCT_FLAG_5, new StringBody("0"));
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE_ID5, new StringBody(s75));

                }
                if (!(EditProImageFragment.selectedPath7.trim().equalsIgnoreCase("NONE"))) {
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE6, bin7);
                    reqEntity.addPart(Config.PARAM_PRODUCT_SORT6, new StringBody(EditProImageFragment.editText6.getText().toString()));
                    reqEntity.addPart(Config.PARAM_PRODUCT_FLAG_6, new StringBody("1"));
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE_ID6, new StringBody(s76));
                } else {
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE6, new StringBody(s36));
                    reqEntity.addPart(Config.PARAM_PRODUCT_FLAG_6, new StringBody("0"));
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE_ID6, new StringBody(s76));

                }
                if (!(EditProImageFragment.selectedPath8.trim().equalsIgnoreCase("NONE"))) {
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE7, bin8);
                    reqEntity.addPart(Config.PARAM_PRODUCT_SORT7, new StringBody(EditProImageFragment.editText7.getText().toString()));
                    reqEntity.addPart(Config.PARAM_PRODUCT_FLAG_7, new StringBody("1"));
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE_ID7, new StringBody(s77));
                } else {
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE7, new StringBody(s37));
                    reqEntity.addPart(Config.PARAM_PRODUCT_FLAG_7, new StringBody("0"));
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE_ID7, new StringBody(s77));

                }
                if (!(EditProImageFragment.selectedPath9.trim().equalsIgnoreCase("NONE"))) {
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE8, bin9);
                    reqEntity.addPart(Config.PARAM_PRODUCT_SORT8, new StringBody(EditProImageFragment.editText8.getText().toString()));
                    reqEntity.addPart(Config.PARAM_PRODUCT_FLAG_8, new StringBody("1"));
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE_ID8, new StringBody(s78));
                } else {
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE8, new StringBody(s38));
                    reqEntity.addPart(Config.PARAM_PRODUCT_FLAG_8, new StringBody("0"));
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE_ID8, new StringBody(s78));

                }
                if (!(EditProImageFragment.selectedPath10.trim().equalsIgnoreCase("NONE"))) {
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE9, bin10);
                    reqEntity.addPart(Config.PARAM_PRODUCT_SORT9, new StringBody(EditProImageFragment.editText9.getText().toString()));
                    reqEntity.addPart(Config.PARAM_PRODUCT_FLAG_9, new StringBody("1"));
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE_ID9, new StringBody(s79));
                } else {
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE9, new StringBody(s39));
                    reqEntity.addPart(Config.PARAM_PRODUCT_FLAG_9, new StringBody("0"));
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE_ID9, new StringBody(s79));

                }
                if (!(EditProImageFragment.selectedPath11.trim().equalsIgnoreCase("NONE"))) {
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE10, bin11);
                    reqEntity.addPart(Config.PARAM_PRODUCT_SORT10, new StringBody(EditProImageFragment.editText10.getText().toString()));
                    reqEntity.addPart(Config.PARAM_PRODUCT_FLAG_10, new StringBody("1"));
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE_ID10, new StringBody(s80));
                } else {
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE10, new StringBody(s40));
                    reqEntity.addPart(Config.PARAM_PRODUCT_FLAG_10, new StringBody("0"));
                    reqEntity.addPart(Config.PARAM_PRODUCT_IMAGE_ID10, new StringBody(s80));

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
                if (resEntity != null) {
                    Log.i("RESPONSE", response_str);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            try {

                                Toast.makeText(getApplicationContext(), "Updated successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(EditProduct.this, ProductList.class);
                                startActivity(intent);
                                EditProduct.this.overridePendingTransition(R.anim.left_in,
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
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}