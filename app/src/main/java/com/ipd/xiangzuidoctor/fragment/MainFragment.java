package com.ipd.xiangzuidoctor.fragment;

import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.activity.AuthenticationActivity;
import com.ipd.xiangzuidoctor.activity.CaptchaLoginActivity;
import com.ipd.xiangzuidoctor.activity.MainActivity;
import com.ipd.xiangzuidoctor.activity.OfflineActivitiesActivity;
import com.ipd.xiangzuidoctor.activity.OfflineActivitiesDetailsActivity;
import com.ipd.xiangzuidoctor.activity.OrderActivity;
import com.ipd.xiangzuidoctor.activity.OrderDetailsActivity;
import com.ipd.xiangzuidoctor.activity.SpecialColumnActivity;
import com.ipd.xiangzuidoctor.activity.StartOperationActivity;
import com.ipd.xiangzuidoctor.activity.WebViewActivity;
import com.ipd.xiangzuidoctor.adapter.MainGridAdapter;
import com.ipd.xiangzuidoctor.adapter.MainOrderAdapter;
import com.ipd.xiangzuidoctor.adapter.RecyclerViewBannerAdapter;
import com.ipd.xiangzuidoctor.adapter.TodayRecommendationAdapter;
import com.ipd.xiangzuidoctor.base.BaseFragment;
import com.ipd.xiangzuidoctor.bean.GetOrderBean;
import com.ipd.xiangzuidoctor.bean.HomeBean;
import com.ipd.xiangzuidoctor.bean.IsArrivalsBean;
import com.ipd.xiangzuidoctor.bean.OrderCancelBean;
import com.ipd.xiangzuidoctor.common.view.CustomLinearLayoutManager;
import com.ipd.xiangzuidoctor.common.view.GridSpacingItemDecoration;
import com.ipd.xiangzuidoctor.common.view.OneBtDialog;
import com.ipd.xiangzuidoctor.common.view.SimpleNoticeMFs;
import com.ipd.xiangzuidoctor.common.view.SpacesItemDecoration;
import com.ipd.xiangzuidoctor.common.view.TopView;
import com.ipd.xiangzuidoctor.common.view.TwoBtDialog;
import com.ipd.xiangzuidoctor.contract.HomeContract;
import com.ipd.xiangzuidoctor.presenter.HomePresenter;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;
import com.ipd.xiangzuidoctor.utils.MD5Utils;
import com.ipd.xiangzuidoctor.utils.SPUtil;
import com.ipd.xiangzuidoctor.utils.StringUtils;
import com.ipd.xiangzuidoctor.utils.ToastUtil;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xuexiang.xui.widget.banner.recycler.BannerLayout;
import com.xuexiang.xui.widget.textview.marqueen.MarqueeFactory;
import com.xuexiang.xui.widget.textview.marqueen.MarqueeView;

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

public class MainFragment extends BaseFragment<HomeContract.View, HomeContract.Presenter> implements HomeContract.View {

    @BindView(R.id.tv_main)
    TopView tvMain;
    @BindView(R.id.bl_banner)
    BannerLayout blBanner;
    @BindView(R.id.rv_grid_main)
    RecyclerView rvGridMain;
    @BindView(R.id.mv_horn)
    MarqueeView mvHorn;
    @BindView(R.id.rv_is_more_order)
    RecyclerView rvIsMoreOrder;
    @BindView(R.id.rv_wait_more_order)
    RecyclerView rvWaitMoreOrder;
    @BindView(R.id.rv_today_recommendation)
    RecyclerView rvTodayRecommendation;
    @BindView(R.id.tv_authentication)
    AppCompatTextView tvAuthentication;
    @BindView(R.id.ll_is_order)
    LinearLayout llIsOrder;
    @BindView(R.id.ll_wait_order)
    LinearLayout llWaitOrder;
    @BindView(R.id.srl_main)
    SwipeRefreshLayout srlMain;

    private List<CharSequence> hornList = new ArrayList<>();//广播
    private List<Integer> itr = new ArrayList<>();//菜单
    private RecyclerViewBannerAdapter recyclerViewBannerAdapter;
    private MainGridAdapter mainGridAdapter;
    private MainOrderAdapter mainOrderAdapter;
    private MainOrderAdapter mainNoOrderAdapter;
    private TodayRecommendationAdapter todayRecommendationAdapter;
    private List<HomeBean.DataBean.PictureListBean> listsss = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public HomeContract.Presenter createPresenter() {
        return new HomePresenter(mContext);
    }

