package com.ipd.xiangzuidoctor.contract;

import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.bean.CaptchaBean;
import com.ipd.xiangzuidoctor.bean.CaptchaLoginBean;
import com.ipd.xiangzuidoctor.bean.PwdLoginBean;
import com.ipd.xiangzuidoctor.bean.RegistsBean;
import com.ipd.xiangzuidoctor.bean.ResetPwdBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface LoginContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultCaptcha(CaptchaBean data);

        void resultRegists(RegistsBean data);

        void resultCaptchaLogin(CaptchaLoginBean data);

        void resultPwdLogin(PwdLoginBean data);

        void resultResetPwd(ResetPwdBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getCaptcha(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getRegists(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getCaptchaLogin(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getPwdLogin(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getResetPwd(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}