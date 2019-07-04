package com.wangliangjun.androidtraining133.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wangliangjun.androidtraining133.bean.ConstellationDataBean;
import com.wangliangjun.androidtraining133.bean.NewsBean;
import com.wangliangjun.androidtraining133.bean.PythonBean;
import com.wangliangjun.androidtraining133.bean.VideoBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

//获取json解析对象
public class JsonParseUtils {

    private static Gson gson = new Gson();
    public static <T>List<T> getList(Class<T> t,String json){
        Type listType = new MyParameterizedType(t);
        return gson.fromJson(json,listType);
    }
    private static class MyParameterizedType implements ParameterizedType{
        Class raw;

        public MyParameterizedType(Class raw) {
            this.raw = raw;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{raw};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}
