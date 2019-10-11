package com.ipd.xiangzuidoctor.presenter;

import android.content.Context;

import com.ipd.xiangzuidoctor.bean.RefundDepositBean;
import com.ipd.xiangzuidoctor.bean.WithdrawAliPayBean;
import com.ipd.xiangzuidoctor.contract.WithdrawAliPayContract;
import com.ipd.xiangzuidoctor.model.WithdrawAliPayModel;
import com.ipd.xiangzuidoctor.progress.ObserverResponseListener;
import com.ipd.xiangzuidoctor.utils.ExceptionHandle;
import com.ipd.xiangzuidoctor.utils.ToastUtil;

import java.util.TreeMap;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public class WithdrawAliPayPresenter extends WithdrawAliPayContract.Presenter {

    private WithdrawAliPayModel model;
    private Context context;

    public WithdrawAliPayPresenter(Context context) {
        this.model = new WithdrawAliPayModel();
        this.context = context;
    }

    @Override
    public void getWithdrawAliPay(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getWithdrawAliPay(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultWithdrawAliPay((WithdrawAliPayBean) o);
                }
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if (getView() != null) {
                    //// TODO: 2017/12/28 自定义处理异常
                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
                }
            }
        });
    }

    @Override
    public void getRefundDeposit(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getRefundDeposit(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultRefundDeposit((RefundDepositBean) o);
                }
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if (getView() != null) {
                    //// TODO: 2017/12/28 自定义处理异常
                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
                }
            }
        });
    }
}