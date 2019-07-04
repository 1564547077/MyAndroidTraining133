package com.wangliangjun.androidtraining133.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
public class ImageUtil {
    public static void setImage(Context context, String imageSrc, ImageView imageView){
        Glide.with(context).load(imageSrc).into(imageView);
    }

}
