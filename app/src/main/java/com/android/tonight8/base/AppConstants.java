package com.android.tonight8.base;

public class AppConstants {

    /**
     * 邮箱的正则表达式
     */
    public static final String strEMAIL = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

    // 手机相关信息
    /**
     * 手机的dpi
     */
    public static String dpi;
    /**
     * 手机的宽度(像素)
     */
    public static int widthPx;
    /**
     * 手机的高度(像素)
     */
    public static int heightPx;
    /**
     * 应用版本号
     */
    public static String version;
    /**
     * 设备类型,这里固定是0，代表android设备
     */
    public static String device_type = "0";
    /**
     * imei号
     */
    public static String imei;
    /**
     * 操作系统版本
     */
    public static String os_version;
    /**
     * 手机型号
     */
    public static String phone_model;
    /**
     * md5
     */
    public static String auth_code;
    /**
     * 定位得到的城市名称
     */
    public static String city_name;
    /**
     * 定位得到的经度
     */
    public static String longitude;
    /**
     * 定位得到的纬度
     */
    public static String latitude;
    /**
     * 定位得到的地址信息
     */
    public static String address;
    // --------------------------微信分享用的全局变量--------------------
    /**
     * 微信分享支持的最低版本号(二进制编码)
     */
    public static final int TIMELINE_SUPPORTED_VERSION = 0x21020001;

    // --------------------------微博授权时所需要的参数--------------------
    /**
     * 根目录
     */
    public static final String ROOT_DIR_PATH = "/tonight8/res";
    /**
     * 缓存目录
     */
    public static final String CACHE_DIR_PATH = ROOT_DIR_PATH + "/cache";
    // --------------------------接口返回的参数--------------------
    /**
     * 成功
     */
    public static final String SUCCESS = "success";
    /**
     * 失败
     */
    public static final String FAIL = "fail";
    /**
     * 错误
     */
    public static final String ERROR = "error";

    public static final String DESCRIPTOR = "com.umeng.share";

    private static final String TIPS = "请移步官方网站 ";
    private static final String END_TIPS = ", 查看相关说明.";
    public static final String TENCENT_OPEN_URL = TIPS + "http://wiki.connect.qq.com/android_sdk使用说明"
            + END_TIPS;
    public static final String PERMISSION_URL = TIPS + "http://wiki.connect.qq.com/openapi权限申请"
            + END_TIPS;

    public static final String SOCIAL_LINK = "http://www.umeng.com/social";
    public static final String SOCIAL_TITLE = "友盟社会化组件帮助应用快速整合分享功能";
    public static final String SOCIAL_IMAGE = "http://www.umeng.com/images/pic/banner_module_social.png";

    public static final String SOCIAL_CONTENT = "友盟社会化组件（SDK）让移动应用快速整合社交分享功能，我们简化了社交平台的接入，为开发者提供坚实的基础服务：（一）支持各大主流社交平台，" +
            "（二）支持图片、文字、gif动图、音频、视频；@好友，关注官方微博等功能" +
            "（三）提供详尽的后台用户社交行为分析。http://www.umeng.com/social";
    // --------------------------分享所需要的参数--------------------
    /**
     * 新浪微博的 APP_KEY
     */
    public static final String SINA_APP_KEY = "3956016765";
    /**
     * 新浪微博默认回调页：https://api.weibo.com/oauth2/default.html
     */
    public static final String SINA_REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";
    // --------------------------新浪微博授权时所需要的参数--------------------
    /**
     * Scope 新是 OAuth2.0 授权机制中 authorize 接口的一个参数。通过 Scope，平台将开放更多的微博信息
     */
    public static final String SINA_SCOPE = "email,direct_messages_read";
    /**
     * 新浪微博的 APP_SECRET
     */
    public static final String SINA_APP_SECRET = "2ea023b56f329f5bf5426416ddc3ddd7";
    /**
     * 微信的注册id
     */
    public static final String WX_APP_ID = "wxb5731b78779c42cf";
    /**
     * 微信的SECRET id
     */
    public static final String WX_APP_SECRET = "a3657f1d3e772ed6d576ccc455c6e082";
    /**QQ的注册appid*/
    public static final String QQ_APP_ID = "1101316332";
    /**QQ的注册appkey*/
    public static final String QQ_APP_KEY = "c7394704798a158208a74ab60104f0ba";

}
