package com.ipd.xiangzuidoctor.api;

import com.ipd.xiangzuidoctor.bean.CaptchaBean;
import com.ipd.xiangzuidoctor.bean.CaptchaLoginBean;
import com.ipd.xiangzuidoctor.bean.HomeBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesAliPayBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesBalancePayBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesCancelBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesDetailsBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesDetailsPayBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesFreeBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesListBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesMyBean;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesWechatPayBean;
import com.ipd.xiangzuidoctor.bean.OrderDetailsBean;
import com.ipd.xiangzuidoctor.bean.PwdLoginBean;
import com.ipd.xiangzuidoctor.bean.RegistsBean;
import com.ipd.xiangzuidoctor.bean.ResetPwdBean;
import com.ipd.xiangzuidoctor.bean.SpecialColumnBean;
import com.ipd.xiangzuidoctor.bean.SpecialColumnCollectionBean;
import com.ipd.xiangzuidoctor.bean.SpecialColumnDetailsBean;
import com.ipd.xiangzuidoctor.bean.TitleListBean;
import com.ipd.xiangzuidoctor.bean.UploadImgBean;
import com.ipd.xiangzuidoctor.bean.VerifiedBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

import static com.ipd.xiangzuidoctor.common.config.UrlConfig.CAPTCHA;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.CAPTCHA_LOGIN;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.HOME;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.OFFLINE_ACTIVITES_ALI_PAY;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.OFFLINE_ACTIVITES_BALANCE_PAY;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.OFFLINE_ACTIVITES_CANCEL;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.OFFLINE_ACTIVITES_DETAILS;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.OFFLINE_ACTIVITES_DETAILS_PAY;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.OFFLINE_ACTIVITES_FREE;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.OFFLINE_ACTIVITES_LIST;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.OFFLINE_ACTIVITES_MY;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.OFFLINE_ACTIVITES_WECHAT_PAY;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.ORDER_DETAILS;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.PWD_LOGIN;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.REGISTER;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.RESET_PWD;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.SPECIAL_COLUMN;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.SPECIAL_COLUMN_COLLECTION;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.SPECIAL_COLUMN_DETAILS;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.TITLE_LIST;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.UPLOAD_IMG;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.VERIFIED;

/**
 * Description ：请求配置
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/27.
 */
public interface ApiService {

    //获取短信接口
    @FormUrlEncoded
    @POST(CAPTCHA)
    Observable<CaptchaBean> getCaptcha(@FieldMap Map<String, String> map);

    //注册
    @FormUrlEncoded
    @POST(REGISTER)
    Observable<RegistsBean> getRegists(@FieldMap Map<String, String> map);

    //验证码登录
    @FormUrlEncoded
    @POST(CAPTCHA_LOGIN)
    Observable<CaptchaLoginBean> getCaptchaLogin(@FieldMap Map<String, String> map);

    //密码登陆
    @FormUrlEncoded
    @POST(PWD_LOGIN)
    Observable<PwdLoginBean> getPwdLogin(@FieldMap Map<String, String> map);

    //重置密码
    @FormUrlEncoded
    @POST(RESET_PWD)
    Observable<ResetPwdBean> getResetPwd(@FieldMap Map<String, String> map);

    //首页数据
    @FormUrlEncoded
    @POST(HOME)
    Observable<HomeBean> getHome(@FieldMap Map<String, String> map);

    //职称-列表
    @FormUrlEncoded
    @POST(TITLE_LIST)
    Observable<TitleListBean> getTitleList(@FieldMap Map<String, String> map);

    //用户信息认证-修改保存
    @FormUrlEncoded
    @POST(VERIFIED)
    Observable<VerifiedBean> getVerified(@FieldMap Map<String, String> map);

    //上传图片
    @Multipart
    @POST(UPLOAD_IMG)
    Observable<UploadImgBean> getUploadImg(@Query("sign") String sign, @PartMap Map<String, RequestBody> map);

    //医生端订单-列表-详情
    @FormUrlEncoded
    @POST(ORDER_DETAILS)
    Observable<OrderDetailsBean> getOrderDetails(@FieldMap Map<String, String> map);

    //医学专栏-列表
    @FormUrlEncoded
    @POST(SPECIAL_COLUMN)
    Observable<SpecialColumnBean> getSpecialColumn(@FieldMap Map<String, String> map);

    //专栏详情
    @FormUrlEncoded
    @POST(SPECIAL_COLUMN_DETAILS)
    Observable<SpecialColumnDetailsBean> getSpecialColumnDetails(@FieldMap Map<String, String> map);

    //医院专栏点击收藏
    @FormUrlEncoded
    @POST(SPECIAL_COLUMN_COLLECTION)
    Observable<SpecialColumnCollectionBean> getSpecialColumnCollection(@FieldMap Map<String, String> map);

    //线下活动-列表
    @FormUrlEncoded
    @POST(OFFLINE_ACTIVITES_LIST)
    Observable<OfflineActivitiesListBean> getOfflineActivitiesList(@FieldMap Map<String, String> map);

    //活动详情
    @FormUrlEncoded
    @POST(OFFLINE_ACTIVITES_DETAILS)
    Observable<OfflineActivitiesDetailsBean> getOfflineActivitiesDetails(@FieldMap Map<String, String> map);

    //活动详情--购买页面
    @FormUrlEncoded
    @POST(OFFLINE_ACTIVITES_DETAILS_PAY)
    Observable<OfflineActivitiesDetailsPayBean> getOfflineActivitiesDetailsPay(@FieldMap Map<String, String> map);

    //免费报名
    @FormUrlEncoded
    @POST(OFFLINE_ACTIVITES_FREE)
    Observable<OfflineActivitiesFreeBean> getOfflineActivitiesFree(@FieldMap Map<String, String> map);

    //支付包报名
    @FormUrlEncoded
    @POST(OFFLINE_ACTIVITES_ALI_PAY)
    Observable<OfflineActivitiesAliPayBean> getOfflineActivitiesAliPay(@FieldMap Map<String, String> map);

    //微信报名
    @FormUrlEncoded
    @POST(OFFLINE_ACTIVITES_WECHAT_PAY)
    Observable<OfflineActivitiesWechatPayBean> getOfflineActivitiesWechatPay(@FieldMap Map<String, String> map);

    //余额报名
    @FormUrlEncoded
    @POST(OFFLINE_ACTIVITES_BALANCE_PAY)
    Observable<OfflineActivitiesBalancePayBean> getOfflineActivitiesBalancePay(@FieldMap Map<String, String> map);

    //我的活动列表
    @FormUrlEncoded
    @POST(OFFLINE_ACTIVITES_MY)
    Observable<OfflineActivitiesMyBean> getOfflineActivitiesMy(@FieldMap Map<String, String> map);

    //活动-取消报名
    @FormUrlEncoded
    @POST(OFFLINE_ACTIVITES_CANCEL)
    Observable<OfflineActivitiesCancelBean> getOfflineActivitiesCancel(@FieldMap Map<String, String> map);
}