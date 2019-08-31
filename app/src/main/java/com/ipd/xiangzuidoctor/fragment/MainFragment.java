package com.ipd.xiangzuidoctor.fragment;

import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.activity.AuthenticationActivity;
import com.ipd.xiangzuidoctor.activity.OfflineActivitiesActivity;
import com.ipd.xiangzuidoctor.activity.OrderActivity;
import com.ipd.xiangzuidoctor.activity.OrderDetailsActivity;
import com.ipd.xiangzuidoctor.activity.SpecialColumnActivity;
import com.ipd.xiangzuidoctor.adapter.MainGridAdapter;
import com.ipd.xiangzuidoctor.adapter.MainOrderAdapter;
import com.ipd.xiangzuidoctor.adapter.RecyclerViewBannerAdapter;
import com.ipd.xiangzuidoctor.adapter.TodayRecommendationAdapter;
import com.ipd.xiangzuidoctor.base.BaseFragment;
import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.bean.TestMultiItemEntityBean;
import com.ipd.xiangzuidoctor.common.view.CustomLinearLayoutManager;
import com.ipd.xiangzuidoctor.common.view.GridSpacingItemDecoration;
import com.ipd.xiangzuidoctor.common.view.OneBtDialog;
import com.ipd.xiangzuidoctor.common.view.SimpleNoticeMFs;
import com.ipd.xiangzuidoctor.common.view.SpacesItemDecoration;
import com.ipd.xiangzuidoctor.common.view.TopView;
import com.ipd.xiangzuidoctor.common.view.TwoBtDialog;
import com.ipd.xiangzuidoctor.utils.SPUtil;
import com.xuexiang.xui.widget.banner.recycler.BannerLayout;
import com.xuexiang.xui.widget.textview.marqueen.MarqueeFactory;
import com.xuexiang.xui.widget.textview.marqueen.MarqueeView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ipd.xiangzuidoctor.common.config.IConstants.IS_SUPPLEMENT_INFO;
import static com.ipd.xiangzuidoctor.utils.StringUtils.isEmpty;
import static com.ipd.xiangzuidoctor.utils.isClickUtil.isFastClick;

public class MainFragment extends BaseFragment {

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

    private List<TestMultiItemEntityBean> str = new ArrayList<>();//轮播
    private List<Integer> itr = new ArrayList<>();//菜单
    private List<TestMultiItemEntityBean> isOrderList = new ArrayList<>();//已接订单
    private List<TestMultiItemEntityBean> waitOrderList = new ArrayList<>();//待接订单
    private List<TestMultiItemEntityBean> todayRecommendationList = new ArrayList<>();//今日推荐
    private MainGridAdapter mainGridAdapter;
    private MainOrderAdapter mainOrderAdapter;
    private MainOrderAdapter mainNoOrderAdapter;
    private TodayRecommendationAdapter todayRecommendationAdapter;
    final List<CharSequence> datas = Arrays.asList(Html.fromHtml("医生李**已完成订单<font color=\"#000000\">阑尾切除术</font>费用<font color=\"#FF5555\">¥" + 300 + "元</font>"), Html.fromHtml("医生李**已完成订单<font color=\"#000000\">阑尾切除术</font>费用<font color=\"#FF5555\">¥" + 300 + "元</font>"));

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
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
    public void onHiddenChanged(boolean hidden) {
        if (!hidden)
            ImmersionBar.with(this).statusBarDarkFont(true).init();
    }

