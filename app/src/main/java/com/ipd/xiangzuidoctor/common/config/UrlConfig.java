package com.ipd.xiangzuidoctor.common.config;

/**
 * Description ：URL 配置
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public interface UrlConfig {
    /**
     * 域名
     */
    String BASE_URL = "http://47.244.137.174:8083/xz/";
    String BASE_LOCAL_URL = "http://47.244.137.174:8083/";

    /**
     * 登陆
     */
    String CAPTCHA = "ah/util/getSms"; //获取短信接口
    String REGISTER = "ad/login/regists"; //注册
    String CAPTCHA_LOGIN = "ad/login/smsCodelogin"; //验证码登录
    String PWD_LOGIN = "ad/login/login"; //密码登陆
    String RESET_PWD = "ad/login/passReset"; //重置密码

    /**
     * 首页
     */
    String HOME = "ad/index/indexData"; //首页数据
    String TITLE_LIST = "ad/user/titleList"; //职称-列表
    String VERIFIED = "ad/user/userApprove"; //用户信息认证-修改保存
    String UPLOAD_IMG = "ah/util/upload"; //上传图片
    String UPLOAD_IMGS = "ah/util/arrUpload"; //上传图片
}
