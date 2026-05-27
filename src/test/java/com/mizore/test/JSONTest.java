package com.mizore.test;
import com.alibaba.fastjson.JSONObject;
import com.mizore.pojo.TowerImgRecogVO;

public class JSONTest {
    public static void main(String[] args) {
        String testJson = "{\"工序\":\"杆塔组立\",\"匹配锚点\":\"锚点F(塔身主体完成，正在安装横担)\",\"预估百分比\":\"75%\",\"置信度\":\"高\",\"推理链\":\"从画面视角可见塔身结构已基本完整...\"}";
        TowerImgRecogVO vo = JSONObject.parseObject(testJson, TowerImgRecogVO.class);
        System.out.println(vo);
    }
}
