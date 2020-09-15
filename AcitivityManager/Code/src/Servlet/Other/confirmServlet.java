package servlet;

import DataBase.BusinessBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "confirmServlet",value = "/confirmServlet")
public class confirmServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =request.getSession();
        String user_id= session.getAttribute("user_id").toString();
        String use_name =session.getAttribute("user_name").toString();
        String tel =session.getAttribute("user_tel").toString();
        String aty_id =session.getAttribute("aty_id").toString();
        BusinessBean businessBean = new BusinessBean();
        int returnInfo = businessBean.applyActivity(aty_id,user_id,use_name,tel);
        if (returnInfo==1)
        {
            response.sendRedirect("html/success.html");
        }
        else if (returnInfo==0)
        {
            response.sendRedirect("html/done.html");
        }
    }
}
