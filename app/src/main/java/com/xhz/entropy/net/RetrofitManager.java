package com.xhz.entropy.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xh.zeng on 2017/1/2.
 */

public class RetrofitManager {

    private Retrofit mRetrofit;
    private GankService mGankService;

    public RetrofitManager() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>(){

                    @Override
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        String date = json.getAsString();
                        if(date == null){return null;}

                        // "2016-02-29T12:19:00.015000", 无法通过，出自搜索API
                        // "2016-12-30T16:16:11.125Z", 可以通过
                        if(date.endsWith("000")){
                            String result = date.substring(0, date.length()-3)+'Z';
                        }

                        SimpleDateFormat formatter = new SimpleDateFormat(GankService.DATE_FORMAT);
//                        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

                        try {
                            return formatter.parse(date);
                        } catch (ParseException e) {
                            System.err.println("Failed to parse Date due to:"+e.getMessage());
                            return null;
                        }
                    }
                })
//                .setDateFormat(PATTERN)
                .serializeNulls()
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(GankService.HOST)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mGankService = mRetrofit.create(GankService.class);
    }

    public GankService getGankService() {
        return mGankService;
    }

}
