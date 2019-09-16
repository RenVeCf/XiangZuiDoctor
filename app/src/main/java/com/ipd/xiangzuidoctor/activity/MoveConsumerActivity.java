package com.ipd.xiangzuidoctor.activity;

import android.content.Intent;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.adapter.FeeRecordAdapter;
import com.ipd.xiangzuidoctor.base.BaseActivity;
import com.ipd.xiangzuidoctor.bean.FeeRecordBean;
import com.ipd.xiangzuidoctor.common.view.TopView;
import com.ipd.xiangzuidoctor.contract.FeeRecordContract;
import com.ipd.xiangzuidoctor.presenter.FeeRecordPresenter;
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
 * Description ：更多收支明细
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/1.
 */
public class MoveConsumerActivity extends BaseActivity<FeeRecordContract.View, FeeRecordContract.Presenter> implements FeeRecordContract.View {

    @BindView(R.id.tv_move_consumer)
    TopView tvMoveConsumer;
    @BindView(R.id.rv_move_consumer)
    RecyclerView rvMoveConsumer;
    @BindView(R.id.srl_move_consumer)
    SwipeRefreshLayout srlMoveConsumer;

    private List<FeeRecordBean.DataBean.RecordListBean> recordList = new ArrayList<>();
    private FeeRecordAdapter feeRecordAdapter;
    private int pageNum = 1;//页数

    @Override
    public int getLayoutId() {
        return R.layout.activity_move_consumer;
    }

    @Override
    public FeeRecordContract.Presenter createPresenter() {
        return new FeeRecordPresenter(this);
    }

    @Override
    public FeeRecordContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvMoveConsumer);

        // 设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvMoveConsumer.setLayoutManager(layoutManager);
        rvMoveConsumer.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvMoveConsumer.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlMoveConsumer.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色
    }

    @Override
    public void initData() {
        TreeMap<String, String> feeRecordMap = new TreeMap<>();
        feeRecordMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        feeRecordMap.put("balanceType", "");
        feeRecordMap.put("pageNum", pageNum + "");
        feeRecordMap.put("pageSize", "10");
        feeRecordMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(feeRecordMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getFeeRecord(feeRecordMap, false, false);
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlMoveConsumer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
                srlMoveConsumer.setRefreshing(false);
            }
        });
    }

    @Override
    public void resultFeeRecord(FeeRecordBean data) {
        switch (data.getCode()) {
            case 200:
                if (data.getTotal() > 0) {
                    if (pageNum == 1) {
                        recordList.clear();
                        recordList.addAll(data.getData().getRecordList());
                        feeRecordAdapter = new FeeRecordAdapter(recordList);
                        rvMoveConsumer.setAdapter(feeRecordAdapter);
                        feeRecordAdapter.bindToRecyclerView(rvMoveConsumer);
                        feeRecordAdapter.setEnableLoadMore(true);
                        feeRecordAdapter.openLoadAnimation();
                        feeRecordAdapter.disableLoadMoreIfNotFullPage();

                        //上拉加载
                        feeRecordAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                            @Override
                            public void onLoadMoreRequested() {
                                rvMoveConsumer.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        initData();
                                    }
                                }, 1000);
                            }
                        }, rvMoveConsumer);

                        if (data.getTotal() > 10) {
                            pageNum += 1;
                        } else {
                            feeRecordAdapter.loadMoreEnd();
                        }
                    } else {
                        if ((data.getTotal() - pageNum * 10) > 0) {
                            pageNum += 1;
                            feeRecordAdapter.addData(data.getData().getRecordList());
                            feeRecordAdapter.loadMoreComplete(); //完成本次
                        } else {
                            feeRecordAdapter.addData(data.getData().getRecordList());
                            feeRecordAdapter.loadMoreEnd(); //完成所有加载
                        }
                    }
                } else {
                    recordList.clear();
                    feeRecordAdapter = new FeeRecordAdapter(recordList);
                    rvMoveConsumer.setAdapter(feeRecordAdapter);
                    feeRecordAdapter.loadMoreEnd(); //完成所有加载
                    feeRecordAdapter.setEmptyView(R.layout.null_data, rvMoveConsumer);
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
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
