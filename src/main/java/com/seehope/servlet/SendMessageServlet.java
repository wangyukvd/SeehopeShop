package com.seehope.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//在src/com.seehope.servlet下创建SendMessageServlet，
// URL为/send,用于发送验证码(随机数)给手机，同时存入session。注意除doGet方法外，大部分源码直接提供

@WebServlet(name = "SendMessageServlet", value = "/send")
public class SendMessageServlet extends HttpServlet {
    public SendMessageServlet(){
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String testUsername = "1768753013";  //在短线宝注册的用户名
        String testPassword = "zxc3838238";  //在短信包注册的密码
//      String testUsername = "1768753013";//在短线宝注册的用户名
//      String testPassword = "zxc3838238";//在短信包注册的密码
        String testPhone= request.getParameter("telephone");
        System.out.println("用户填入的电话号码（telephone）"+testPhone);

//        Math.random()取得是0-1之间的随机小数（事实上取不到 0 和１），乘以9之后应该是0-9之间的随机小数，
//        实际上也就是0.**到8.****之间的小数（大于０而小于 9），经过int类型转换之后，应该是 0-8 之间的随机整数，
//        所以"+1"之后就会得到1-9之间的的小数，乘以10000来获取范围在（10000-999999）之间的6位的整型数字。

        int number=(int)((Math.random()*9+1)*100000);  //随机生成6位整型数字
        System.out.println("随机生成的验证码（number）:"+number);
        HttpSession session=request.getSession();
        session.setAttribute("num",String.valueOf(number));  //将验证码存入 num 的session时域中
        String testContent = "【砺锋商城】您的验证码是"+number+",5分钟内有效，若本人操作请勿忽略此消息";  //注意测试时，也请带上公司简称或网络签名，发送正规内容短信。千万不能发送
        //↑↑↑↑↑发送的短信内容
        String httpUrl="http://api.smsbao.com/sms";  //短信包的网站http://api.smsbao.com

        //用于你和短信包对接信息
        StringBuffer httpArg=new StringBuffer();
        httpArg.append("u=").append(testUsername).append("&");   //用户名
        httpArg.append("p=").append(md5(testPassword)).append("&");//密码
        httpArg.append("m=").append(testPhone).append("&");         //电话
        httpArg.append("c=").append(encodeUrlString(testContent,"UTF-8"));  //短信内容用中文需要编译
        String result = request(httpUrl,httpArg.toString());  //把信息传递给短信包
        System.out.println("短信宝接收后，返回信息数据"+result); //0表示发送成功  41余而不足
        response.getWriter().print(result);
    }

    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = reader.readLine();
            if (strRead != null) {
                sbf.append(strRead);
                while ((strRead = reader.readLine()) != null) {
                    sbf.append("\n");
                    sbf.append(strRead);
                }
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String md5(String plainText) {
        StringBuffer buf = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0){i += 256;}

                if (i < 16){
                    buf.append("0");
                }

                buf.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }

    public static String encodeUrlString(String str, String charset) {
        String strret = null;
        if (str == null)
            return str;
        try {
            strret = java.net.URLEncoder.encode(str, charset);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return strret;
    }

    /**
     * @see HttpSession #doPost(HttpServletRequest request, HttpServletResponse response)
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
