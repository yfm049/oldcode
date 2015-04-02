<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="y" uri="/yfm-page"%>
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

		<title>My JSP 'user_list.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/admin.css">
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript">
			function quxiao(){
				window.open("<%=basePath%>/manager/user!list.action","_self");
			}
			function save(){
				var password=$("#password").val();
				var password1=$("#password1").val();
				
				if(password.length<=0){
					alert("密码不能为空");
					return;
				}
				if(password1.length<=0){
					alert("确认密码不能为空");
					return;
				}
				if(password1!=password){
					alert("确认密码不正确");
					return;
				}
				
				$.getJSON("admin!update.action",{'pass':password},function(msg){
					alert(msg.msg);
					if(msg.msg=="修改成功"){
						window.open("<%=basePath%>/manager/user!list.action","_self");
					}
				});
			}
		</script>
	</head>

	<body>
		<table width="100%" cellspacing="0" cellpadding="0" border="0"
			align="center">
			<tbody>
				<tr height="28">
					<td background="images/title_bg1.jpg" style="padding-left: 10px;">
						当前位置: 用户修改
					</td>
				</tr>
				<tr>
					<td height="1" bgcolor="#b1ceef"></td>
				</tr>
				<tr height="20">
					<td background="images/shadow_bg.jpg"></td>
				</tr>
			</tbody>
		</table>
			<table width="99%" cellspacing="0" cellpadding="2" border="0"
				align="center" class="userlist">
				<tbody>
					<tr>
						<td style="width: 20%;">
							密码
						</td>
						<td>
							<input type="password" name="password" value="${map.password }" id="password">
						</td>
					</tr>
					<tr>
						<td style="width: 20%;">
							确认密码
						</td>
						<td>
							<input type="password" name="password1" value="${map.password }" id="password1">
						</td>
					</tr>
					
				</tbody>
			</table>
		<table width="99%" cellspacing="0" cellpadding="2" border="0"
				align="center" class="userlist">
				<tr>
					<td>
						<button onclick="save()">修改</button>&nbsp;&nbsp;&nbsp;<button onclick="quxiao()">取消</button>
					</td>
				</tr>
		</table>
	</body>
</html>
