package com.ipd.xiangzuidoctor.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.activity.AuthenticationActivity;
import com.ipd.xiangzuidoctor.activity.CaptchaLoginActivity;
import com.ipd.xiangzuidoctor.activity.MainActivity;
import com.ipd.xiangzuidoctor.activity.OrderDetailsActivity;
import com.ipd.xiangzuidoctor.activity.StartOperationActivity;
import com.ipd.xiangzuidoctor.adapter.MainOrderAdapter;
import com.ipd.xiangzuidoctor.base.BaseFragment;
import com.ipd.xiangzuidoctor.bean.AnesthesiaListBean;
import com.ipd.xiangzuidoctor.bean.GetOrderBean;
import com.ipd.xiangzuidoctor.bean.IngOperationEndBean;
import com.ipd.xiangzuidoctor.bean.IsArrivalsBean;
import com.ipd.xiangzuidoctor.bean.IsOrderOperationEndBean;
import com.ipd.xiangzuidoctor.bean.OperationStartBean;
import com.ipd.xiangzuidoctor.bean.OrderCancelBean;
import com.ipd.xiangzuidoctor.bean.OrderDetailsBean;
import com.ipd.xiangzuidoctor.bean.OrderListBean;
import com.ipd.xiangzuidoctor.common.view.OneBtDialog;
import com.ipd.xiangzuidoctor.common.view.SpacesItemDecoration;
import com.ipd.xiangzuidoctor.common.view.TwoBtDialog;
import com.ipd.xiangzuidoctor.contract.OrderContract;
import com.ipd.xiangzuidoctor.presenter.OrderPresenter;
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
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzuidoctor.common.config.IConstants.IS_SUPPLEMENT_INFO;
import static com.ipd.xiangzuidoctor.common.config.IConstants.SIGN;
import static com.ipd.xiangzuidoctor.common.config.IConstants.USER_ID;
import static com.ipd.xiangzuidoctor.utils.DateUtils.StartTimeToEndTime;
import static com.ipd.xiangzuidoctor.utils.DateUtils.timedate;
import static com.ipd.xiangzuidoctor.utils.isClickUtil.isFastClick;

/**
 * Description ：订单
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/16.
 */
public class OrderFragment extends BaseFragment<OrderContract.View, OrderContract.Presenter> implements OrderContract.View {

    @BindView(R.id.ll_order)
    LinearLayoutCompat llOrder;
    @BindView(R.id.rv_order)
    RecyclerView rvOrder;
    @BindView(R.id.srl_order)
    SwipeRefreshLayout srlOrder;

    private List<OrderListBean.DataBean.OrderListsBean> orderList = new ArrayList<>();
    private MainOrderAdapter mainOrderAdapter;
    private int pageNum = 1;//页数
    private String orderType;//订单状态 0:待接单， 1:已接单， 2:进行中， 3:已完成
    private int removePosition;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    public OrderContract.Presenter createPresenter() {
        return new OrderPresenter(mContext);
    }

