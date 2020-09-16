package servlet;

import servlet.AccessToken;

public class TokenInfo {

    public static AccessToken accessToken = null; //AccessTokenServlet自启动 每2小时自动获取一次
    public static User user = null;
    public static String content= null;
}
