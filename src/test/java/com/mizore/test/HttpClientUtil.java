package com.mizore.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Http 工具类
 *
 */
public class HttpClientUtil {

    // 统一超时时间配置 (单位: 毫秒)
    private static final int TIMEOUT_MSES = 100 * 1000; // 建议适当延长超时时间，AI接口响应可能较慢

    // 使用静态连接池，避免频繁创建销毁连接带来的性能开销
    private static final CloseableHttpClient HTTP_CLIENT = HttpClients.custom()
            .setMaxConnTotal(100) // 最大连接数
            .setMaxConnPerRoute(50) // 每个路由的最大连接数
            .build();

    /**
     * 发送GET 方式请求
     * @param url 请求地址
     * @param paramMap 请求参数
     * @return 响应字符串
     */
    public static String doGet(String url, Map<String, String> paramMap) {
        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            if (paramMap != null) {
                for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                    builder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            URI uri = builder.build();
            HttpGet httpGet = new HttpGet(uri);

            // 设置超时配置
            httpGet.setConfig(builderRequestConfig());

            response = HTTP_CLIENT.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(response);
        }
        return "";
    }

    /**
     * 发送POST方式请求 (表单格式 application/x-www-form-urlencoded)
     * @param url 请求地址
     * @param paramMap 请求参数
     * @return 响应字符串
     */
    public static String doPost(String url, Map<String, String> paramMap) {
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(builderRequestConfig());

            if (paramMap != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (Map.Entry<String, String> param : paramMap.entrySet()) {
                    paramList.add(new BasicNameValuePair(param.getKey(), param.getValue()));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "UTF-8");
                httpPost.setEntity(entity);
            }

            response = HTTP_CLIENT.execute(httpPost);
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(response);
        }
        return "";
    }

    /**
     * 发送POST方式请求 (JSON格式 application/json) - 不带自定义Header
     * @param url 请求地址
     * @param jsonParam JSON 格式的请求体对象
     * @return 响应字符串
     */
    public static String doPostJson(String url, JSONObject jsonParam) {
        return doPostJsonWithHeaders(url, jsonParam, null);
    }

    /**
     * 【新增】发送POST方式请求 (JSON格式 application/json) - 支持自定义Header
     * 专门用于调用需要鉴权（如Bearer Token）的第三方API
     * @param url 请求地址
     * @param jsonParam JSON 格式的请求体对象
     * @param headerMap 自定义请求头 (例如: Authorization, Content-Type等)
     * @return 响应字符串
     */
    public static String doPostJsonWithHeaders(String url, JSONObject jsonParam, Map<String, String> headerMap) {
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(builderRequestConfig());

            // 1. 设置自定义请求头
            if (headerMap != null && !headerMap.isEmpty()) {
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            } else {
                // 默认设置 JSON 类型
                httpPost.setHeader("Content-Type", "application/json");
            }

            // 2. 设置 JSON 请求体
            if (jsonParam != null) {
                StringEntity entity = new StringEntity(jsonParam.toJSONString(), "UTF-8");
                entity.setContentEncoding("UTF-8");
                // 如果headerMap中没有指定Content-Type，这里兜底设置一下
                if (headerMap == null || !headerMap.containsKey("Content-Type")) {
                    entity.setContentType("application/json");
                }
                httpPost.setEntity(entity);
            }

            response = HTTP_CLIENT.execute(httpPost);
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(response);
        }
        return "";
    }

    /**
     * 构建统一的请求超时配置
     */
    private static RequestConfig builderRequestConfig() {
        return RequestConfig.custom()
                .setConnectTimeout(TIMEOUT_MSES)          // 连接建立超时时间
                .setConnectionRequestTimeout(TIMEOUT_MSES) // 从连接池获取连接的超时时间
                .setSocketTimeout(TIMEOUT_MSES)           // 数据传输超时时间
                .build();
    }

    /**
     * 统一关闭资源
     */
    private static void closeResources(CloseableHttpResponse response) {
        try {
            if (response != null) {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}