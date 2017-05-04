package com.zykj.onexiubrother.utils;

/**
 * 地址工具类
 * Created by Administrator on 2017/4/15.
 */

public class YURL {


    // 服务器地址
    public static final String BASE_HOST = "http://221.207.184.124:7071/";


    //项目名称
    public static final String HOST = BASE_HOST + "yxg/";


    //查找手机品牌
    public static final String FIND_PHONE_BRAND = HOST + "findPhoneBrand";
    //查找电脑品牌
    public static final String FIND_COMPUTER_BRAND = HOST + "findComputerBrand";


    //根据品牌查找型号
    public static final String FIND_PHONE_MODEL = HOST + "findPhoneModel";
    //根据品牌查找电脑型号
    public static final String FIND_BY_COMPUTER_MODEL = HOST + "findByComputerModel";
    //根据品牌查找电脑类型
    public static final String FIND_COMPUTER_CATEGORY = HOST + "findComputerCategory";

    //查询手机故障
    public static final String FIND_PHONE_FAULT = HOST + "findPhoneFault";
    //查找家电品牌
    public static final String FIND_BY_APPLIANCE_BRAND = HOST + "findByApplianceBrand";
    //查找家电型号
    public static final String FIND_BY_APPLIANCE_MODEL = HOST + "findByApplianceModel";
    //查找加点类型
    public static final String FIND_APPLIANCE_CATEGORY = HOST + "findApplianceCategory";
    //注册
    public static final String REGISTER = HOST + "register";
    //设置密码
    public static final String SETPASSWORD = HOST + "setpassword";
    //登录
    public static final String LOGIN = HOST + "login";
    //上传头像
    public static final String UP_LOAD_ICON = HOST + "uploadIcon";
    //上传用户信息
    public static final String SET_USER_INFO = HOST + "setUserInfo";
    //身份证认证
    public static final String UP_LOAD_ID_CARD = HOST + "uploadIdCard";
    //添加地址
    public static final String ADD_ADDRESS = HOST + "addaddress";
    //查询地址
    public static final String SELECT_ADDRESS = HOST + "selectAddress";
    //删除地址
    public static final String DEL_ADDRESS = HOST + "delAddress";
    //删除地址
    public static final String DEF_ADDRESS = HOST + "defAddress";
    //发布订单
    public static final String ADD_ORDER = HOST + "addOrder";
    //查找订单
    public static final String FIND_ORDER_BY_STATE = HOST + "findOrderByState";
    //删除订单
    public static final String DEL_ORDER = HOST + "delOrder";

}
