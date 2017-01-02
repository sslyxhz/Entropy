package com.xhz.entropy;

import com.xhz.entropy.gankioAPI.DayListDataUnitTest;
import com.xhz.entropy.gankioAPI.HistoryDataOfDayUnitTest;
import com.xhz.entropy.gankioAPI.HistoryDataOfSomeDayUnitTest;
import com.xhz.entropy.gankioAPI.HistoryDayUnitTest;
import com.xhz.entropy.gankioAPI.RandomDataUnitTest;
import com.xhz.entropy.gankioAPI.SearchDataUnitTest;
import com.xhz.entropy.gankioAPI.TypeDataUnitTest;
import com.xhz.entropy.net.GankService;
import com.xhz.entropy.net.ServiceFactory;

import org.junit.Test;

/**
 * GankIO API Test
 * Created by xh.zeng on 2017/1/2.
 */

public class GankIOUnitTest {
    public static final String TAG = GankIOUnitTest.class.getSimpleName();

    private GankService mGankService;

    public GankIOUnitTest() {
        mGankService = ServiceFactory.getGankServiceInstance();
    }

    @Test
    public void test() throws InterruptedException {
        testSearchData("Android",5,1);
//        testHistoryDataOfDay(2016,11,11);
//        testHistoryDataOfSomeDay(5,1);
//        testGetHistoryDay();
//        testTypeData("Android",5,1);
//        testDataOfDay(2016, 11, 11);
//        testDataOfRandom("Android",5);

        Thread.sleep(8000);
    }

    public void testSearchData(String type, int count, int page) {
        SearchDataUnitTest testSearchData = new SearchDataUnitTest(mGankService);
        testSearchData.getSearchData(type, count, page);
        testSearchData.getSearchData2(type, count, page);
        testSearchData.getSearchData3(type, count, page);
    }

    public void testHistoryDataOfDay(int year, int month, int day) {
        HistoryDataOfDayUnitTest testHistoryDataOfDay = new HistoryDataOfDayUnitTest(mGankService);
        testHistoryDataOfDay.getHistoryDataOfDay(year, month, day);
        testHistoryDataOfDay.getHistoryDataOfDay2(year, month, day);
        testHistoryDataOfDay.getHistoryDataOfDay3(year, month, day);
    }

    public void testHistoryDataOfSomeDay(int pagesize, int page) {
        HistoryDataOfSomeDayUnitTest testHistoryDataOfSomeDay = new HistoryDataOfSomeDayUnitTest(mGankService);
        testHistoryDataOfSomeDay.getHistoryDataOfSomeDay(pagesize, page);
        testHistoryDataOfSomeDay.getHistoryDataOfSomeDay2(pagesize, page);
        testHistoryDataOfSomeDay.getHistoryDataOfSomeDay3(pagesize, page);
    }

    public void testGetHistoryDay() {
        HistoryDayUnitTest testHistoryDay = new HistoryDayUnitTest(mGankService);
        testHistoryDay.getHistoryDay();
        testHistoryDay.getHistoryDay2();
        testHistoryDay.getHistoryDay3();
    }

    public void testTypeData(String type, int pagesize, int page) {
        TypeDataUnitTest testTypeData = new TypeDataUnitTest(mGankService);
        testTypeData.getTypeData(type, pagesize, page);
        testTypeData.getTypeData2(type, pagesize, page);
        testTypeData.getTypeData3(type, pagesize, page);
    }

    public void testDataOfDay(int year, int month, int day) {
        DayListDataUnitTest testDayListData = new DayListDataUnitTest(mGankService);
        testDayListData.getDayData(year, month, day);
        testDayListData.getDayData2(year, month, day);
        testDayListData.getDayData3(year, month, day);
    }

    public void testDataOfRandom(String type, int count) {
        RandomDataUnitTest testRandomData = new RandomDataUnitTest(mGankService);
        testRandomData.getRandomData(type, count);
        testRandomData.getRandomData2(type, count);
        testRandomData.getRandomData3(type, count);
    }
}
