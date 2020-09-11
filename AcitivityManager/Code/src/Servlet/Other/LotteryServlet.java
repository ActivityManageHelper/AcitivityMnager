package servlet;

import DataBase.BusinessBean;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LotteryServlet",value = "/LotteryServlet")
public class LotteryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type","text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String aty_id_undo = request.getParameter("aty_id");
        String[] aty_id = aty_id_undo.split(":");
        System.out.println("LotteryServlet:"+aty_id[1]);
        BusinessBean businessBean =new BusinessBean();
        JSONObject jsonObject =businessBean.getLotteryUserInfo(aty_id[1]);
        PrintWriter out=response.getWriter();
        out.println(jsonObject);
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
