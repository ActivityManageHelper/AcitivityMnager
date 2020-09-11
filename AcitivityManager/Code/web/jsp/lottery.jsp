<%--
  Created by IntelliJ IDEA.
  User: HANDSOMELEE
  Date: 2020/6/5
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../mdui/css/lottery.css" type="text/css">
    <script src="../mdui/js/jquery.js"></script>
    <script src="../mdui/js/lottery_ajax.js"></script>
    <title>抽奖</title>
</head>

<% String aty_id = request.getParameter("id");
   String aty_name = request.getParameter("name");
%>

<body background="../image/background.png" style="background-size: cover">
<div class="box_outer">
    <div class="box_form">
        <form class="form_style" autocomplete="off">
            <h2>中奖信息</h2>
            <div class="box_input">
                <input type="text" readonly="readonly" name="aty_name" id="aty_name" placeholder="<%=aty_name%>"/>
            </div>
            <div class="box_input">
                <input type="text"  readonly="readonly" name="aty_id" id="aty_id" placeholder="活动id:<%=aty_id%>"/>
            </div>
            <div class="box_input">
                <input type="text" readonly="readonly" name="user_name" id="user_name" placeholder="中奖人姓名"/>
            </div>
            <div class="box_input">
                <input type="text"  readonly="readonly" name="user_id" id="user_id" placeholder="中奖人学号"/>
            </div>
            <div class="box_input">
                <input type="text"  readonly="readonly" name="tel" id="tel" placeholder="中奖人手机号"/>
            </div>
            <br>
            <button type="button" name="lottery" id="lottery" class="btn" >抽奖</button>
        </form>
    </div>
</div>
</body>
</html>
