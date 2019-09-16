package com.ipd.xiangzuidoctor.activity;

import android.os.Bundle;
import android.view.View;

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
import com.ipd.xiangzuidoctor.fragment.OrderFragment;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：订单
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/31.
 */
public class OrderActivity extends BaseActivity {

    @BindView(R.id.tv_order)
    TopView tvOrder;
    @BindView(R.id.nfsl_order)
    NavitationLayout nfslOrder;
    @BindView(R.id.vp_order)
    ViewPager vpOrder;

    private String[] titles;
    private List<Fragment> fragments;
    private OrderFragment orderFragment;
    private ViewPagerAdapter viewPagerAdapter;
    private int currentPosition;//初始Position

    @Override
    public int getLayoutId() {
        return R.layout.activity_order;
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
        ImmersionBar.setTitleBar(this, tvOrder);

        currentPosition = getIntent().getIntExtra("current_position", 0);
    }

    @Override
    public void initData() {
        titles = new String[]{"待接单", "已接单", "进行中", "已完成"};
        //向集合添加Fragment
        fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            orderFragment = new OrderFragment();
            Bundle args = new Bundle();
            args.putString("order_type", i + "");
            orderFragment.setArguments(args);
            fragments.add(orderFragment);
        }
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        vpOrder.setAdapter(viewPagerAdapter);
        vpOrder.setOffscreenPageLimit(titles.length);

        //设置导航条
        nfslOrder.setViewPager(this, titles, vpOrder, R.color.tx_bottom_navigation, R.color.white, 12, 12, currentPosition, 45, true);
        nfslOrder.setBgLine(this, 1, R.color.white);
        nfslOrder.setNavLine(this, 1, R.color.white, currentPosition);
    }

    @Override
    public void initListener() {

    }
}
