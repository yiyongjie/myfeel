//package com.jie.test.common.util;
//
//import com.csj.linkorder.linkorderapp.common.constant.SmsConStant;
//import com.csj.linkorder.linkorderapp.common.exception.AppException;
//import com.csj.linkorder.linkorderapp.common.model.APIResponse;
//import com.csj.linkorder.linkorderapp.common.model.SmsVO;
//import com.google.common.collect.Maps;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Map;
//
//public class SmsUtil {
//    public static int sms(String telphone) {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Map<String, Object> map = Maps.newHashMap();
//        map.put("accountSid", SmsConStant.SMS_ACCOUNTID);
//        map.put("to", "13606772657");
//        map.put("templateid", SmsConStant.SMS_TEMPID);
//        String date = df.format(new Date()).replace("-", "").replace(":", "").replace(" ", "");
//        map.put("timestamp", date);
//        int verifica = (int) ((Math.random() * 9 + 1) * 100000);
//        map.put("param", verifica);
//        StringBuffer sb = new StringBuffer();
//        sb.append(SmsConStant.SMS_ACCOUNTID).append(SmsConStant.SMS_TOKEN).append(date);
//        String makeSign = sb.toString();
//        String sign = Md5Util.getMD5(makeSign);
//        map.put("sig", sign);
//        SmsVO vo = HttpRequestUtil.postDataFrom(SmsConStant.SMS_URL, map);
//        if (vo.getRespCode().equals("00000")) {
//            return verifica;
//        } else {
//            throw new AppException(APIResponse.FAIL, "调用短信接口错误");
//        }
//    }
//
//    public static void main(String[] args) {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Map<String, Object> map = Maps.newHashMap();
//        map.put("accountSid", SmsConStant.SMS_ACCOUNTID);
//        map.put("to", "13606772657");
//        map.put("templateid", SmsConStant.SMS_TEMPID);
//        String date = df.format(new Date()).replace("-", "").replace(":", "").replace(" ", "");
//        map.put("timestamp", date);
//        map.put("param", (int) ((Math.random() * 9 + 1) * 100000));
//        StringBuffer sb = new StringBuffer();
//        sb.append(SmsConStant.SMS_ACCOUNTID).append(SmsConStant.SMS_TOKEN).append(date);
//        String makeSign = sb.toString();
//        String sign = Md5Util.getMD5(makeSign);
//        map.put("sig", sign);
//        SmsVO vo = HttpRequestUtil.postDataFrom(SmsConStant.SMS_URL, map);
//        System.out.println(vo.getRespCode());
//    }
//}
