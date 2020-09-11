<%--
  Created by IntelliJ IDEA.
  User: HANDSOMELEE
  Date: 2020/6/4
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="DataBase.BusinessBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>报名活动</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <link href="../1.0.9/iconfont.css" rel="stylesheet" type="text/css" />
    <style>
        .login-button { /* 按钮美化 */
            width: 270px; /* 宽度 */
            height: 40px; /* 高度 */
            border-width: 0px; /* 边框宽度 */
            border-radius: 3px; /* 边框半径 */
            background: #1E90FF; /* 背景颜色 */
            cursor: pointer; /* 鼠标移入按钮范围时出现手势 */
            outline: none; /* 不显示轮廓线 */
            color: white; /* 字体颜色 */
            font-size: 17px; /* 字体大小 */
        }
        .login-button:hover { /* 鼠标移入按钮范围时改变颜色 */
            background: #5599FF;
        }
    </style>
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
            <td style="border: 0px"> <%=request.getAttribute("aty_date").toString()%> </td>
        </tr>
        <tr>
            <td style="border: 0px"><i class="Hui-iconfont">&#xe671;活动地点</i></td>
            <td style="border: 0px"> <%=request.getAttribute("aty_place").toString()%> </td>
        </tr>
        <tr>
            <td style="border: 0px"><i class="Hui-iconfont">&#xe62c;组织者</i></td>
            <td style="border: 0px"> <%=request.getAttribute("user_name").toString()%> </td>
        </tr>
        <tr>
            <td style="border: 0px"><i class="Hui-iconfont">&#xe6a3;联系方式</i></td>
            <td style="border: 0px"> <%=request.getAttribute("user_tel").toString()%> </td>
        </tr>
        <tr>
            <td style="border: 0px"><i class="Hui-iconfont">&#xe616;活动简介</i></td>
            <td style="border: 0px"> <%=request.getAttribute("aty_content").toString()%> </td>
        </tr>
    </table>
    <hr/>

    <h4>↓↓↓这些人也参加了活动↓↓↓</h4>
    <table class="table table-striped">
        <tr>
            <th>学  号</th>
            <th>姓  名</th>
            <th>报名时间</th>
        </tr>
        <%
            List<Map<String,String>> list;
            BusinessBean businessBean = new BusinessBean();
            list = businessBean.getTableInfo(aty_id,"apply");

        %>
        <% for (Map<String,String> m:list){ %>
        <tr class="success">
            <td><%=m.get("user_id")%></td>
            <td><%=m.get("user_name")%></td>
            <td><%=m.get("apply_time")%></td>
        </tr>
        <%}%>
    </table>
    <form action="${pageContext.request.contextPath}/confirmServlet" STYLE="text-align: center">
        <input class="login-button" type="submit" name="input" value="报名活动">
    </form>
</div>
</body>
</html>