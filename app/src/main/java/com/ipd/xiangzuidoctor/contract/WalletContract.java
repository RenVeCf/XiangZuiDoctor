package com.ipd.xiangzuidoctor.contract;

import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.bean.HomeBean;
import com.ipd.xiangzuidoctor.bean.WalletBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface WalletContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultWallet(WalletBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getWallet(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}