    @Override
    public void init(View view) {
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(getActivity(), tvMain);

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

        for (int i = 0; i < 2; i++) {
            TestMultiItemEntityBean testData = new TestMultiItemEntityBean();
            testData.setOrderType("0");
            isOrderList.add(testData);
        }
        rvIsMoreOrder.setAdapter(mainOrderAdapter = new MainOrderAdapter(isOrderList));
        mainOrderAdapter.bindToRecyclerView(rvIsMoreOrder);
        mainOrderAdapter.openLoadAnimation();

        //待接订单
        CustomLinearLayoutManager layoutManager1 = new CustomLinearLayoutManager(getContext());
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvWaitMoreOrder.setLayoutManager(layoutManager1);
        rvWaitMoreOrder.setNestedScrollingEnabled(false);
        rvWaitMoreOrder.addItemDecoration(new SpacesItemDecoration(1, 50));
        rvWaitMoreOrder.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvWaitMoreOrder.setItemAnimator(new DefaultItemAnimator());//加载动画

        for (int i = 0; i < 2; i++) {
            TestMultiItemEntityBean testData = new TestMultiItemEntityBean();
            testData.setOrderType("1");
            waitOrderList.add(testData);
        }
        rvWaitMoreOrder.setAdapter(mainNoOrderAdapter = new MainOrderAdapter(waitOrderList));
        mainNoOrderAdapter.bindToRecyclerView(rvWaitMoreOrder);
        mainNoOrderAdapter.openLoadAnimation();

        //今日推荐
        CustomLinearLayoutManager layoutManager2 = new CustomLinearLayoutManager(getContext());
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvTodayRecommendation.setLayoutManager(layoutManager2);
        rvTodayRecommendation.setNestedScrollingEnabled(false);
        rvTodayRecommendation.addItemDecoration(new SpacesItemDecoration(1, 50));
        rvTodayRecommendation.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvTodayRecommendation.setItemAnimator(new DefaultItemAnimator());//加载动画

        for (int i = 0; i < 2; i++) {
            TestMultiItemEntityBean testData = new TestMultiItemEntityBean();
            todayRecommendationList.add(testData);
        }
        rvTodayRecommendation.setAdapter(todayRecommendationAdapter = new TodayRecommendationAdapter(todayRecommendationList));
        todayRecommendationAdapter.bindToRecyclerView(rvTodayRecommendation);
        todayRecommendationAdapter.openLoadAnimation();
    }

    @Override
    public void initListener() {
        mainGridAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        //订单
                        startActivity(new Intent(getContext(), OrderActivity.class));
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
                        break;
                }
            }
        });

        mainOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.cv_order_item:
                        startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("surgery_type", 1).putExtra("order_type", "1"));
                        break;
                    case R.id.stv_start_time:
                        startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("surgery_type", 1).putExtra("order_type", "1"));
                        break;
                    case R.id.stv_fee:
                        startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("surgery_type", 1).putExtra("order_type", "1"));
                        break;
                    case R.id.stv_name:
                        startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("surgery_type", 1).putExtra("order_type", "1"));
                        break;
                    case R.id.stv_address:
                        startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("surgery_type", 1).putExtra("order_type", "1"));
                        break;
                    case R.id.bt_first:
                        if (isFastClick())
                            startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("surgery_type", 1).putExtra("order_type", "1"));
                        break;
                    case R.id.bt_second:
                        break;
                    case R.id.bt_third:
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
                }
            }
        });

        mainNoOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.cv_order_item:
                        startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("surgery_type", 1).putExtra("order_type", "0"));
                        break;
                    case R.id.stv_start_time:
                        startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("surgery_type", 1).putExtra("order_type", "0"));
                        break;
                    case R.id.stv_fee:
                        startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("surgery_type", 1).putExtra("order_type", "0"));
                        break;
                    case R.id.stv_name:
                        startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("surgery_type", 1).putExtra("order_type", "0"));
                        break;
                    case R.id.stv_address:
                        startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("surgery_type", 1).putExtra("order_type", "0"));
                        break;
                    case R.id.bt_first:
                        if (isFastClick())
                            startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("surgery_type", 1).putExtra("order_type", "0"));
                        break;
                    case R.id.bt_second:
                        break;
                    case R.id.bt_third:
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
                }
            }
        });

        todayRecommendationAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    public void initData() {
        //轮播
        for (int i = 0; i < 3; i++) {
            str.add(new TestMultiItemEntityBean());
        }
        blBanner.setAdapter(new RecyclerViewBannerAdapter(str));

        //大喇叭
        MarqueeFactory<TextView, CharSequence> marqueeFactory = new SimpleNoticeMFs(getContext());
        marqueeFactory.setData(datas);
//        mvHorn.setAnimInAndOut(R.anim.marquee_top_in, R.anim.marquee_bottom_out);
        mvHorn.setMarqueeFactory(marqueeFactory);
        mvHorn.startFlipping();
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
}