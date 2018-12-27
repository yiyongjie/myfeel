package com.jie.test.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.csj.linkorder.linkorderapp.common.model.SmsVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Slf4j
public class HttpRequestUtil {

    public static SmsVO postData(String url, Map<String, Object> params) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        SmsVO response = null;
        try {
            String val = JSON.toJSON(params).toString();
            StringEntity s = new StringEntity(val, Charset.forName("UTF-8"));
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");
            post.setEntity(s);
            HttpResponse res = httpClient.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = toBean(result, SmsVO.class);
            }
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static SmsVO postDataFrom(String url, Map<String, Object> params) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String trueUrl = makeUrl(url, params);
        HttpPost post = new HttpPost(trueUrl);
        SmsVO response = null;
        try {
//            StringEntity s = new StringEntity(JSON.toJSON(params).toString(), Charset.forName("UTF-8"));
//            s.setContentEncoding("UTF-8");
//            s.setContentType("application/x-www-form-urlencoded");
//            post.setEntity(s);
            HttpResponse res = httpClient.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = toBean(result, SmsVO.class);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static String makeUrl(String url, Map<String, Object> map) {
        Set<String> s = map.keySet();
        Iterator iterator = s.iterator();
        StringBuffer sb = new StringBuffer();
        while (iterator.hasNext()) {
            sb.append("&");
            String key = String.valueOf(iterator.next());
            sb.append(key + "=");
            String value = String.valueOf(map.get(key));
            sb.append(value);
        }
        sb.deleteCharAt(0);
        String trueUrl = url + "?" + sb.toString();
        return trueUrl;
    }


    public static <T> T toBean(String jsonStr, Class<T> beanClass) {
        JSONObject jo = JSON.parseObject(jsonStr);
        jo.put(JSON.DEFAULT_TYPE_KEY, beanClass.getName());
        return JSON.parseObject(jo.toJSONString(), beanClass);
    }

}