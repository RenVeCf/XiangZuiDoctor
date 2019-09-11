package com.ipd.xiangzuidoctor.presenter;

import android.content.Context;

import com.ipd.xiangzuidoctor.bean.SpecialColumnBean;
import com.ipd.xiangzuidoctor.bean.SpecialColumnCollectionBean;
import com.ipd.xiangzuidoctor.bean.SpecialColumnDetailsBean;
import com.ipd.xiangzuidoctor.contract.SpecialColumnContract;
import com.ipd.xiangzuidoctor.model.SpecialColumnModel;
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
public class SpecialColumnPresenter extends SpecialColumnContract.Presenter {

    private SpecialColumnModel model;
    private Context context;

    public SpecialColumnPresenter(Context context) {
        this.model = new SpecialColumnModel();
        this.context = context;
    }

    @Override
    public void getSpecialColumn(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getSpecialColumn(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultSpecialColumn((SpecialColumnBean) o);
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
    public void getSpecialColumnDetails(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getSpecialColumnDetails(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultSpecialColumnDetails((SpecialColumnDetailsBean) o);
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
    public void getSpecialColumnCollection(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getSpecialColumnCollection(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultSpecialColumnCollection((SpecialColumnCollectionBean) o);
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