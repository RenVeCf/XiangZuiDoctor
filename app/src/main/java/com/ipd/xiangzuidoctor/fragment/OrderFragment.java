package com.ipd.xiangzuidoctor.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.activity.AuthenticationActivity;
import com.ipd.xiangzuidoctor.activity.EndOperationActivity;
import com.ipd.xiangzuidoctor.activity.OrderDetailsActivity;
import com.ipd.xiangzuidoctor.activity.StartOperationActivity;
import com.ipd.xiangzuidoctor.adapter.MainOrderAdapter;
import com.ipd.xiangzuidoctor.base.BaseFragment;
import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.bean.TestMultiItemEntityBean;
import com.ipd.xiangzuidoctor.common.view.OneBtDialog;
import com.ipd.xiangzuidoctor.common.view.SpacesItemDecoration;
import com.ipd.xiangzuidoctor.common.view.TwoBtDialog;
import com.ipd.xiangzuidoctor.utils.SPUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ipd.xiangzuidoctor.common.config.IConstants.IS_SUPPLEMENT_INFO;
import static com.ipd.xiangzuidoctor.utils.StringUtils.isEmpty;
import static com.ipd.xiangzuidoctor.utils.isClickUtil.isFastClick;

/**
 * Description ：订单
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/16.
 */
public class OrderFragment extends BaseFragment {
    @BindView(R.id.stv_order_time)
    SuperTextView stvOrderTime;
    @BindView(R.id.stv_order_region)
    SuperTextView stvOrderRegion;
    @BindView(R.id.stv_order_money)
    SuperTextView stvOrderMoney;
    @BindView(R.id.rv_order)
    RecyclerView rvOrder;
    @BindView(R.id.srl_order)
    SwipeRefreshLayout srlOrder;

    private List<TestMultiItemEntityBean> str1 = new ArrayList<>();
    private MainOrderAdapter mainOrderAdapter;
    private int pageNum = 1;//页数
    private String orderType;//订单状态 0:待接单， 1:已接单， 2:进行中， 3:已完成

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void init(View view) {
        Bundle args = getArguments();
        if (args != null) {
            orderType = args.getString("order_type");
        }

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
        if (5 > 0) {//TODO 有接口后5更换总条数
            if (pageNum == 1) {
                str1.clear();
                for (int i = 0; i < 5; i++) {//TODO 有接口后去掉
                    TestMultiItemEntityBean testData = new TestMultiItemEntityBean();
                    testData.setAddFee(true);
                    testData.setOrderType(orderType);
                    str1.add(testData);
                }
//                str1.addAll(data.getData().getMessageList());//TODO 有接口后打开
                mainOrderAdapter = new MainOrderAdapter(str1);
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
                                startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("surgery_type", 1).putExtra("order_type", orderType));
                                break;
                            case R.id.stv_start_time:
                                startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("surgery_type", 1).putExtra("order_type", orderType));
                                break;
                            case R.id.stv_fee:
                                startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("surgery_type", 1).putExtra("order_type", orderType));
                                break;
                            case R.id.stv_name:
                                startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("surgery_type", 1).putExtra("order_type", orderType));
                                break;
                            case R.id.stv_address:
                                startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("surgery_type", 1).putExtra("order_type", orderType));
                                break;
                            case R.id.bt_first:
                                switch (str1.get(position).getOrderType()) {
                                    case "0":
                                        if (isFastClick())
                                            startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("surgery_type", 1).putExtra("order_type", orderType));
                                        break;
                                    case "1":
                                        if (isFastClick())
                                            new TwoBtDialog(getActivity(), "取消订单将扣除订单的20%作为违约金", "确认") {
                                                @Override
                                                public void confirm() {
                                                    str1.remove(position);
                                                    mainOrderAdapter.notifyDataSetChanged();
                                                    mainOrderAdapter.setEmptyView(R.layout.null_data, rvOrder);
                                                }
                                            }.show();
                                        break;
                                    case "2":
                                        if (isFastClick())
                                            startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("surgery_type", 1).putExtra("order_type", orderType));
                                        break;
                                    case "3":
                                        if (isFastClick())
                                            startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("surgery_type", 1).putExtra("order_type", orderType));
                                        break;
                                }
                                break;
                            case R.id.bt_second:
                                switch (str1.get(position).getOrderType()) {
                                    case "0":
                                        if (isFastClick())
                                            break;
                                    case "1":
                                        if (isFastClick())
                                            startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("surgery_type", 1).putExtra("order_type", orderType));
                                        break;
                                    case "2":
                                        if (isFastClick())
                                            break;
                                }
                                break;
                            case R.id.bt_third:
                                switch (str1.get(position).getOrderType()) {
                                    case "0":
                                        if (isFastClick() && isEmpty(SPUtil.get(getContext(), IS_SUPPLEMENT_INFO, "") + ""))
                                            new TwoBtDialog(getActivity(), "请先实名认证后才可以接单", "去认证") {
                                                @Override
                                                public void confirm() {
                                                    startActivity(new Intent(getContext(), AuthenticationActivity.class));
                                                }
                                            }.show();
                                        else if (isFastClick())
                                            new OneBtDialog(getActivity(), "请提前做好多点执业备案") {
                                                @Override
                                                public void confirm() {
                                                }
                                            }.show();
                                        break;
                                    case "1":
                                        if (isFastClick())
                                            startActivity(new Intent(getContext(), StartOperationActivity.class).putExtra("title", "开始手术").putExtra("content", "麻醉器械、药品、急救设备及药品齐全"));
                                        break;
                                    case "2":
                                        if (isFastClick())
                                            startActivity(new Intent(getContext(), EndOperationActivity.class));
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

                if (5 > 10) {//TODO 有接口后5更换list.size
                    pageNum += 1;
                } else {
                    mainOrderAdapter.loadMoreEnd();
                }
            } else {
                if ((5 - pageNum * 10) > 0) {//TODO 有接口后5更换list.size
                    pageNum += 1;
//                    mainOrderAdapter.addData(data.getData().getMessageList());//TODO 有接口后打开
                    mainOrderAdapter.loadMoreComplete(); //完成本次
                } else {
//                    mainOrderAdapter.addData(data.getData().getMessageList());//TODO 有接口后打开
                    mainOrderAdapter.loadMoreEnd(); //完成所有加载
                }
            }
        } else {
            str1.clear();
            mainOrderAdapter = new MainOrderAdapter(str1);
            rvOrder.setAdapter(mainOrderAdapter);
            mainOrderAdapter.loadMoreEnd(); //完成所有加载
            mainOrderAdapter.setEmptyView(R.layout.null_data, rvOrder);
        }
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
}
