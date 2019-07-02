package com.wangliangjun.androidtraining133.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wangliangjun.androidtraining133.bean.ConstellationDataBean;
import com.wangliangjun.androidtraining133.bean.NewsBean;
import com.wangliangjun.androidtraining133.bean.PythonBean;
import com.wangliangjun.androidtraining133.bean.VideoBean;

import java.lang.reflect.Type;
import java.util.List;

//获取json解析对象
public class JsonParseUtils {
    private static Gson gson = new Gson();
    public static List<NewsBean> getNewsList(String json){
        Type listType = new TypeToken<List<NewsBean>>(){}.getType();
        return gson.fromJson(json,listType);
    }
    public static List<PythonBean> getPythonList(String json){
        Type listType = new TypeToken<List<PythonBean>>(){}.getType();
        return gson.fromJson(json,listType);
    }
    public static List<VideoBean> getVideoList(String json){
        Type listType = new TypeToken<List<VideoBean>>(){}.getType();
        return gson.fromJson(json,listType);
    }
    public static List<ConstellationDataBean> getConstellationDataList(String json){
        Type listType = new TypeToken<List<ConstellationDataBean>>(){}.getType();
        return gson.fromJson(json,listType);
    }
}
