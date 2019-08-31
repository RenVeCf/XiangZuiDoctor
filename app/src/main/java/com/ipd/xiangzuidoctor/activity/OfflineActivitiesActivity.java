package com.ipd.xiangzuidoctor.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.adapter.ViewPagerAdapter;
import com.ipd.xiangzuidoctor.base.BaseActivity;
import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.common.view.NavitationLayout;
import com.ipd.xiangzuidoctor.common.view.TopView;
import com.ipd.xiangzuidoctor.fragment.OfflineActivitiesFragment;
import com.ipd.xiangzuidoctor.fragment.SpecialColumnFragment;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Description ：线下活动
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/31.
 */
public class OfflineActivitiesActivity extends BaseActivity {

    @BindView(R.id.tv_offline_activities)
    TopView tvOfflineActivities;
    @BindView(R.id.nfsl_offline_activities)
    NavitationLayout nfslOfflineActivities;
    @BindView(R.id.vp_offline_activities)
    ViewPager vpOfflineActivities;

    private String[] titles;
    private List<Fragment> fragments;
    private OfflineActivitiesFragment orderFragment;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_offline_activities;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvOfflineActivities);
    }

    @Override
    public void initData() {
        titles = new String[]{"最新活动", "我的活动"};
        //向集合添加Fragment
        fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            orderFragment = new OfflineActivitiesFragment();
            Bundle args = new Bundle();
            args.putString("offline_activities_type", i + "");
            orderFragment.setArguments(args);
            fragments.add(orderFragment);
        }
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        vpOfflineActivities.setAdapter(viewPagerAdapter);
        vpOfflineActivities.setOffscreenPageLimit(titles.length);

        //设置导航条
        nfslOfflineActivities.setViewPager(this, titles, vpOfflineActivities, R.color.tx_bottom_navigation, R.color.white, 12, 12, 0, 45, true);
        nfslOfflineActivities.setBgLine(this, 1, R.color.white);
        nfslOfflineActivities.setNavLine(this, 1, R.color.white, 0);
    }

    @Override
    public void initListener() {

    }
}
