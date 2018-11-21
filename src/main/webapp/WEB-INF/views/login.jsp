<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户关系管理系统</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" type="text/css" href="/js/easyui/themes/default/easyui.css"><!-- 样式文件 -->   
<link rel="stylesheet" type="text/css" href="/js/easyui/themes/icon.css"> <!--图标样式  -->  
<script type="text/javascript" src="/js/easyui/jquery.min.js"></script> <!-- jQuery核心库 -->
<script type="text/javascript" src="/js/easyui/jquery.easyui.min.js"></script>  <!-- EasyUI核心库 --> 
<script type="text/javascript" src="/js/login.js"></script>

</head>
<body>
  <section class="container">
    <div class="login">
      <h1>用户登录</h1>
      <form method="post">
        <p><input type="text" name="username" value="" placeholder="账号"></p>
        <p><input type="password" name="password" value="" placeholder="密码"></p>
        <p class="submit">
        	<input type="button" value="登录" onclick="loginForm();">
        	<input type="button" value="重置" onclick="resetForm();">
        </p>
      </form>
    </div>
  </section>
</html>