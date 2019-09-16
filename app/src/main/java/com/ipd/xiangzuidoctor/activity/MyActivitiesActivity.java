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
import com.ipd.xiangzuidoctor.adapter.OfflineActivitiesMyAdapter;
import com.ipd.xiangzuidoctor.base.BaseActivity;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesAliPayBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesBalancePayBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesCancelBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesDetailsBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesDetailsPayBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesFreeBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesListBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesMyBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesWechatPayBean;
import com.ipd.xiangzuidoctor.common.view.SpacesItemDecoration;
import com.ipd.xiangzuidoctor.common.view.TopView;
import com.ipd.xiangzuidoctor.contract.OfflineActivitiesContract;
import com.ipd.xiangzuidoctor.presenter.OfflineActivitiesPresenter;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;
import com.ipd.xiangzuidoctor.utils.MD5Utils;
import com.ipd.xiangzuidoctor.utils.SPUtil;
import com.ipd.xiangzuidoctor.utils.StringUtils;
import com.ipd.xiangzuidoctor.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzuidoctor.common.config.IConstants.SIGN;
import static com.ipd.xiangzuidoctor.common.config.IConstants.USER_ID;

/**
 * Description ：我的活动
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/28.
 */
public class MyActivitiesActivity extends BaseActivity<OfflineActivitiesContract.View, OfflineActivitiesContract.Presenter> implements OfflineActivitiesContract.View {

    @BindView(R.id.tv_my_activities)
    TopView tvMyActivities;
    @BindView(R.id.rv_my_activities)
    RecyclerView rvMyActivities;
    @BindView(R.id.srl_my_activities)
    SwipeRefreshLayout srlMyActivities;

    private List<OfflineActivitiesMyBean.DataBean.ActivityListBean> activityMyBean = new ArrayList<>();
    private OfflineActivitiesMyAdapter offlineActivitiesMyAdapter;
    private int pageNum = 1;//页数
    private int removePosition; //删除的下标

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_activities;
    }

    @Override
    public OfflineActivitiesContract.Presenter createPresenter() {
        return new OfflineActivitiesPresenter(this);
    }

    @Override
    public OfflineActivitiesContract.View createView() {
        return this;
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
        TreeMap<String, String> offlineActivitiesMyMap = new TreeMap<>();
        offlineActivitiesMyMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        offlineActivitiesMyMap.put("pageNum", pageNum + "");
        offlineActivitiesMyMap.put("pageSize", "10");
        offlineActivitiesMyMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(offlineActivitiesMyMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getOfflineActivitiesMy(offlineActivitiesMyMap, false, false);
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

    @Override
    public void resultOfflineActivitiesList(OfflineActivitiesListBean data) {

    }

    @Override
    public void resultOfflineActivitiesDetails(OfflineActivitiesDetailsBean data) {

    }

    @Override
    public void resultOfflineActivitiesDetailsPay(OfflineActivitiesDetailsPayBean data) {

    }

    @Override
    public void resultOfflineActivitiesFree(OfflineActivitiesFreeBean data) {

    }

    @Override
    public void resultOfflineActivitiesAliPay(OfflineActivitiesAliPayBean data) {

    }

    @Override
    public void resultOfflineActivitiesWechatPay(OfflineActivitiesWechatPayBean data) {

    }

    @Override
    public void resultOfflineActivitiesBalancePay(OfflineActivitiesBalancePayBean data) {

    }

    @Override
    public void resultOfflineActivitiesMy(OfflineActivitiesMyBean data) {
        switch (data.getCode()) {
            case 200:
                if (data.getTotal() > 0) {
                    if (pageNum == 1) {
                        activityMyBean.clear();
                        activityMyBean.addAll(data.getData().getActivityList());
                        offlineActivitiesMyAdapter = new OfflineActivitiesMyAdapter(activityMyBean);
                        rvMyActivities.setAdapter(offlineActivitiesMyAdapter);
                        offlineActivitiesMyAdapter.bindToRecyclerView(rvMyActivities);
                        offlineActivitiesMyAdapter.setEnableLoadMore(true);
                        offlineActivitiesMyAdapter.openLoadAnimation();
                        offlineActivitiesMyAdapter.disableLoadMoreIfNotFullPage();

                        offlineActivitiesMyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                startActivity(new Intent(MyActivitiesActivity.this, OfflineActivitiesDetailsActivity.class).putExtra("offline_activities_type", 1).putExtra("activityId", activityMyBean.get(position).getActivityId()));
                            }
                        });

                        offlineActivitiesMyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                switch (view.getId()) {
                                    case R.id.bt_cancel:
                                        removePosition = position;
                                        TreeMap<String, String> offlineActivitiesCancelMap = new TreeMap<>();
                                        offlineActivitiesCancelMap.put("userId", SPUtil.get(MyActivitiesActivity.this, USER_ID, "") + "");
                                        offlineActivitiesCancelMap.put("activityId", activityMyBean.get(position).getActivityId() + "");
                                        offlineActivitiesCancelMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(offlineActivitiesCancelMap.toString().replaceAll(" ", "") + SIGN)));
                                        getPresenter().getOfflineActivitiesCancel(offlineActivitiesCancelMap, false, false);
                                        break;
                                }
                            }
                        });

                        //上拉加载
                        offlineActivitiesMyAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                            @Override
                            public void onLoadMoreRequested() {
                                rvMyActivities.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        initData();
                                    }
                                }, 1000);
                            }
                        }, rvMyActivities);

                        if (data.getTotal() > 10) {
                            pageNum += 1;
                        } else {
                            offlineActivitiesMyAdapter.loadMoreEnd();
                        }
                    } else {
                        if ((data.getTotal() - pageNum * 10) > 0) {
                            pageNum += 1;
                            offlineActivitiesMyAdapter.addData(data.getData().getActivityList());
                            offlineActivitiesMyAdapter.loadMoreComplete(); //完成本次
                        } else {
                            offlineActivitiesMyAdapter.addData(data.getData().getActivityList());
                            offlineActivitiesMyAdapter.loadMoreEnd(); //完成所有加载
                        }
                    }
                } else {
                    activityMyBean.clear();
                    offlineActivitiesMyAdapter = new OfflineActivitiesMyAdapter(activityMyBean);
                    rvMyActivities.setAdapter(offlineActivitiesMyAdapter);
                    offlineActivitiesMyAdapter.loadMoreEnd(); //完成所有加载
                    offlineActivitiesMyAdapter.setEmptyView(R.layout.null_data, rvMyActivities);
                }
                break;
            case 900:
                ToastUtil.showLongToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(this, CaptchaLoginActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void resultOfflineActivitiesCancel(OfflineActivitiesCancelBean data) {
        switch (data.getCode()) {
            case 200:
                activityMyBean.remove(removePosition);
                offlineActivitiesMyAdapter.notifyDataSetChanged();
                offlineActivitiesMyAdapter.setEmptyView(R.layout.null_data, rvMyActivities);
                startActivity(new Intent(this, AuthenticationResultActivity.class).putExtra("result_type", 4));
                break;
            case 900:
                ToastUtil.showLongToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(this, CaptchaLoginActivity.class));
                finish();
                break;
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
