package com.ipd.xiangzuidoctor.contract;

import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.bean.GetOrderBean;
import com.ipd.xiangzuidoctor.bean.IngOperationEndBean;
import com.ipd.xiangzuidoctor.bean.IsArrivalsBean;
import com.ipd.xiangzuidoctor.bean.IsOrderOperationEndBean;
import com.ipd.xiangzuidoctor.bean.OperationStartBean;
import com.ipd.xiangzuidoctor.bean.OrderCancelBean;
import com.ipd.xiangzuidoctor.bean.OrderDetailsBean;
import com.ipd.xiangzuidoctor.bean.OrderListBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface OrderContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultOrderList(OrderListBean data);

        void resultOrderDetails(OrderDetailsBean data);

        void resultIsOrderOperationEnd(IsOrderOperationEndBean data);

        void resultIngOperationEnd(IngOperationEndBean data);

        void resultOperationStart(OperationStartBean data);

        void resultIsArrivals(IsArrivalsBean data);

        void resultOrderCancel(OrderCancelBean data);

        void resultGetOrder(GetOrderBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getOrderList(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getOrderDetails(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getIsOrderOperationEnd(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getIngOperationEnd(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getOperationStart(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getIsArrivals(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getOrderCancel(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getGetOrder(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}