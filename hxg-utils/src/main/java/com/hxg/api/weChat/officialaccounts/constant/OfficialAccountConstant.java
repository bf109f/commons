package com.hxg.api.weChat.officialaccounts.constant;

/**
 * 微信公众号相关常量
 */
public interface OfficialAccountConstant
{
    /**
     * 事件消息
     */
    String MESSAGE_TYPE_EVENT = "event";

    /**
     * 文本消息
     */
    String MESSAGE_TYPE_TEXT = "text";

    /**
     * 图片消息
     */
    String MESSAGE_TYPE_IMAGE = "image";

    /**
     * 语音消息
     */
    String MESSAGE_TYPE_VOICE = "voice";

    /**
     * 视频消息
     */
    String MESSAGE_TYPE_VIDEO = "video";

    /**
     * 短视频消息
     */
    String MESSAGE_TYPE_SHORT_VIDEO = "shortvideo";

    /**
     * 位置消息
     */
    String MESSAGE_TYPE_LOCATION = "location";

    /**
     * 链接消息
     */
    String MESSAGE_TYPE_LINK = "link";

    /**
     * 订阅 用户未关注时，进行关注后的事件推送
     */
    String MESSAGE_EVENT_SUBSCRIBE = "subscribe";

    /**
     * 取消订阅
     */
    String MESSAGE_EVENT_UNSUBSCRIBE = "unsubscribe";

    /**
     * 用户已关注时的事件推送
     */
    String MESSAGE_EVENT_SCAN = "SCAN";

    /**
     * 地理位置事件
     */
    String MESSAGE_EVENT_LOCATION = "LOCATION";

    /**
     * 自定义菜单事件
     */
    String MESSAGE_EVENT_CLICK = "CLICK";

    /**
     * 点击菜单跳转链接时的事件推送
     */
    String MESSAGE_EVENT_VIEW = "VIEW";

    
}
