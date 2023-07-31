package com.example.signconsumer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestBean {
    //来源渠道，不同系统分配不同 appKey
    private String appKey;
    // 签名
    private String sign;
    //10位时间戳
    private long timestamp;
    //每次查询数据条数
    private Integer limit;
    //开始查询下标
    private Integer offset;
    //查询关键词
    private String query;
}
