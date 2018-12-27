package com.jie.test.common.config.rest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestClientException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class CustomResponseErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return super.hasError(response);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        String body = IOUtils.toString(response.getBody(), StandardCharsets.UTF_8);
        String transBody = unicodeToString(body);
        log.info(transBody);
        String[] strArray = transBody.split("\"message\":");
        String finalMessage = strArray[1].trim().replaceAll("\"", "").replace("}", "");
        try {
            super.handleError(response);
        } catch (RestClientException rcx) {
            throw new CustomRestClientException(rcx.getMessage(), rcx, finalMessage);
        }
    }

    /**
     * unicode编码转中文
     *
     * @param str unicode编码字串
     * @return 转码后的中文字串
     */
    private String unicodeToString(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            String group = matcher.group(2);
            ch = (char) Integer.parseInt(group, 16);
            String group1 = matcher.group(1);
            str = str.replace(group1, ch + "");
        }
        return str;
    }
}
