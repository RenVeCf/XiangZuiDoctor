package com.ipd.xiangzuidoctor.contract;

import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.bean.SpecialColumnBean;
import com.ipd.xiangzuidoctor.bean.SpecialColumnCollectionBean;
import com.ipd.xiangzuidoctor.bean.SpecialColumnDetailsBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface SpecialColumnContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultSpecialColumn(SpecialColumnBean data);

        void resultSpecialColumnDetails(SpecialColumnDetailsBean data);

        void resultSpecialColumnCollection(SpecialColumnCollectionBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getSpecialColumn(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getSpecialColumnDetails(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getSpecialColumnCollection(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}