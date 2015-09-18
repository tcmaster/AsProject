package com.android.tonight8.dao.model.other;

/**
 * 类描述:
 * 创建人：asus
 * 创建时间：2015/9/18 11:29
 * 修改时间：2015/9/18 11:29
 * 修改备注：
 */

public class ShareThirdEntity {
    /**
     * 分享的标题,最长30个字符
     */
    public String title;
    /**
     * 分享图片的URL，音乐只支持网络链接,分享类型是纯图片时需要分享的本地图片路径
     */
    public String imageUrl;
    /**
     * 手Q客户端顶部，替换“返回”按钮文字，如果为空，用返回代替
     */
    public String appName;
    /**
     * 分享后好友点击的url地址
     */
    public String targetUrl;
    /**
     * 分享类型（ 图文1、网页2、纯图片3 、分享音乐4、应用分享5 、视频6。类型是图文时：targetUrl和title不能为空
     */
    public int shareType;
    /**
     * 分享的消息摘要,最长40个字
     */
    public String summary;
    /**
     * 分享的音乐时必填的链接
     */
    public String audioUrl;

}
