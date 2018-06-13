package com.fingertip.catalog.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fingertip.catalog.EditProduct;
import com.fingertip.catalog.R;
import com.fingertip.catalog.api.ProductImageDeleteAPI;
import com.fingertip.catalog.callback.Config;
import com.fingertip.catalog.callback.FilePath;
import com.fingertip.catalog.interfaces.NetworkCallback;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import org.apache.http.HttpEntity;
import org.json.JSONObject;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * Created by lenevo on 16-05-2018.
 */

public class EditProImageFragment extends Fragment implements NetworkCallback {
    TextView textView,textView1,textView2,textView3;
    Typeface typeface,typeface1;
    public static EditText editText1,editText2,editText3,editText4,editText5,editText6,editText7,editText8,editText9,editText10;
    HttpEntity resEntity;
   public static ImageView imageView,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10,imageView11;
    private static final int SELECT_FILE1 = 1;
    private static final int SELECT_FILE2 = 2;
    private static final int SELECT_FILE3 = 3;
    private static final int SELECT_FILE4 = 4;
    private static final int SELECT_FILE5 = 5;
    private static final int SELECT_FILE6 = 6;
    private static final int SELECT_FILE7 = 7;
    private static final int SELECT_FILE8 = 8;
    private static final int SELECT_FILE9 = 9;
    private static final int SELECT_FILE10 = 10;
    private static final int SELECT_FILE11 = 11;
    public  static  int count=0;
    private int REQUEST_CODE_OPEN = 2;
    Button button;
    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private Bitmap bitmap3;
    private Bitmap bitmap4;
    private Bitmap bitmap5;
    private Bitmap bitmap6;
    private Bitmap bitmap7;
    private Bitmap bitmap8;
    private Bitmap bitmap9;
    private Bitmap bitmap10;
    private Bitmap bitmap11;
    StringBuilder checkedcontacts;

    ProgressDialog progressDialog;
    String path;
    public static String selectedPath1 = "NONE";
    public static String selectedPath2 = "NONE";
    public static  String selectedPath3 = "NONE";
    public static  String selectedPath4 = "NONE";
    public static  String selectedPath5 = "NONE";
    public static   String selectedPath6 = "NONE";
    public static   String selectedPath7 = "NONE";
    public static   String selectedPath8 = "NONE";
    public static   String selectedPath9 = "NONE";
    public static  String selectedPath10 = "NONE";
    public static  String selectedPath11 = "NONE";
   public static LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4,linearLayout5,linearLayout6,linearLayout7,linearLayout8,linearLayout9,linearLayout10;
    EditText editText;
    ImageView cm,cm2,cm3,cm4,cm5,cm6,cm7,cm8,cm9,cm10,cm11;
    ImageView btnadd1,btnadd2,btnadd3,btnadd4,btnadd5,btnadd6,btnadd7,btnadd8,btnadd9,btnadd10,delbtn1,delbtn2,delbtn3,delbtn4,delbtn5,delbtn6,delbtn7,delbtn8,delbtn9,delbtn10;
    TextView imgchoose1,imgchoose2,imgchoose3,imgchoose4,imgchoose5,imgchoose6,imgchoose7,imgchoose8,imgchoose9,imgchoose10,imgchoose11;
    public EditProImageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.edit_pro_image, container, false);

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);



    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        textView=(TextView)getView().findViewById(R.id.featext);

        textView2=(TextView)getView().findViewById(R.id.addtext);

        imageView=(ImageView)getView().findViewById(R.id.pronoimage);
        imageView1=(ImageView)getView().findViewById(R.id.pronoimage2);
        imageView2=(ImageView)getView().findViewById(R.id.pronoimage3);
        imageView3=(ImageView)getView().findViewById(R.id.pronoimage4);
        imageView4=(ImageView)getView().findViewById(R.id.pronoimage5);
        imageView5=(ImageView)getView().findViewById(R.id.pronoimage6);
        imageView6=(ImageView)getView().findViewById(R.id.pronoimage7);
        imageView7=(ImageView)getView().findViewById(R.id.pronoimage8);
        imageView8=(ImageView)getView().findViewById(R.id.pronoimage9);
        imageView9=(ImageView)getView().findViewById(R.id.pronoimage10);
        imageView10=(ImageView)getView().findViewById(R.id.pronoimage11);
        imgchoose1=(TextView)getView().findViewById(R.id.choose1);
        imgchoose2=(TextView)getView().findViewById(R.id.choose2);
        imgchoose3=(TextView)getView().findViewById(R.id.choose3);
        imgchoose4=(TextView)getView().findViewById(R.id.choose4);
        imgchoose5=(TextView)getView().findViewById(R.id.choose5);
        imgchoose6=(TextView)getView().findViewById(R.id.choose6);
        imgchoose7=(TextView)getView().findViewById(R.id.choose7);
        imgchoose8=(TextView)getView().findViewById(R.id.choose8);
        imgchoose9=(TextView)getView().findViewById(R.id.choose9);
        imgchoose10=(TextView)getView().findViewById(R.id.choose10);
        imgchoose11=(TextView)getView().findViewById(R.id.choose11);

        editText1=(EditText)getView().findViewById(R.id.sortname1);
        editText2=(EditText)getView().findViewById(R.id.sortname2);
        editText3=(EditText)getView().findViewById(R.id.sortname3);
        editText4=(EditText)getView().findViewById(R.id.sortname4);
        editText5=(EditText)getView().findViewById(R.id.sortname5);
        editText6=(EditText)getView().findViewById(R.id.sortname6);
        editText7=(EditText)getView().findViewById(R.id.sortname7);
        editText8=(EditText)getView().findViewById(R.id.sortname8);
        editText9=(EditText)getView().findViewById(R.id.sortname9);
        editText10=(EditText)getView().findViewById(R.id.sortname10);



        btnadd1=(ImageView)getView().findViewById(R.id.vis1);
        btnadd2=(ImageView)getView().findViewById(R.id.vis2);
        btnadd3=(ImageView)getView().findViewById(R.id.vis3);
        btnadd4=(ImageView)getView().findViewById(R.id.vis4);
        btnadd5=(ImageView)getView().findViewById(R.id.vis5);
        btnadd6=(ImageView)getView().findViewById(R.id.vis6);
        btnadd7=(ImageView)getView().findViewById(R.id.vis7);
        btnadd8=(ImageView)getView().findViewById(R.id.vis8);
        btnadd9=(ImageView)getView().findViewById(R.id.vis9);

        delbtn1=(ImageView)getView().findViewById(R.id.del1);
        delbtn2=(ImageView)getView().findViewById(R.id.del2);
        delbtn3=(ImageView)getView().findViewById(R.id.del3);
        delbtn4=(ImageView)getView().findViewById(R.id.del4);
        delbtn5=(ImageView)getView().findViewById(R.id.del5);
        delbtn6=(ImageView)getView().findViewById(R.id.del6);
        delbtn7=(ImageView)getView().findViewById(R.id.del7);
        delbtn8=(ImageView)getView().findViewById(R.id.del8);
        delbtn9=(ImageView)getView().findViewById(R.id.del9);
        delbtn10=(ImageView)getView().findViewById(R.id.del10);

        cm=(ImageView)getView().findViewById(R.id.cam1);
        cm2=(ImageView)getView().findViewById(R.id.cam2);
        cm3=(ImageView)getView().findViewById(R.id.cam3);
        cm4=(ImageView)getView().findViewById(R.id.cam4);
        cm5=(ImageView)getView().findViewById(R.id.cam5);
        cm6=(ImageView)getView().findViewById(R.id.cam6);
        cm7=(ImageView)getView().findViewById(R.id.cam7);
        cm8=(ImageView)getView().findViewById(R.id.cam8);
        cm9=(ImageView)getView().findViewById(R.id.cam9);
        cm10=(ImageView)getView().findViewById(R.id.cam10);
        cm11=(ImageView)getView().findViewById(R.id.cam11);

        linearLayout1=(LinearLayout)getView().findViewById(R.id.lay1);
        linearLayout2=(LinearLayout)getView().findViewById(R.id.lay2);
        linearLayout3=(LinearLayout)getView().findViewById(R.id.lay3);
        linearLayout4=(LinearLayout)getView().findViewById(R.id.lay4);
        linearLayout5=(LinearLayout)getView().findViewById(R.id.lay5);
        linearLayout6=(LinearLayout)getView().findViewById(R.id.lay6);
        linearLayout7=(LinearLayout)getView().findViewById(R.id.lay7);
        linearLayout8=(LinearLayout)getView().findViewById(R.id.lay8);
        linearLayout9=(LinearLayout)getView().findViewById(R.id.lay9);
        linearLayout10=(LinearLayout)getView().findViewById(R.id.lay10);

        typeface = Typeface.createFromAsset(getActivity().getAssets(),"robotoRegular.ttf");
        typeface1 = Typeface.createFromAsset(getActivity().getAssets(),"robotoBold.ttf");

        textView.setTypeface(typeface);

        textView2.setTypeface(typeface);
        imgchoose1.setTypeface(typeface);
        imgchoose2.setTypeface(typeface);
        imgchoose3.setTypeface(typeface);
        imgchoose4.setTypeface(typeface);
        imgchoose5.setTypeface(typeface);
        imgchoose6.setTypeface(typeface);
        imgchoose7.setTypeface(typeface);
        imgchoose8.setTypeface(typeface);
        imgchoose9.setTypeface(typeface);
        imgchoose10.setTypeface(typeface);
        imgchoose11.setTypeface(typeface);
        editText1.setTypeface(typeface);
        editText2.setTypeface(typeface);
        editText3.setTypeface(typeface);
        editText4.setTypeface(typeface);
        editText5.setTypeface(typeface);
        editText6.setTypeface(typeface);
        editText7.setTypeface(typeface);
        editText8.setTypeface(typeface);
        editText9.setTypeface(typeface);
        editText10.setTypeface(typeface);
