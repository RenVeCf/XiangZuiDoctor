package com.ipd.xiangzuidoctor.model;

import android.content.Context;

import com.ipd.xiangzuidoctor.api.Api;
import com.ipd.xiangzuidoctor.base.BaseModel;
import com.ipd.xiangzuidoctor.progress.ObserverResponseListener;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public class OfflineActivitiesModel<T> extends BaseModel {

    public void getOfflineActivitiesList(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                         ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getOfflineActivitiesList(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getOfflineActivitiesDetails(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                            ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getOfflineActivitiesDetails(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getOfflineActivitiesDetailsPay(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                               ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getOfflineActivitiesDetailsPay(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getOfflineActivitiesFree(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                         ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getOfflineActivitiesFree(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getOfflineActivitiesAliPay(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                           ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getOfflineActivitiesAliPay(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getOfflineActivitiesWechatPay(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                              ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getOfflineActivitiesWechatPay(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getOfflineActivitiesBalancePay(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                               ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getOfflineActivitiesBalancePay(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getOfflineActivitiesMy(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                               ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getOfflineActivitiesMy(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getOfflineActivitiesCancel(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                       ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getOfflineActivitiesCancel(map), observerListener, transformer, isDialog, cancelable);
    }
    //// TODO: 2017/12/27 其他需要请求、数据库等等的操作
}
