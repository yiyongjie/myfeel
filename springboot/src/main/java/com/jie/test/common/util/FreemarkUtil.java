package com.jie.test.common.util;

import com.jie.test.common.model.gen.GenContent;
import com.jie.test.common.smallDemo.DataUse;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class FreemarkUtil {
    private static final String CLASS_PATH = "/src/main/java/com/jie/test/model";

    public static void createData(String tableName){
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            //获取模版路径
            Resource resource = new ClassPathResource("templates");
            File file = resource.getFile();
            configuration.setDirectoryForTemplateLoading(file);
            //获取到这个数据库里所有的表
            List<String> tableNames=DataUse.getTableNames();
            boolean isContain=tableNames.contains(tableName);
            if(isContain){
                    //准备一下模板参数
                    Map<String, Object> dataMap = new HashMap<String, Object>();
                    dataMap.put("classPath", "com.jie.test.model");
//                    dataMap.put("tableName", tableName);
                    GenContent genContent=DataUse.getModel(tableName);
                    //把下斜杠去掉再把每个斜杠后面的字符大写
                    dataMap.put("genContent",genContent);
                    //  加载模版文件
                    Template template = configuration.getTemplate("test.ftl");
                    //生成数据,因拆多模块所以加模块，单模块不需要
                    String p="springboot"+CLASS_PATH +"/"+genContent.getClassName()+".java";
//            String p=System.getProperty("user.dir")+"/springboot"+CLASS_PATH +"/UserT.java";
                    File docFile = new File(p);
                    out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
                    //输出文件
                    template.process(dataMap, out);
                    System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^UserT.java 文件创建成功 !");
                }

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

    public static void main(String[] args) {
        createData("user_test");
    }
}
