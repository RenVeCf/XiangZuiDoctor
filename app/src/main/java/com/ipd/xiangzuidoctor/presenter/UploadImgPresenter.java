package com.ipd.xiangzuidoctor.presenter;

import android.content.Context;

import com.ipd.xiangzuidoctor.bean.UploadImgBean;
import com.ipd.xiangzuidoctor.contract.UploadImgContract;
import com.ipd.xiangzuidoctor.model.UploadImgModel;
import com.ipd.xiangzuidoctor.progress.ObserverResponseListener;
import com.ipd.xiangzuidoctor.utils.ExceptionHandle;
import com.ipd.xiangzuidoctor.utils.ToastUtil;

import java.util.TreeMap;

import okhttp3.RequestBody;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public class UploadImgPresenter extends UploadImgContract.Presenter {

    private UploadImgModel model;
    private Context context;

    public UploadImgPresenter(Context context) {
        this.model = new UploadImgModel();
        this.context = context;
    }

    @Override
    public void getUploadImg(TreeMap<String, RequestBody> map, String sign, boolean isDialog, boolean cancelable) {
        model.getUploadImg(context, map, sign, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultUploadImg((UploadImgBean) o);
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