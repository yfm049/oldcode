<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'right_top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="images/main.css" rel="stylesheet" type="text/css" />
  </head>
  
 <body>
     <div id="right_top">
			   <div id="loginout">
			        <div id="loginoutimg"><img src="images/loginout.gif" /></div>
			        <span class="logintext">退出系统</span>	 
			   </div>			   		
			   </div>
			   <div id="right_font"><img src="images/main_14.gif"/> 您现在所在的位置：首页 → <span class="bfont">用户信息</span></div>
</body>

</html>
