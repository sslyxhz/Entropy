package com.sslyxhz.entropy.gankioAPI;

import com.sslyxhz.entropy.data.GankResult;
import com.sslyxhz.entropy.data.bean.GankRandomData;
import com.sslyxhz.entropy.net.GankService;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * 获取随机数据测试
 * Created by xh.zeng on 2017/1/2.
 */

public class RandomDataUnitTest {
    public static final String TAG = RandomDataUnitTest.class.getSimpleName();

    private GankService mGankService;

    public RandomDataUnitTest(GankService gankService) {
        mGankService = gankService;
    }

    private void showMsg(String methodName, String msg){
        System.out.println(TAG+"-"+methodName+">>>"+msg);
    }

    public void getRandomData(String type, int count) {
        Call<ResponseBody> call = mGankService.getDataOfRandom(type, count);
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        final String result = response.body().string();
                        showMsg("getRandomData", "onResponse:" + result);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    showMsg("getRandomData", "onResponse, code=" + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getRandomData2(String type, int count) {
        Call<GankResult<GankRandomData>> call = mGankService.getDataOfRandom2(type, count);
        call.enqueue(new Callback<GankResult<GankRandomData>>() {

            @Override
            public void onResponse(Call<GankResult<GankRandomData>> call, Response<GankResult<GankRandomData>> response) {
                if (response.isSuccessful()) {
                    final String result = response.body().toString();
                    showMsg("getRandomData2", "onResponse:" + result);
                } else {
                    showMsg("getRandomData2", "onResponse, code=" + response.code());
                }
            }

            @Override
            public void onFailure(Call<GankResult<GankRandomData>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getRandomData3(String type, int count) {
        mGankService.getDataOfRandomRx(type, count)
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<GankResult<GankRandomData>>() {

                    @Override
                    public void onNext(GankResult<GankRandomData> gankResult) {
                        showMsg("getRandomData3", "onNext, response:" + gankResult.toString());
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showMsg("getRandomData3", "onError, msg=:" + e.getMessage());
                        e.printStackTrace();
                    }
                });
    }
}
