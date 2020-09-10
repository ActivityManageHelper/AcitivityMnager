<%--
  Created by IntelliJ IDEA.
  User: HANDSOMELEE
  Date: 2020/6/2
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="DataBase.BusinessBean" %>
<%@ page import="servlet.TokenInfo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>我的活动</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <link href="../1.0.9/iconfont.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="../mdui/css/mdui.min.css">
</head>
<style type="text/css">
    a:link {
        text-decoration: none;
    }
    a:visited {
        text-decoration: none;
    }
    a:hover {
        text-decoration: none;
    }
    a:active {
        text-decoration: none;
    }
</style>
<body>

<div class="container">
    <H3 class="text-center">我的活动</H3>
    <div style="text-align: center;">
    	<span style="width: 50%;float: left;">
    		<a href="" style="padding-bottom: 5px;border-bottom:  2px solid #0000ff;color: #0000ff;">我参加的</a>
    	</span>
        <span style="width: 50%;float: left;">
    		<a href="myCreate.jsp" style="color: #000000;" target="_self">我创建的</a>
    	</span>
    </div>
</div>
    <%
        List<Map<String,String>> list;
        String user_id = "1705050108";  //这里只是为了方便测试，打包的时候要用下面这行代码！！
        //String user_id = session.getAttribute("user_id").toString();
        //String user_id = TokenInfo.user.getUser_id();
        BusinessBean businessBean = new BusinessBean();
        list = businessBean.getJoinedActivity(user_id);
        if(list.isEmpty()){
    %>
    <div style="text-align: center;">
        <div>
            <img src="../image/list.png" style="margin-top: 30%">
        </div>
        <p style="color: #9fc5e8;margin-top: 10px">您还未参加任何活动，快去参加吧</p>
    </div>

    <%} else {
    for (Map<String, String> m :list){%>
<div class="mdui-container" style="margin-top: 20px">
    <div class="mdui-row">
        <div class="mdui-col-sm-6 mdui-col-md-4">
            <div class="mdui-card">
                <div class="mdui-card-media">
                    <img src="../image/bg.png"/>
                    <div class="mdui-card-menu">
                        <a href="../DetailServlet?id=<%=m.get("aty_id")%>">
                            <button class="mdui-btn mdui-btn-icon mdui-text-color-white"><i class="Hui-iconfont">&#xe6f9;</i></button>
                        </a>

                    </div>

                    <div class="mdui-card-media-covered">
                        <div class="mdui-card-primary">
                            <div class="mdui-card-primary-title"><%=m.get("aty_name")%></div>
                            <div class="mdui-card-primary-subtitle"><i class="Hui-iconfont">&#xe606;</i> <%=m.get("aty_date")%></div>
                            <div class="mdui-card-primary-subtitle"><i class="Hui-iconfont">&#xe671;</i> <%=m.get("aty_place")%></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
    <% }}%>

<script src="mdui/js/mdui.min.js"></script>
</body>
</html>
