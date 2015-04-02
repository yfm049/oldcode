<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'into.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body background="images/mail.jpg">
  <p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>


    <form action="servlet/ImportServlet" name="frm" enctype="multipart/form-data"  method="post">
    <p align="center"> 
	<input type="file" name ="importfile"/>
	<input type="submit" value="导入" /><br/><br/>
	<a href="login.jsp">返回登录</a>
	</p>
</form>

	<%
						if (request.getParameter("msg") != null) {

							int res = Integer.parseInt(request.getParameter("msg"));
							if (res == 1) {

								out.print("<script>alert('注册成功，欢迎下次使用!'); </script>");
							} else {

								out.print("<script>alert('注册失败!');</script>");

							}

						}
					%>
  </body>
</html>
