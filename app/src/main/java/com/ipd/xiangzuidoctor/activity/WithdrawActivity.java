package com.ipd.xiangzuidoctor.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
 * Description ：提现
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/12.
 */
public class WithdrawActivity extends BaseActivity {

    @BindView(R.id.tv_withdraw)
    TopView tvWithdraw;
    @BindView(R.id.tv_service_fee)
    TextView tvServiceFee;
    @BindView(R.id.et_service_fee)
    EditText etServiceFee;
    @BindView(R.id.stv_wechat_withdraw)
    SuperTextView stvWechatWithdraw;
    @BindView(R.id.stv_ali_withdraw)
    SuperTextView stvAliWithdraw;

    @Override
    public int getLayoutId() {
        return R.layout.activity_withdraw;
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
        ImmersionBar.setTitleBar(this, tvWithdraw);
    }

    @Override
    public void initData() {
        tvServiceFee.setText("提现金额（收取0.3%服务费）");
    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.stv_wechat_withdraw, R.id.stv_ali_withdraw, R.id.sb_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stv_wechat_withdraw:
                stvWechatWithdraw.setRightIcon(R.drawable.ic_check_blue);
                stvAliWithdraw.setRightIcon(R.drawable.ic_check_gray);
                break;
            case R.id.stv_ali_withdraw:
                stvAliWithdraw.setRightIcon(R.drawable.ic_check_blue);
                stvWechatWithdraw.setRightIcon(R.drawable.ic_check_gray);
                break;
            case R.id.sb_confirm:
                startActivity(new Intent(this, WithdrawSuccessActivity.class));
                break;
        }
    }
}
