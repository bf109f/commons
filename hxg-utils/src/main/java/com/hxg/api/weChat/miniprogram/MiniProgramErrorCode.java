package com.hxg.api.weChat.miniprogram;

import java.util.HashMap;
import java.util.Map;

public class MiniProgramErrorCode
{
    private static Map<Integer, String> errorCode = new HashMap<>();

    static
    {
        errorCode.put(0, "成功");
        errorCode.put(40003, "touser字段openid为空或者不正确");
        errorCode.put(40037, "订阅模板id为空不正确");
        errorCode.put(43101, "用户拒绝接受消息，如果用户之前曾经订阅过，则表示用户取消了订阅关系");
        errorCode.put(47003, "模板参数不准确，可能为空或者不满足规则，errmsg会提示具体是哪个字段出错");
        errorCode.put(41030, "page路径不正确，需要保证在现网版本小程序中存在，与app.json保持一致");
        errorCode.put(200014, "模版 tid 参数错误");
        errorCode.put(200020, "关键词列表 kidList 参数错误");
        errorCode.put(200021, "场景描述 sceneDesc 参数错误");
        errorCode.put(200011, "此账号已被封禁，无法操作");
        errorCode.put(200013, "此模版已被封禁，无法选用");
        errorCode.put(200012, "个人模版数已达上限，上限25个");
        errorCode.put(40029, "code 无效");
        errorCode.put(45011, "频率限制，每个用户每分钟100次");
    }

    public static Map<Integer, String> getErrorCode()
    {
        return errorCode;
    }
}
