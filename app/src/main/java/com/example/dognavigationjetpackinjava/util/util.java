package com.example.dognavigationjetpackinjava.util;

import android.content.Context;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class util {

    public static void loadImage(ImageView imageView, String url, CircularProgressDrawable pD){

        RequestOptions options = new RequestOptions()
                .placeholder(pD);

        Glide.with(imageView.getContext()).load(url).into(imageView);

    }
    public static CircularProgressDrawable getpD(Context context){
        CircularProgressDrawable cpd = new CircularProgressDrawable(context);
        cpd.setStrokeWidth(10f);
        cpd.setCenterRadius(50f);
        cpd.start();
        return cpd;
    }


}




