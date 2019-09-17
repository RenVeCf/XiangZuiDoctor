package com.ipd.xiangzuidoctor.contract;

import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.bean.GetOrderBean;
import com.ipd.xiangzuidoctor.bean.HomeBean;
import com.ipd.xiangzuidoctor.bean.IsArrivalsBean;
import com.ipd.xiangzuidoctor.bean.OrderCancelBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface HomeContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultHome(HomeBean data);

        void resultIsArrivals(IsArrivalsBean data);

        void resultOrderCancel(OrderCancelBean data);

        void resultGetOrder(GetOrderBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getHome(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getIsArrivals(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getOrderCancel(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getGetOrder(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}