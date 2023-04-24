package com.seehope.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021000121651713";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCvIqWEKmv+/e3QvnKySU09zfpaW+On8GQD/gjQ0jFryXpSNNPCBhv8tR8AqiZJBjkmhEIaZQWLbByT4vxcJGh53/8zRaT0zlc2P3bQ3HMzHLw5ZGD43lzFlCxbKJ1hNP73w2i1NrPhSQf1wIefOo2zuqu/5UBYibIfpI+VXAhVEL2HsOQV1fOmFqxaPAfqOVtkN10nr+ZfUVGhK3V427iN0Iy+ePVLjtxbj5+TJclU3yKF+XYGfLPiTW1jJvFuV1j1rSJvHbmvuj3gOA4fxqwtus+gf0c63QXFLyVaV/7US6ZMvfWjSYcfeXHL5mKxm3n/EEapwlZpYYBljtne/yz3AgMBAAECggEACGK+Th/B4AnEk1QrJcJAwo7UR/NSaT4na9GEFouZ9jFFfB4vbYDhYi5B98Qt5R05K1D14VGo/E4G6EXcE4Viw0MocwoIpfgjUOOlCscjdPURRiY6RnBPPN6laQ/+ySLCepaOsu6Vmlnm2auIUuV413w+sQeHFzYwozc78MjhVUFNiNR9AWc+yg708vl5bPL7IvEH6croQHIZUKoDF2Dm6/ByHOJzeAXw8Erqek2924chMQPQ+m99y1cF2EHQ6vJkfzIkqWFIduPy4t2boEaWp6PHD82JCaCOwvBsEkIPRgVf9Fyaw7xm4taAURxpbN+lXiaMHOzrQMJe5ahjytcoIQKBgQDtcednC8QkY3vJXeZZFnTIYdAEZhTTWyY8jafYAMtgZVrj/SwK5y+9G5U4Cd2ZaUhmXalzTuiVWtuF1FzC54Qa7CFIQNN+xWzUC3zLwQ9AsO+znamNiNOoezqkH0J2SnyoJoK1VRJoKXwirdITSHm2lpl+7xMdt7A3YLSZsWOwrwKBgQC80jyH0RsYFj7x1sXnvGcTbHHh6F8G/sFflCPcAmzVEih6zFlzPuSCbtE5lqSvvDEG35+iRKy85A3KP1ugMBRjmRNzbZndkqauStHmB7vpl64CbdHncu0bFiLsX0uT8/lA515m8mxshkYl3ozXtWdp2WDlJCpkxh5OExsSEPoKOQKBgQC8zErgxpxJCslMjdswjwiApDWQqdJ5VAqNL118BPWxSiYBDjdM3BI7XYlCq6WF7A7jRioMQ3vv9fSC7x5q40dJWOQF3XXNYRFtcUzjfjiwiX+u4/gDT/5sAr/X6/ser4967FQV9vTR2dqYgHl/V6tfoVOfeF1MzdfR0JUJd+D6hQKBgEdlw6ejhPg1qTVu/cRjdCJfFizS3ePaPqpybKDLdfWXeSD91jECrgs39fstoDYnFe7Abi9AgoSmQySfknje1UbX4gf6gHzM8CPlfLrbX82zIgQfHIjE5y0f0LLKUdyvGyfUlNiegCsFFs9I+L60BFd87+uVIkaat2jURp8tNi7ZAoGAYtrCZmrXeWLjtmOHoB3YV9hupLQ+QtvkWpse07JxMFZrICPjti9KqXRrRH9POmNaMPZLzRqpFY4yBnz26QlCfi4cU883fD2ycGweROESv/zOpv2hgSpD83tXuotkA0orkD5QvIxb++PswvJuraXvaeXgWGYCLOBf9DkGXLIB1Ck=";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgSdVIUu0Kww5JyvZdIegkedxceJvSypT93Qn3WFHvn5oCYctG49xS5iEBZO2Dg4IexT9Sg4Uas1QfHw5H/CxBKBltAaCAAy9wDf80oO3lzQrOS5kvkCgSw5PqvUltS5GLVG3rOyb46Jw034Je1eWN4jeyBponp4BDNz4OnDAzZHYc24/RmwQCIHUjOpjBgoKeHObO46LfMRTHaOM1i/TyDJG7BWswsLFRXuPK1Jf1judygLloDXUlfyMxs1BWbWVQ4qulRvGBg5C6BMrMjwZ9Tq0wTgsaRaxyIBvVuEWkDw9UMnY5aYfHzKt3ZpofZTwWoRFIQX3y9uroN3uCVR3/QIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://4c88344d.vip.cpolar.cn/SeehopeShop/pay";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://4c88344d.vip.cpolar.cn/SeehopeShop/pay";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

