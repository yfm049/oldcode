<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>用户授权管理</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/admin.css">

	</head>
	<c:if test="${sessionScope.name==null}">
		<c:redirect url="/index.jsp"></c:redirect>
	</c:if>
	<frameset border=0 framespacing=0 rows="60, *" frameborder=0>
		<frame name=header src="manager/header.jsp" frameborder=0 noresize
			scrolling=no>
		<frameset cols="170, *">
			<frame name=menu src="manager/menu.jsp" frameborder=0 noresize>
			<frame name=main src="manager/user!list.action" frameborder=0 noresize scrolling=yes>
		</frameset>
	</frameset>
</html>
