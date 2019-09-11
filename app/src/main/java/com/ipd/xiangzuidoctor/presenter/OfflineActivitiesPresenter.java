package com.ipd.xiangzuidoctor.presenter;

import android.content.Context;

import com.ipd.xiangzuidoctor.bean.OfflineActivitiesAliPayBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesBalancePayBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesCancelBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesDetailsBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesDetailsPayBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesFreeBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesListBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesMyBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesWechatPayBean;
import com.ipd.xiangzuidoctor.contract.OfflineActivitiesContract;
import com.ipd.xiangzuidoctor.model.OfflineActivitiesModel;
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
public class OfflineActivitiesPresenter extends OfflineActivitiesContract.Presenter {

    private OfflineActivitiesModel model;
    private Context context;

    public OfflineActivitiesPresenter(Context context) {
        this.model = new OfflineActivitiesModel();
        this.context = context;
    }

    @Override
    public void getOfflineActivitiesList(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getOfflineActivitiesList(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultOfflineActivitiesList((OfflineActivitiesListBean) o);
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
    public void getOfflineActivitiesDetails(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getOfflineActivitiesDetails(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultOfflineActivitiesDetails((OfflineActivitiesDetailsBean) o);
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
    public void getOfflineActivitiesDetailsPay(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getOfflineActivitiesDetailsPay(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultOfflineActivitiesDetailsPay((OfflineActivitiesDetailsPayBean) o);
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
    public void getOfflineActivitiesFree(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getOfflineActivitiesFree(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultOfflineActivitiesFree((OfflineActivitiesFreeBean) o);
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
    public void getOfflineActivitiesAliPay(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getOfflineActivitiesAliPay(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultOfflineActivitiesAliPay((OfflineActivitiesAliPayBean) o);
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
    public void getOfflineActivitiesWechatPay(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getOfflineActivitiesWechatPay(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultOfflineActivitiesWechatPay((OfflineActivitiesWechatPayBean) o);
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
    public void getOfflineActivitiesBalancePay(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getOfflineActivitiesBalancePay(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultOfflineActivitiesBalancePay((OfflineActivitiesBalancePayBean) o);
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
    public void getOfflineActivitiesMy(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getOfflineActivitiesMy(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultOfflineActivitiesMy((OfflineActivitiesMyBean) o);
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
    public void getOfflineActivitiesCancel(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getOfflineActivitiesCancel(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultOfflineActivitiesCancel((OfflineActivitiesCancelBean) o);
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