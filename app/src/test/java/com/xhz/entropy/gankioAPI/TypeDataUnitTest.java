package com.xhz.entropy.gankioAPI;

import com.xhz.entropy.data.GankResult;
import com.xhz.entropy.data.bean.GankTypeData;
import com.xhz.entropy.net.GankService;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * 获取分类数据测试
 * Created by xh.zeng on 2017/1/2.
 */

public class TypeDataUnitTest {
    public static final String TAG = TypeDataUnitTest.class.getSimpleName();

    private GankService mGankService;

    public TypeDataUnitTest(GankService gankService) {
        mGankService = gankService;
    }

    private void showMsg(String methodName, String msg){
        System.out.println(TAG+"-"+methodName+">>>"+msg);
    }

    public void getTypeData(String type, int pagesize, int page) {
        Call<ResponseBody> call = mGankService.getDataOfType(type, pagesize, page);
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        final String result = response.body().string();
                        showMsg("getTypeData", "onResponse:" + result);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    showMsg("getTypeData", "onResponse, code=" + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getTypeData2(String type, int pagesize, int page) {
        Call<GankResult<GankTypeData>> call = mGankService.getDataOfType2(type, pagesize, page);
        call.enqueue(new Callback<GankResult<GankTypeData>>() {

            @Override
            public void onResponse(Call<GankResult<GankTypeData>> call, Response<GankResult<GankTypeData>> response) {
                if (response.isSuccessful()) {
                    final String result = response.body().toString();
                    showMsg("getTypeData2", "onResponse:" + result);
                } else {
                    showMsg("getTypeData2", "onResponse, code=" + response.code());
                }
            }

            @Override
            public void onFailure(Call<GankResult<GankTypeData>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getTypeData3(String type, int pagesize, int page) {
        mGankService.getDataOfTypeRx(type, pagesize, page)
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<GankResult<GankTypeData>>() {

                    @Override
                    public void onNext(GankResult<GankTypeData> gankResult) {
                        showMsg("getTypeData3", "onNext, response:" + gankResult.toString());
                    }

                    @Override
                    public void onCompleted() {
                        showMsg("getTypeData3", "onCompleted...");
                    }

                    @Override
                    public void onError(Throwable e) {
                        showMsg("getTypeData3", "onError, msg=:" + e.getMessage());
                        e.printStackTrace();
                    }
                });
    }
}
