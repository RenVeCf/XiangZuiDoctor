package com.ipd.xiangzuidoctor.activity;

import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.base.BaseActivity;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesAliPayBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesBalancePayBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesCancelBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesDetailsBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesDetailsPayBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesFreeBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesListBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesMyBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesWechatPayBean;
import com.ipd.xiangzuidoctor.common.view.TopView;
import com.ipd.xiangzuidoctor.contract.OfflineActivitiesContract;
import com.ipd.xiangzuidoctor.presenter.OfflineActivitiesPresenter;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;
import com.ipd.xiangzuidoctor.utils.MD5Utils;
import com.ipd.xiangzuidoctor.utils.SPUtil;
import com.ipd.xiangzuidoctor.utils.StringUtils;
import com.ipd.xiangzuidoctor.utils.ToastUtil;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzuidoctor.common.config.IConstants.SIGN;
import static com.ipd.xiangzuidoctor.common.config.IConstants.USER_ID;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.BASE_LOCAL_URL;

/**
 * Description ：活动详情
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/31.
 */
public class OfflineActivitiesDetailsActivity extends BaseActivity<OfflineActivitiesContract.View, OfflineActivitiesContract.Presenter> implements OfflineActivitiesContract.View {

    @BindView(R.id.ll_enroll)
    LinearLayoutCompat llEnroll;
    @BindView(R.id.ll_cancel)
    LinearLayoutCompat llCancel;
    @BindView(R.id.tv_time)
    AppCompatTextView tvTime;
    @BindView(R.id.tv_fee)
    AppCompatTextView tvFee;
    @BindView(R.id.tv_content)
    AppCompatTextView tvContent;
    @BindView(R.id.wv_content)
    WebView wvContent;
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.tv_label)
    AppCompatTextView tvLabel;
    @BindView(R.id.tv_offline_activities_details)
    TopView tvOfflineActivitiesDetails;
    @BindView(R.id.iv_img)
    RadiusImageView ivImg;

    private String offlineActivitiesType;//订单状态 0:最新活动， 1:我的活动
    private int activityId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_offline_activities_details;
    }

    @Override
    public OfflineActivitiesContract.Presenter createPresenter() {
        return new OfflineActivitiesPresenter(this);
    }

    @Override
    public OfflineActivitiesContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvOfflineActivitiesDetails);

        activityId = getIntent().getIntExtra("activityId", 0);

        offlineActivitiesType = getIntent().getStringExtra("offline_activities_type");
        switch (offlineActivitiesType) {
            case "0":
                llEnroll.setVisibility(View.VISIBLE);
                tvLabel.setText("进行中");
                tvLabel.setBackgroundResource(R.drawable.bg_label3);
                break;
            case "1":
                llCancel.setVisibility(View.VISIBLE);
                tvLabel.setText("进行中");
                tvLabel.setBackgroundResource(R.drawable.bg_label3);
                break;
        }

        WebSettings settings = wvContent.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        wvContent.setWebViewClient(new WebViewActivity.MyWebViewClient(this));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        } else {
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }
    }

    @Override
    public void initData() {
        TreeMap<String, String> offlineActivitiesDetailsMap = new TreeMap<>();
        offlineActivitiesDetailsMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        offlineActivitiesDetailsMap.put("activityId", activityId + "");
        offlineActivitiesDetailsMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(offlineActivitiesDetailsMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getOfflineActivitiesDetails(offlineActivitiesDetailsMap, false, false);
    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.bt_enroll, R.id.bt_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_enroll:
                startActivity(new Intent(this, EnrollActivity.class).putExtra("activityId", activityId));
                break;
            case R.id.bt_cancel:
                TreeMap<String, String> offlineActivitiesCancelMap = new TreeMap<>();
                offlineActivitiesCancelMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                offlineActivitiesCancelMap.put("activityId", activityId + "");
                offlineActivitiesCancelMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(offlineActivitiesCancelMap.toString().replaceAll(" ", "") + SIGN)));
                getPresenter().getOfflineActivitiesCancel(offlineActivitiesCancelMap, false, false);
                break;
        }
    }

    @Override
    public void resultOfflineActivitiesList(OfflineActivitiesListBean data) {

    }

    @Override
    public void resultOfflineActivitiesDetails(OfflineActivitiesDetailsBean data) {
        switch (data.getCode()) {
            case 200:
                Glide.with(ApplicationUtil.getContext()).load(BASE_LOCAL_URL + data.getData().getActivityDetails().getLogo()).apply(new RequestOptions().placeholder(R.mipmap.bg_special_big)).into(ivImg);
                tvTime.setText("活动时间：" + data.getData().getActivityDetails().getStartTime());
                tvContent.setText(data.getData().getActivityDetails().getContent());
                tvTitle.setText(data.getData().getActivityDetails().getTitle());
                tvFee.setText(Html.fromHtml("<font color=\"#000000\">平台优惠价: </font>费用<font color=\"#FF5555\">¥" + data.getData().getActivityDetails().getDiscountsMoney() + "</font>市场价: ¥" + data.getData().getActivityDetails().getPlatformMoney()));
                wvContent.loadData(getHtmlData(data.getData().getActivityDetails().getContent()), "text/html;charset=utf-8", "utf-8");
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

    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>html{padding:15px;} body{word-wrap:break-word;font-size:13px;padding:0px;margin:0px} p{padding:0px;margin:0px;font-size:13px;color:#222222;line-height:1.3;} img{padding:0px,margin:0px;max-width:100%; width:auto; height:auto;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }

    @Override
    public void resultOfflineActivitiesDetailsPay(OfflineActivitiesDetailsPayBean data) {

    }

    @Override
    public void resultOfflineActivitiesFree(OfflineActivitiesFreeBean data) {

    }

    @Override
    public void resultOfflineActivitiesAliPay(OfflineActivitiesAliPayBean data) {

    }

    @Override
    public void resultOfflineActivitiesWechatPay(OfflineActivitiesWechatPayBean data) {

    }

    @Override
    public void resultOfflineActivitiesBalancePay(OfflineActivitiesBalancePayBean data) {

    }

    @Override
    public void resultOfflineActivitiesMy(OfflineActivitiesMyBean data) {

    }

    @Override
    public void resultOfflineActivitiesCancel(OfflineActivitiesCancelBean data) {
        switch (data.getCode()) {
            case 200:
                startActivity(new Intent(this, AuthenticationResultActivity.class).putExtra("result_type", 4));
                finish();
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
