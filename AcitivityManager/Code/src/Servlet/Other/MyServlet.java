package servlet;

import DataBase.BusinessBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //MyServlet是一个用来测试的servlet 不执行任何项目里的功能功能。
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置网页响应类型
        response.setContentType("text/html");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        //实现具体操作
        PrintWriter out = response.getWriter();
        out.println("This is a new servlet page");

        String code = request.getParameter("code");
        out.print(request.getParameter("code"));



        out.println("-------------------------------------");
        out.print(TokenInfo.accessToken.getAccessToken());

        HttpSession session =request.getSession();
        session.setAttribute("user_id","1");
        session.setAttribute("user_name","1");
        session.setAttribute("user_tel","1");
        session.setAttribute("aty_id","1");
        BusinessBean businessBean =new BusinessBean();
        String aty_id =request.getParameter("a");
        int activity_id =  Integer.parseInt(aty_id);
        List<String> list ;
        list = businessBean.getAtyInfo(activity_id);
        request.setAttribute("aty_name",list.get(0));
        request.setAttribute("aty_date",list.get(1));
        request.setAttribute("aty_place",list.get(2));
        request.setAttribute("aty_content",list.get(3));
        request.setAttribute("user_name",list.get(4));
        request.setAttribute("user_tel",list.get(5));
        request.setAttribute("aty_id",aty_id);
        request.getRequestDispatcher("jsp/applyConfirm.jsp").forward(request,response);

    }
}
