<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>ERP系统访问授权</title>
    
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
  
 
<frameset rows="60,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" />
  <frameset rows="*" cols="188,*" framespacing="0" frameborder="no" border="0">
    <frame src="left.jsp" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" />
    <frameset rows="73,*" cols="*">
      <frame src="right_top.jsp" name="mainFrame" id="mainFrame" />
      <frame src="servlet/Listdate" name="right"/>
    </frameset>
  </frameset>
</frameset>
<noframes><body>
</body>
</noframes>
</html>
