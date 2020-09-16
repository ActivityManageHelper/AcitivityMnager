package servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QRcodeServlet",value = "/QRcodeServlet")
public class QRcodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream outputStream ;
        String content = "这是二维码的默认内容";
        String type = request.getParameter("type");
        if (type.equals("apply"))
        {
            // 接收来自QRLinkServlet的参数
            content = request.getSession().getAttribute("content_apply").toString();
        }
        else if (type.equals("checkin"))
        {
            // 接收来自QRLinkServlet的参数
            content = request.getSession().getAttribute("content_checkin").toString();
        }
        outputStream = response.getOutputStream();
        try {
            //打印输出二维码
            QRCodeUtil.writeToStream(content,outputStream,500,500);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (outputStream != null)
            {
                try
                {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
