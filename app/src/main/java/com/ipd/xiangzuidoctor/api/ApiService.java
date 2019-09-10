package com.ipd.xiangzuidoctor.api;

import com.ipd.xiangzuidoctor.bean.CaptchaBean;
import com.ipd.xiangzuidoctor.bean.CaptchaLoginBean;
import com.ipd.xiangzuidoctor.bean.HomeBean;
import com.ipd.xiangzuidoctor.bean.PwdLoginBean;
import com.ipd.xiangzuidoctor.bean.RegistsBean;
import com.ipd.xiangzuidoctor.bean.ResetPwdBean;
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
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.PWD_LOGIN;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.REGISTER;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.RESET_PWD;
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
}