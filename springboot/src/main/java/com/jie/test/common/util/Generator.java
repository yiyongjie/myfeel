//package com.jie.test.common.util;
//
//import org.assertj.core.util.Lists;
//import org.mybatis.generator.api.MyBatisGenerator;
//import org.mybatis.generator.config.Configuration;
//import org.mybatis.generator.config.xml.ConfigurationParser;
//import org.mybatis.generator.internal.DefaultShellCallback;
//
//import java.io.File;
//import java.util.List;
//
//public class Generator {
//    public void generate() throws Exception {
//
//        List<String> warnings = Lists.newArrayList();
//        boolean overwrite = true;
//
//        //需要指定上一步创建的配置文件在计算机中的绝对路径
//        File configFile = new File(this.getClass().getResource("/").getPath() + "generatorConfig.xml");
//        ConfigurationParser cp = new ConfigurationParser(warnings);
//        Configuration config = cp.parseConfiguration(configFile);
//        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
//        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
//                callback, warnings);
//        myBatisGenerator.generate(null);
//
//    }

//    public static void main(String[] args) throws Exception {
//        try {
//            Generator gen = new Generator();
//            gen.generate();
//            System.out.println("Generate Successfully!");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
