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

    /**
     * 订单
     */
    String ORDER_DETAILS = "ad/order/orderDetails"; //医生端订单-列表-详情


    /**
     * 医学专栏
     */
    String SPECIAL_COLUMN = "ad/medical/medicalList"; //医学专栏-列表
    String SPECIAL_COLUMN_DETAILS = "ad/medical/medicalDetails"; //专栏详情
    String SPECIAL_COLUMN_COLLECTION = "ad/medical/collection"; //医院专栏点击收藏

    /**
     * 线下活动
     */
    String OFFLINE_ACTIVITES_LIST = "ad/activity/activityList"; //线下活动-列表
    String OFFLINE_ACTIVITES_DETAILS = "ad/activity/activityDetails"; //活动详情
    String OFFLINE_ACTIVITES_DETAILS_PAY = "ad/activity/activityPayDetails"; //活动详情--购买页面
    String OFFLINE_ACTIVITES_FREE = "ad/activityRegist/freeRegist"; //免费报名
    String OFFLINE_ACTIVITES_ALI_PAY = "ad/activityRegist/alipay"; //支付包报名
    String OFFLINE_ACTIVITES_WECHAT_PAY = "ad/activityRegist/wechatPay"; //微信报名
    String OFFLINE_ACTIVITES_BALANCE_PAY = "ad/activityRegist/payBalance"; //余额报名
    String OFFLINE_ACTIVITES_MY = "ad/activity/myActivityList"; //我的活动列表
    String OFFLINE_ACTIVITES_CANCEL = "ad/activity/cancelRegist"; //活动-取消报名
}
