package com.sslyxhz.entropy.gankioAPI;

import com.sslyxhz.entropy.data.GankResultOfDay;
import com.sslyxhz.entropy.net.GankService;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * 获取每日数据测试
 * Created by xh.zeng on 2017/1/2.
 */

public class DayListDataUnitTest {
    public static final String TAG = DayListDataUnitTest.class.getSimpleName();

    private GankService mGankService;

    public DayListDataUnitTest(GankService gankService) {
        mGankService = gankService;
    }

    private void showMsg(String methodName, String msg){
        System.out.println(TAG+"-"+methodName+">>>"+msg);
    }

    public void getDayData(int year, int month, int day) {
        Call<ResponseBody> call = mGankService.getDataOfDay(year, month, day);
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        final String result = response.body().string();
                        showMsg("getDayData", "onResponse:" + result);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    showMsg("getDayData", "onResponse, code=" + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getDayData2(int year, int month, int day) {
        Call<GankResultOfDay> call = mGankService.getDataOfDay2(year, month, day);
        call.enqueue(new Callback<GankResultOfDay>() {

            @Override
            public void onResponse(Call<GankResultOfDay> call, Response<GankResultOfDay> response) {
                if (response.isSuccessful()) {
                    final String result = response.body().toString();
                    showMsg("getDayData2", "onResponse:" + result);
                } else {
                    showMsg("getDayData2", "onResponse, code=" + response.code());
                }
            }

            @Override
            public void onFailure(Call<GankResultOfDay> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getDayData3(int year, int month, int day) {
        mGankService.getDataOfDayRx(year, month, day)
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<GankResultOfDay>() {

                    @Override
                    public void onNext(GankResultOfDay gankResult) {
                        showMsg("getDayData3", "onNext, response:" + gankResult.toString());
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showMsg("getDayData3", "onError, msg=" + e.getMessage());
                        e.printStackTrace();
                    }
                });
    }
}
