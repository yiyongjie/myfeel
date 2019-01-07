package com.jie.test.common.smallDemo;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestFreeMarker {
    private static final String TEMPLATE_PATH = "src/main/resources/templates";
    private static final String CLASS_PATH = "/src/main/java/com/jie/test/model";

    public static void main(String[] args) {
        // step1 创建freeMarker配置实例
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            // step2 获取模版路径
            Resource resource = new ClassPathResource("templates");
            File file = resource.getFile();
            configuration.setDirectoryForTemplateLoading(file);
            // step3 创建数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("classPath", "com.jie.test.model");
            dataMap.put("className", "UserT");
            dataMap.put("Id", "id");
            dataMap.put("userName", "userName");
            dataMap.put("password","password");
            // step4 加载模版文件
            Template template = configuration.getTemplate("test.ftl");
            // step5 生成数据
            //因拆多模块所以加模块，单模块不需要
            String p="springboot"+CLASS_PATH +"/UserT.java";
//            String p=System.getProperty("user.dir")+"/springboot"+CLASS_PATH +"/UserT.java";
            File docFile = new File(p);
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            // step6 输出文件
            template.process(dataMap, out);
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^UserT.java 文件创建成功 !");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
