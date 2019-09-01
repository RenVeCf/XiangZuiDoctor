package com.ipd.xiangzuidoctor.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.base.BaseActivity;
import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.common.view.TopView;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：认证成功
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/15.
 */
public class AuthenticationResultActivity extends BaseActivity {

    @BindView(R.id.tv_recharge_success)
    TopView tvRechargeSuccess;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.iv_result)
    AppCompatImageView ivResult;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.ll_reset_authentication)
    LinearLayout llResetAuthentication;

    private int resultType;

    @Override
    public int getLayoutId() {
        return R.layout.activity_authentication_result;
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
        ImmersionBar.setTitleBar(this, tvRechargeSuccess);

        resultType = getIntent().getIntExtra("result_type", 0);
    }

    @Override
    public void initData() {
        switch (resultType) {
            case 1:
                ivResult.setImageResource(R.drawable.ic_recharge_success);
                tvTopTitle.setText("提交成功");
                tvTitle.setText("提交成功");
                tvContent.setText("您已提交成功，2-3个工作日会给您审核结");
                break;
            case 2:
                ivResult.setImageResource(R.drawable.ic_wait);
                tvTopTitle.setText("审核中");
                tvTitle.setText("审核中");
                tvContent.setText("您的资料还在审核中，2-3个工作日 会给您审核结果");
                break;
            case 3:
                llResetAuthentication.setVisibility(View.VISIBLE);
                ivResult.setImageResource(R.drawable.ic_wait);
                tvTopTitle.setText("审核失败");
                tvTitle.setText("审核失败");
                tvContent.setText("您的资料审核失败，请重新提交");
                break;
            case 4:
                ivResult.setImageResource(R.drawable.ic_recharge_success);
                tvTopTitle.setText("取消报名");
                tvTitle.setText("取消成功");
                tvContent.setText("您的报名费将于2日内返还至您的账户中");
                break;
            case 5:
                ivResult.setImageResource(R.drawable.ic_recharge_success);
                tvTopTitle.setText("充值成功");
                tvTitle.setText("充值成功");
                tvContent.setText("您已充值成功，钱款已到账");
                break;
        }
    }

    @Override
    public void initListener() {

    }

    @OnClick(R.id.bt_reset_authentication)
    public void onViewClicked() {
        startActivity(new Intent(this, AuthenticationActivity.class));
        finish();
    }
}
