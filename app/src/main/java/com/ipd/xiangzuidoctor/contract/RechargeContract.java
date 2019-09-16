package com.ipd.xiangzuidoctor.contract;

import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.bean.RechargeAliPayBean;
import com.ipd.xiangzuidoctor.bean.RechargeWechatPayBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface RechargeContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultRechargeAliPay(RechargeAliPayBean data);

        void resultRechargeWechatPay(RechargeWechatPayBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getRechargeAliPay(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getRechargeWechatPay(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}