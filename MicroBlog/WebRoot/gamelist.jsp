<%@ page language="java" import="java.util.*,com.microblog.po.*,com.microblog.dao.*" import= "java.sql.*" import= "com.microblog.dbutil.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style type="text/css">
<!-- a:LINK {
	text-decoration:none;
	} 
	a:visited{
	text-decoration:none;}
	a:hover{
	text-decoration:none;
	}
	a:active{
	text-decoration:none}
	
	-->
</style>
<title>微博 - ${sessionScope.user.u_account}的主页</title>
<script type="text/javascript" src="script/home.js"></script>

</head>
   <body background="images/bg.png">
   <br/><br/><br/><br/><br/><br/><br/><br/>
  <font size="200">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="game.jsp?msg=1" style="color: white">暴力摩托</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><br/>
 <font size="200">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <a href="game.jsp?msg=2" style="color: white">宠物连连看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><br/>
  <font size="200">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="game.jsp?msg=3" style="color: white">电眼美女2</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><br/>
   <font size="200">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="game.jsp?msg=4" style="color: white">黄金矿工双人版</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><br/>
   
   </body>


</html>
