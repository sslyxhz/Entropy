package com.xhz.entropy.gankioAPI;

import com.xhz.entropy.data.GankResult;
import com.xhz.entropy.data.bean.GankHistoryDataOfDay;
import com.xhz.entropy.net.GankService;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * 获取特定日期网站数据测试
 * Created by xh.zeng on 2017/1/2.
 */

public class HistoryDataOfDayUnitTest {
    public static final String TAG = HistoryDataOfDayUnitTest.class.getSimpleName();

    private GankService mGankService;

    public HistoryDataOfDayUnitTest(GankService gankService) {
        mGankService = gankService;
    }

    private void showMsg(String methodName, String msg){
        System.out.println(TAG+"-"+methodName+">>>"+msg);
    }

    public void getHistoryDataOfDay(int year, int month, int day) {
        Call<ResponseBody> call = mGankService.getHistoryDataOfDay(year, month, day);
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        final String result = response.body().string();
                        showMsg("getHistoryDataOfDay", "onResponse:" + result);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    showMsg("getHistoryDataOfDay", "onResponse, code=" + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getHistoryDataOfDay2(int year, int month, int day) {
        Call<GankResult<GankHistoryDataOfDay>> call = mGankService.getHistoryDataOfDay2(year, month, day);
        call.enqueue(new Callback<GankResult<GankHistoryDataOfDay>>() {
            @Override
            public void onResponse(Call<GankResult<GankHistoryDataOfDay>> call, Response<GankResult<GankHistoryDataOfDay>> response) {
                if (response.isSuccessful()) {
                    final String result = response.body().toString();
                    showMsg("getHistoryDataOfDay2", "onResponse:" + result);
                } else {
                    showMsg("getHistoryDataOfDay2", "onResponse, code=" + response.code());
                }
            }

            @Override
            public void onFailure(Call<GankResult<GankHistoryDataOfDay>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getHistoryDataOfDay3(int year, int month, int day) {
        mGankService.getHistoryDataOfDayRx(year, month, day)
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<GankResult<GankHistoryDataOfDay>>() {

                    @Override
                    public void onNext(GankResult<GankHistoryDataOfDay> gankResult) {
                        showMsg("getHistoryDataOfDayRx", "response:" + gankResult.toString());
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        showMsg("getHistoryDataOfDayRx", "onError, msg=" + e.getMessage());
                        e.printStackTrace();
                    }
                });
    }
}
