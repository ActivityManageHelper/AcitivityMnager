<%@ page import="servlet.TokenInfo" %>
<%--
  Created by IntelliJ IDEA.
  servlet.User: HANDSOMELEE
  Date: 2020/5/31
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>创建活动</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <STYLE>
        .buttoncover{
            background-color: #4CAF50; /* Green */
            border: none;
            border-radius: 5px;
            color: white;
            padding: 10px 25px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 17px;
        }
    </STYLE>
</head>

                    <!--为保证UI正常，每个文本框最多容纳20个字，使用maxlength="20"-->
<body>
<div class="container">
    <form method="post" action="CreateAtyServlet">
        <H3 class="text-center">创建活动</H3>
        <div class="form-group">
            <label for="exampleInputName">学号</label>                                                         <%-- TokenInfo.user.getUser_id() --%>
            <input type="text" class="form-control" readonly="readonly" name="id" id="exampleInputId" required="required" value="<%=session.getAttribute("user_id").toString()%>">
        </div>
        <div class="form-group">
            <label for="exampleInputName">姓名</label>                                                          <%-- TokenInfo.user.getName() --%>
            <input type="text" class="form-control" readonly="readonly" name="name" id="exampleInputName" required="required" value="<%=session.getAttribute("user_name").toString()%>">
        </div>
        <div class="form-group">
            <label for="exampleInputNumber">手机号</label>
            <input type="text" class="form-control" readonly="readonly" name="tel" id="exampleInputNumber" required="required" value="<%=session.getAttribute("user_tel").toString()%>">
        </div>
        <div class="form-group">
            <label for="exampleInputName">活动名称</label>
            <input type="text" class="form-control" maxlength="20" name="Aty_name" id="exampleInputAtyName" required="required" placeholder="请输入活动名称">
        </div>
        <div class="form-group">
            <label for="exampleInputPlace">地点</label>
            <input type="text" class="form-control"  maxlength="20" name="place" id="exampleInputPlace" required="required" placeholder="请输入活动地址">
        </div>
        <div class="form-group">
            <label for="meeting">活动时间</label>
            <input id="meeting" type="datetime-local" name="date" class="form-control" value="2020-05-19"/>
        </div>
        <div class="form-group">
            <label for="exampleInputName">活动介绍</label>
            <textarea class="form-control" name="content" maxlength="20" placeholder="仅限20字以内" rows="3"></textarea>
        </div>
        <div style="margin-top: 50px">
            <button type="submit" class="buttoncover" style="margin-left: 50px;">提  交</button>
            <button type="reset" class="buttoncover" style="margin-right: 50px; float: right;" >重  置</button>
        </div>
    </form>
</div>

</body>
</html>

<%
    String createReturn=(String)request.getAttribute("createReturn");
    System.out.println("my create_return="+createReturn);
    //从getUserIdServlet传过来的createReturn为default，下面两个if都不执行
    //提交表单后，跳转到CreateAtyServlet进行数据库操作，成功，重新把createReturn设置为1再次跳转回该界面
    //执行if (createReturn.equals("1"))，跳转到myCreate.jsp界面
    if (createReturn.equals("0"))
    {
        out.println("<script type=\"text/javascript\">alert(\"创建活动失败\");</script>");
    }
    if (createReturn.equals("1"))
    {
        out.println("<script type=\"text/javascript\">alert(\"创建活动成功!\"); window.location.href = \"jsp/myCreate.jsp\"; </script>");
    }
%>
