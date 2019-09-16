package com.ipd.xiangzuidoctor.api;

import com.ipd.xiangzuidoctor.base.BaseApi;

import static com.ipd.xiangzuidoctor.common.config.UrlConfig.BASE_LOCAL_URL;

/**
 * Description ：处理请求链接的地方
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/27.
 */

public class H5Api {

    private volatile static ApiService apiService;

    public static ApiService getH5ApiService() {
        if (apiService == null) {
            synchronized (H5Api.class) {
                if (apiService == null) {
                    new H5Api();
                }
            }
        }
        return apiService;
    }

    private H5Api() {
        BaseApi baseApi = new BaseApi();
        apiService = baseApi.getRetrofit(BASE_LOCAL_URL).create(ApiService.class);
    }
}
