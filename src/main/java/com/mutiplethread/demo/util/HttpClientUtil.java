package com.mutiplethread.demo.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName HttpClientUtil
 * @Description: TODO
 * @Author lxc
 * @Date 2020/7/9 10:39
 * @Version V1.0
 **/
public class HttpClientUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private static final String ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_.!~*'()";

    @SuppressWarnings("unchecked")
    public static Map<String, Object> doGet(String url) {
        Map<String, Object> responseMap = new HashMap<>();
        CloseableHttpClient httpclient = HttpClients.createDefault();// 创建默认的httpClient实例.
        HttpGet httpget = new HttpGet(url);// 创建httpget
        try {
            logger.info("执行get请求:{}", httpget.getURI());

            try (CloseableHttpResponse clientResponse = httpclient.execute(httpget)) {
                HttpEntity entity = clientResponse.getEntity();
                if (entity != null) {
                    String responseJSON = EntityUtils.toString(entity, "UTF-8");
                    responseMap = JSON.parseObject(responseJSON, HashMap.class);
                }
            } catch (Exception e) {
                logger.error("获取信息异常", e);

                responseMap.put("success", "false");
                responseMap.put("message", "获取信息失败");
            }
        } catch (Exception e) {
            logger.error("Exception", e);
        } finally {
            try {
                httpclient.close();
            } catch (Exception e) {
                logger.error("Exception", e);// 关闭连接,释放资源
            }
        }
        logger.info("执行get响应:{}", responseMap);

        return responseMap;
    }

   /* @SuppressWarnings()
    public static Map<String, Object> doGet(String url) {
        Map<String, Object> responseMap = new HashMap<>();
        CloseableHttpClient httpclient = HttpClients.createDefault();// 创建默认的httpClient实例.
        HttpGet httpget = new HttpGet(url);// 创建httpget
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(50000).setConnectTimeout(50000).build();

        //设置请求头
        httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.79 Safari/537.1");

        httpget.setConfig(requestConfig);
        Random r = new Random();
        try (CloseableHttpResponse clientResponse = httpclient.execute(httpget)) {
            if (HttpStatus.SC_OK == clientResponse.getStatusLine().getStatusCode()) {
                HttpEntity entity = clientResponse.getEntity();

                InputStream in = entity.getContent();

                //将图片下载到本地
                FileUtils.copyInputStreamToFile(in, new File("C:/Users/Administrator/Desktop/img/" + r.nextDouble() + ".jpg"));
            } catch(Exception e){
                //            logger.error("Exception", e);
            } finally{
                try {
                    httpget.releaseConnection();
                    httpclient.close();
                } catch (Exception e) {
                    //                logger.error("Exception", e);// 关闭连接,释放资源
                }
            }
//        logger.info("执行get响应:{}", responseMap);

            return responseMap;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
        @SuppressWarnings("unchecked")
        public static Map<String, Object> doPost (String url, List < NameValuePair > formParams){
            Map<String, Object> responseMap = new HashMap<>();
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).build();// 创建默认的httpClient实例.
            CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
            HttpPost httppost = new HttpPost(url);// 创建httppost
            UrlEncodedFormEntity uefEntity;
            try {
                uefEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
                httppost.setEntity(uefEntity);
                logger.info("执行post请求:{},参数:{}", httppost.getURI(), formParams.toString());

                try (CloseableHttpResponse clientResponse = httpclient.execute(httppost)) {
                    HttpEntity entity = clientResponse.getEntity();
                    if (entity != null) {
                        String responseJSON = EntityUtils.toString(entity, "UTF-8");
                        responseMap = JSON.parseObject(responseJSON, HashMap.class);
                    }
                } catch (Exception e) {
                    logger.error("获取信息异常", e);

                    responseMap.put("success", "false");
                    responseMap.put("message", "获取信息失败");
                }
            } catch (Exception e) {
                logger.error("Exception", e);
            } finally {
                try {
                    httpclient.close();// 关闭连接,释放资源
                } catch (Exception e) {
                    logger.error("Exception", e);
                }
            }
            logger.info("执行post响应:{}", responseMap);

            return responseMap;
        }

        public static String encodeURIComponent (String input){
            if (input == null || "".equals(input)) {
                return input;
            }
            int l = input.length();
            StringBuilder o = new StringBuilder(l * 3);
            try {
                for (int i = 0; i < l; i++) {
                    String e = input.substring(i, i + 1);
                    if (!ALLOWED_CHARS.contains(e)) {
                        byte[] b = e.getBytes("utf-8");
                        o.append(getHex(b));
                        continue;
                    }
                    o.append(e);
                }
                return o.toString();
            } catch (UnsupportedEncodingException e) {
                logger.error("UnsupportedEncodingException", e);
            }
            return input;
        }

        private static String getHex ( byte[] buf){
            StringBuilder o = new StringBuilder(buf.length * 3);
            for (byte b : buf) {
                int n = (int) b & 0xff;
                o.append("%");
                if (n < 0x10) {
                    o.append("0");
                }
                o.append(Long.toString(n, 16).toUpperCase());
            }
            return o.toString();
        }

        public static String decodeURIComponent (String encodedURI){
            char actualChar;
            StringBuilder buffer = new StringBuilder();
            int bytePattern, sumb = 0;
            for (int i = 0, more = -1; i < encodedURI.length(); i++) {
                actualChar = encodedURI.charAt(i);
                switch (actualChar) {
                    case '%': {
                        actualChar = encodedURI.charAt(++i);
                        int hb = (Character.isDigit(actualChar) ? actualChar - '0' : 10 + Character.toLowerCase(actualChar) - 'a') & 0xF;
                        actualChar = encodedURI.charAt(++i);
                        int lb = (Character.isDigit(actualChar) ? actualChar - '0' : 10 + Character.toLowerCase(actualChar) - 'a') & 0xF;
                        bytePattern = (hb << 4) | lb;
                        break;
                    }
                    case '+': {
                        bytePattern = ' ';
                        break;
                    }
                    default: {
                        bytePattern = actualChar;
                    }
                }
                if ((bytePattern & 0xc0) == 0x80) { // 10xxxxxx
                    sumb = (sumb << 6) | (bytePattern & 0x3f);
                    if (--more == 0)
                        buffer.append((char) sumb);
                } else if ((bytePattern & 0x80) == 0x00) { // 0xxxxxxx
                    buffer.append((char) bytePattern);
                } else if ((bytePattern & 0xe0) == 0xc0) { // 110xxxxx
                    sumb = bytePattern & 0x1f;
                    more = 1;
                } else if ((bytePattern & 0xf0) == 0xe0) { // 1110xxxx
                    sumb = bytePattern & 0x0f;
                    more = 2;
                } else if ((bytePattern & 0xf8) == 0xf0) { // 11110xxx
                    sumb = bytePattern & 0x07;
                    more = 3;
                } else if ((bytePattern & 0xfc) == 0xf8) { // 111110xx
                    sumb = bytePattern & 0x03;
                    more = 4;
                } else { // 1111110x
                    sumb = bytePattern & 0x01;
                    more = 5;
                }
            }
            return buffer.toString();
        }

        public static String getIpAddress (HttpServletRequest request){
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            return ip;
        }
    }