    @Override
    public OrderContract.View createView() {
        return this;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void init(View view) {
        Bundle args = getArguments();
        if (args != null) {
            orderType = args.getString("order_type");
        }
        if ("0".equals(orderType))
            llOrder.setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvOrder.setLayoutManager(layoutManager);
        rvOrder.setNestedScrollingEnabled(false);
        rvOrder.addItemDecoration(new SpacesItemDecoration(1, 50));
        rvOrder.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvOrder.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlOrder.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlOrder.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
                srlOrder.setRefreshing(false);
            }
        });
    }

    @Override
    public void initData() {
        orderList("", "", "");
    }

    private void orderList(String orderByColumn, String isAsc, String dist) {
        TreeMap<String, String> orderListMap = new TreeMap<>();
        orderListMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
        orderListMap.put("status", (Integer.parseInt(orderType) + 1) + "");
        orderListMap.put("pageNum", pageNum + "");
        orderListMap.put("pageSize", "10");
        orderListMap.put("orderByColumn", orderByColumn);
        orderListMap.put("isAsc", isAsc);
        orderListMap.put("dist", dist);
        orderListMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(orderListMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getOrderList(orderListMap, false, false);
    }

    @Override
    public void resultOrderList(OrderListBean data) {
        switch (data.getCode()) {
            case 200:
                if (data.getTotal() > 0) {
                    if (pageNum == 1) {
                        orderList.clear();
                        orderList.addAll(data.getData().getOrderList());
                        mainOrderAdapter = new MainOrderAdapter(orderList);
                        rvOrder.setAdapter(mainOrderAdapter);
                        mainOrderAdapter.bindToRecyclerView(rvOrder);
                        mainOrderAdapter.setEnableLoadMore(true);
                        mainOrderAdapter.openLoadAnimation();
                        mainOrderAdapter.disableLoadMoreIfNotFullPage();

                        mainOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                switch (view.getId()) {
                                    case R.id.cv_order_item:
                                        startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("order_status", orderList.get(position).getStatus()).putExtra("orderId", data.getData().getOrderList().get(position).getOrderId()));
                                        break;
                                    case R.id.stv_start_time:
                                        startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("order_status", orderList.get(position).getStatus()).putExtra("orderId", data.getData().getOrderList().get(position).getOrderId()));
                                        break;
                                    case R.id.stv_fee:
                                        startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("order_status", orderList.get(position).getStatus()).putExtra("orderId", data.getData().getOrderList().get(position).getOrderId()));
                                        break;
                                    case R.id.stv_name:
                                        startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("order_status", orderList.get(position).getStatus()).putExtra("orderId", data.getData().getOrderList().get(position).getOrderId()));
                                        break;
                                    case R.id.stv_address:
                                        startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("order_status", orderList.get(position).getStatus()).putExtra("orderId", data.getData().getOrderList().get(position).getOrderId()));
                                        break;
                                    case R.id.bt_first:
                                        switch (orderType) {
                                            case "0":
                                                if (isFastClick())
                                                    startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("order_status", orderList.get(position).getStatus()).putExtra("orderId", data.getData().getOrderList().get(position).getOrderId()));
                                                break;
                                            case "1":
                                                if (isFastClick())
                                                    new TwoBtDialog(getActivity(), "取消订单将扣除订单的20%作为违约金", "确认") {
                                                        @Override
                                                        public void confirm() {
                                                            removePosition = position;

                                                            TreeMap<String, String> orderCancelMap = new TreeMap<>();
                                                            orderCancelMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
                                                            orderCancelMap.put("orderId", orderList.get(position).getOrderId() + "");
                                                            orderCancelMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(orderCancelMap.toString().replaceAll(" ", "") + SIGN)));
                                                            getPresenter().getOrderCancel(orderCancelMap, false, false);
                                                        }
                                                    }.show();
                                                break;
                                            case "2":
                                                if (isFastClick())
                                                    startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("order_status", orderList.get(position).getStatus()).putExtra("orderId", data.getData().getOrderList().get(position).getOrderId()));
                                                break;
                                            case "3":
                                                if (isFastClick())
                                                    startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("order_status", orderList.get(position).getStatus()).putExtra("orderId", data.getData().getOrderList().get(position).getOrderId()));
                                                break;
                                        }
                                        break;
                                    case R.id.bt_second:
                                        switch (orderType) {
                                            case "0":
                                                if (isFastClick())
                                                    break;
                                            case "1":
                                                if (isFastClick())
                                                    startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("order_status", orderList.get(position).getStatus()).putExtra("orderId", data.getData().getOrderList().get(position).getOrderId()));
                                                break;
                                            case "2":
                                                if (isFastClick())
                                                    break;
                                        }
                                        break;
                                    case R.id.bt_third:
                                        switch (orderType) {
                                            case "0":
                                                if (isFastClick() && "1".equals(SPUtil.get(getContext(), IS_SUPPLEMENT_INFO, "") + ""))
                                                    new TwoBtDialog(getActivity(), "请先实名认证后才可以接单", "去认证") {
                                                        @Override
                                                        public void confirm() {
                                                            startActivity(new Intent(getContext(), AuthenticationActivity.class));
                                                        }
                                                    }.show();
                                                else if ("2".equals(SPUtil.get(getContext(), IS_SUPPLEMENT_INFO, "") + ""))
                                                    new OneBtDialog(getActivity(), "请提前做好多点执业备案") {
                                                        @Override
                                                        public void confirm() {
                                                            TreeMap<String, String> getOrderMap = new TreeMap<>();
                                                            getOrderMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
                                                            getOrderMap.put("orderId", orderList.get(position).getOrderId() + "");
                                                            getOrderMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(getOrderMap.toString().replaceAll(" ", "") + SIGN)));
                                                            getPresenter().getGetOrder(getOrderMap, false, false);
                                                        }
                                                    }.show();
                                                else
                                                    ToastUtil.showShortToast("请稍后点击！");
                                                break;
                                            case "1":
                                                if (isFastClick()) {
                                                    switch (orderList.get(position).getStatus()) {
                                                        case "2":
                                                            new TwoBtDialog(getActivity(), "是否确认已到达？", "温馨提示") {
                                                                @Override
                                                                public void confirm() {
                                                                    TreeMap<String, String> isArrivalsMap = new TreeMap<>();
                                                                    isArrivalsMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
                                                                    isArrivalsMap.put("orderId", orderList.get(position).getOrderId() + "");
                                                                    isArrivalsMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(isArrivalsMap.toString().replaceAll(" ", "") + SIGN)));
                                                                    getPresenter().getIsArrivals(isArrivalsMap, false, false);
                                                                }
                                                            }.show();
                                                            break;
                                                        case "8":
                                                            int endTime = Integer.parseInt(String.format("%010d", System.currentTimeMillis() / 1000));
                                                            String useTime = StartTimeToEndTime(orderList.get(position).getArriveTime(), timedate(endTime + ""), 1);
                                                            startActivity(new Intent(getContext(), StartOperationActivity.class).putExtra("title", "开始手术").putExtra("content", "麻醉器械、药品、急救设备及药品齐全").putExtra("orderId", data.getData().getOrderList().get(position).getOrderId()).putExtra("waitTime", useTime));
                                                            break;
                                                    }
                                                }
                                                break;
                                            case "2":
                                                if (isFastClick())
                                                    startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("order_status", orderList.get(position).getStatus()).putExtra("orderId", data.getData().getOrderList().get(position).getOrderId()));
