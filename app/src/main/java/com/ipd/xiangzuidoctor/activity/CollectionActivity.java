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
import com.ipd.xiangzuidoctor.adapter.SpecialColumnAdapter;
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
 * Description ：我的收藏
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/28.
 */
public class CollectionActivity extends BaseActivity {

    @BindView(R.id.tv_collection)
    TopView tvCollection;
    @BindView(R.id.rv_collection)
    RecyclerView rvCollection;
    @BindView(R.id.srl_collection)
    SwipeRefreshLayout srlCollection;

    private List<TestMultiItemEntityBean> str1 = new ArrayList<>();
    private SpecialColumnAdapter specialColumnAdapter;
    private int pageNum = 1;//页数

    @Override
    public int getLayoutId() {
        return R.layout.activity_collection;
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
        ImmersionBar.setTitleBar(this, tvCollection);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvCollection.setLayoutManager(layoutManager);
        rvCollection.setNestedScrollingEnabled(false);
        rvCollection.addItemDecoration(new SpacesItemDecoration(1, 50));
        rvCollection.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvCollection.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlCollection.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色
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
                rvCollection.setAdapter(specialColumnAdapter);
                specialColumnAdapter.bindToRecyclerView(rvCollection);
                specialColumnAdapter.setEnableLoadMore(true);
                specialColumnAdapter.openLoadAnimation();
                specialColumnAdapter.disableLoadMoreIfNotFullPage();

                specialColumnAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        startActivity(new Intent(CollectionActivity.this, SpecialColumnDetailsActivity.class));
                    }
                });

                //上拉加载
                specialColumnAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {
                        rvCollection.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                initData();
                            }
                        }, 1000);
                    }
                }, rvCollection);

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
            rvCollection.setAdapter(specialColumnAdapter);
            specialColumnAdapter.loadMoreEnd(); //完成所有加载
            specialColumnAdapter.setEmptyView(R.layout.null_data, rvCollection);
        }
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlCollection.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
                srlCollection.setRefreshing(false);
            }
        });
    }
}
