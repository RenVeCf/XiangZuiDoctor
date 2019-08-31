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
import com.ipd.xiangzuidoctor.activity.SpecialColumnDetailsActivity;
import com.ipd.xiangzuidoctor.adapter.SpecialColumnAdapter;
import com.ipd.xiangzuidoctor.base.BaseFragment;
import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.bean.TestMultiItemEntityBean;
import com.ipd.xiangzuidoctor.common.view.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SpecialColumnFragment extends BaseFragment {

    @BindView(R.id.rv_special_column)
    RecyclerView rvSpecialColumn;
    @BindView(R.id.srl_special_column)
    SwipeRefreshLayout srlSpecialColumn;

    private List<TestMultiItemEntityBean> str1 = new ArrayList<>();
    private SpecialColumnAdapter specialColumnAdapter;
    private int pageNum = 1;//页数
    private String specialColumnType;//订单状态 0:最新文章， 1:新闻， 2:临床， 3:科研

    @Override
    public int getLayoutId() {
        return R.layout.fragment_special_column;
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
        if (5 > 0) {//TODO 有接口后5更换总条数
            if (pageNum == 1) {
                str1.clear();
                for (int i = 0; i < 5; i++) {//TODO 有接口后去掉
                    TestMultiItemEntityBean testData = new TestMultiItemEntityBean();
                    str1.add(testData);
                }
//                str1.addAll(data.getData().getMessageList());//TODO 有接口后打开
                specialColumnAdapter = new SpecialColumnAdapter(str1);
                rvSpecialColumn.setAdapter(specialColumnAdapter);
                specialColumnAdapter.bindToRecyclerView(rvSpecialColumn);
                specialColumnAdapter.setEnableLoadMore(true);
                specialColumnAdapter.openLoadAnimation();
                specialColumnAdapter.disableLoadMoreIfNotFullPage();

                specialColumnAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        startActivity(new Intent(getContext(), SpecialColumnDetailsActivity.class));
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

                if (5 > 10) {//TODO 有接口后5更换list.size
                    pageNum += 1;
                } else {
                    specialColumnAdapter.loadMoreEnd();
                }
            } else {
                if ((5 - pageNum * 10) > 0) {//TODO 有接口后5更换list.size
                    pageNum += 1;
//                    specialColumnAdapter.addData(data.getData().getMessageList());//TODO 有接口后打开
                    specialColumnAdapter.loadMoreComplete(); //完成本次
                } else {
//                    specialColumnAdapter.addData(data.getData().getMessageList());//TODO 有接口后打开
                    specialColumnAdapter.loadMoreEnd(); //完成所有加载
                }
            }
        } else {
            str1.clear();
            specialColumnAdapter = new SpecialColumnAdapter(str1);
            rvSpecialColumn.setAdapter(specialColumnAdapter);
            specialColumnAdapter.loadMoreEnd(); //完成所有加载
            specialColumnAdapter.setEmptyView(R.layout.null_data, rvSpecialColumn);
        }
    }
}
