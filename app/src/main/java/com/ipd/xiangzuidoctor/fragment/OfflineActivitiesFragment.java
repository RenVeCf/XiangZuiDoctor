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
import com.ipd.xiangzuidoctor.activity.AuthenticationResultActivity;
import com.ipd.xiangzuidoctor.activity.CaptchaLoginActivity;
import com.ipd.xiangzuidoctor.activity.EnrollActivity;
import com.ipd.xiangzuidoctor.activity.MainActivity;
import com.ipd.xiangzuidoctor.activity.OfflineActivitiesDetailsActivity;
import com.ipd.xiangzuidoctor.adapter.OfflineActivitiesAdapter;
import com.ipd.xiangzuidoctor.adapter.OfflineActivitiesMyAdapter;
import com.ipd.xiangzuidoctor.base.BaseFragment;
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
import com.ipd.xiangzuidoctor.contract.OfflineActivitiesContract;
import com.ipd.xiangzuidoctor.presenter.OfflineActivitiesPresenter;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;
import com.ipd.xiangzuidoctor.utils.MD5Utils;
import com.ipd.xiangzuidoctor.utils.SPUtil;
import com.ipd.xiangzuidoctor.utils.StringUtils;
import com.ipd.xiangzuidoctor.utils.ToastUtil;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzuidoctor.common.config.IConstants.SIGN;
import static com.ipd.xiangzuidoctor.common.config.IConstants.USER_ID;

public class OfflineActivitiesFragment extends BaseFragment<OfflineActivitiesContract.View, OfflineActivitiesContract.Presenter> implements OfflineActivitiesContract.View {

    @BindView(R.id.rv_offline_activities)
    RecyclerView rvOfflineActivities;
    @BindView(R.id.srl_offline_activities)
    SwipeRefreshLayout srlOfflineActivities;

    private List<OfflineActivitiesListBean.DataBean.ActivityListBean> activityListBean = new ArrayList<>();
    private OfflineActivitiesAdapter offlineActivitiesAdapter;
    private List<OfflineActivitiesMyBean.DataBean.ActivityListBean> activityMyBean = new ArrayList<>();
    private OfflineActivitiesMyAdapter offlineActivitiesMyAdapter;
    private int pageNum = 1;//页数
    private String offlineActivitiesType;//订单状态 0:最新活动， 1:我的活动
    private int removePosition; //删除的下标

    @Override
    public int getLayoutId() {
        return R.layout.fragment_offline_activities;
    }

    @Override
    public OfflineActivitiesContract.Presenter createPresenter() {
        return new OfflineActivitiesPresenter(mContext);
    }

