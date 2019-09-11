package com.ipd.xiangzuidoctor.presenter;

import android.content.Context;

import com.ipd.xiangzuidoctor.bean.OrderDetailsBean;
import com.ipd.xiangzuidoctor.contract.OrderDetailsContract;
import com.ipd.xiangzuidoctor.model.OrderDetailsModel;
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
public class OrderDetailsPresenter extends OrderDetailsContract.Presenter {

    private OrderDetailsModel model;
    private Context context;

    public OrderDetailsPresenter(Context context) {
        this.model = new OrderDetailsModel();
        this.context = context;
    }

    @Override
    public void getOrderDetails(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getOrderDetails(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultOrderDetails((OrderDetailsBean) o);
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