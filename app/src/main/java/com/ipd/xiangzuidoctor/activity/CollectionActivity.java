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
import com.ipd.xiangzuidoctor.bean.CollectionListBean;
import com.ipd.xiangzuidoctor.common.view.SpacesItemDecoration;
import com.ipd.xiangzuidoctor.common.view.TopView;
import com.ipd.xiangzuidoctor.contract.CollectionListContract;
import com.ipd.xiangzuidoctor.presenter.CollectionListPresenter;
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
 * Description ：我的收藏
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/28.
 */
public class CollectionActivity extends BaseActivity<CollectionListContract.View, CollectionListContract.Presenter> implements CollectionListContract.View {

    @BindView(R.id.tv_collection)
    TopView tvCollection;
    @BindView(R.id.rv_collection)
    RecyclerView rvCollection;
    @BindView(R.id.srl_collection)
    SwipeRefreshLayout srlCollection;

    private List<CollectionListBean.DataBean.CollectionListsBean> collectionList = new ArrayList<>();
    private SpecialColumnAdapter specialColumnAdapter;
    private int pageNum = 1;//页数

    @Override
    public int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    public CollectionListContract.Presenter createPresenter() {
        return new CollectionListPresenter(this);
    }

    @Override
    public CollectionListContract.View createView() {
        return this;
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
        TreeMap<String, String> collectionListMap = new TreeMap<>();
        collectionListMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        collectionListMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(collectionListMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getCollectionList(collectionListMap, false, false);
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

    @Override
    public void resultCollectionList(CollectionListBean data) {
        switch (data.getCode()) {
            case 200:
                if (data.getTotal() > 0) {
                    if (pageNum == 1) {
                        collectionList.clear();
                        collectionList.addAll(data.getData().getCollectionList());
                        specialColumnAdapter = new SpecialColumnAdapter(collectionList);
                        rvCollection.setAdapter(specialColumnAdapter);
                        specialColumnAdapter.bindToRecyclerView(rvCollection);
                        specialColumnAdapter.setEnableLoadMore(true);
                        specialColumnAdapter.openLoadAnimation();
                        specialColumnAdapter.disableLoadMoreIfNotFullPage();

                        specialColumnAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                startActivity(new Intent(CollectionActivity.this, SpecialColumnDetailsActivity.class).putExtra("medicalId", collectionList.get(position).getMedicalId()));                            }
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

                        if (data.getTotal() > 10) {//TODO 有接口后5更换list.size
                            pageNum += 1;
                        } else {
                            specialColumnAdapter.loadMoreEnd();
                        }
                    } else {
                        if ((data.getTotal() - pageNum * 10) > 0) {
                            pageNum += 1;
                            specialColumnAdapter.addData(data.getData().getCollectionList());
                            specialColumnAdapter.loadMoreComplete(); //完成本次
                        } else {
                            specialColumnAdapter.addData(data.getData().getCollectionList());
                            specialColumnAdapter.loadMoreEnd(); //完成所有加载
                        }
                    }
                } else {
                    collectionList.clear();
                    specialColumnAdapter = new SpecialColumnAdapter(collectionList);
                    rvCollection.setAdapter(specialColumnAdapter);
                    specialColumnAdapter.loadMoreEnd(); //完成所有加载
                    specialColumnAdapter.setEmptyView(R.layout.null_data, rvCollection);
                }
                break;
            case 900:
                ToastUtil.showShortToast(data.getMsg());
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
