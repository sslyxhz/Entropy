package com.sslyxhz.entropy.gankioAPI;

import com.sslyxhz.entropy.data.GankResult;
import com.sslyxhz.entropy.data.bean.GankSearchData;
import com.sslyxhz.entropy.net.GankService;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * 搜索 API 测试
 * Created by xh.zeng on 2017/1/2.
 */

public class SearchDataUnitTest {
    public static final String TAG = SearchDataUnitTest.class.getSimpleName();

    private GankService mGankService;

    public SearchDataUnitTest(GankService gankService) {
        mGankService = gankService;
    }

    private void showMsg(String methodName, String msg){
        System.out.println(TAG+"-"+methodName+">>>"+msg);
    }

    public void getSearchData(String type, int count, int page) {
        Call<ResponseBody> call = mGankService.getDataSearch(type, count, page);
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        final String result = response.body().string();
                        showMsg("getSearchData", "onResponse:" + result);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    showMsg("getSearchData", "onResponse, code=" + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getSearchData2(String type, int count, int page) {
        Call<GankResult<GankSearchData>> call = mGankService.getDataSearch2(type, count, page);
        call.enqueue(new Callback<GankResult<GankSearchData>>() {

            @Override
            public void onResponse(Call<GankResult<GankSearchData>> call, Response<GankResult<GankSearchData>> response) {
                if (response.isSuccessful()) {
                    final String result = response.body().toString();
                    showMsg("getSearchData2", "onResponse:" + result);
                } else {
                    showMsg("getSearchData2", "onResponse, code=" + response.code());
                }
            }

            @Override
            public void onFailure(Call<GankResult<GankSearchData>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getSearchData3(String type, int count, int page) {
        mGankService.getDataSearchRx(type, count, page)
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<GankResult<GankSearchData>>() {

                    @Override
                    public void onNext(GankResult<GankSearchData> gankResult) {
                        showMsg("getSearchData3", "onNext, response:" + gankResult.toString());
                    }

                    @Override
                    public void onCompleted() {
                        showMsg("getSearchData3", "onCompleted.:");
                    }

                    @Override
                    public void onError(Throwable e) {
                        showMsg("getSearchData3", "onError, msg=:" + e.getMessage());
                        e.printStackTrace();
                    }
                });
    }
}
