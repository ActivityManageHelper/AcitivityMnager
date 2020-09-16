package servlet;

import DataBase.BusinessBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

@WebServlet(name = "myAtyServlet",value = "/myAtyServlet")
public class myAtyServlet extends HttpServlet {
    //这个servlet要在菜单里面，用户点击，授权，登录获取学号，活动用户信息，重新指向user
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        if (request.getParameter("input").equals("我组织的活动"))
        {
            //这里要拿获取的学号执行查询数据库：我创建了多少活动，然后返回一个嵌套集合给myCreate.jsp
            System.out.println("查看创建的活动");
            request.getRequestDispatcher("jsp/myCreate.jsp").forward(request,response);
        }
        else if (request.getParameter("input").equals("我参加的活动"))
        {
            System.out.println("查看参加的活动");
            //这里要拿获取的学号执行查询数据库：我参加了多少活动，然后返回一个嵌套集合给myJoin.jsp
            request.getRequestDispatcher("jsp/myJoin.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
