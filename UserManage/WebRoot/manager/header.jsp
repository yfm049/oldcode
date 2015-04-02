<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

		<title>My JSP 'top.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/admin.css">

	</head>

	<body>
		<table cellspacing=0 cellpadding=0 width="100%"
			background="images/header_bg.jpg" border=0>
			<tr height=56>
				<td width=260>
					<img height=56 src="images/header_left.jpg" width=260>
				</td>
				<td style="font-weight: bold; color: #fff; padding-top: 20px"
					align=middle>
					当前用户：admin &nbsp;&nbsp;
					<a style="color: #fff" href="manager/admin/user_update.jsp" target=main>修改口令</a> &nbsp;&nbsp;
					<a style="color: #fff"
						onclick="if (confirm('确定要退出吗？')) return true; else return false;"
						href="admin!loginout.action" target=_top>退出系统</a>
				</td>
				<td align=right width=268>
					<img height=56 src="images/header_right.jpg" width=268>
				</td>
			</tr>
		</table>
		<table cellspacing=0 cellpadding=0 width="100%" border=0>
			<tr bgcolor=#1c5db6 height=4>
				<td></td>
			</tr>
		</table>
	</body>
</html>