//        selectedPath1=Config.IMAGEURL+EditProduct.s22;


        if(EditProduct.s22.isEmpty())
        {
            imageView.setImageBitmap(null);
            imageView.setImageResource(R.drawable.noimage);
            cm.setVisibility(View.VISIBLE);
        }
        else {
            UrlImageViewHelper.setUrlDrawable(imageView,  Config.IMAGEURL+EditProduct.s22);
            cm.setVisibility(View.GONE);
        }
        System.out.println("ferattt"+EditProduct.s22);

        if(EditProduct.s23.equals("0"))
        {
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.GONE);
            linearLayout3.setVisibility(View.GONE);
            linearLayout4.setVisibility(View.GONE);
            linearLayout5.setVisibility(View.GONE);
            linearLayout6.setVisibility(View.GONE);
            linearLayout7.setVisibility(View.GONE);
            linearLayout8.setVisibility(View.GONE);
            linearLayout9.setVisibility(View.GONE);
            linearLayout10.setVisibility(View.GONE);
        }
else if(EditProduct.s23.equals("1"))
{
    linearLayout1.setVisibility(View.VISIBLE);
    linearLayout2.setVisibility(View.GONE);
    linearLayout3.setVisibility(View.GONE);
    linearLayout4.setVisibility(View.GONE);
    linearLayout5.setVisibility(View.GONE);
    linearLayout6.setVisibility(View.GONE);
    linearLayout7.setVisibility(View.GONE);
    linearLayout8.setVisibility(View.GONE);
    linearLayout9.setVisibility(View.GONE);
    linearLayout10.setVisibility(View.GONE);
    cm2.setVisibility(View.GONE);
    imgchoose2.setVisibility(View.GONE);

    UrlImageViewHelper.setUrlDrawable(imageView1,  Config.IMAGEURL+EditProduct.s31);
    editText1.setText(EditProduct.s51);

//    selectedPath2=Config.IMAGEURL+EditProduct.s31;
    count=1;
}
       else if(EditProduct.s23.equals("2"))
        {
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            linearLayout3.setVisibility(View.GONE);
            linearLayout4.setVisibility(View.GONE);
            linearLayout5.setVisibility(View.GONE);
            linearLayout6.setVisibility(View.GONE);
            linearLayout7.setVisibility(View.GONE);
            linearLayout8.setVisibility(View.GONE);
            linearLayout9.setVisibility(View.GONE);
            linearLayout10.setVisibility(View.GONE);
            UrlImageViewHelper.setUrlDrawable(imageView1,  Config.IMAGEURL+EditProduct.s31);
            UrlImageViewHelper.setUrlDrawable(imageView2,  Config.IMAGEURL+EditProduct.s32);
            editText1.setText(EditProduct.s51);
            editText2.setText(EditProduct.s52);
            cm2.setVisibility(View.GONE);
            imgchoose2.setVisibility(View.GONE);
            cm3.setVisibility(View.GONE);
            imgchoose3.setVisibility(View.GONE);
//            selectedPath2=Config.IMAGEURL+EditProduct.s31;
//            selectedPath3=Config.IMAGEURL+EditProduct.s32;
            count=2;
        }
      else  if(EditProduct.s23.equals("3"))
        {
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            linearLayout3.setVisibility(View.VISIBLE);
            linearLayout4.setVisibility(View.GONE);
            linearLayout5.setVisibility(View.GONE);
            linearLayout6.setVisibility(View.GONE);
            linearLayout7.setVisibility(View.GONE);
            linearLayout8.setVisibility(View.GONE);
            linearLayout9.setVisibility(View.GONE);
            linearLayout10.setVisibility(View.GONE);
            UrlImageViewHelper.setUrlDrawable(imageView1,  Config.IMAGEURL+EditProduct.s31);
            UrlImageViewHelper.setUrlDrawable(imageView2,  Config.IMAGEURL+EditProduct.s32);
            UrlImageViewHelper.setUrlDrawable(imageView3,  Config.IMAGEURL+EditProduct.s33);
            editText1.setText(EditProduct.s51);
            editText2.setText(EditProduct.s52);
            editText3.setText(EditProduct.s53);
            cm2.setVisibility(View.GONE);
            imgchoose2.setVisibility(View.GONE);
            cm3.setVisibility(View.GONE);
            imgchoose3.setVisibility(View.GONE);
            cm4.setVisibility(View.GONE);
            imgchoose4.setVisibility(View.GONE);

//            selectedPath2=Config.IMAGEURL+EditProduct.s31;
//            selectedPath3=Config.IMAGEURL+EditProduct.s32;
//            selectedPath4=Config.IMAGEURL+EditProduct.s33;
            count=3;
        }
      else  if(EditProduct.s23.equals("4"))
        {
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            linearLayout3.setVisibility(View.VISIBLE);
            linearLayout4.setVisibility(View.VISIBLE);
            linearLayout5.setVisibility(View.GONE);
            linearLayout6.setVisibility(View.GONE);
            linearLayout7.setVisibility(View.GONE);
            linearLayout8.setVisibility(View.GONE);
            linearLayout9.setVisibility(View.GONE);
            linearLayout10.setVisibility(View.GONE);
            UrlImageViewHelper.setUrlDrawable(imageView1,  Config.IMAGEURL+EditProduct.s31);
            UrlImageViewHelper.setUrlDrawable(imageView2,  Config.IMAGEURL+EditProduct.s32);
            UrlImageViewHelper.setUrlDrawable(imageView3,  Config.IMAGEURL+EditProduct.s33);
            UrlImageViewHelper.setUrlDrawable(imageView4,  Config.IMAGEURL+EditProduct.s34);
            editText1.setText(EditProduct.s51);
            editText2.setText(EditProduct.s52);
            editText3.setText(EditProduct.s53);
            editText4.setText(EditProduct.s54);
            cm2.setVisibility(View.GONE);
            imgchoose2.setVisibility(View.GONE);
            cm3.setVisibility(View.GONE);
            imgchoose3.setVisibility(View.GONE);
            cm4.setVisibility(View.GONE);
            imgchoose4.setVisibility(View.GONE);
            cm5.setVisibility(View.GONE);
            imgchoose5.setVisibility(View.GONE);
//            selectedPath2=Config.IMAGEURL+EditProduct.s31;
//            selectedPath3=Config.IMAGEURL+EditProduct.s32;
//            selectedPath4=Config.IMAGEURL+EditProduct.s33;
//            selectedPath5=Config.IMAGEURL+EditProduct.s34;
            count=4;

        }
       else if(EditProduct.s23.equals("5"))
        {
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            linearLayout3.setVisibility(View.VISIBLE);
            linearLayout4.setVisibility(View.VISIBLE);
            linearLayout5.setVisibility(View.VISIBLE);
            linearLayout6.setVisibility(View.GONE);
            linearLayout7.setVisibility(View.GONE);
            linearLayout8.setVisibility(View.GONE);
            linearLayout9.setVisibility(View.GONE);
            linearLayout10.setVisibility(View.GONE);
            UrlImageViewHelper.setUrlDrawable(imageView1,  Config.IMAGEURL+EditProduct.s31);
            UrlImageViewHelper.setUrlDrawable(imageView2,  Config.IMAGEURL+EditProduct.s32);
            UrlImageViewHelper.setUrlDrawable(imageView3,  Config.IMAGEURL+EditProduct.s33);
            UrlImageViewHelper.setUrlDrawable(imageView4,  Config.IMAGEURL+EditProduct.s34);
            UrlImageViewHelper.setUrlDrawable(imageView5,  Config.IMAGEURL+EditProduct.s35);
            editText1.setText(EditProduct.s51);
            editText2.setText(EditProduct.s52);
            editText3.setText(EditProduct.s53);
            editText4.setText(EditProduct.s54);
            editText5.setText(EditProduct.s55);
            cm2.setVisibility(View.GONE);
            imgchoose2.setVisibility(View.GONE);
            cm3.setVisibility(View.GONE);
            imgchoose3.setVisibility(View.GONE);
            cm4.setVisibility(View.GONE);
            imgchoose4.setVisibility(View.GONE);
            cm5.setVisibility(View.GONE);
            imgchoose5.setVisibility(View.GONE);
            cm6.setVisibility(View.GONE);
            imgchoose6.setVisibility(View.GONE);
//            selectedPath2=Config.IMAGEURL+EditProduct.s31;
//            selectedPath3=Config.IMAGEURL+EditProduct.s32;
//            selectedPath4=Config.IMAGEURL+EditProduct.s33;
//            selectedPath5=Config.IMAGEURL+EditProduct.s34;
//            selectedPath6=Config.IMAGEURL+EditProduct.s35;
            count=5;

        }
      else  if(EditProduct.s23.equals("6"))
        {
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            linearLayout3.setVisibility(View.VISIBLE);
            linearLayout4.setVisibility(View.VISIBLE);
            linearLayout5.setVisibility(View.VISIBLE);
            linearLayout6.setVisibility(View.VISIBLE);
            linearLayout7.setVisibility(View.GONE);
            linearLayout8.setVisibility(View.GONE);
            linearLayout9.setVisibility(View.GONE);
            linearLayout10.setVisibility(View.GONE);
            UrlImageViewHelper.setUrlDrawable(imageView1,  Config.IMAGEURL+EditProduct.s31);
            UrlImageViewHelper.setUrlDrawable(imageView2,  Config.IMAGEURL+EditProduct.s32);
            UrlImageViewHelper.setUrlDrawable(imageView3,  Config.IMAGEURL+EditProduct.s33);
            UrlImageViewHelper.setUrlDrawable(imageView4,  Config.IMAGEURL+EditProduct.s34);
            UrlImageViewHelper.setUrlDrawable(imageView5,  Config.IMAGEURL+EditProduct.s35);
            UrlImageViewHelper.setUrlDrawable(imageView6,  Config.IMAGEURL+EditProduct.s36);
            editText1.setText(EditProduct.s51);
            editText2.setText(EditProduct.s52);
            editText3.setText(EditProduct.s53);
            editText4.setText(EditProduct.s54);
            editText5.setText(EditProduct.s55);
            editText6.setText(EditProduct.s56);
            cm2.setVisibility(View.GONE);
            imgchoose2.setVisibility(View.GONE);
            cm3.setVisibility(View.GONE);
            imgchoose3.setVisibility(View.GONE);
            cm4.setVisibility(View.GONE);
            imgchoose4.setVisibility(View.GONE);
            cm5.setVisibility(View.GONE);
            imgchoose5.setVisibility(View.GONE);
            cm6.setVisibility(View.GONE);
            imgchoose6.setVisibility(View.GONE);
            cm7.setVisibility(View.GONE);
            imgchoose7.setVisibility(View.GONE);
//            selectedPath2=Config.IMAGEURL+EditProduct.s31;
//            selectedPath3=Config.IMAGEURL+EditProduct.s32;
//            selectedPath4=Config.IMAGEURL+EditProduct.s33;
//            selectedPath5=Config.IMAGEURL+EditProduct.s34;
//            selectedPath6=Config.IMAGEURL+EditProduct.s35;
//            selectedPath7=Config.IMAGEURL+EditProduct.s36;
            count=6;
        }
       else if(EditProduct.s23.equals("7"))
        {
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            linearLayout3.setVisibility(View.VISIBLE);
            linearLayout4.setVisibility(View.VISIBLE);
            linearLayout5.setVisibility(View.VISIBLE);
            linearLayout6.setVisibility(View.VISIBLE);
            linearLayout7.setVisibility(View.VISIBLE);
            linearLayout8.setVisibility(View.GONE);
            linearLayout9.setVisibility(View.GONE);
            linearLayout10.setVisibility(View.GONE);
            UrlImageViewHelper.setUrlDrawable(imageView1,  Config.IMAGEURL+EditProduct.s31);
            UrlImageViewHelper.setUrlDrawable(imageView2,  Config.IMAGEURL+EditProduct.s32);
            UrlImageViewHelper.setUrlDrawable(imageView3,  Config.IMAGEURL+EditProduct.s33);
            UrlImageViewHelper.setUrlDrawable(imageView4,  Config.IMAGEURL+EditProduct.s34);
            UrlImageViewHelper.setUrlDrawable(imageView5,  Config.IMAGEURL+EditProduct.s35);
            UrlImageViewHelper.setUrlDrawable(imageView6,  Config.IMAGEURL+EditProduct.s36);
            UrlImageViewHelper.setUrlDrawable(imageView7,  Config.IMAGEURL+EditProduct.s37);
            editText1.setText(EditProduct.s51);
            editText2.setText(EditProduct.s52);
            editText3.setText(EditProduct.s53);
            editText4.setText(EditProduct.s54);
            editText5.setText(EditProduct.s55);
            editText6.setText(EditProduct.s56);
            editText7.setText(EditProduct.s57);
            cm2.setVisibility(View.GONE);
            imgchoose2.setVisibility(View.GONE);
            cm3.setVisibility(View.GONE);
            imgchoose3.setVisibility(View.GONE);
            cm4.setVisibility(View.GONE);
            imgchoose4.setVisibility(View.GONE);
            cm5.setVisibility(View.GONE);
            imgchoose5.setVisibility(View.GONE);
            cm6.setVisibility(View.GONE);
            imgchoose6.setVisibility(View.GONE);
            cm7.setVisibility(View.GONE);
            imgchoose7.setVisibility(View.GONE);
            cm8.setVisibility(View.GONE);
            imgchoose8.setVisibility(View.GONE);
//            selectedPath2=Config.IMAGEURL+EditProduct.s31;
//            selectedPath3=Config.IMAGEURL+EditProduct.s32;
//            selectedPath4=Config.IMAGEURL+EditProduct.s33;
//            selectedPath5=Config.IMAGEURL+EditProduct.s34;
//            selectedPath6=Config.IMAGEURL+EditProduct.s35;
//            selectedPath7=Config.IMAGEURL+EditProduct.s36;
//            selectedPath8=Config.IMAGEURL+EditProduct.s37;
            count=7;
        }
       else if(EditProduct.s23.equals("8"))
        {
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            linearLayout3.setVisibility(View.VISIBLE);
            linearLayout4.setVisibility(View.VISIBLE);
            linearLayout5.setVisibility(View.VISIBLE);
            linearLayout6.setVisibility(View.VISIBLE);
            linearLayout7.setVisibility(View.VISIBLE);
            linearLayout8.setVisibility(View.VISIBLE);
            linearLayout9.setVisibility(View.GONE);
            linearLayout10.setVisibility(View.GONE);
            UrlImageViewHelper.setUrlDrawable(imageView1,  Config.IMAGEURL+EditProduct.s31);
            UrlImageViewHelper.setUrlDrawable(imageView2,  Config.IMAGEURL+EditProduct.s32);
            UrlImageViewHelper.setUrlDrawable(imageView3,  Config.IMAGEURL+EditProduct.s33);
            UrlImageViewHelper.setUrlDrawable(imageView4,  Config.IMAGEURL+EditProduct.s34);
            UrlImageViewHelper.setUrlDrawable(imageView5,  Config.IMAGEURL+EditProduct.s35);
            UrlImageViewHelper.setUrlDrawable(imageView6,  Config.IMAGEURL+EditProduct.s36);
            UrlImageViewHelper.setUrlDrawable(imageView7,  Config.IMAGEURL+EditProduct.s37);
            UrlImageViewHelper.setUrlDrawable(imageView8,  Config.IMAGEURL+EditProduct.s38);
            editText1.setText(EditProduct.s51);
            editText2.setText(EditProduct.s52);
            editText3.setText(EditProduct.s53);
            editText4.setText(EditProduct.s54);
            editText5.setText(EditProduct.s55);
            editText6.setText(EditProduct.s56);
            editText7.setText(EditProduct.s57);
            editText8.setText(EditProduct.s58);
            cm2.setVisibility(View.GONE);
            imgchoose2.setVisibility(View.GONE);
            cm3.setVisibility(View.GONE);
            imgchoose3.setVisibility(View.GONE);
            cm4.setVisibility(View.GONE);
            imgchoose4.setVisibility(View.GONE);
            cm5.setVisibility(View.GONE);
            imgchoose5.setVisibility(View.GONE);
            cm6.setVisibility(View.GONE);
            imgchoose6.setVisibility(View.GONE);
            cm7.setVisibility(View.GONE);
            imgchoose7.setVisibility(View.GONE);
            cm9.setVisibility(View.GONE);
            imgchoose9.setVisibility(View.GONE);
//            selectedPath2=Config.IMAGEURL+EditProduct.s31;
//            selectedPath3=Config.IMAGEURL+EditProduct.s32;
//            selectedPath4=Config.IMAGEURL+EditProduct.s33;
//            selectedPath5=Config.IMAGEURL+EditProduct.s34;
//            selectedPath6=Config.IMAGEURL+EditProduct.s35;
//            selectedPath7=Config.IMAGEURL+EditProduct.s36;
//            selectedPath8=Config.IMAGEURL+EditProduct.s37;
//            selectedPath9=Config.IMAGEURL+EditProduct.s38;
            count=8;

        }
       else if(EditProduct.s23.equals("9"))
        {
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            linearLayout3.setVisibility(View.VISIBLE);
            linearLayout4.setVisibility(View.VISIBLE);
            linearLayout5.setVisibility(View.VISIBLE);
            linearLayout6.setVisibility(View.VISIBLE);
            linearLayout7.setVisibility(View.VISIBLE);
            linearLayout8.setVisibility(View.VISIBLE);
            linearLayout9.setVisibility(View.VISIBLE);
            linearLayout10.setVisibility(View.GONE);
            UrlImageViewHelper.setUrlDrawable(imageView1,  Config.IMAGEURL+EditProduct.s31);
            UrlImageViewHelper.setUrlDrawable(imageView2,  Config.IMAGEURL+EditProduct.s32);
            UrlImageViewHelper.setUrlDrawable(imageView3,  Config.IMAGEURL+EditProduct.s33);
            UrlImageViewHelper.setUrlDrawable(imageView4,  Config.IMAGEURL+EditProduct.s34);
            UrlImageViewHelper.setUrlDrawable(imageView5,  Config.IMAGEURL+EditProduct.s35);
            UrlImageViewHelper.setUrlDrawable(imageView6,  Config.IMAGEURL+EditProduct.s36);
            UrlImageViewHelper.setUrlDrawable(imageView7,  Config.IMAGEURL+EditProduct.s37);
            UrlImageViewHelper.setUrlDrawable(imageView8,  Config.IMAGEURL+EditProduct.s38);
            UrlImageViewHelper.setUrlDrawable(imageView9,  Config.IMAGEURL+EditProduct.s39);
            editText1.setText(EditProduct.s51);
            editText2.setText(EditProduct.s52);
            editText3.setText(EditProduct.s53);
            editText4.setText(EditProduct.s54);
            editText5.setText(EditProduct.s55);
            editText6.setText(EditProduct.s56);
            editText7.setText(EditProduct.s57);
            editText8.setText(EditProduct.s58);
            editText9.setText(EditProduct.s59);
            cm2.setVisibility(View.GONE);
            imgchoose2.setVisibility(View.GONE);
            cm3.setVisibility(View.GONE);
            imgchoose3.setVisibility(View.GONE);
            cm4.setVisibility(View.GONE);
            imgchoose4.setVisibility(View.GONE);
            cm5.setVisibility(View.GONE);
            imgchoose5.setVisibility(View.GONE);
            cm6.setVisibility(View.GONE);
            imgchoose6.setVisibility(View.GONE);
            cm7.setVisibility(View.GONE);
            imgchoose7.setVisibility(View.GONE);
            cm9.setVisibility(View.GONE);
            imgchoose9.setVisibility(View.GONE);
            cm10.setVisibility(View.GONE);
            imgchoose10.setVisibility(View.GONE);
//            selectedPath2=Config.IMAGEURL+EditProduct.s31;
//            selectedPath3=Config.IMAGEURL+EditProduct.s32;
//            selectedPath4=Config.IMAGEURL+EditProduct.s33;
//            selectedPath5=Config.IMAGEURL+EditProduct.s34;
//            selectedPath6=Config.IMAGEURL+EditProduct.s35;
//            selectedPath7=Config.IMAGEURL+EditProduct.s36;
//            selectedPath8=Config.IMAGEURL+EditProduct.s37;
//            selectedPath9=Config.IMAGEURL+EditProduct.s38;
//            selectedPath10=Config.IMAGEURL+EditProduct.s39;
            count=9;

        }
       else if(EditProduct.s23.equals("10"))
        {
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            linearLayout3.setVisibility(View.VISIBLE);
            linearLayout4.setVisibility(View.VISIBLE);
            linearLayout5.setVisibility(View.VISIBLE);
            linearLayout6.setVisibility(View.VISIBLE);
            linearLayout7.setVisibility(View.VISIBLE);
            linearLayout8.setVisibility(View.VISIBLE);
            linearLayout9.setVisibility(View.VISIBLE);
            linearLayout10.setVisibility(View.VISIBLE);
            UrlImageViewHelper.setUrlDrawable(imageView1,  Config.IMAGEURL+EditProduct.s31);
            UrlImageViewHelper.setUrlDrawable(imageView2,  Config.IMAGEURL+EditProduct.s32);
            UrlImageViewHelper.setUrlDrawable(imageView3,  Config.IMAGEURL+EditProduct.s33);
            UrlImageViewHelper.setUrlDrawable(imageView4,  Config.IMAGEURL+EditProduct.s34);
            UrlImageViewHelper.setUrlDrawable(imageView5,  Config.IMAGEURL+EditProduct.s35);
            UrlImageViewHelper.setUrlDrawable(imageView6,  Config.IMAGEURL+EditProduct.s36);
            UrlImageViewHelper.setUrlDrawable(imageView7,  Config.IMAGEURL+EditProduct.s37);
            UrlImageViewHelper.setUrlDrawable(imageView8,  Config.IMAGEURL+EditProduct.s38);
            UrlImageViewHelper.setUrlDrawable(imageView9,  Config.IMAGEURL+EditProduct.s39);
            UrlImageViewHelper.setUrlDrawable(imageView10,  Config.IMAGEURL+EditProduct.s40);
            editText1.setText(EditProduct.s51);
            editText2.setText(EditProduct.s52);
            editText3.setText(EditProduct.s53);
            editText4.setText(EditProduct.s54);
            editText5.setText(EditProduct.s55);
            editText6.setText(EditProduct.s56);
            editText7.setText(EditProduct.s57);
            editText8.setText(EditProduct.s58);
            editText9.setText(EditProduct.s59);
            editText10.setText(EditProduct.s60);
            cm2.setVisibility(View.GONE);
            imgchoose2.setVisibility(View.GONE);
            cm3.setVisibility(View.GONE);
            imgchoose3.setVisibility(View.GONE);
            cm4.setVisibility(View.GONE);
            imgchoose4.setVisibility(View.GONE);
            cm5.setVisibility(View.GONE);
            imgchoose5.setVisibility(View.GONE);
            cm6.setVisibility(View.GONE);
            imgchoose6.setVisibility(View.GONE);
            cm7.setVisibility(View.GONE);
            imgchoose7.setVisibility(View.GONE);
            cm9.setVisibility(View.GONE);
            imgchoose9.setVisibility(View.GONE);
            cm10.setVisibility(View.GONE);
            imgchoose10.setVisibility(View.GONE);
            cm11.setVisibility(View.GONE);
            imgchoose11.setVisibility(View.GONE);
//            selectedPath2=Config.IMAGEURL+EditProduct.s31;
//            selectedPath3=Config.IMAGEURL+EditProduct.s32;
//            selectedPath4=Config.IMAGEURL+EditProduct.s33;
//            selectedPath5=Config.IMAGEURL+EditProduct.s34;
//            selectedPath6=Config.IMAGEURL+EditProduct.s35;
//            selectedPath7=Config.IMAGEURL+EditProduct.s36;
//            selectedPath8=Config.IMAGEURL+EditProduct.s37;
//            selectedPath9=Config.IMAGEURL+EditProduct.s38;
//            selectedPath10=Config.IMAGEURL+EditProduct.s39;
//            selectedPath11=Config.IMAGEURL+EditProduct.s40;
            count=10;

        }

        btnadd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout2.setVisibility(View.VISIBLE);
                btnadd1.setVisibility(View.GONE);
                btnadd2.setVisibility(View.VISIBLE);
                bitmap3=null;
                if(bitmap3==null)
                {
                    cm3.setVisibility(View.VISIBLE);
                }




            }
        });
        btnadd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout3.setVisibility(View.VISIBLE);
                btnadd2.setVisibility(View.GONE);
                btnadd3.setVisibility(View.VISIBLE);
                imageView3.setImageBitmap(null);
                imageView3.setImageResource(R.drawable.proimage);


                btnadd1.setVisibility(View.GONE);


                if((linearLayout2.getVisibility()==View.GONE)&&(linearLayout3.getVisibility()==View.GONE)&&(linearLayout4.getVisibility()==View.GONE)&&(linearLayout5.getVisibility()==View.GONE)&&(linearLayout6.getVisibility()==View.GONE)&&(linearLayout7.getVisibility()==View.GONE)&&(linearLayout8.getVisibility()==View.GONE)&&(linearLayout9.getVisibility()==View.GONE)&&(linearLayout10.getVisibility()==View.GONE))
                {
                    btnadd1.setVisibility(View.VISIBLE);
                }
                bitmap4=null;
                if(bitmap4==null)
                {
                    cm4.setVisibility(View.VISIBLE);
                }

            }
        });
        btnadd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout4.setVisibility(View.VISIBLE);
                btnadd3.setVisibility(View.GONE);
                btnadd4.setVisibility(View.VISIBLE);
                imageView4.setImageBitmap(null);
                imageView4.setImageResource(R.drawable.proimage);
                bitmap5=null;
                if(bitmap5==null)
                {
                    cm5.setVisibility(View.VISIBLE);
                }



            }
        });
        btnadd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout5.setVisibility(View.VISIBLE);
                btnadd4.setVisibility(View.GONE);
                btnadd5.setVisibility(View.VISIBLE);
                imageView5.setImageBitmap(null);
                imageView5.setImageResource(R.drawable.proimage);
                bitmap6=null;
                if(bitmap6==null)
                {
                    cm6.setVisibility(View.VISIBLE);
                }


            }
        });
        btnadd5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout6.setVisibility(View.VISIBLE);
                btnadd5.setVisibility(View.GONE);
                btnadd6.setVisibility(View.VISIBLE);
                imageView6.setImageBitmap(null);
                imageView6.setImageResource(R.drawable.proimage);
                bitmap7=null;
                if(bitmap7==null)
                {
                    cm7.setVisibility(View.VISIBLE);
                }


            }
        });
        btnadd6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout7.setVisibility(View.VISIBLE);
                btnadd6.setVisibility(View.GONE);
                btnadd7.setVisibility(View.VISIBLE);
                imageView7.setImageBitmap(null);
                imageView7.setImageResource(R.drawable.proimage);
                bitmap8=null;
                if(bitmap8==null)
                {
                    cm8.setVisibility(View.VISIBLE);
                }


            }
        });
        btnadd7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout8.setVisibility(View.VISIBLE);
                btnadd7.setVisibility(View.GONE);
                btnadd8.setVisibility(View.VISIBLE);
                imageView8.setImageBitmap(null);
                imageView8.setImageResource(R.drawable.proimage);
                bitmap9=null;
                if(bitmap9==null)
                {
                    cm9.setVisibility(View.VISIBLE);
                }


            }
        });
        btnadd8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout9.setVisibility(View.VISIBLE);
                btnadd8.setVisibility(View.GONE);
                btnadd9.setVisibility(View.VISIBLE);
                imageView9.setImageBitmap(null);
                imageView9.setImageResource(R.drawable.proimage);bitmap9=null;
                bitmap10=null;
                if(bitmap10==null)
                {
                    cm10.setVisibility(View.VISIBLE);
                }




            }
        });
        btnadd9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout10.setVisibility(View.VISIBLE);
                btnadd9.setVisibility(View.GONE);
                btnadd10.setVisibility(View.VISIBLE);
                imageView10.setImageBitmap(null);
                imageView10.setImageResource(R.drawable.proimage);
                bitmap11=null;
                if(bitmap11==null)
                {
                    cm11.setVisibility(View.VISIBLE);
                }


            }
        });
        delbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!(selectedPath2.equals("NONE"))) {
                    deleteImages(EditProduct.s71);
                    count--;
              //  }
                imageView1.setImageBitmap(null);
                imageView1.setImageResource(R.drawable.proimage);
cm2.setVisibility(View.VISIBLE);
                imgchoose2.setVisibility(View.VISIBLE);

                selectedPath2 = "NONE";
            }
        });

        delbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(!(selectedPath3.equals("NONE"))) {


                    deleteImages(EditProduct.s72);
                    count--;
                //}
                imageView2.setImageBitmap(null);
                imageView2.setImageResource(R.drawable.proimage);
                linearLayout2.setVisibility(View.GONE);
                if ((linearLayout2.getVisibility() == View.GONE) && (linearLayout3.getVisibility() == View.GONE) && (linearLayout4.getVisibility() == View.GONE) && (linearLayout5.getVisibility() == View.GONE) && (linearLayout6.getVisibility() == View.GONE) && (linearLayout7.getVisibility() == View.GONE) && (linearLayout8.getVisibility() == View.GONE) && (linearLayout9.getVisibility() == View.GONE) && (linearLayout10.getVisibility() == View.GONE)) {
                    btnadd1.setVisibility(View.VISIBLE);
                }
                cm3.setVisibility(View.VISIBLE);
                imgchoose3.setVisibility(View.VISIBLE);


                selectedPath3 = "NONE";

            }
        });
        delbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
