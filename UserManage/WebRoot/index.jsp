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

		<title>管理员登录</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/admin.css">
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript">
			function login(){
				var name=$("input[name='name']").val();
				var pass=$("input[name='pass']").val();
				if(name.length<=0){
					alert("用户名不能为空");
					return;
				}
				if(pass.length<=0){
					alert("密码不能为空");
					return;
				}
				$.getJSON("<%=basePath%>/admin!login.action",{'name':name,'pass':pass},function(msg){
					alert(msg.msg);
					if(msg.msg=="登录成功"){
						window.open("<%=basePath%>/manager/index.jsp","_self");
					}
				});
			}
		</script>
	</head>

	<body>
		<table height="100%" cellspacing=0 cellpadding=0 width="100%"
			bgcolor=#002779 border=0>
			<tr>
				<td align=middle>
					<table cellspacing=0 cellpadding=0 width=468 border=0>
						<tr>
							<td>
								<img height=23 src="images/login_1.jpg" width=468>
							</td>
						</tr>
						<tr>
							<td>
								<img height=147 src="images/login_2.jpg" width=468>
							</td>
						</tr>
					</table>
					<table cellspacing=0 cellpadding=0 width=468 bgcolor=#ffffff
						border=0>
						<tr>
							<td width=16>
								<img height=122 src="images/login_3.jpg" width=16>
							</td>
							<td align=middle>
								<table cellspacing=0 cellpadding=0 width=230 border=0>

									<tr height=5>
										<td width=5></td>
										<td width=56></td>
										<td></td>
									</tr>
									<tr height=36>
										<td></td>
										<td>
											用户名
										</td>
										<td>
											<input
												style="border-right: #000000 1px solid; border-top: #000000 1px solid; border-left: #000000 1px solid; border-bottom: #000000 1px solid"
												maxlength=30 size=24 value="" name=name>
										</td>
									</tr>
									<tr height=36>
										<td>
											&nbsp;
										</td>
										<td>
											口 令
										</td>
										<td>
											<input
												style="border-right: #000000 1px solid; border-top: #000000 1px solid; border-left: #000000 1px solid; border-bottom: #000000 1px solid"
												type=password maxlength=30 size=24 value=""
												name=pass>
										</td>
									</tr>
									<tr height=5>
										<td colspan=3></td>
									</tr>
									<tr>
										<td>
											&nbsp;
										</td>
										<td>
											&nbsp;
										</td>
										<td>
											<input type=image height=18 width=70
												src="images/bt_login.gif" onclick="login()">
										</td>
									</tr>
								</table>
							</td>
							<td width=16>
								<img height=122 src="images/login_4.jpg" width=16>
							</td>
						</tr>
					</table>
					<table cellspacing=0 cellpadding=0 width=468 border=0>
						<tr>
							<td>
								<img height=16 src="images/login_5.jpg" width=468>
							</td>
						</tr>
					</table>
					<table cellspacing=0 cellpadding=0 width=468 border=0>
						<tr>
							<td align=right>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
