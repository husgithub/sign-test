package com.example.signprovider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.TreeMap;

@Slf4j
@RestController
@RequestMapping("/provider")
public class HelloController {

    private static final long EXPIRE_TIME = 5;

    /**
     * 不同系统对应不同 appKey 和 secretKey
     */
    private static final Map<String, String> APP_KEY_MAP = new HashMap<>();

    static {
        APP_KEY_MAP.put("A", "123456");
    }

    @GetMapping("/hello")
    public String hello(RequestBean requestBean) {
        //获取客户端 appKey
        String appKey = requestBean.getAppKey();
        Assert.isTrue(APP_KEY_MAP.containsKey(appKey), "无效appKey！");
        //客户端传入的签名
        String requestSign = requestBean.getSign();
        //检查有无传入签名
        Assert.hasText(requestSign, "无效签名！");
        long requestTime = requestBean.getTimestamp();
        //如果请求发起时间与当前时间超过expireTime，则接口请求过期
        Assert.isTrue(System.currentTimeMillis() / 1000 - requestTime <= EXPIRE_TIME, "请求过期！");
        //生成签名
        String sign = "";
        try {
            sign = getSign(requestBean, APP_KEY_MAP.get(appKey));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("获取签名失败！");
        }
        //比对签名与传入签名是否一致
        Assert.isTrue(requestSign.equals(sign), "无效签名！");
        return "接口调用成功：" + requestBean;
    }

    private String getSign(RequestBean requestBean, String secretKey) throws IllegalAccessException {
        Map<String, Object> map = new TreeMap<>(String::compareTo);
        Field[] fields = requestBean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (!"sign".equals(field.getName())) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(requestBean));
            }
        }
        StringJoiner stringJoiner = new StringJoiner("&");
        map.forEach((k, v) -> stringJoiner.add(k + "=" + v));
        log.debug("stringJoiner:" + stringJoiner);
        String paramStr = stringJoiner + secretKey;
        //MD5加密
        return DigestUtils.md5DigestAsHex(paramStr.getBytes(StandardCharsets.UTF_8));
    }

}
