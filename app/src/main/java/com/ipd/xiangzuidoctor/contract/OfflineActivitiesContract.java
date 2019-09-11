package com.ipd.xiangzuidoctor.contract;

import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesAliPayBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesBalancePayBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesCancelBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesDetailsBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesDetailsPayBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesFreeBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesListBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesMyBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesWechatPayBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface OfflineActivitiesContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultOfflineActivitiesList(OfflineActivitiesListBean data);

        void resultOfflineActivitiesDetails(OfflineActivitiesDetailsBean data);

        void resultOfflineActivitiesDetailsPay(OfflineActivitiesDetailsPayBean data);

        void resultOfflineActivitiesFree(OfflineActivitiesFreeBean data);

        void resultOfflineActivitiesAliPay(OfflineActivitiesAliPayBean data);

        void resultOfflineActivitiesWechatPay(OfflineActivitiesWechatPayBean data);

        void resultOfflineActivitiesBalancePay(OfflineActivitiesBalancePayBean data);

        void resultOfflineActivitiesMy(OfflineActivitiesMyBean data);

        void resultOfflineActivitiesCancel(OfflineActivitiesCancelBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getOfflineActivitiesList(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getOfflineActivitiesDetails(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getOfflineActivitiesDetailsPay(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getOfflineActivitiesFree(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getOfflineActivitiesAliPay(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getOfflineActivitiesWechatPay(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getOfflineActivitiesBalancePay(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getOfflineActivitiesMy(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getOfflineActivitiesCancel(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}