    @Override
    public HomeContract.View createView() {
        return this;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden)
            ImmersionBar.with(this).statusBarDarkFont(true).init();
    }

    @Override
    public void init(View view) {
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(getActivity(), tvMain);

        tvAuthentication.setVisibility(View.GONE);

        srlMain.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色

        //菜单
        GridLayoutManager NotUseList = new GridLayoutManager(getContext(), 4);
        rvGridMain.setLayoutManager(NotUseList);
        rvGridMain.addItemDecoration(new GridSpacingItemDecoration(4, 30, false));
        rvGridMain.setNestedScrollingEnabled(false);
        rvGridMain.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvGridMain.setItemAnimator(new DefaultItemAnimator()); //默认动画

        for (int i = 0; i < 4; i++) {
            itr.add(i);
        }
        rvGridMain.setAdapter(mainGridAdapter = new MainGridAdapter(itr));

        //已接订单
        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvIsMoreOrder.setLayoutManager(layoutManager);
        rvIsMoreOrder.setNestedScrollingEnabled(false);
        rvIsMoreOrder.addItemDecoration(new SpacesItemDecoration(1, 50));
        rvIsMoreOrder.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvIsMoreOrder.setItemAnimator(new DefaultItemAnimator());//加载动画

        //待接订单
        CustomLinearLayoutManager layoutManager1 = new CustomLinearLayoutManager(getContext());
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvWaitMoreOrder.setLayoutManager(layoutManager1);
        rvWaitMoreOrder.setNestedScrollingEnabled(false);
        rvWaitMoreOrder.addItemDecoration(new SpacesItemDecoration(1, 50));
        rvWaitMoreOrder.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvWaitMoreOrder.setItemAnimator(new DefaultItemAnimator());//加载动画

        //今日推荐
        CustomLinearLayoutManager layoutManager2 = new CustomLinearLayoutManager(getContext());
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvTodayRecommendation.setLayoutManager(layoutManager2);
        rvTodayRecommendation.setNestedScrollingEnabled(false);
        rvTodayRecommendation.addItemDecoration(new SpacesItemDecoration(1, 50));
        rvTodayRecommendation.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvTodayRecommendation.setItemAnimator(new DefaultItemAnimator());//加载动画

        recyclerViewBannerAdapter = new RecyclerViewBannerAdapter(listsss);
        blBanner.setAdapter(recyclerViewBannerAdapter);
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlMain.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
                srlMain.setRefreshing(false);
            }
        });

        mainGridAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        //订单
                        if ("2".equals(SPUtil.get(getContext(), IS_SUPPLEMENT_INFO, "")))
                            startActivity(new Intent(getContext(), OrderActivity.class));
                        else
                            ToastUtil.showShortToast("您的身份尚未认证,请您先去认证！");
                        break;
                    case 1:
                        //医学专栏
                        startActivity(new Intent(getContext(), SpecialColumnActivity.class));
                        break;
                    case 2:
                        //线下活动
                        startActivity(new Intent(getContext(), OfflineActivitiesActivity.class));
                        break;
                    case 3:
                        //多点执业
                        startActivity(new Intent(getContext(), WebViewActivity.class).putExtra("h5Type", 4));
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
        TreeMap<String, String> homeMap = new TreeMap<>();
        homeMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
        homeMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(homeMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getHome(homeMap, true, false);
    }

    @OnClick({R.id.tv_authentication, R.id.bt_is_more_order, R.id.bt_wait_more_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_authentication:
                startActivity(new Intent(getContext(), AuthenticationActivity.class));
                break;
            case R.id.bt_is_more_order:
                startActivity(new Intent(getContext(), OrderActivity.class).putExtra("current_position", 1));
                break;
            case R.id.bt_wait_more_order:
                startActivity(new Intent(getContext(), OrderActivity.class));
                break;
        }
    }

    @Override
    public void resultHome(HomeBean data) {
        switch (data.getCode()) {
            case 200:
                //轮播
                recyclerViewBannerAdapter.addData(data.getData().getPictureList());
                blBanner.setAdapter(recyclerViewBannerAdapter);

                recyclerViewBannerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (data.getData().getPictureList().get(position).getType()) {
                            case "1"://无链接
                                break;
                            case "2"://有链接
                                startActivity(new Intent(getContext(), WebViewActivity.class).putExtra("h5Type", 5).putExtra("h5Url", data.getData().getPictureList().get(position).getUrl()));
                                break;
                            case "3"://文本内容
                                startActivity(new Intent(getContext(), WebViewActivity.class).putExtra("h5Type", 6).putExtra("h5_url", data.getData().getPictureList().get(position).getContent()));
                                break;
                        }
                    }
                });

                //大喇叭
                for (int i = 0; i < data.getData().getInfoList().size(); i++) {
                    hornList.add(Html.fromHtml("医生" + data.getData().getInfoList().get(i).getNickname() + "已完成订单<font color=\"#000000\">" + data.getData().getInfoList().get(i).getSurgeryName() + "</font>费用<font color=\"#FF5555\">¥" + data.getData().getInfoList().get(i).getOrderCost() + "元</font>"));
                }
                MarqueeFactory<TextView, CharSequence> marqueeFactory = new SimpleNoticeMFs(getContext());
                marqueeFactory.setData(hornList);
