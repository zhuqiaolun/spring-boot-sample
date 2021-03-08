package com.demon.sample.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author: Demon
 * @date 2021/3/5 14:47
 * @description: java原生HttpURLConnection
 */
public class HttpClientUtil {

    public static String doGet(String httpUrl) {
        HttpURLConnection connection;
        try {
            // 创建远程url连接对象
            URL url = new URL(httpUrl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            return getResult(null,connection);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String doPost(String httpUrl, String param) {
        HttpURLConnection connection;
        OutputStream os;
        try {
            URL url = new URL(httpUrl);
            // 通过远程url连接对象打开连接
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接请求方式
            connection.setRequestMethod("POST");
            // 设置连接主机服务器超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(60000);
            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 通过连接对象获取一个输出流
            os = connection.getOutputStream();
            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            os.write(param.getBytes());
            return getResult(os,connection);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getResult(OutputStream os,HttpURLConnection connection){
        int responseCode = 200;
        InputStream is = null;
        BufferedReader br = null;
        try {
            String result = null;
            if (connection.getResponseCode() == responseCode) {
                is = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                StringBuilder stringBuilder = new StringBuilder();
                String temp;
                while ((temp = br.readLine()) != null) {
                    stringBuilder.append(temp);
                }
                result = stringBuilder.toString();
            }
            return result;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            getClose(br,os,is,connection);
        }
    }

    private static void getClose(BufferedReader br,OutputStream os, InputStream is,HttpURLConnection connection ){
        // 关闭资源
        if (null != br) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (null != os) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (null != is) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 断开与远程地址url的连接
        if(connection != null){
            connection.disconnect();
        }
    }

}
