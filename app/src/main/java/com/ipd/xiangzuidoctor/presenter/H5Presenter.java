package com.ipd.xiangzuidoctor.presenter;

import android.content.Context;

import com.ipd.xiangzuidoctor.bean.H5Bean;
import com.ipd.xiangzuidoctor.contract.H5Contract;
import com.ipd.xiangzuidoctor.model.H5Model;
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
public class H5Presenter extends H5Contract.Presenter {

    private H5Model model;
    private Context context;

    public H5Presenter(Context context) {
        this.model = new H5Model();
        this.context = context;
    }

    @Override
    public void getH5(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getH5(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultH5((H5Bean) o);
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