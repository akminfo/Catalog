package com.fingertip.catalog.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fingertip.catalog.R;
import com.fingertip.catalog.callback.FilePath;

import org.apache.http.entity.mime.content.FileBody;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by lenevo on 04-05-2018.
 */

public class ProFragment extends Fragment {
    TextView textView,textView1,textView2,textView3;
    Typeface typeface,typeface1,typeface2,typeface3;
    Button button;

    public  static  String selectedPath1;
    Bitmap thumbnail;
    public static FileBody fileBody;
    private int PICK_IMAGE_REQUEST1 = 2;
    private int PICK_IMAGE_REQUEST = 1;
    Dialog dialog1;
    ImageView imageView;
    CircleImageView imageView1;
    EditText editText;
    public ProFragment() {
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
        return inflater.inflate(R.layout.cusimage, container, false);

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);



    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView=(TextView)getView().findViewById(R.id.choose3);

 imageView=(ImageView)getView().findViewById(R.id.cuspic);
        imageView1=(CircleImageView)getView().findViewById(R.id.profilepic);
        typeface = Typeface.createFromAsset(getActivity().getAssets(),"robotoRegular.ttf");
        typeface1 = Typeface.createFromAsset(getActivity().getAssets(),"robotoBold.ttf");
        typeface2 = Typeface.createFromAsset(getActivity().getAssets(),"robotoMedium.ttf");
        typeface3 = Typeface.createFromAsset(getActivity().getAssets(),"robotoLight.ttf");

        textView.setTypeface(typeface);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1 = new Dialog(getActivity());

                dialog1.setContentView(R.layout.alert1_layout);
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //dialog1.setTitle("Register to Rate !");

//            dialog1.setTitle("Stock Details");
     final TextView texttitle = (TextView) dialog1.findViewById(R.id.title);
                final TextView texttitle1 = (TextView) dialog1.findViewById(R.id.t1);
                texttitle.setTypeface(typeface1);
                texttitle1.setTypeface(typeface3);
                final TextView cam = (TextView) dialog1.findViewById(R.id.take);
                final TextView terms = (TextView) dialog1.findViewById(R.id.term);
                cam.setTypeface(typeface);
                terms.setTypeface(typeface);
                final TextView gall = (TextView) dialog1.findViewById(R.id.gal);
                gall.setTypeface(typeface);

                cam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, PICK_IMAGE_REQUEST);
                        dialog1.dismiss();
                    }
                });
                gall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, PICK_IMAGE_REQUEST1);
                        dialog1.dismiss();
                    }
                });
                dialog1.show();

            }
        });




    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri selectedImageUri = data.getData();
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST) {
                thumbnail = (Bitmap) data.getExtras().get("data");
                imageView1.setImageBitmap(thumbnail);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                File destination = new File(Environment.getExternalStorageDirectory(), "catalogb2b.jpg");
                FileOutputStream fo;
                try {
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("pathfile" + destination);
                fileBody = new FileBody(destination);
                System.out.println("pathfile1" + fileBody);
                selectedPath1 = null;


            }
            if (requestCode == PICK_IMAGE_REQUEST1) {
                selectedPath1 = FilePath.getPath(getActivity(), selectedImageUri);
                System.out.println("selectedPath1 : " + selectedPath1);
                try {
                    thumbnail = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                    imageView1.setImageBitmap(thumbnail);
                    fileBody = null;


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




}