//        mvHorn.setAnimInAndOut(R.anim.marquee_top_in, R.anim.marquee_bottom_out); //向下滚动
                mvHorn.setMarqueeFactory(marqueeFactory);
                mvHorn.startFlipping();

                //已接订单
                llIsOrder.setVisibility(data.getData().getAlreadyList().size() > 0 ? View.VISIBLE : View.GONE);
                rvIsMoreOrder.setAdapter(mainOrderAdapter = new MainOrderAdapter(data.getData().getAlreadyList()));
                mainOrderAdapter.bindToRecyclerView(rvIsMoreOrder);
                mainOrderAdapter.openLoadAnimation();

                mainOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()) {
                            case R.id.cv_order_item:
                            case R.id.stv_start_time:
                            case R.id.stv_fee:
                            case R.id.stv_name:
                            case R.id.stv_address:
                                if ("2".equals(SPUtil.get(getContext(), IS_SUPPLEMENT_INFO, "")))
                                    startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("order_status", data.getData().getAlreadyList().get(position).getStatus()).putExtra("orderId", data.getData().getAlreadyList().get(position).getOrderId()));
                                else
                                    ToastUtil.showShortToast("您的身份尚未认证,请您先去认证！");
                                break;
                            case R.id.bt_first:
                                switch (data.getData().getAlreadyList().get(position).getStatus()) {
                                    case "1":
                                        if (isFastClick()) {
                                            if ("2".equals(SPUtil.get(getContext(), IS_SUPPLEMENT_INFO, "")))
                                                startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("order_status", data.getData().getAlreadyList().get(position).getStatus()).putExtra("orderId", data.getData().getAlreadyList().get(position).getOrderId()));
                                            else
                                                ToastUtil.showShortToast("您的身份尚未认证,请您先去认证！");
                                        }
                                        break;
                                    case "2":
                                        if (isFastClick())
                                            new TwoBtDialog(getActivity(), "取消订单将扣除订单的20%作为违约金", "确认") {
                                                @Override
                                                public void confirm() {
                                                    TreeMap<String, String> orderCancelMap = new TreeMap<>();
                                                    orderCancelMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
                                                    orderCancelMap.put("orderId", data.getData().getAlreadyList().get(position).getOrderId() + "");
                                                    orderCancelMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(orderCancelMap.toString().replaceAll(" ", "") + SIGN)));
                                                    getPresenter().getOrderCancel(orderCancelMap, false, false);
                                                }
                                            }.show();
                                        break;
                                    case "3":
                                    case "4":
                                    case "5":
                                    case "6":
                                    case "7":
                                    case "8":
                                        if (isFastClick()) {
                                            if ("2".equals(SPUtil.get(getContext(), IS_SUPPLEMENT_INFO, "")))
                                                startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("order_status", data.getData().getAlreadyList().get(position).getStatus()).putExtra("orderId", data.getData().getAlreadyList().get(position).getOrderId()));
                                            else
                                                ToastUtil.showShortToast("您的身份尚未认证,请您先去认证！");
                                        }
                                        break;
                                }
                                break;
                            case R.id.bt_second:
                                switch (data.getData().getAlreadyList().get(position).getStatus()) {
                                    case "2":
                                    case "8":
                                        if (isFastClick()) {
                                            if ("2".equals(SPUtil.get(getContext(), IS_SUPPLEMENT_INFO, "")))
                                                startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("order_status", data.getData().getAlreadyList().get(position).getStatus()).putExtra("orderId", data.getData().getAlreadyList().get(position).getOrderId()));
                                            else
                                                ToastUtil.showShortToast("您的身份尚未认证,请您先去认证！");
                                        }
                                        break;
                                }
                                break;
                            case R.id.bt_third:
                                switch (data.getData().getAlreadyList().get(position).getStatus()) {
                                    case "1":
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
                                                    getOrderMap.put("orderId", data.getData().getAlreadyList().get(position).getOrderId() + "");
                                                    getOrderMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(getOrderMap.toString().replaceAll(" ", "") + SIGN)));
                                                    getPresenter().getGetOrder(getOrderMap, false, false);
                                                }
                                            }.show();
                                        else
                                            ToastUtil.showShortToast("请稍后点击！");
                                        break;
                                    case "2":
                                        if (isFastClick()) {
                                            new TwoBtDialog(getActivity(), "是否确认已到达？", "温馨提示") {
                                                @Override
                                                public void confirm() {
                                                    TreeMap<String, String> isArrivalsMap = new TreeMap<>();
                                                    isArrivalsMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
                                                    isArrivalsMap.put("orderId", data.getData().getAlreadyList().get(position).getOrderId() + "");
                                                    isArrivalsMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(isArrivalsMap.toString().replaceAll(" ", "") + SIGN)));
                                                    getPresenter().getIsArrivals(isArrivalsMap, false, false);
                                                }
                                            }.show();
                                        }
                                        break;
                                    case "3":
                                        if (isFastClick()) {
                                            if ("2".equals(SPUtil.get(getContext(), IS_SUPPLEMENT_INFO, "")))
                                                startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("order_status", data.getData().getAlreadyList().get(position).getStatus()).putExtra("orderId", data.getData().getAlreadyList().get(position).getOrderId()));
                                            else
                                                ToastUtil.showShortToast("您的身份尚未认证,请您先去认证！");
                                        }
                                        break;
                                    case "8":
                                        int endTime = Integer.parseInt(String.format("%010d", System.currentTimeMillis() / 1000));
                                        String useTime = StartTimeToEndTime(data.getData().getAlreadyList().get(position).getArriveTime(), timedate(endTime + ""), 1);
                                        startActivity(new Intent(getContext(), StartOperationActivity.class).putExtra("title", "开始手术").putExtra("content", "麻醉器械、药品、急救设备及药品齐全").putExtra("orderId", data.getData().getAlreadyList().get(position).getOrderId()).putExtra("waitTime", useTime));
                                        break;
                                }
                                break;
                        }
                    }
                });

                //待接订单
                llWaitOrder.setVisibility(data.getData().getStayList().size() > 0 ? View.VISIBLE : View.GONE);
                rvWaitMoreOrder.setAdapter(mainNoOrderAdapter = new MainOrderAdapter(data.getData().getStayList()));
                mainNoOrderAdapter.bindToRecyclerView(rvWaitMoreOrder);
                mainNoOrderAdapter.openLoadAnimation();

                mainNoOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()) {
                            case R.id.cv_order_item:
                            case R.id.stv_start_time:
                            case R.id.stv_fee:
                            case R.id.stv_name:
                            case R.id.stv_address:
                                if ("2".equals(SPUtil.get(getContext(), IS_SUPPLEMENT_INFO, "")))
                                    startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("order_status", data.getData().getStayList().get(position).getStatus()).putExtra("orderId", data.getData().getStayList().get(position).getOrderId()));
                                else
                                    ToastUtil.showShortToast("您的身份尚未认证,请您先去认证！");
                                break;
                            case R.id.bt_first:
                                switch (data.getData().getStayList().get(position).getStatus()) {
                                    case "1":
                                        if (isFastClick()) {
                                            if ("2".equals(SPUtil.get(getContext(), IS_SUPPLEMENT_INFO, "")))
                                                startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("order_status", data.getData().getStayList().get(position).getStatus()).putExtra("orderId", data.getData().getStayList().get(position).getOrderId()));
                                            else
                                                ToastUtil.showShortToast("您的身份尚未认证,请您先去认证！");
                                        }
                                        break;
                                    case "2":
                                        if (isFastClick())
                                            new TwoBtDialog(getActivity(), "取消订单将扣除订单的20%作为违约金", "确认") {
                                                @Override
                                                public void confirm() {
                                                    TreeMap<String, String> orderCancelMap = new TreeMap<>();
                                                    orderCancelMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
                                                    orderCancelMap.put("orderId", data.getData().getStayList().get(position).getOrderId() + "");
                                                    orderCancelMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(orderCancelMap.toString().replaceAll(" ", "") + SIGN)));
                                                    getPresenter().getOrderCancel(orderCancelMap, false, false);
                                                }
                                            }.show();
                                        break;
                                    case "3":
                                    case "4":
                                    case "5":
                                    case "6":
                                    case "7":
                                    case "8":
                                        if (isFastClick()) {
                                            if ("2".equals(SPUtil.get(getContext(), IS_SUPPLEMENT_INFO, "")))
                                                startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("order_status", data.getData().getStayList().get(position).getStatus()).putExtra("orderId", data.getData().getStayList().get(position).getOrderId()));
                                            else
                                                ToastUtil.showShortToast("您的身份尚未认证,请您先去认证！");
                                        }
                                        break;
                                }
                                break;
                            case R.id.bt_second:
                                switch (data.getData().getStayList().get(position).getStatus()) {
                                    case "2":
                                    case "8":
                                        if (isFastClick()) {
                                            if ("2".equals(SPUtil.get(getContext(), IS_SUPPLEMENT_INFO, "")))
                                                startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("order_status", data.getData().getStayList().get(position).getStatus()).putExtra("orderId", data.getData().getStayList().get(position).getOrderId()));
                                            else
                                                ToastUtil.showShortToast("您的身份尚未认证,请您先去认证！");
                                        }
                                        break;
                                }
                                break;
                            case R.id.bt_third:
                                switch (data.getData().getStayList().get(position).getStatus()) {
                                    case "1":
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
                                                    getOrderMap.put("orderId", data.getData().getStayList().get(position).getOrderId() + "");
                                                    getOrderMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(getOrderMap.toString().replaceAll(" ", "") + SIGN)));
                                                    getPresenter().getGetOrder(getOrderMap, false, false);
                                                }
                                            }.show();
                                        else
                                            ToastUtil.showShortToast("请稍后点击！");
                                        break;
                                    case "2":
                                        if (isFastClick()) {
                                            new TwoBtDialog(getActivity(), "是否确认已到达？", "温馨提示") {
                                                @Override
                                                public void confirm() {
                                                    TreeMap<String, String> isArrivalsMap = new TreeMap<>();
                                                    isArrivalsMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
                                                    isArrivalsMap.put("orderId", data.getData().getStayList().get(position).getOrderId() + "");
                                                    isArrivalsMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(isArrivalsMap.toString().replaceAll(" ", "") + SIGN)));
                                                    getPresenter().getIsArrivals(isArrivalsMap, false, false);
                                                }
                                            }.show();
                                        }
                                        break;
                                    case "3":
                                        if (isFastClick()) {
                                            if ("2".equals(SPUtil.get(getContext(), IS_SUPPLEMENT_INFO, "")))
                                                startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("order_status", data.getData().getStayList().get(position).getStatus()).putExtra("orderId", data.getData().getStayList().get(position).getOrderId()));
                                            else
                                                ToastUtil.showShortToast("您的身份尚未认证,请您先去认证！");
                                        }
                                        break;
                                    case "8":
                                        int endTime = Integer.parseInt(String.format("%010d", System.currentTimeMillis() / 1000));
                                        String useTime = StartTimeToEndTime(data.getData().getStayList().get(position).getArriveTime(), timedate(endTime + ""), 1);
                                        startActivity(new Intent(getContext(), StartOperationActivity.class).putExtra("title", "开始手术").putExtra("content", "麻醉器械、药品、急救设备及药品齐全").putExtra("orderId", data.getData().getStayList().get(position).getOrderId()).putExtra("waitTime", useTime));
                                        break;
                                }
                                break;
                        }
                    }
                });

                //今日推荐
                rvTodayRecommendation.setAdapter(todayRecommendationAdapter = new TodayRecommendationAdapter(data.getData().getActivityList()));
                todayRecommendationAdapter.bindToRecyclerView(rvTodayRecommendation);
                todayRecommendationAdapter.openLoadAnimation();

                todayRecommendationAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        startActivity(new Intent(getContext(), OfflineActivitiesDetailsActivity.class).putExtra("offline_activities_type", "0").putExtra("activityId", data.getData().getActivityList().get(position).getActivityId()));
                    }
                });
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
    public void resultIsArrivals(IsArrivalsBean data) {
        ToastUtil.showLongToast(data.getMsg());
        switch (data.getCode()) {
            case 200:
                initData();
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
                initData();
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
                initData();
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
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }
}