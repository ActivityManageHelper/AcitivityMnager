package servlet;

import DataBase.BusinessBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateAtyServlet",value = "/CreateAtyServlet")
public class CreateAtyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean flag;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //获取createAty传过来的表单数据
        String user_id = request.getParameter("id");
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String Aty_name = request.getParameter("Aty_name");
        String place = request.getParameter("place");
        String date = request.getParameter("date");
        String content = request.getParameter("content");
        BusinessBean businessBean=new BusinessBean();
        flag = businessBean.add_activity(user_id,name,tel,Aty_name,date,place,content);
        if (flag)
        {
            System.out.println("插入活动成功");
            request.setAttribute("createReturn","1");
            request.getRequestDispatcher("jsp/createAty.jsp").forward(request,response);
        }
        else
        {
            System.out.println("插入活动失败");
            request.setAttribute("createReturn","0");
            request.getRequestDispatcher("jsp/createAty.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
