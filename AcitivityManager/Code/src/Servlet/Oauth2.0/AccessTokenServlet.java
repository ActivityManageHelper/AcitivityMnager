package servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import servlet.AccessToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * 用于获取accessToken的Servlet
 * Created by btang 
 */
@WebServlet(
        name = "AccessTokenServlet",
        urlPatterns = {"/AccessTokenServlet"},
        loadOnStartup = 1,
        initParams = {
                @WebInitParam(name = "corpid", value = "ww0b671be552210405"),
                @WebInitParam(name = "appSecret", value = "JNqd7DXnIMS6iRa1Q0daRYThWIeW_DztB7w-GvVcJmU")
        })
public class AccessTokenServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("启动AccessTokenServlet");
        super.init();

        final String corpId = getInitParameter("corpid");
        final String appSecret = getInitParameter("appSecret");

        //开启一个新的线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        //获取accessToken
                        TokenInfo.accessToken = getAccessToken(corpId, appSecret);
                        //获取成功
                        if (TokenInfo.accessToken != null) {
                            //获取到access_token 休眠7000秒,大约2个小时左右
                            Thread.sleep(7000 * 1000);
                        } else {
                            //获取失败
                            Thread.sleep(1000 * 3); //获取的access_token为空 休眠3秒
                        }
                    } catch (Exception e) {
                        System.out.println("发生异常：" + e.getMessage());
                        e.printStackTrace();
                        try {
                            Thread.sleep(1000 * 10); //发生异常休眠1秒
                        } catch (Exception e1) {

                        }
                    }
                }

            }
        }).start();
    }

    /**
     * 获取access_token
     *
     * @return servlet.AccessToken
     */
    private AccessToken getAccessToken(String corpId, String appSecret) {
        NetWorkHelper netHelper = new NetWorkHelper();
        /**
         * 接口地址为
         * https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRET
         */
        String Url = String.format("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=%s&corpsecret=%s", corpId, appSecret);
        //此请求为https的get请求，返回的数据格式为{"access_token":"ACCESS_TOKEN","expires_in":7200}
        String result = netHelper.getHttpsResponse(Url, "");
        System.out.println("获取到的json为"+result);

        //使用FastJson将Json字符串解析成Json对象
        JSONObject json = JSON.parseObject(result);
        AccessToken token = new AccessToken();
        token.setAccessToken(json.getString("access_token"));
        token.setExpiresin(json.getInteger("expires_in"));

        return token;
    }
}