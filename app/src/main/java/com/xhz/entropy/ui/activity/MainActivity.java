package com.xhz.entropy.ui.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.xhz.entropy.R;
import com.xhz.entropy.net.GankService;
import com.xhz.entropy.presenter.MainPresenter;
import com.xhz.entropy.ui.adapter.MainFragmentPagerAdapter;
import com.xhz.entropy.ui.fragment.TypeDataFragment;
import com.xhz.entropy.ui.view.IMainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 主界面
 * Created by xh.zeng on 2017/1/4.
 */
public class MainActivity extends BaseActivity<MainPresenter>
        implements IMainView, NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.drawer_layout) DrawerLayout mDrawer;
    @BindView(R.id.nav_view) NavigationView mNavigationView;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.fab) FloatingActionButton mFloatingActionButton;

    @BindView(R.id.vp_main_content) ViewPager mViewPager;
    @BindView(R.id.tl_main_content) TabLayout mTabLayout;

    MainFragmentPagerAdapter mPagerAdapter;
    private List<TypeDataFragment> mFragments;

    @Override
    protected void init(){
        initData();

        setSupportActionBar(mToolbar);
        setTitle("Entropy", false);     // 设置标题

        mNavigationView.setNavigationItemSelectedListener(this);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.d(TAG, "--------onPageScrolled position:" + position);
//                Log.d(TAG, "--------onPageScrolled positionOffset:" + positionOffset);
//                Log.d(TAG, "--------onPageScrolled positionOffsetPixels:" + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
//                Log.d(TAG, "-------onPageSelected:" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                Log.d(TAG, "------onPageScrollStateChanged:" + state);
            }
        });
    }

    private void initData(){
        mFragments = new ArrayList<>();
        mFragments.add(TypeDataFragment.newInstance(GankService.GankType.all.name()));
        mFragments.add(TypeDataFragment.newInstance(GankService.GankType.Android.name()));
        mFragments.add(TypeDataFragment.newInstance(GankService.GankType.iOS.name()));
        mFragments.add(TypeDataFragment.newInstance(GankService.GankType.休息视频.name()));
        mFragments.add(TypeDataFragment.newInstance(GankService.GankType.福利.name()));
        mFragments.add(TypeDataFragment.newInstance(GankService.GankType.拓展资源.name()));
        mFragments.add(TypeDataFragment.newInstance(GankService.GankType.瞎推荐.name()));
        mFragments.add(TypeDataFragment.newInstance(GankService.GankType.App.name()));
        mPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), mFragments);
    }

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter(this, this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_all) {
        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onLoadFailure(Throwable e) {

    }

    @Override
    public void onLoadEmpty() {

    }
}
