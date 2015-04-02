<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'right.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="images/main.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.STYLE1 {font-size: 12px}
.STYLE3 {color: #707070; font-size: 12px; }
.STYLE5 {color: #0a6e0c; font-size: 12px; }
body {
	margin-top: 0px;
	margin-bottom: 0px;
}
.STYLE7 {font-size: 12}
-->
</style>
  </head>
  
  <body>
  <center>
<form action="add" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#c9c9c9">
      	 <tr>
      	 	<td bgcolor="#FFFFFF" style="width: 130px" class="STYLE3">用户名：</td>
      	 	<td bgcolor="#FFFFFF"><input type="text" id="userName" name="userName"></td>
     	 </tr>
     	 <tr>
      	 	<td bgcolor="#FFFFFF" style="width: 130px" class="STYLE3">密码：</td>
      	 	<td bgcolor="#FFFFFF"><input type="password" id="userName" name="password"></td>
     	 </tr>
     	 <tr>
      	 	<td bgcolor="#FFFFFF" class="STYLE3">IMEI：</td>
      	 	
      	 	<td bgcolor="#FFFFFF"><input type="text" id="imei" name="imei"></td>
     	 </tr>
     	 <tr>
     	 	<td bgcolor="#FFFFFF" colspan="2">
      	 	<input type="submit" value="提交">
      	 	</td>
     	 </tr>
    	</table>
    </td>
  </tr>
</table>
</form>
</center>
  </body>
</html>
