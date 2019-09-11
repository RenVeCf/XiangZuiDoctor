package com.ipd.xiangzuidoctor.activity;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.widget.AppCompatTextView;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.base.BaseActivity;
import com.ipd.xiangzuidoctor.bean.SpecialColumnBean;
import com.ipd.xiangzuidoctor.bean.SpecialColumnCollectionBean;
import com.ipd.xiangzuidoctor.bean.SpecialColumnDetailsBean;
import com.ipd.xiangzuidoctor.common.view.ShareDialog;
import com.ipd.xiangzuidoctor.common.view.TopView;
import com.ipd.xiangzuidoctor.contract.SpecialColumnContract;
import com.ipd.xiangzuidoctor.presenter.SpecialColumnPresenter;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;
import com.ipd.xiangzuidoctor.utils.MD5Utils;
import com.ipd.xiangzuidoctor.utils.SPUtil;
import com.ipd.xiangzuidoctor.utils.StringUtils;
import com.ipd.xiangzuidoctor.utils.ToastUtil;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.textview.supertextview.SuperButton;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzuidoctor.common.config.IConstants.SIGN;
import static com.ipd.xiangzuidoctor.common.config.IConstants.USER_ID;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.BASE_LOCAL_URL;

/**
 * Description ：专栏详情
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/31.
 */
public class SpecialColumnDetailsActivity extends BaseActivity<SpecialColumnContract.View, SpecialColumnContract.Presenter> implements SpecialColumnContract.View {

    @BindView(R.id.bt_collection)
    SuperButton btCollection;
    @BindView(R.id.tv_time)
    AppCompatTextView tvTime;
    @BindView(R.id.tv_content)
    AppCompatTextView tvContent;
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.tv_special_column_details)
    TopView tvSpecialColumnDetails;
    @BindView(R.id.iv_img)
    RadiusImageView ivImg;
    @BindView(R.id.wv_special_column_details)
    WebView wvSpecialColumnDetails;

    private int medicalId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_special_column_details;
    }

    @Override
    public SpecialColumnContract.Presenter createPresenter() {
        return new SpecialColumnPresenter(this);
    }

    @Override
    public SpecialColumnContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvSpecialColumnDetails);

        medicalId = getIntent().getIntExtra("medicalId", 0);

        WebSettings webSettings = wvSpecialColumnDetails.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        //支持自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);// 排版适应屏幕
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    @Override
    public void initData() {
        TreeMap<String, String> specialColumnDetailsMap = new TreeMap<>();
        specialColumnDetailsMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        specialColumnDetailsMap.put("medicalId", medicalId + "");
        specialColumnDetailsMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(specialColumnDetailsMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getSpecialColumnDetails(specialColumnDetailsMap, true, false);
    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.bt_share, R.id.bt_collection})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_share:
                new ShareDialog(this) {
                }.show();
                break;
            case R.id.bt_collection:
                TreeMap<String, String> specialColumnCollectionMap = new TreeMap<>();
                specialColumnCollectionMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                specialColumnCollectionMap.put("medicalId", medicalId + "");
                if ("收藏".equals(btCollection.getText().toString()))
                    specialColumnCollectionMap.put("isFollow", "2");
                else
                    specialColumnCollectionMap.put("isFollow", "1");
                specialColumnCollectionMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(specialColumnCollectionMap.toString().replaceAll(" ", "") + SIGN)));
                getPresenter().getSpecialColumnCollection(specialColumnCollectionMap, true, false);
                break;
        }
    }

    @Override
    public void resultSpecialColumn(SpecialColumnBean data) {

    }

    @Override
    public void resultSpecialColumnDetails(SpecialColumnDetailsBean data) {
        switch (data.getCode()) {
            case 200:
                wvSpecialColumnDetails.loadUrl(BASE_LOCAL_URL + data.getData().getH5medicalDetails());
                btCollection.setText(data.getData().getIsFollow() == 1 ? "收藏" : "取消收藏");
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
    public void resultSpecialColumnCollection(SpecialColumnCollectionBean data) {
        switch (data.getCode()) {
            case 200:
                if ("收藏".equals(btCollection.getText().toString()))
                    btCollection.setText("取消收藏");
                else
                    btCollection.setText("收藏");
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
