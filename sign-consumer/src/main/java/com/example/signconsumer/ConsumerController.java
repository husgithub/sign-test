package com.example.signconsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.StringJoiner;
import java.util.TreeMap;

@Slf4j
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    private static final String APP_KEY = "A";
    private static final String SECRET_KEY = "123456";

    private static final String URL = "http://127.0.0.1:9091/provider/hello";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello(RequestBean requestBean) {
        //使用 TreeMap 可对key排序
        Map<String, Object> params = new TreeMap<>();
        params.put("appKey", APP_KEY);
        params.put("timestamp", System.currentTimeMillis() / 1000);
        params.put("limit", requestBean.getLimit());
        params.put("offset", requestBean.getOffset());
        params.put("query", requestBean.getQuery());
        //生成签名
        String sign = getSign(params);
        log.debug("sign:{}", sign);
        params.put("sign", sign);
        StringJoiner stringJoiner = new StringJoiner("&");
        params.forEach((k, v) -> stringJoiner.add(k + "=" + v));
        ResponseEntity<String> result = restTemplate.getForEntity(URL + "?" + stringJoiner, String.class);
        return result.getBody();
    }

    private String getSign(Map<String, Object> params) {
        StringJoiner stringJoiner = new StringJoiner("&");
        params.forEach((k, v) -> stringJoiner.add(k + "=" + v));
        log.debug("stringJoiner:" + stringJoiner);
        String paramStr = stringJoiner.toString() + SECRET_KEY;
        return DigestUtils.md5DigestAsHex(paramStr.getBytes(StandardCharsets.UTF_8));
    }
}
