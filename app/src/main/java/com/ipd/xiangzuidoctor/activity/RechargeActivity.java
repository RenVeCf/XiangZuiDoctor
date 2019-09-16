package com.ipd.xiangzuidoctor.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.aliPay.AliPay;
import com.ipd.xiangzuidoctor.base.BaseActivity;
import com.ipd.xiangzuidoctor.bean.RechargeAliPayBean;
import com.ipd.xiangzuidoctor.bean.RechargeWechatPayBean;
import com.ipd.xiangzuidoctor.common.view.TopView;
import com.ipd.xiangzuidoctor.contract.RechargeContract;
import com.ipd.xiangzuidoctor.presenter.RechargePresenter;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;
import com.ipd.xiangzuidoctor.utils.MD5Utils;
import com.ipd.xiangzuidoctor.utils.SPUtil;
import com.ipd.xiangzuidoctor.utils.StringUtils;
import com.ipd.xiangzuidoctor.utils.ToastUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzuidoctor.common.config.IConstants.SIGN;
import static com.ipd.xiangzuidoctor.common.config.IConstants.USER_ID;
import static com.ipd.xiangzuidoctor.utils.StringUtils.isEmpty;

/**
 * Description ：充值
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/15.
 */
public class RechargeActivity extends BaseActivity<RechargeContract.View, RechargeContract.Presenter> implements RechargeContract.View {

    @BindView(R.id.tv_recharge)
    TopView tvRecharge;
    @BindView(R.id.et_service_fee)
    EditText etServiceFee;
    @BindView(R.id.stv_wechat_withdraw)
    SuperTextView stvWechatWithdraw;
    @BindView(R.id.stv_ali_withdraw)
    SuperTextView stvAliWithdraw;

    private int payType; //1: 支付宝， 2：微信

    @Override
    public int getLayoutId() {
        return R.layout.activity_recharge;
    }

    @Override
    public RechargeContract.Presenter createPresenter() {
        return new RechargePresenter(this);
    }

    @Override
    public RechargeContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvRecharge);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    private void pay(int payType) {
        switch (payType) {
            case 1:
                TreeMap<String, String> rechargeAliPayMap = new TreeMap<>();
                rechargeAliPayMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                rechargeAliPayMap.put("balanceMoney", etServiceFee.getText().toString().trim());
                rechargeAliPayMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(rechargeAliPayMap.toString().replaceAll(" ", "") + SIGN)));
                getPresenter().getRechargeAliPay(rechargeAliPayMap, false, false);
                break;
            case 2:
                TreeMap<String, String> rechargeWechatPayMap = new TreeMap<>();
                rechargeWechatPayMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                rechargeWechatPayMap.put("balanceMoney", etServiceFee.getText().toString().trim());
                rechargeWechatPayMap.put("type", "1");
                rechargeWechatPayMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(rechargeWechatPayMap.toString().replaceAll(" ", "") + SIGN)));
                getPresenter().getRechargeWechatPay(rechargeWechatPayMap, false, false);
                break;
        }
    }

    @OnClick({R.id.stv_wechat_withdraw, R.id.stv_ali_withdraw, R.id.sb_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stv_wechat_withdraw:
                stvWechatWithdraw.setRightIcon(R.drawable.ic_check_blue);
                stvAliWithdraw.setRightIcon(R.drawable.ic_check_gray);
                payType = 2;
                break;
            case R.id.stv_ali_withdraw:
                stvAliWithdraw.setRightIcon(R.drawable.ic_check_blue);
                stvWechatWithdraw.setRightIcon(R.drawable.ic_check_gray);
                payType = 1;
                break;
            case R.id.sb_confirm:
                if (!isEmpty(etServiceFee.getText().toString().trim()))
                    pay(payType);
                else
                    ToastUtil.showLongToast("请填写充值金额！");
                break;
        }
    }

    @Override
    public void resultRechargeAliPay(RechargeAliPayBean data) {
        switch (data.getCode()) {
            case 200:
                new AliPay(this, data.getData().getSign());
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
    public void resultRechargeWechatPay(RechargeWechatPayBean data) {
        switch (data.getCode()) {
            case 200:
                IWXAPI api = WXAPIFactory.createWXAPI(this, null);
                api.registerApp("wx2bed183f1f29ee2f");
                PayReq req = new PayReq();
                req.appId = data.getData().getSign().getAppid();//你的微信appid
                req.partnerId = data.getData().getSign().getPartnerid();//商户号
                req.prepayId = data.getData().getSign().getPrepayid();//预支付交易会话ID
                req.nonceStr = data.getData().getSign().getNoncestr();//随机字符串
                req.timeStamp = data.getData().getSign().getTimestamp() + "";//时间戳
                req.packageValue = data.getData().getSign().getPackageX(); //扩展字段, 这里固定填写Sign = WXPay
                req.sign = data.getData().getSign().getSign();//签名
                //  req.extData         = "app data"; // optional
                // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                api.sendReq(req);
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
