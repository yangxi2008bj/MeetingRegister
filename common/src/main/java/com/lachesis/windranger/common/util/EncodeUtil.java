package com.lachesis.windranger.common.util;

/**
 * Created by changsheng.wang on 2017/4/14.
 * AllRight
 */
public class EncodeUtil {
    public static String UTF8 = "UTF-8";
    public static String getEncoding(String str) {
        String encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {      //判断是不是GB2312
                String s = encode;
                return s;
            }
        } catch (Exception exception) {
        }
        encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {      //判断是不是ISO-8859-1
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {   //判断是不是UTF-8
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {      //判断是不是GBK
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";
    }
}
