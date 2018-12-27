package com.jie.test.common.config.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.csj.linkorder.linkorderapp.common.exception.AppException;
import com.csj.linkorder.linkorderapp.common.model.APIResponse;
import com.csj.linkorder.linkorderapp.dao.UserTokenMapper;
import com.csj.linkorder.linkorderapp.model.UserToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class UserConfig implements HandlerInterceptor {

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        JSONObject param= null;
        try {
            BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }

            param = JSONObject.parseObject(responseStrBuilder.toString());
            String phone=param.getString("telphone");
            String token=param.getString("token");
            UserToken userToken=new UserToken();
            if(StringUtils.isNotBlank(phone)){
                userToken=userTokenMapper.getUserTokenByMobile(phone);
            }
            if(userToken==null){
                throw new AppException(APIResponse.FAIL,"用户未登录");
            }
            long cur=System.currentTimeMillis();
            long ago = userToken.getCreateDate().getTime();
            long userTime=cur-ago/1000;
            if(userTime>1200){
                throw new AppException(APIResponse.FAIL,"已经失效了请重新登录");
            }
            if(!userToken.equals(token)){
                throw new AppException(APIResponse.FAIL,"已经失效了请重新登录");
            }
            System.out.println("菜鸡陈斌不要玩打野了真菜已经不能一带四了");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
