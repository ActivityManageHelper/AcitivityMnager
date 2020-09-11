<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="DataBase.BusinessBean" %><%--
  Created by IntelliJ IDEA.
  User: HANDSOMELEE
  Date: 2020/6/1
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>活动详情</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <link href="1.0.9/iconfont.css" rel="stylesheet" type="text/css" />
    </head>
    <% String aty_id = request.getAttribute("aty_id").toString(); %>
    <body>
        <div class="container">
            <H3 class="text-center"> <%=request.getAttribute("aty_name").toString()%> </H3>
            <table class="table table-bordered">
                <tr class="active" >
                    <th style="border: 0px"></th>
                    <th style="border: 0px"></th>
                </tr>
                <tr>
                    <td style="border: 0px"><i class="Hui-iconfont">&#xe606;活动时间</i></td>
                    <td style="border: 0px"><i class="Hui-iconfont"><%=request.getAttribute("aty_date").toString()%></i></td>
                </tr>
                <tr>
                    <td style="border: 0px"><i class="Hui-iconfont">&#xe671;活动地点</i></td>
                    <td style="border: 0px"><i class="Hui-iconfont"> <%=request.getAttribute("aty_place").toString()%></i></td>
                </tr>
                <tr>
                    <td style="border: 0px"><i class="Hui-iconfont">&#xe62c;组织者</i></td>
                    <td style="border: 0px"><i class="Hui-iconfont"><%=request.getAttribute("user_name").toString()%></i></td>
                </tr>
                <tr>
                    <td style="border: 0px"><i class="Hui-iconfont">&#xe6a3;联系方式</i></td>
                    <td style="border: 0px"><i class="Hui-iconfont"><%=request.getAttribute("user_tel").toString()%></i></td>
                </tr>
                <tr>
                    <td style="border: 0px"><i class="Hui-iconfont">&#xe616;活动简介</i></td>
                    <td style="border: 0px"><i class="Hui-iconfont"><%=request.getAttribute("aty_content").toString()%></i> </td>
                </tr>
            </table>
            <hr/>
            <h4>同行人<a class="btn btn-default" href="${pageContext.request.contextPath}/getExcelServlet?aty_id=<%=aty_id%>" role="button" style="float: right"><i class="Hui-iconfont">&#xe632;</i>保存</a></h4>

            <table class="table table-striped">
                <tr>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>电话</th>
                    <th>报名时间</th>
                    <th>是否签到</th>
                </tr>
                <%
                    //String aty_id = request.getAttribute("aty_id").toString();
                    List<Map<String,String>> list;
                    BusinessBean businessBean = new BusinessBean();
                    list = businessBean.getTableInfo(aty_id,"apply");

                %>

                <% for (Map<String,String> m:list){ %>
                <% if(m.get("check").equals("是")){ %>
                <tr class="success">
                <%}else{%>
                <tr class="danger">
                <%}%>
                    <td><%=m.get("user_id")%></td>
                    <td><%=m.get("user_name")%></td>
                    <td><%=m.get("user_tel")%></td>
                    <td><%=m.get("apply_time")%></td>
                    <td><%=m.get("check")%></td>
                </tr>
                <%}%>
            </table>
        </div>
    </body>
</html>
