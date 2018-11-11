package com.sslyxhz.entropy.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sslyxhz.entropy.ui.fragment.TypeDataFragment;

import java.util.List;

/**
 * Created by xh.zeng on 2017/1/5.
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    public static final String TAG = MainFragmentPagerAdapter.class.getSimpleName();

    private List<TypeDataFragment> mFragments;

    public MainFragmentPagerAdapter(FragmentManager fm, List<TypeDataFragment> list){
        super(fm);
        mFragments = list;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTagName();
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
