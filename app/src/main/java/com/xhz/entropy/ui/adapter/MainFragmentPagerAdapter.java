package com.xhz.entropy.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by xh.zeng on 2017/1/5.
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    public MainFragmentPagerAdapter(FragmentManager fm, List<Fragment> list){
        super(fm);
        mFragments = list;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Tab"+position;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
