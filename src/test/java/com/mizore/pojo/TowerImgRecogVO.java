package com.mizore.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class TowerImgRecogVO {

    @JSONField(name = "工序")
    private String processStage;

    @JSONField(name = "匹配锚点")
    private String matchedAnchor;

    @JSONField(name = "预估百分比")
    private String estimatedPercent;

    @JSONField(name = "置信度")
    private String confidence;

    @JSONField(name = "推理链")
    private String reasoningChain;
}