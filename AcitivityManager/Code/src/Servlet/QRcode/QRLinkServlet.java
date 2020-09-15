package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "QRLinkServlet",value = "/QRLinkServlet")
public class QRLinkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String aty_id = request.getParameter("id");
        String uri = "http://masterlee.online/ActivityManager/getUserIdServlet?action=get&aty_id="+aty_id;
        String redirect_uri = URLEncoder.encode(uri,"GBK");
        String content_apply = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=ww0b671be552210405&" +
                "redirect_uri=" +redirect_uri+
                "&response_type=code&" +
                "scope=snsapi_base&" +
                "state=apply";
        String content_checkin = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=ww0b671be552210405&" +
                "redirect_uri=" +redirect_uri+
                "&response_type=code&" +
                "scope=snsapi_base&" +
                "state=checkin";
        HttpSession session = request.getSession();
        session.setAttribute("content_apply",content_apply);
        session.setAttribute("content_checkin",content_checkin);
        request.getRequestDispatcher("jsp/qrcode.jsp").forward(request,response);
    }
}
