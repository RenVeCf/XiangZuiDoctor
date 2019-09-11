package com.ipd.xiangzuidoctor.activity;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.adapter.OfflineActivitiesAdapter;
import com.ipd.xiangzuidoctor.base.BaseActivity;
import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.bean.TestMultiItemEntityBean;
import com.ipd.xiangzuidoctor.common.view.SpacesItemDecoration;
import com.ipd.xiangzuidoctor.common.view.TopView;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Description ：我的活动
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/28.
 */
public class MyActivitiesActivity extends BaseActivity {

    @BindView(R.id.tv_my_activities)
    TopView tvMyActivities;
    @BindView(R.id.rv_my_activities)
    RecyclerView rvMyActivities;
    @BindView(R.id.srl_my_activities)
    SwipeRefreshLayout srlMyActivities;

    private List<TestMultiItemEntityBean> str1 = new ArrayList<>();
    private OfflineActivitiesAdapter offlineActivitiesAdapter;
    private int pageNum = 1;//页数

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_activities;
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
        ImmersionBar.setTitleBar(this, tvMyActivities);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvMyActivities.setLayoutManager(layoutManager);
        rvMyActivities.setNestedScrollingEnabled(false);
        rvMyActivities.addItemDecoration(new SpacesItemDecoration(1, 50));
        rvMyActivities.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvMyActivities.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlMyActivities.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色
    }

    @Override
    public void initData() {
//        if (5 > 0) {//TODO 有接口后5更换总条数
//            if (pageNum == 1) {
//                str1.clear();
//                for (int i = 0; i < 5; i++) {//TODO 有接口后去掉
//                    TestMultiItemEntityBean testData = new TestMultiItemEntityBean();
//                    str1.add(testData);
//                }
////                str1.addAll(data.getData().getMessageList());//TODO 有接口后打开
//                offlineActivitiesAdapter = new OfflineActivitiesAdapter(str1, 1);
//                rvMyActivities.setAdapter(offlineActivitiesAdapter);
//                offlineActivitiesAdapter.bindToRecyclerView(rvMyActivities);
//                offlineActivitiesAdapter.setEnableLoadMore(true);
//                offlineActivitiesAdapter.openLoadAnimation();
//                offlineActivitiesAdapter.disableLoadMoreIfNotFullPage();
//
//                offlineActivitiesAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                        startActivity(new Intent(MyActivitiesActivity.this, OfflineActivitiesDetailsActivity.class).putExtra("offline_activities_type", "1"));
//                    }
//                });
//
//                //上拉加载
//                offlineActivitiesAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//                    @Override
//                    public void onLoadMoreRequested() {
//                        rvMyActivities.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                initData();
//                            }
//                        }, 1000);
//                    }
//                }, rvMyActivities);
//
//                if (5 > 10) {//TODO 有接口后5更换list.size
//                    pageNum += 1;
//                } else {
//                    offlineActivitiesAdapter.loadMoreEnd();
//                }
//            } else {
//                if ((5 - pageNum * 10) > 0) {//TODO 有接口后5更换list.size
//                    pageNum += 1;
////                    offlineActivitiesAdapter.addData(data.getData().getMessageList());//TODO 有接口后打开
//                    offlineActivitiesAdapter.loadMoreComplete(); //完成本次
//                } else {
////                    offlineActivitiesAdapter.addData(data.getData().getMessageList());//TODO 有接口后打开
//                    offlineActivitiesAdapter.loadMoreEnd(); //完成所有加载
//                }
//            }
//        } else {
//            str1.clear();
//            offlineActivitiesAdapter = new OfflineActivitiesAdapter(str1, 1);
//            rvMyActivities.setAdapter(offlineActivitiesAdapter);
//            offlineActivitiesAdapter.loadMoreEnd(); //完成所有加载
//            offlineActivitiesAdapter.setEmptyView(R.layout.null_data, rvMyActivities);
//        }
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlMyActivities.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
                srlMyActivities.setRefreshing(false);
            }
        });
    }
}
