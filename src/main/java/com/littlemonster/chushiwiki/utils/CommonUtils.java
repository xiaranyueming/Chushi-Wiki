package com.littlemonster.chushiwiki.utils;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.littlemonster.chushiwiki.common.Constant;
import com.littlemonster.chushiwiki.entity.domain.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: white-zhou
 * @date: 2024-04-06 19:01
 * @description: 通用工具类
 **/
public class CommonUtils {


    // md5加密
    public static String md5(String str) {
        return DigestUtil.md5Hex(Constant.SALT_PREFIX + str + Constant.SALT_SUFFIX);
    }


    // 生成jwt token
    public static String createToken(User user) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", user.getId());
        map.put("userName", user.getUserName());

        return JWTUtil.createToken(map, Constant.TOKEN_KEY.getBytes());
    }


    // 解析jwt token
    public static Map<String, Object> parseToken(String token) {
        Map<String, Object> map = new HashMap<>();

        JWT jwt = JWTUtil.parseToken(token);
        Object userId = jwt.getPayload("userId");
        Object userName = jwt.getPayload("userName");

        map.put("userId", userId);
        map.put("userName", userName);

        return map;
    }
}