//                                                startActivity(new Intent(getContext(), EndOperationActivity.class));
                                                break;
                                        }
                                        break;
                                }
                            }
                        });

                        //上拉加载
                        mainOrderAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                            @Override
                            public void onLoadMoreRequested() {
                                rvOrder.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        initData();
                                    }
                                }, 1000);
                            }
                        }, rvOrder);

                        if (data.getTotal() > 10) {
                            pageNum += 1;
                        } else {
                            mainOrderAdapter.loadMoreEnd();
                        }
                    } else {
                        if ((data.getTotal() - pageNum * 10) > 0) {
                            pageNum += 1;
                            mainOrderAdapter.addData(data.getData().getOrderList());
                            mainOrderAdapter.loadMoreComplete(); //完成本次
                        } else {
                            mainOrderAdapter.addData(data.getData().getOrderList());
                            mainOrderAdapter.loadMoreEnd(); //完成所有加载
                        }
                    }
                } else {
                    orderList.clear();
                    mainOrderAdapter = new MainOrderAdapter(orderList);
                    rvOrder.setAdapter(mainOrderAdapter);
                    mainOrderAdapter.loadMoreEnd(); //完成所有加载
                    mainOrderAdapter.setEmptyView(R.layout.null_data, rvOrder);
                }
                break;
            case 900:
                ToastUtil.showShortToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(getContext(), CaptchaLoginActivity.class));
                getActivity().finish();
                break;
        }
    }

    @Override
    public void resultOrderDetails(OrderDetailsBean data) {

    }

    @Override
    public void resultIsOrderOperationEnd(IsOrderOperationEndBean data) {

    }

    @Override
    public void resultIngOperationEnd(IngOperationEndBean data) {

    }

    @Override
    public void resultOperationStart(OperationStartBean data) {

    }

    @Override
    public void resultIsArrivals(IsArrivalsBean data) {
        ToastUtil.showLongToast(data.getMsg());
        switch (data.getCode()) {
            case 200:
                orderList("", "", "");
                break;
            case 900:
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(getContext(), CaptchaLoginActivity.class));
                getActivity().finish();
                break;
        }
    }

    @Override
    public void resultOrderCancel(OrderCancelBean data) {
        ToastUtil.showLongToast(data.getMsg());
        switch (data.getCode()) {
            case 200:
                orderList.remove(removePosition);
                mainOrderAdapter.notifyDataSetChanged();
                mainOrderAdapter.setEmptyView(R.layout.null_data, rvOrder);
                break;
            case 900:
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(getContext(), CaptchaLoginActivity.class));
                getActivity().finish();
                break;
        }
    }

    @Override
    public void resultGetOrder(GetOrderBean data) {
        ToastUtil.showLongToast(data.getMsg());
        switch (data.getCode()) {
            case 200:
                orderList("", "", "");
                break;
            case 900:
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(getContext(), CaptchaLoginActivity.class));
                getActivity().finish();
                break;
        }
    }

    @Override
    public void resultAnesthesiaList(AnesthesiaListBean data) {

    }

    @OnClick({R.id.stv_order_time, R.id.stv_order_region, R.id.stv_order_money})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stv_order_time:
                break;
            case R.id.stv_order_region:
                break;
            case R.id.stv_order_money:
                break;
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }
}
