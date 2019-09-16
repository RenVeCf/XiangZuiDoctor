package com.ipd.xiangzuidoctor.contract;

import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.bean.CollectionListBean;
import com.ipd.xiangzuidoctor.bean.GetUserInfoBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface GetUserInfoContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultGetUserInfo(GetUserInfoBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getGetUserInfo(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}