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
    private static final String MODEL_PATH = "/src/main/java/com/jie/test/model";
    private static final String MAPPER_PATH = "/src/main/java/com/jie/test/dao";
    private static final String XML_PATH = "/src/main/resources/mapper";

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
                dataMap.put("modelClassPath", "com.jie.test.model");
                dataMap.put("mapperClassPath", "com.jie.test.dao");
                GenContent genContent=DataUse.getModel(tableName);
                dataMap.put("genContent",genContent);
                //  加载模版文件
                Template modelTemplate = configuration.getTemplate("model.ftl");
                Template mapperTemplate = configuration.getTemplate("mapper.ftl");
                Template xmlTemplate = configuration.getTemplate("xml.ftl");
                //生成数据位置,因拆多模块所以加模块，单模块不需要
                String modelPosition="springboot"+MODEL_PATH +"/"+genContent.getClassName()+".java";
                String mapperPosition="springboot"+MAPPER_PATH +"/"+genContent.getClassName()+".java";
                String xmlPosition="springboot"+XML_PATH +"/"+genContent.getClassName()+".xml";
                //输出model文件
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(modelPosition))));
                modelTemplate.process(dataMap, out);
                //输出mapper文件
                out=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(mapperPosition))));
                mapperTemplate.process(dataMap, out);
                //输出xml文件
                out=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(xmlPosition))));
                mapperTemplate.process(dataMap, out);
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
