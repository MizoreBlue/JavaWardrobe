package com.mizore.test;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mizore.pojo.TowerImgRecogVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Map;


/**
 * 杆塔图象识别 service 实现类
 *
 * @author MizoreBlue
 */
public class TowerImgRecogServiceImpl  {

    private static final String API_KEY = "sk-7d727a6bed1845bc9622a526ea115e93";
    private static final String API_URL = "https://dashscope.aliyuncs.com/compatible-mode/v1/chat/completions";
    private static final String MODEL_NAME = "qwen-vl-max";

    /**
     * 封装底层的 HTTP 请求细节
     */
    private JSONObject callVlmApi(JSONArray messages) {
        // 1. 准备请求头
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + API_KEY);
        headers.put("Content-Type", "application/json");

        // 2. 准备请求体
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", MODEL_NAME);
        requestBody.put("messages", messages);
        requestBody.put("temperature", 0.1);
        requestBody.put("extra_body", new JSONObject().fluentPut("enable_thinking", true));

        // 3. 发起请求并获取结果
        String responseJsonStr = HttpClientUtil.doPostJsonWithHeaders(API_URL, requestBody, headers);
//        清洗 Json
        responseJsonStr = responseJsonStr.replace("```json", "")
                .replace("```", "")
                .trim(); // 去除首尾空格

        return JSON.parseObject(responseJsonStr);
    }

    @Test
    public void recognizeByPath() {
        try {
            // 图片转 Base64
            String base64Image = ImageUtils.toBase64("D:\\GPNU\\Trainee\\yolo\\PowerTowerAI\\images\\cable\\done\\架线已完成4.png");
            String dataUri = "data:image/png;base64," + base64Image;

            // 构建符合 OpenAI 格式的 messages 数组
            JSONArray messages = new JSONArray();
            JSONObject userMsg = new JSONObject();
            userMsg.put("role", "user");

            JSONArray contentArray = new JSONArray();

            // 图片部分
            JSONObject imageObj = new JSONObject();
            imageObj.put("type", "image_url");
            imageObj.put("image_url", new JSONObject().fluentPut("url", dataUri));
            contentArray.add(imageObj);

            // 文字提示词部分
            JSONObject textObj = new JSONObject();
            textObj.put("type", "text");
            textObj.put("text", AiPromptConstants.TOWER_RECOGNITION_PROMPT);
            contentArray.add(textObj);

            userMsg.put("content", contentArray);
            messages.add(userMsg);

            // 4. 发起请求
            JSONObject apiResponse = callVlmApi(messages);

            // 5. 解析 API 返回的结果
            String content = apiResponse.getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");

//            解析 API返回的结果
            System.out.println("原始 content"+content);

            content = content.replaceAll("\\s+", "").trim();

            System.out.println("解析后 content"+content);

            // 将 AI 返回的 JSON 字符串转为 VO 对象
            TowerImgRecogVO towerImgRecogVO = JSONObject.parseObject(content, TowerImgRecogVO.class);
            System.out.println(towerImgRecogVO);

        } catch (Exception e) {
             e.printStackTrace();
            throw new RuntimeException("AI 识别调用失败: " + e.getMessage());
        }
    }
}