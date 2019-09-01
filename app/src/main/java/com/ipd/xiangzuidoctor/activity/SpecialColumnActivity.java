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
import com.ipd.xiangzuidoctor.fragment.SpecialColumnFragment;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Description ：医学专栏
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/31.
 */
public class SpecialColumnActivity extends BaseActivity {

    @BindView(R.id.tv_special_column)
    TopView tvSpecialColumn;
    @BindView(R.id.nfsl_special_column)
    NavitationLayout nfslSpecialColumn;
    @BindView(R.id.vp_special_column)
    ViewPager vpSpecialColumn;

    private String[] titles;
    private List<Fragment> fragments;
    private SpecialColumnFragment orderFragment;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_special_column;
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
        ImmersionBar.setTitleBar(this, tvSpecialColumn);
    }

    @Override
    public void initData() {
        titles = new String[]{"最新文章", "新闻", "临床", "科研"};
        //向集合添加Fragment
        fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            orderFragment = new SpecialColumnFragment();
            Bundle args = new Bundle();
            args.putString("special_column_type", i + "");
            orderFragment.setArguments(args);
            fragments.add(orderFragment);
        }
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        vpSpecialColumn.setAdapter(viewPagerAdapter);
        vpSpecialColumn.setOffscreenPageLimit(titles.length);

        //设置导航条
        nfslSpecialColumn.setViewPager(this, titles, vpSpecialColumn, R.color.tx_bottom_navigation, R.color.white, 12, 12, 0, 45, true);
        nfslSpecialColumn.setBgLine(this, 1, R.color.white);
        nfslSpecialColumn.setNavLine(this, 1, R.color.white, 0);
    }

    @Override
    public void initListener() {

    }
}
