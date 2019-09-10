package com.ipd.xiangzuidoctor.contract;

import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.bean.TitleListBean;
import com.ipd.xiangzuidoctor.bean.VerifiedBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface VerifiedContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultTitleList(TitleListBean data);

        void resultVerified(VerifiedBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getTitleList(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getVerified(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}