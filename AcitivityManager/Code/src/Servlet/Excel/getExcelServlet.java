package servlet;

import DataBase.BusinessBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "getExcelServlet",value ="/getExcelServlet")
public class getExcelServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String aty_id = request.getParameter("aty_id");
        BusinessBean businessBean = new BusinessBean();
        List<Map<String,String>> maps =businessBean.getTableInfo(aty_id,"apply");
        if (maps != null && maps.size() > 0)
        {
            String sheetTitle = "参与人员";
            String[] title = new String[]{"学号", "姓名", "电话", "报名时间","是否签到"};        //设置表格表头字段
            String[] properties = new String[]{"user_id", "user_name", "user_tel", "apply_time","check"};  // 查询对应的字段
            ExcelExportUtil excelExport2 = new ExcelExportUtil();
            excelExport2.setData(maps);
            excelExport2.setHeardKey(properties);
            excelExport2.setFontSize(14);
            excelExport2.setSheetName(sheetTitle); //sheet的名字
            excelExport2.setTitle(sheetTitle);     //sheet里面第一行，标题
            excelExport2.setHeardList(title);
            excelExport2.exportExport(request, response);
        }
        else
        {
            //设置输出编码是UTF-8
            response.setCharacterEncoding("UTF-8");
            //设置浏览器显示编码是utf-8
            response.setHeader("content-type", "text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            //这句代码必须放在response.setCharacterEncoding("UTF-8");之后
            //否则response.setCharacterEncoding("UTF-8")这行代码的设置将无效，浏览器显示的时候还是乱码
            out.write("<h1>表格为空，无需下载</h1>");
            //在开发过程中，如果希望服务器输出什么浏览器就能看到什么，比如数字1，要先把1+””转成字符串，再输出

        }
    }
}
