package com.ipd.xiangzuidoctor.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatEditText;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.base.BaseActivity;
import com.ipd.xiangzuidoctor.bean.RefundDepositBean;
import com.ipd.xiangzuidoctor.bean.WithdrawAliPayBean;
import com.ipd.xiangzuidoctor.common.view.TopView;
import com.ipd.xiangzuidoctor.contract.WithdrawAliPayContract;
import com.ipd.xiangzuidoctor.presenter.WithdrawAliPayPresenter;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;
import com.ipd.xiangzuidoctor.utils.MD5Utils;
import com.ipd.xiangzuidoctor.utils.SPUtil;
import com.ipd.xiangzuidoctor.utils.StringUtils;
import com.ipd.xiangzuidoctor.utils.ToastUtil;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzuidoctor.common.config.IConstants.SIGN;
import static com.ipd.xiangzuidoctor.common.config.IConstants.USER_ID;
import static com.ipd.xiangzuidoctor.utils.StringUtils.isEmpty;

/**
 * Description ：提现
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/12.
 */
public class WithdrawActivity extends BaseActivity<WithdrawAliPayContract.View, WithdrawAliPayContract.Presenter> implements WithdrawAliPayContract.View {

    @BindView(R.id.tv_withdraw)
    TopView tvWithdraw;
    @BindView(R.id.et_service_fee)
    EditText etServiceFee;
    @BindView(R.id.et_name)
    AppCompatEditText etName;
    @BindView(R.id.et_code)
    AppCompatEditText etCode;

    private int type; //1: 提现，2：退保证金

    @Override
    public int getLayoutId() {
        return R.layout.activity_withdraw;
    }

    @Override
    public WithdrawAliPayContract.Presenter createPresenter() {
        return new WithdrawAliPayPresenter(this);
    }

    @Override
    public WithdrawAliPayContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvWithdraw);

        type = getIntent().getIntExtra("type", 1);
        if (type == 2)
            etServiceFee.setText("500");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK, new Intent().putExtra("refresh", 1));
        finish();
    }

    @OnClick({R.id.ll_top_back, R.id.sb_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_top_back:
                setResult(RESULT_OK, new Intent().putExtra("refresh", 1));
                finish();
                break;
            case R.id.sb_confirm:
                if (!isEmpty(etServiceFee.getText().toString().trim())) {
                    if (!isEmpty(etName.getText().toString().trim()) && !isEmpty(etCode.getText().toString().trim())) {
                        switch (type) {
                            case 1:
                                TreeMap<String, String> withdrawAliPayMap = new TreeMap<>();
                                withdrawAliPayMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                                withdrawAliPayMap.put("withMoney", etServiceFee.getText().toString().trim());
                                withdrawAliPayMap.put("payName", etName.getText().toString().trim());
                                withdrawAliPayMap.put("payAccount", etCode.getText().toString().trim());
                                withdrawAliPayMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(withdrawAliPayMap.toString().replaceAll(" ", "") + SIGN)));
                                getPresenter().getWithdrawAliPay(withdrawAliPayMap, false, false);
                                break;
                            case 2:
                                TreeMap<String, String> refundDepositMap = new TreeMap<>();
                                refundDepositMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                                refundDepositMap.put("payName", etName.getText().toString().trim());
                                refundDepositMap.put("payAccount", etCode.getText().toString().trim());
                                refundDepositMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(refundDepositMap.toString().replaceAll(" ", "") + SIGN)));
                                getPresenter().getRefundDeposit(refundDepositMap, false, false);
                                break;
                        }
                    } else {
                        ToastUtil.showShortToast("请将信息填写完整！");
                    }
                } else
                    ToastUtil.showShortToast("请填写提现金额！");
                break;
        }
    }

    @Override
    public void resultWithdrawAliPay(WithdrawAliPayBean data) {
        switch (data.getCode()) {
            case 200:
                startActivity(new Intent(this, WithdrawSuccessActivity.class));
                break;
            case 900:
                ToastUtil.showShortToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(this, CaptchaLoginActivity.class));
                finish();
                break;
            default:
                ToastUtil.showShortToast(data.getMsg());
        }
    }

    @Override
    public void resultRefundDeposit(RefundDepositBean data) {
        switch (data.getCode()) {
            case 200:
                startActivity(new Intent(this, WithdrawSuccessActivity.class));
                break;
            case 900:
                ToastUtil.showShortToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(this, CaptchaLoginActivity.class));
                finish();
                break;
            default:
                ToastUtil.showShortToast(data.getMsg());
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
