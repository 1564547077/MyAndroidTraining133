package com.wangliangjun.androidtraining133.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreFUtils {
    //取出存在取出存在SharedPreferences中对应key的布尔类型数据中对应key的布尔类型数据
    public static boolean getBoolean(Context context,String key,boolean defaultValue){
        SharedPreferences sharedPreferences =
                context.getSharedPreferences("config",context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key,defaultValue);
    }
    //存入SharedPreferences
    public static void setBoolean(Context context,String key,boolean booleanValue){
        SharedPreferences sharedPreferences =
                context.getSharedPreferences("config",context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(key,booleanValue).apply();
    }
}
