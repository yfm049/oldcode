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
		<base href="<%=basePath%>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>用户登录</title>
		<link href="login/images/login.css" rel="stylesheet" type="text/css" />
		<script>
	function submits(){
		logins.submit();
	}
</script>
	</head>

	<body>

		<div id="login">

			<div id="top">
				<div id="top_left">
					<img src="login/images/login_03.gif" />
				</div>
				<div id="top_center"></div>
			</div>
			<form action="servlet/AdminLogin" method="post" id="logins">
				<div id="center">
					<div id="center_left"></div>
					<div id="center_middle">
						<div id="user">
							用 户
							<input type="text" name="name" />
						</div>
						<div id="password">
							密 码
							<input type="password" name="pass" />
						</div>
						<div id="btn">
							<a onclick="submits()">登录</a><a href="#">取消</a>
						</div>

					</div>
					<div id="center_right"></div>
				</div>
			</form>
			<div id="down">
				<div id="down_left">
					<div id="inf">
						<span class="inf_text">版本信息</span>
						<span class="copyright">管理信息系统 2012 v2.0</span>
					</div>
				</div>
				<div id="down_center"></div>
			</div>

		</div>
	</body>
</html>
