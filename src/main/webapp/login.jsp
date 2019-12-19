<%@ page language="java" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>学生信息管理系统</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="plugin/easyui/themes/bootstrap/easyui.css" rel="stylesheet">
    <link href="css/mycss.css" rel="stylesheet">
</head>
<body>
    <div class="login-box">
        <fieldset>
            <legend><h1>优秀大学生信息管理系统</h1></legend>
            <fieldset>
                <div class="form-horizontal mybox">
                    <div class="form-group">
                        <%--有了Lable的话，你在“用户名”这四个汉字上点一下，光标也进入输入框--%>
                        <label for="username" class="col-md-3 control-label">用户名：</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="username" placeholder="请输入登陆名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-md-3 control-label">密码：</label>
                        <div class="col-md-8">
                            <%--id 一般用于css和js中引用,name用于表单提交,--%>
                            <input type="password" class="form-control" id="password" placeholder="请输入密码">

                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-md-3 control-label">身份：</label>
                        <div class="col-md-8">
                            <label class="radio-inline">
                                    <%--单选--%>
                                <input type="radio" name="role" value="student" > 学生
                            </label>
                            <label class="radio-inline">
                                <%--checked默认单选--%>
                                <input type="radio" name="role" value="manager" checked> 管理员

                            </label>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 30px;">
                        <div class="col-md-offset-5">
                            <button class="btn btn-primary" id="login">登陆</button>
                        </div>
                    </div>
                </div>
            </fieldset>
        </fieldset>
    </div>
<script src="js/jquery-2.1.1.min.js"></script>
<script src="plugin/easyui/jquery.easyui.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/login.js"></script>
</body>
</html>