////                if (!(selectedPath4.equals("NONE")))
//                {

                deleteImages(EditProduct.s73);
                    count--;
           // }
                imageView3.setImageBitmap(null);
                imageView3.setImageResource(R.drawable.proimage);
                linearLayout3.setVisibility(View.GONE);
                btnadd2.setVisibility(View.VISIBLE);
                if ((linearLayout2.getVisibility() == View.GONE) && (linearLayout3.getVisibility() == View.GONE) && (linearLayout4.getVisibility() == View.GONE) && (linearLayout5.getVisibility() == View.GONE) && (linearLayout6.getVisibility() == View.GONE) && (linearLayout7.getVisibility() == View.GONE) && (linearLayout8.getVisibility() == View.GONE) && (linearLayout9.getVisibility() == View.GONE) && (linearLayout10.getVisibility() == View.GONE)) {
                    btnadd1.setVisibility(View.VISIBLE);
                }
                cm4.setVisibility(View.VISIBLE);
                imgchoose4.setVisibility(View.VISIBLE);


                selectedPath4 = "NONE";
            }
        });
        delbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!(selectedPath5.equals("NONE"))) {

                    deleteImages(EditProduct.s74);
                    count--;
               // }
                imageView4.setImageBitmap(null);
                imageView4.setImageResource(R.drawable.proimage);
                linearLayout4.setVisibility(View.GONE);
                btnadd3.setVisibility(View.VISIBLE);

                if ((linearLayout2.getVisibility() == View.GONE) && (linearLayout3.getVisibility() == View.GONE) && (linearLayout4.getVisibility() == View.GONE) && (linearLayout5.getVisibility() == View.GONE) && (linearLayout6.getVisibility() == View.GONE) && (linearLayout7.getVisibility() == View.GONE) && (linearLayout8.getVisibility() == View.GONE) && (linearLayout9.getVisibility() == View.GONE) && (linearLayout10.getVisibility() == View.GONE)) {
                    btnadd1.setVisibility(View.VISIBLE);
                }
                cm5.setVisibility(View.VISIBLE);
                imgchoose5.setVisibility(View.VISIBLE);


                selectedPath5 = "NONE";
            }
        });
        delbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!(selectedPath6.equals("NONE"))) {

                    deleteImages(EditProduct.s75);
                    count--;
              //  }
                imageView5.setImageBitmap(null);
                imageView5.setImageResource(R.drawable.proimage);
                linearLayout5.setVisibility(View.GONE);
                btnadd4.setVisibility(View.VISIBLE);
                if((linearLayout2.getVisibility()==View.GONE)&&(linearLayout3.getVisibility()==View.GONE)&&(linearLayout4.getVisibility()==View.GONE)&&(linearLayout5.getVisibility()==View.GONE)&&(linearLayout6.getVisibility()==View.GONE)&&(linearLayout7.getVisibility()==View.GONE)&&(linearLayout8.getVisibility()==View.GONE)&&(linearLayout9.getVisibility()==View.GONE)&&(linearLayout10.getVisibility()==View.GONE))
                {
                    btnadd1.setVisibility(View.VISIBLE);
                }
                cm6.setVisibility(View.VISIBLE);
                imgchoose6.setVisibility(View.VISIBLE);

                selectedPath6="NONE";

            }
        });
        delbtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!(selectedPath7.equals("NONE"))) {

                    deleteImages(EditProduct.s76);
                    count--;
               // }
                imageView6.setImageBitmap(null);
                imageView6.setImageResource(R.drawable.proimage);
                linearLayout6.setVisibility(View.GONE);
                btnadd5.setVisibility(View.VISIBLE);

                if((linearLayout2.getVisibility()==View.GONE)&&(linearLayout3.getVisibility()==View.GONE)&&(linearLayout4.getVisibility()==View.GONE)&&(linearLayout5.getVisibility()==View.GONE)&&(linearLayout6.getVisibility()==View.GONE)&&(linearLayout7.getVisibility()==View.GONE)&&(linearLayout8.getVisibility()==View.GONE)&&(linearLayout9.getVisibility()==View.GONE)&&(linearLayout10.getVisibility()==View.GONE))
                {
                    btnadd1.setVisibility(View.VISIBLE);
                }
                cm7.setVisibility(View.VISIBLE);
                imgchoose7.setVisibility(View.VISIBLE);

                selectedPath7="NONE";

            }
        });
        delbtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!(selectedPath8.equals("NONE"))) {

                    deleteImages(EditProduct.s77);
                    count--;
               // }
                imageView7.setImageBitmap(null);
                imageView7.setImageResource(R.drawable.proimage);
                linearLayout7.setVisibility(View.GONE);
                btnadd6.setVisibility(View.VISIBLE);

                if((linearLayout2.getVisibility()==View.GONE)&&(linearLayout3.getVisibility()==View.GONE)&&(linearLayout4.getVisibility()==View.GONE)&&(linearLayout5.getVisibility()==View.GONE)&&(linearLayout6.getVisibility()==View.GONE)&&(linearLayout7.getVisibility()==View.GONE)&&(linearLayout8.getVisibility()==View.GONE)&&(linearLayout9.getVisibility()==View.GONE)&&(linearLayout10.getVisibility()==View.GONE))
                {
                    btnadd1.setVisibility(View.VISIBLE);
                }
                cm8.setVisibility(View.VISIBLE);
                imgchoose8.setVisibility(View.VISIBLE);

                selectedPath8="NONE";

            }
        });
        delbtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!(selectedPath9.equals("NONE"))) {

                    deleteImages(EditProduct.s78);
                    count--;
               // }
                imageView8.setImageBitmap(null);
                imageView8.setImageResource(R.drawable.proimage);
                linearLayout8.setVisibility(View.GONE);
                btnadd7.setVisibility(View.VISIBLE);

                if((linearLayout2.getVisibility()==View.GONE)&&(linearLayout3.getVisibility()==View.GONE)&&(linearLayout4.getVisibility()==View.GONE)&&(linearLayout5.getVisibility()==View.GONE)&&(linearLayout6.getVisibility()==View.GONE)&&(linearLayout7.getVisibility()==View.GONE)&&(linearLayout8.getVisibility()==View.GONE)&&(linearLayout9.getVisibility()==View.GONE)&&(linearLayout10.getVisibility()==View.GONE))
                {
                    btnadd1.setVisibility(View.VISIBLE);
                }
                cm9.setVisibility(View.VISIBLE);
                imgchoose9.setVisibility(View.VISIBLE);

                selectedPath9="NONE";

            }
        });
        delbtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!(selectedPath10.equals("NONE"))) {

                    deleteImages(EditProduct.s79);
                    count--;
             //   }
                imageView9.setImageBitmap(null);
                imageView9.setImageResource(R.drawable.proimage);
                linearLayout9.setVisibility(View.GONE);
                btnadd8.setVisibility(View.VISIBLE);

                if((linearLayout2.getVisibility()==View.GONE)&&(linearLayout3.getVisibility()==View.GONE)&&(linearLayout4.getVisibility()==View.GONE)&&(linearLayout5.getVisibility()==View.GONE)&&(linearLayout6.getVisibility()==View.GONE)&&(linearLayout7.getVisibility()==View.GONE)&&(linearLayout8.getVisibility()==View.GONE)&&(linearLayout9.getVisibility()==View.GONE)&&(linearLayout10.getVisibility()==View.GONE))
                {
                    btnadd1.setVisibility(View.VISIBLE);
                }
                cm10.setVisibility(View.VISIBLE);
                imgchoose10.setVisibility(View.VISIBLE);

                selectedPath10="NONE";

            }
        });
        delbtn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!(selectedPath11.equals("NONE"))) {

                    deleteImages(EditProduct.s80);
                    count--;
               // }
                imageView10.setImageBitmap(null);
                imageView10.setImageResource(R.drawable.proimage);
                linearLayout10.setVisibility(View.GONE);
                btnadd9.setVisibility(View.VISIBLE);

                if((linearLayout2.getVisibility()==View.GONE)&&(linearLayout3.getVisibility()==View.GONE)&&(linearLayout4.getVisibility()==View.GONE)&&(linearLayout5.getVisibility()==View.GONE)&&(linearLayout6.getVisibility()==View.GONE)&&(linearLayout7.getVisibility()==View.GONE)&&(linearLayout8.getVisibility()==View.GONE)&&(linearLayout9.getVisibility()==View.GONE)&&(linearLayout10.getVisibility()==View.GONE))
                {
                    btnadd1.setVisibility(View.VISIBLE);

                }
                cm11.setVisibility(View.VISIBLE);
                imgchoose11.setVisibility(View.VISIBLE);

                selectedPath11="NONE";

            }
        });
        imgchoose1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                String[] mimetypes = {"image/jpeg","image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                startActivityForResult(intent, SELECT_FILE1);
            }
        });

        cm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                String[] mimetypes = {"image/jpeg","image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                startActivityForResult(intent, SELECT_FILE1);
            }
        });

        imgchoose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                String[] mimetypes = {"image/jpeg","image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                startActivityForResult(intent, SELECT_FILE2);
            }
        });
        cm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                String[] mimetypes = {"image/jpeg","image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                startActivityForResult(intent, SELECT_FILE2);
            }
        });
        imgchoose3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                String[] mimetypes = {"image/jpeg","image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                startActivityForResult(intent, SELECT_FILE3);
            }
        });
        cm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                String[] mimetypes = {"image/jpeg","image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                startActivityForResult(intent, SELECT_FILE3);
            }
        });

        imgchoose4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                String[] mimetypes = {"image/jpeg","image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                startActivityForResult(intent, SELECT_FILE4);
            }
        });
        cm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                String[] mimetypes = {"image/jpeg","image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                startActivityForResult(intent, SELECT_FILE4);
            }
        });
        imgchoose5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                String[] mimetypes = {"image/jpeg","image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                startActivityForResult(intent, SELECT_FILE5);
            }
        });
        cm5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                String[] mimetypes = {"image/jpeg","image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                startActivityForResult(intent, SELECT_FILE5);
            }
        });
        imgchoose6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                String[] mimetypes = {"image/jpeg","image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                startActivityForResult(intent, SELECT_FILE6);
            }
        });
        cm6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                String[] mimetypes = {"image/jpeg","image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                startActivityForResult(intent, SELECT_FILE6);
            }
        });
        imgchoose7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                String[] mimetypes = {"image/jpeg","image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                startActivityForResult(intent, SELECT_FILE7);
            }
        });
        cm7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                String[] mimetypes = {"image/jpeg","image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                startActivityForResult(intent, SELECT_FILE7);
            }
        });
        imgchoose8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                String[] mimetypes = {"image/jpeg","image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                startActivityForResult(intent, SELECT_FILE8);
            }
        });
        cm8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                String[] mimetypes = {"image/jpeg","image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                startActivityForResult(intent, SELECT_FILE8);
            }
        });
        imgchoose9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                String[] mimetypes = {"image/jpeg","image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                startActivityForResult(intent, SELECT_FILE9);
            }
        });
        cm9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                String[] mimetypes = {"image/jpeg","image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                startActivityForResult(intent, SELECT_FILE8);
            }
        });
        imgchoose10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                String[] mimetypes = {"image/jpeg","image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                startActivityForResult(intent, SELECT_FILE10);
            }
        });
        cm10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                String[] mimetypes = {"image/jpeg","image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                startActivityForResult(intent, SELECT_FILE10);
            }
        });
        imgchoose11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                String[] mimetypes = {"image/jpeg","image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                startActivityForResult(intent, SELECT_FILE11);
            }
        });
        cm11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                String[] mimetypes = {"image/jpeg","image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                startActivityForResult(intent, SELECT_FILE11);
            }
        });


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            if (requestCode == SELECT_FILE1)
            {
                selectedPath1 = FilePath.getPath(getActivity(), selectedImageUri);
                System.out.println("selectedPath1 : " + selectedPath1);
                try {
                    bitmap1 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                    imageView.setImageBitmap(bitmap1);
                    cm.setVisibility(View.GONE);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (requestCode == SELECT_FILE2)
            {
                selectedPath2 = FilePath.getPath(getActivity(), selectedImageUri);
                System.out.println("selectedPath2 : " + selectedPath2);
                try {
                    bitmap2 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                    imageView1.setImageBitmap(bitmap2);
                    count++;
                    cm2.setVisibility(View.GONE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (requestCode == SELECT_FILE3)
            {
                selectedPath3 = FilePath.getPath(getActivity(), selectedImageUri);
                System.out.println("selectedPath2 : " + selectedPath3);
                try {
                    bitmap3 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                    imageView2.setImageBitmap(bitmap3);

                    cm3.setVisibility(View.GONE);
                    count++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (requestCode == SELECT_FILE4)
            {
                selectedPath4 = FilePath.getPath(getActivity(), selectedImageUri);
                System.out.println("selectedPath2 : " + selectedPath4);
                try {
                    bitmap4 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                    imageView3.setImageBitmap(bitmap4);
                    count++;
                    cm4.setVisibility(View.GONE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (requestCode == SELECT_FILE5)
            {
                selectedPath5 = FilePath.getPath(getActivity(), selectedImageUri);
                System.out.println("selectedPath2 : " + selectedPath5);
                try {
                    bitmap5 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                    imageView4.setImageBitmap(bitmap5);
                    count++;
                    cm5.setVisibility(View.GONE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (requestCode == SELECT_FILE6)
            {
                selectedPath6 = FilePath.getPath(getActivity(), selectedImageUri);
                System.out.println("selectedPath2 : " + selectedPath6);
                try {
                    bitmap6 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                    imageView5.setImageBitmap(bitmap6);
                    count++;
                    cm6.setVisibility(View.GONE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (requestCode == SELECT_FILE7)
            {
                selectedPath7 = FilePath.getPath(getActivity(), selectedImageUri);
                System.out.println("selectedPath2 : " + selectedPath7);
                try {
                    bitmap7 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                    imageView6.setImageBitmap(bitmap7);
                    count++;
                    cm7.setVisibility(View.GONE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (requestCode == SELECT_FILE8)
            {
                selectedPath8 = FilePath.getPath(getActivity(), selectedImageUri);
                System.out.println("selectedPath2 : " + selectedPath8);
                try {
                    bitmap8 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                    imageView7.setImageBitmap(bitmap8);
                    count++;
                    cm8.setVisibility(View.GONE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (requestCode == SELECT_FILE9)
            {
                selectedPath9 = FilePath.getPath(getActivity(), selectedImageUri);
                System.out.println("selectedPath2 : " + selectedPath9);
                try {
                    bitmap9 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                    imageView8.setImageBitmap(bitmap9);
                    count++;
                    cm9.setVisibility(View.GONE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (requestCode == SELECT_FILE10)
            {
                selectedPath10 = FilePath.getPath(getActivity(), selectedImageUri);
                System.out.println("selectedPath2 : " + selectedPath10);
                try {
                    bitmap10 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                    imageView9.setImageBitmap(bitmap10);
                    count++;
                    cm10.setVisibility(View.GONE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (requestCode == SELECT_FILE11)
            {
                selectedPath11 = FilePath.getPath(getActivity(), selectedImageUri);
                System.out.println("selectedPath2 : " + selectedPath11);
                try {
                    bitmap11 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                    imageView10.setImageBitmap(bitmap11);
                    count++;
                    cm11.setVisibility(View.GONE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


//

    }
    public void deleteImages(String s)
    {
        ProductImageDeleteAPI productImageDeleteAPI=new ProductImageDeleteAPI(getActivity(),this);
        productImageDeleteAPI.processDelete(s);

    }


    @Override
    public void updateScreen(String data, String tag) {
        try {
            if (tag.compareTo(Config.TAG_DELETE_IMAGES_LIST) == 0) {
                Log.d("datahoome", data);
                JSONObject jsonObject=new JSONObject(data);
                if(jsonObject.getString("status").equals("removed"))
                {
                    Toast.makeText(getActivity(),"Image Deleted",Toast.LENGTH_SHORT).show();
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

