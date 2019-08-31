package com.ipd.xiangzuidoctor.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.activity.OfflineActivitiesDetailsActivity;
import com.ipd.xiangzuidoctor.activity.SpecialColumnDetailsActivity;
import com.ipd.xiangzuidoctor.adapter.OfflineActivitiesAdapter;
import com.ipd.xiangzuidoctor.base.BaseFragment;
import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.bean.TestMultiItemEntityBean;
import com.ipd.xiangzuidoctor.common.view.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OfflineActivitiesFragment extends BaseFragment {

    @BindView(R.id.rv_offline_activities)
    RecyclerView rvOfflineActivities;
    @BindView(R.id.srl_offline_activities)
    SwipeRefreshLayout srlOfflineActivities;

    private List<TestMultiItemEntityBean> str1 = new ArrayList<>();
    private OfflineActivitiesAdapter offlineActivitiesAdapter;
    private int pageNum = 1;//页数
    private String offlineActivitiesType;//订单状态 0:最新活动， 1:我的活动

    @Override
    public int getLayoutId() {
        return R.layout.fragment_offline_activities;
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
    public void init(View view) {
        Bundle args = getArguments();
        if (args != null) {
            offlineActivitiesType = args.getString("offline_activities_type");
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvOfflineActivities.setLayoutManager(layoutManager);
        rvOfflineActivities.setNestedScrollingEnabled(false);
        rvOfflineActivities.addItemDecoration(new SpacesItemDecoration(1, 50));
        rvOfflineActivities.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvOfflineActivities.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlOfflineActivities.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlOfflineActivities.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
                srlOfflineActivities.setRefreshing(false);
            }
        });
    }

    @Override
    public void initData() {
        if (5 > 0) {//TODO 有接口后5更换总条数
            if (pageNum == 1) {
                str1.clear();
                for (int i = 0; i < 5; i++) {//TODO 有接口后去掉
                    TestMultiItemEntityBean testData = new TestMultiItemEntityBean();
                    str1.add(testData);
                }
//                str1.addAll(data.getData().getMessageList());//TODO 有接口后打开
                offlineActivitiesAdapter = new OfflineActivitiesAdapter(str1, Integer.parseInt(offlineActivitiesType));
                rvOfflineActivities.setAdapter(offlineActivitiesAdapter);
                offlineActivitiesAdapter.bindToRecyclerView(rvOfflineActivities);
                offlineActivitiesAdapter.setEnableLoadMore(true);
                offlineActivitiesAdapter.openLoadAnimation();
                offlineActivitiesAdapter.disableLoadMoreIfNotFullPage();

                offlineActivitiesAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        startActivity(new Intent(getContext(), OfflineActivitiesDetailsActivity.class).putExtra("offline_activities_type", offlineActivitiesType));
                    }
                });

                //上拉加载
                offlineActivitiesAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {
                        rvOfflineActivities.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                initData();
                            }
                        }, 1000);
                    }
                }, rvOfflineActivities);

                if (5 > 10) {//TODO 有接口后5更换list.size
                    pageNum += 1;
                } else {
                    offlineActivitiesAdapter.loadMoreEnd();
                }
            } else {
                if ((5 - pageNum * 10) > 0) {//TODO 有接口后5更换list.size
                    pageNum += 1;
//                    offlineActivitiesAdapter.addData(data.getData().getMessageList());//TODO 有接口后打开
                    offlineActivitiesAdapter.loadMoreComplete(); //完成本次
                } else {
//                    offlineActivitiesAdapter.addData(data.getData().getMessageList());//TODO 有接口后打开
                    offlineActivitiesAdapter.loadMoreEnd(); //完成所有加载
                }
            }
        } else {
            str1.clear();
            offlineActivitiesAdapter = new OfflineActivitiesAdapter(str1, Integer.parseInt(offlineActivitiesType));
            rvOfflineActivities.setAdapter(offlineActivitiesAdapter);
            offlineActivitiesAdapter.loadMoreEnd(); //完成所有加载
            offlineActivitiesAdapter.setEmptyView(R.layout.null_data, rvOfflineActivities);
        }
    }
}
