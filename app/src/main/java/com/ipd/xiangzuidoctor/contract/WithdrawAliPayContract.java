package com.ipd.xiangzuidoctor.contract;

import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.bean.RefundDepositBean;
import com.ipd.xiangzuidoctor.bean.WithdrawAliPayBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface WithdrawAliPayContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultWithdrawAliPay(WithdrawAliPayBean data);

        void resultRefundDeposit(RefundDepositBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getWithdrawAliPay(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getRefundDeposit(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}