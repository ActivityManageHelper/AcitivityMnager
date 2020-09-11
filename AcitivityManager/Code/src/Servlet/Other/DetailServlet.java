package servlet;

import DataBase.BusinessBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DetailServlet",value = "/DetailServlet")
public class DetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        List<String> list ;
        String aty_id = request.getParameter("id");
        int activity_id =  Integer.parseInt(aty_id);
        BusinessBean businessBean = new BusinessBean();
        list = businessBean.getAtyInfo(activity_id);
        request.setAttribute("aty_name",list.get(0));
        request.setAttribute("aty_date",list.get(1));
        request.setAttribute("aty_place",list.get(2));
        request.setAttribute("aty_content",list.get(3));
        request.setAttribute("user_name",list.get(4));
        request.setAttribute("user_tel",list.get(5));
        request.setAttribute("aty_id",aty_id);
        request.getRequestDispatcher("jsp/atyDetail.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
