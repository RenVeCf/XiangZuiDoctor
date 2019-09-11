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
import com.ipd.xiangzuidoctor.activity.CaptchaLoginActivity;
import com.ipd.xiangzuidoctor.activity.MainActivity;
import com.ipd.xiangzuidoctor.activity.SpecialColumnDetailsActivity;
import com.ipd.xiangzuidoctor.adapter.SpecialColumnAdapter;
import com.ipd.xiangzuidoctor.base.BaseFragment;
import com.ipd.xiangzuidoctor.bean.SpecialColumnBean;
import com.ipd.xiangzuidoctor.bean.SpecialColumnCollectionBean;
import com.ipd.xiangzuidoctor.bean.SpecialColumnDetailsBean;
import com.ipd.xiangzuidoctor.common.view.SpacesItemDecoration;
import com.ipd.xiangzuidoctor.contract.SpecialColumnContract;
import com.ipd.xiangzuidoctor.presenter.SpecialColumnPresenter;
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

public class SpecialColumnFragment extends BaseFragment<SpecialColumnContract.View, SpecialColumnContract.Presenter> implements SpecialColumnContract.View {

    @BindView(R.id.rv_special_column)
    RecyclerView rvSpecialColumn;
    @BindView(R.id.srl_special_column)
    SwipeRefreshLayout srlSpecialColumn;

    private List<SpecialColumnBean.DataBean.MedicalListBean> medicalListBean = new ArrayList<>();
    private SpecialColumnAdapter specialColumnAdapter;
    private int pageNum = 1;//页数
    private String specialColumnType;//订单状态 0:最新文章， 1:新闻， 2:临床， 3:科研

    @Override
    public int getLayoutId() {
        return R.layout.fragment_special_column;
    }

    @Override
    public SpecialColumnContract.Presenter createPresenter() {
        return new SpecialColumnPresenter(mContext);
    }

    @Override
    public SpecialColumnContract.View createView() {
        return this;
    }

    @Override
    public void init(View view) {
        Bundle args = getArguments();
        if (args != null) {
            specialColumnType = args.getString("special_column_type");
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvSpecialColumn.setLayoutManager(layoutManager);
        rvSpecialColumn.setNestedScrollingEnabled(false);
        rvSpecialColumn.addItemDecoration(new SpacesItemDecoration(1, 50));
        rvSpecialColumn.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvSpecialColumn.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlSpecialColumn.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlSpecialColumn.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
                srlSpecialColumn.setRefreshing(false);
            }
        });
    }

    @Override
    public void initData() {
        TreeMap<String, String> homeMap = new TreeMap<>();
        homeMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
        homeMap.put("tag", specialColumnType);
        homeMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(homeMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getSpecialColumn(homeMap, false, false);
    }

    @Override
    public void resultSpecialColumn(SpecialColumnBean data) {
        switch (data.getCode()) {
            case 200:
                if (data.getTotal() > 0) {
                    if (pageNum == 1) {
                        medicalListBean.clear();
                        medicalListBean.addAll(data.getData().getMedicalList());
                        specialColumnAdapter = new SpecialColumnAdapter(medicalListBean);
                        rvSpecialColumn.setAdapter(specialColumnAdapter);
                        specialColumnAdapter.bindToRecyclerView(rvSpecialColumn);
                        specialColumnAdapter.setEnableLoadMore(true);
                        specialColumnAdapter.openLoadAnimation();
                        specialColumnAdapter.disableLoadMoreIfNotFullPage();

                        specialColumnAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                startActivity(new Intent(getContext(), SpecialColumnDetailsActivity.class).putExtra("medicalId", medicalListBean.get(position).getMedicalId()));
                            }
                        });

                        //上拉加载
                        specialColumnAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                            @Override
                            public void onLoadMoreRequested() {
                                rvSpecialColumn.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        initData();
                                    }
                                }, 1000);
                            }
                        }, rvSpecialColumn);

                        if (data.getTotal() > 10) {
                            pageNum += 1;
                        } else {
                            specialColumnAdapter.loadMoreEnd();
                        }
                    } else {
                        if ((data.getTotal() - pageNum * 10) > 0) {
                            pageNum += 1;
                            specialColumnAdapter.addData(data.getData().getMedicalList());
                            specialColumnAdapter.loadMoreComplete(); //完成本次
                        } else {
                            specialColumnAdapter.addData(data.getData().getMedicalList());
                            specialColumnAdapter.loadMoreEnd(); //完成所有加载
                        }
                    }
                } else {
                    medicalListBean.clear();
                    specialColumnAdapter = new SpecialColumnAdapter(medicalListBean);
                    rvSpecialColumn.setAdapter(specialColumnAdapter);
                    specialColumnAdapter.loadMoreEnd(); //完成所有加载
                    specialColumnAdapter.setEmptyView(R.layout.null_data, rvSpecialColumn);
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
    public void resultSpecialColumnDetails(SpecialColumnDetailsBean data) {

    }

    @Override
    public void resultSpecialColumnCollection(SpecialColumnCollectionBean data) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }
}
