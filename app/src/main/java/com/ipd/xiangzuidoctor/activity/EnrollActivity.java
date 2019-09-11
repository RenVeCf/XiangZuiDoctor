package com.ipd.xiangzuidoctor.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

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
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzuidoctor.common.config.IConstants.SIGN;
import static com.ipd.xiangzuidoctor.common.config.IConstants.USER_ID;

/**
 * Description ：报名
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/31.
 */
public class EnrollActivity extends BaseActivity<OfflineActivitiesContract.View, OfflineActivitiesContract.Presenter> implements OfflineActivitiesContract.View {

    @BindView(R.id.tv_wechat_pay)
    SuperTextView tvWechatPay;
    @BindView(R.id.tv_ali_pay)
    SuperTextView tvAliPay;
    @BindView(R.id.tv_balance_pay)
    SuperTextView tvBalancePay;
    @BindView(R.id.tv_discount_fee)
    AppCompatTextView tvDiscountFee;
    @BindView(R.id.tv_market_fee)
    AppCompatTextView tvMarketFee;
    @BindView(R.id.tv_enroll)
    TopView tvEnroll;

    private int activityId;
    private double balanceFee;//余额
    private String isMoney; //1不需要钱 2需要钱

    @Override
    public int getLayoutId() {
        return R.layout.activity_enroll;
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
        ImmersionBar.setTitleBar(this, tvEnroll);

        activityId = getIntent().getIntExtra("activityId", 0);
    }

    @Override
    public void initData() {
        TreeMap<String, String> offlineActivitiesDetailsPayMap = new TreeMap<>();
        offlineActivitiesDetailsPayMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        offlineActivitiesDetailsPayMap.put("activityId", activityId + "");
        offlineActivitiesDetailsPayMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(offlineActivitiesDetailsPayMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getOfflineActivitiesDetailsPay(offlineActivitiesDetailsPayMap, false, false);
    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.tv_wechat_pay, R.id.tv_ali_pay, R.id.tv_balance_pay, R.id.bt_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_wechat_pay:
                tvWechatPay.setRightIcon(R.drawable.ic_check_blue);
                tvAliPay.setRightIcon(R.drawable.ic_check_gray);
                tvBalancePay.setRightIcon(R.drawable.ic_check_gray);
                break;
            case R.id.tv_ali_pay:
                tvWechatPay.setRightIcon(R.drawable.ic_check_gray);
                tvAliPay.setRightIcon(R.drawable.ic_check_blue);
                tvBalancePay.setRightIcon(R.drawable.ic_check_gray);
                break;
            case R.id.tv_balance_pay:
                tvWechatPay.setRightIcon(R.drawable.ic_check_gray);
                tvAliPay.setRightIcon(R.drawable.ic_check_gray);
                tvBalancePay.setRightIcon(R.drawable.ic_check_blue);
                break;
            case R.id.bt_pay:
                Drawable.ConstantState drawableWechat = tvWechatPay.getRightIconIV().getDrawable().getCurrent().getConstantState();
                Drawable.ConstantState drawableAli = tvAliPay.getRightIconIV().getDrawable().getCurrent().getConstantState();
                Drawable.ConstantState drawableBalance = tvBalancePay.getRightIconIV().getDrawable().getCurrent().getConstantState();
                if ("1".equals(isMoney)) {
                    TreeMap<String, String> offlineActivitiesFreeMap = new TreeMap<>();
                    offlineActivitiesFreeMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                    offlineActivitiesFreeMap.put("activityId", activityId + "");
                    offlineActivitiesFreeMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(offlineActivitiesFreeMap.toString().replaceAll(" ", "") + SIGN)));
                    getPresenter().getOfflineActivitiesFree(offlineActivitiesFreeMap, false, false);
                } else if (drawableWechat.equals(ContextCompat.getDrawable(this, R.drawable.ic_check_blue).getConstantState())) {
                    TreeMap<String, String> offlineActivitiesWechatPayMap = new TreeMap<>();
                    offlineActivitiesWechatPayMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                    offlineActivitiesWechatPayMap.put("activityId", activityId + "");
                    offlineActivitiesWechatPayMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(offlineActivitiesWechatPayMap.toString().replaceAll(" ", "") + SIGN)));
                    getPresenter().getOfflineActivitiesWechatPay(offlineActivitiesWechatPayMap, false, false);
                } else if (drawableAli.equals(ContextCompat.getDrawable(this, R.drawable.ic_check_blue).getConstantState())) {
                    TreeMap<String, String> offlineActivitiesAliPayMap = new TreeMap<>();
                    offlineActivitiesAliPayMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                    offlineActivitiesAliPayMap.put("activityId", activityId + "");
                    offlineActivitiesAliPayMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(offlineActivitiesAliPayMap.toString().replaceAll(" ", "") + SIGN)));
                    getPresenter().getOfflineActivitiesAliPay(offlineActivitiesAliPayMap, false, false);
                } else if (drawableBalance.equals(ContextCompat.getDrawable(this, R.drawable.ic_check_blue).getConstantState()) && balanceFee >= Double.parseDouble(tvDiscountFee.getText().toString().trim().replaceAll("¥", ""))) {
                    TreeMap<String, String> offlineActivitiesBalancePay = new TreeMap<>();
                    offlineActivitiesBalancePay.put("userId", SPUtil.get(this, USER_ID, "") + "");
                    offlineActivitiesBalancePay.put("activityId", activityId + "");
                    offlineActivitiesBalancePay.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(offlineActivitiesBalancePay.toString().replaceAll(" ", "") + SIGN)));
                    getPresenter().getOfflineActivitiesBalancePay(offlineActivitiesBalancePay, false, false);
                } else
                    ToastUtil.showLongToast("余额不足，请选择其他支付方式！");
                break;
        }
    }

    @Override
    public void resultOfflineActivitiesList(OfflineActivitiesListBean data) {

    }

    @Override
    public void resultOfflineActivitiesDetails(OfflineActivitiesDetailsBean data) {

    }

    @Override
    public void resultOfflineActivitiesDetailsPay(OfflineActivitiesDetailsPayBean data) {
        switch (data.getCode()) {
            case 200:
                balanceFee = data.getData().getBalance();
                isMoney = data.getData().getActivityDetails().getIsMoney();
                tvDiscountFee.setText("¥" + data.getData().getActivityDetails().getDiscountsMoney());
                tvMarketFee.setText("市场价：¥" + data.getData().getActivityDetails().getPlatformMoney());
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
    public void resultOfflineActivitiesFree(OfflineActivitiesFreeBean data) {
        ToastUtil.showLongToast(data.getMsg());
        switch (data.getCode()) {
            case 200:
                finish();
                break;
            case 900:
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(this, CaptchaLoginActivity.class));
                finish();
                break;
        }
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

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