    @Override
    public OfflineActivitiesContract.View createView() {
        return this;
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
        switch (offlineActivitiesType) {
            case "0":
                TreeMap<String, String> offlineActivitiesListMap = new TreeMap<>();
                offlineActivitiesListMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
                offlineActivitiesListMap.put("pageNum", pageNum + "");
                offlineActivitiesListMap.put("pageSize", "10");
                offlineActivitiesListMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(offlineActivitiesListMap.toString().replaceAll(" ", "") + SIGN)));
                getPresenter().getOfflineActivitiesList(offlineActivitiesListMap, false, false);
                break;
            case "1":
                TreeMap<String, String> offlineActivitiesMyMap = new TreeMap<>();
                offlineActivitiesMyMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
                offlineActivitiesMyMap.put("pageNum", pageNum + "");
                offlineActivitiesMyMap.put("pageSize", "10");
                offlineActivitiesMyMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(offlineActivitiesMyMap.toString().replaceAll(" ", "") + SIGN)));
                getPresenter().getOfflineActivitiesMy(offlineActivitiesMyMap, false, false);
                break;
        }
    }

    @Override
    public void resultOfflineActivitiesList(OfflineActivitiesListBean data) {
        switch (data.getCode()) {
            case 200:
                if (data.getTotal() > 0) {
                    if (pageNum == 1) {
                        activityListBean.clear();
                        activityListBean.addAll(data.getData().getActivityList());
                        offlineActivitiesAdapter = new OfflineActivitiesAdapter(activityListBean);
                        rvOfflineActivities.setAdapter(offlineActivitiesAdapter);
                        offlineActivitiesAdapter.bindToRecyclerView(rvOfflineActivities);
                        offlineActivitiesAdapter.setEnableLoadMore(true);
                        offlineActivitiesAdapter.openLoadAnimation();
                        offlineActivitiesAdapter.disableLoadMoreIfNotFullPage();

                        offlineActivitiesAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                startActivity(new Intent(getContext(), OfflineActivitiesDetailsActivity.class).putExtra("offline_activities_type", offlineActivitiesType).putExtra("activityId", activityListBean.get(position).getActivityId()));
                            }
                        });

                        offlineActivitiesAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                switch (view.getId()) {
                                    case R.id.bt_enroll:
                                        startActivity(new Intent(getContext(), EnrollActivity.class).putExtra("activityId", activityListBean.get(position).getActivityId()));
                                        break;
                                    case R.id.bt_cancel:
                                        TreeMap<String, String> offlineActivitiesCancelMap = new TreeMap<>();
                                        offlineActivitiesCancelMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
                                        offlineActivitiesCancelMap.put("activityId", activityListBean.get(position).getActivityId() + "");
                                        offlineActivitiesCancelMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(offlineActivitiesCancelMap.toString().replaceAll(" ", "") + SIGN)));
                                        getPresenter().getOfflineActivitiesCancel(offlineActivitiesCancelMap, false, false);
                                        break;
                                }
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

                        if (data.getTotal() > 10) {
                            pageNum += 1;
                        } else {
                            offlineActivitiesAdapter.loadMoreEnd();
                        }
                    } else {
                        if ((data.getTotal() - pageNum * 10) > 0) {
                            pageNum += 1;
                            offlineActivitiesAdapter.addData(data.getData().getActivityList());
                            offlineActivitiesAdapter.loadMoreComplete(); //完成本次
                        } else {
                            offlineActivitiesAdapter.addData(data.getData().getActivityList());
                            offlineActivitiesAdapter.loadMoreEnd(); //完成所有加载
                        }
                    }
                } else {
                    activityListBean.clear();
                    offlineActivitiesAdapter = new OfflineActivitiesAdapter(activityListBean);
                    rvOfflineActivities.setAdapter(offlineActivitiesAdapter);
                    offlineActivitiesAdapter.loadMoreEnd(); //完成所有加载
                    offlineActivitiesAdapter.setEmptyView(R.layout.null_data, rvOfflineActivities);
                }
                break;
            case 900:
                ToastUtil.showLongToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(getActivity(), CaptchaLoginActivity.class));
                getActivity().finish();
                break;
        }
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
                        rvOfflineActivities.setAdapter(offlineActivitiesMyAdapter);
                        offlineActivitiesMyAdapter.bindToRecyclerView(rvOfflineActivities);
                        offlineActivitiesMyAdapter.setEnableLoadMore(true);
                        offlineActivitiesMyAdapter.openLoadAnimation();
                        offlineActivitiesMyAdapter.disableLoadMoreIfNotFullPage();

                        offlineActivitiesMyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                startActivity(new Intent(getContext(), OfflineActivitiesDetailsActivity.class).putExtra("offline_activities_type", offlineActivitiesType).putExtra("activityId", activityListBean.get(position).getActivityId()));
                            }
                        });

                        offlineActivitiesMyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                switch (view.getId()) {
                                    case R.id.bt_enroll:
                                        startActivity(new Intent(getContext(), EnrollActivity.class).putExtra("activityId", activityListBean.get(position).getActivityId()));
                                        break;
                                    case R.id.bt_cancel:
                                        removePosition = position;
                                        TreeMap<String, String> offlineActivitiesCancelMap = new TreeMap<>();
                                        offlineActivitiesCancelMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
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
                                rvOfflineActivities.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        initData();
                                    }
                                }, 1000);
                            }
                        }, rvOfflineActivities);

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
                    rvOfflineActivities.setAdapter(offlineActivitiesMyAdapter);
                    offlineActivitiesMyAdapter.loadMoreEnd(); //完成所有加载
                    offlineActivitiesMyAdapter.setEmptyView(R.layout.null_data, rvOfflineActivities);
                }
                break;
            case 900:
                ToastUtil.showLongToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(getActivity(), CaptchaLoginActivity.class));
                getActivity().finish();
                break;
        }
    }

    @Override
    public void resultOfflineActivitiesCancel(OfflineActivitiesCancelBean data) {
        switch (data.getCode()) {
            case 200:
                activityMyBean.remove(removePosition);
                offlineActivitiesMyAdapter.notifyDataSetChanged();
                offlineActivitiesMyAdapter.setEmptyView(R.layout.null_data, rvOfflineActivities);
                startActivity(new Intent(getContext(), AuthenticationResultActivity.class).putExtra("result_type", 4));
                break;
            case 900:
                ToastUtil.showLongToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(getActivity(), CaptchaLoginActivity.class));
                getActivity().finish();
                break;
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }
}
