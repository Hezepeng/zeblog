package com.zeblog.util;

import com.alibaba.fastjson.JSONObject;
import com.tencent.cloud.CosStsClient;
import sun.security.provider.ConfigFile;

import javax.json.JsonObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Properties;
import java.util.TreeMap;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-08-12 16:54
 */
public class TencentCloudUtil {
    public static JSONObject getTempToken() {
        TreeMap<String, Object> config = new TreeMap<String, Object>();

        try {
            Properties properties = new Properties();
            String configFileName = "local.properties";
            properties.load(new InputStreamReader(Objects.requireNonNull(PropertiesUtil.class.getClassLoader().getResourceAsStream(configFileName)), "UTF-8"));

//            properties.load(new FileInputStream(configFile));

            // 云 API 密钥 secretId
            config.put("secretId", properties.getProperty("SecretId"));
            // 云 API 密钥 secretKey
            config.put("secretKey", properties.getProperty("SecretKey"));
            //若需要设置网络代理，则可以如下设置
            if (properties.containsKey("https.proxyHost")) {
                System.setProperty("https.proxyHost", properties.getProperty("https.proxyHost"));
                System.setProperty("https.proxyPort", properties.getProperty("https.proxyPort"));
            }

            // 临时密钥有效时长，单位是秒
            config.put("durationSeconds", 1800);

            // 换成你的 bucket
            config.put("bucket", properties.getProperty("Bucket"));
            // 换成 bucket 所在地区
            config.put("region", properties.getProperty("Region"));

            // 设置可操作的资源路径前缀，根据实际情况进行设置
            // 如授予可操作所有的资源：则为 *；
            // 如授予操作某个路径a下的所有资源，则为 a/*；
            // 如授予只能操作某个特定路径的文件 a/test.jpg， 则为 a/test.jpg
            config.put("allowPrefix", "*");

            // 密钥的权限列表。简单上传和分片需要以下的权限，其他权限列表请看 https://cloud.tencent.com/document/product/436/31923
            String[] allowActions = new String[]{
                    // 简单上传
                    "name/cos:PutObject",
                    // 表单上传
                    "name/cos:PostObject",
                    // 分片上传： 初始化分片
                    "name/cos:InitiateMultipartUpload",
                    // 分片上传： 查询 bucket 中未完成分片上传的UploadId
                    "name/cos:ListMultipartUploads",
                    // 分片上传： 查询已上传的分片
                    "name/cos:ListParts",
                    // 分片上传： 上传分片块
                    "name/cos:UploadPart",
                    // 分片上传： 完成分片上传
                    "name/cos:CompleteMultipartUpload"
            };
            config.put("allowActions", allowActions);
            // 请求临时密钥信息
            JSONObject credential= com.alibaba.fastjson.JSONObject.parseObject(CosStsClient.getCredential(config).toString());
            // 请求成功：打印对应的临时密钥信息
            System.out.println(credential.toString());
            return credential;
        } catch (Exception e) {
            // 请求失败，抛出异常

            System.out.println(e.getMessage());
            throw new IllegalArgumentException("no valid secret !");
        }
    }
}
