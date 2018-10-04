package com.xhz.entropy.gankioAPI;

import com.xhz.entropy.data.GankResult;
import com.xhz.entropy.net.GankService;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * 获取发过干货日期接口测试
 * Created by xh.zeng on 2017/1/2.
 */

public class HistoryDayUnitTest {
    public static final String TAG = HistoryDayUnitTest.class.getSimpleName();

    private GankService mGankService;

    public HistoryDayUnitTest(GankService gankService) {
        mGankService = gankService;
    }

    private void showMsg(String methodName, String msg){
        System.out.println(TAG+"-"+methodName+">>>"+msg);
    }

    public void getHistoryDay() {
        Call<ResponseBody> call = mGankService.getDayHistory();
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        final String result = response.body().string();
                        showMsg("getHistoryDay", "onResponse:" + result);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    showMsg("getHistoryDay", "onResponse, code=" + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getHistoryDay2() {
        Call<GankResult<String>> call = mGankService.getDayHistory2();
        call.enqueue(new Callback<GankResult<String>>() {

            @Override
            public void onResponse(Call<GankResult<String>> call, Response<GankResult<String>> response) {
                if (response.isSuccessful()) {
                    final String result = response.body().toString();
                    showMsg("getHistoryDay2", "onResponse:" + result);
                } else {
                    showMsg("getHistoryDay2", "onResponse, code=" + response.code());
                }
            }

            @Override
            public void onFailure(Call<GankResult<String>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getHistoryDay3() {
        mGankService.getDayHistoryRx()
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<GankResult<String>>() {

                    @Override
                    public void onNext(GankResult<String> gankResult) {
                        showMsg("getHistoryDay3", "onNext, response:" + gankResult.toString());
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        showMsg("getHistoryDay3", "onError, msg=" + e.getMessage());
                        e.printStackTrace();
                    }
                });
    }
}
