package com.xhz.entropy.gankioAPI;

import com.xhz.entropy.data.GankResult;
import com.xhz.entropy.data.bean.GankHistoryDataOfSomeDay;
import com.xhz.entropy.net.GankService;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * 获取某几日干货网站数据测试
 * Created by xh.zeng on 2017/1/2.
 */

public class HistoryDataOfSomeDayUnitTest {
    public static final String TAG = HistoryDataOfSomeDayUnitTest.class.getSimpleName();

    private GankService mGankService;

    public HistoryDataOfSomeDayUnitTest(GankService gankService) {
        mGankService = gankService;
    }

    private void showMsg(String methodName, String msg){
        System.out.println(TAG+"-"+methodName+">>>"+msg);
    }

    public void getHistoryDataOfSomeDay(int pagesize, int page) {
        Call<ResponseBody> call = mGankService.getHistoryDataOfSomeDay(pagesize, page);
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        final String result = response.body().string();
                        showMsg("getHistoryDataOfSomeDay", "onResponse:" + result);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    showMsg("getHistoryDataOfSomeDay", "onResponse, code=" + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getHistoryDataOfSomeDay2(int pagesize, int page) {
        Call<GankResult<GankHistoryDataOfSomeDay>> call = mGankService.getHistoryDataOfSomeDay2(pagesize, page);
        call.enqueue(new Callback<GankResult<GankHistoryDataOfSomeDay>>() {

            @Override
            public void onResponse(Call<GankResult<GankHistoryDataOfSomeDay>> call, Response<GankResult<GankHistoryDataOfSomeDay>> response) {
                if (response.isSuccessful()) {
                    final String result = response.body().toString();
                    showMsg("getHistoryDataOfSomeDay2", "onResponse:" + result);
                } else {
                    showMsg("getHistoryDataOfSomeDay2", "onResponse, code=" + response.code());
                }
            }

            @Override
            public void onFailure(Call<GankResult<GankHistoryDataOfSomeDay>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getHistoryDataOfSomeDay3(int pagesize, int page) {
        mGankService.getHistoryDataOfSomeDayRx(pagesize, page)
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<GankResult<GankHistoryDataOfSomeDay>>() {

                    @Override
                    public void onNext(GankResult<GankHistoryDataOfSomeDay> gankResult) {
                        showMsg("getHistoryDataOfSomeDayRx", "onNext, response:" + gankResult.toString());
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showMsg("getHistoryDataOfSomeDayRx", "onError, msg=" + e.getMessage());
                        e.printStackTrace();
                    }
                });
    }
}
