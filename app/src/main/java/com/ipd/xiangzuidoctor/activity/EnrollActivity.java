package com.ipd.xiangzuidoctor.activity;

import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.base.BaseActivity;
import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.common.view.TopView;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：报名
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/31.
 */
public class EnrollActivity extends BaseActivity {

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

    @Override
    public int getLayoutId() {
        return R.layout.activity_enroll;
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
        ImmersionBar.setTitleBar(this, tvEnroll);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.tv_wechat_pay, R.id.tv_ali_pay, R.id.tv_balance_pay, R.id.bt_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_wechat_pay:
                tvWechatPay.setRightIcon(R.drawable.ic_check_blue);
                tvAliPay.setRightIcon(0);
                tvBalancePay.setRightIcon(0);
                break;
            case R.id.tv_ali_pay:
                tvWechatPay.setRightIcon(0);
                tvAliPay.setRightIcon(R.drawable.ic_check_blue);
                tvBalancePay.setRightIcon(0);
                break;
            case R.id.tv_balance_pay:
                tvWechatPay.setRightIcon(0);
                tvAliPay.setRightIcon(0);
                tvBalancePay.setRightIcon(R.drawable.ic_check_blue);
                break;
            case R.id.bt_pay:
                finish();
                break;
        }
    }
}
