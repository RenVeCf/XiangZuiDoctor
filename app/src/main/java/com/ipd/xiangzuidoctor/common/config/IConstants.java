package com.ipd.xiangzuidoctor.common.config;

/**
 * Description ：公共配置类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public interface IConstants {
    /**
     * 包名
     */
    String PACKAGE_NAME = "com.ipd.xiangzuidoctor";

    /**
     * SharedPreferences
     * 共享参数
     */
    String FIRST_APP = "is_first"; //第一次进应用
    String IS_LOGIN = "is_login"; //已经登录
    String IS_SUPPLEMENT_INFO = "is_supplement_info"; // 1 未认证 2认证了
    String USER_ID = "user_id"; //用户标识
    String NAME = "name"; //用户真实姓名
    String NIKE_NAME = "nike_name"; //用户姓名
    String PHONE = "phone"; //用户手机号码
    String SERVICE_PHONE = "15139877951"; //咨询客服号码
    String AVATAR = "avatar"; //头像
    String LATIUDE = "latitude"; //经度
    String LONGTITUDE = "longtitude"; //纬度
    String CITY = "city"; //城市
    String TOKEN = "is_token"; //token
    String SIGN = "40777B235DFE79175B6D921D1B7536C4"; //签名后缀
    int JPUSH_SEQUENCE = 100; //极光精准推送序列


    /**
     * requestCode
     * 请求码
     */
    int REQUEST_CODE_90 = 90;//实名认证照片上传
    int REQUEST_CODE_91 = 91;//实名认证身份证上传
    int REQUEST_CODE_92 = 92;//实名认证执业资格证/医师资格证上传
    int REQUEST_CODE_93 = 93;//实名认证胸牌上传
    int REQUEST_CODE_94 = 94;//结束手术麻醉单上传
    int REQUEST_CODE_95 = 95;//医生信息回跳
    int REQUEST_CODE_96 = 96;//订单详情-开始手术回跳
    int REQUEST_CODE_97 = 97;//订单-进行中-结束手术-结束手术界面回跳刷新
    int REQUEST_CODE_98 = 98;// 详情回跳刷新列表
    int REQUEST_CODE_99 = 99;//钱包回跳刷新
    int REQUEST_CODE_100 = 100;//首页刷新回跳
    int REQUEST_CODE_101 = 101;//
    int REQUEST_CODE_102 = 102;//
    int REQUEST_CODE_103 = 103;//
    int REQUEST_CODE_104 = 104;//
    int REQUEST_CODE_105 = 105;//
    int REQUEST_CODE_106 = 106;
    int REQUEST_CODE_107 = 107;
    int REQUEST_CODE_108 = 108;
    int REQUEST_CODE_109 = 109;

    /**
     * resultCode
     * 返回码
     */
    int RESULT_CODE = 0;
}
