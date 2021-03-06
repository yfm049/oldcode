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
				window.open("manager/user!list.action","_self");
			}
			function save(){
				var username=$("#username").val();
				var password=$("#password").val();
				var realname=$("#realname").val();
				var phonenum=$("#phonenum").val();
				var imei=$("#imei").val();
				var email=$("#email").val();
				if(username.length<=0){
					alert("用户名不能为空");
					return;
				}
				if(password.length<=0){
					alert("密码不能为空");
					return;
				}
				if(realname.length<=0){
					alert("真实姓名不能为空");
					return;
				}
				if(phonenum.length<=0){
					alert("手机号不能为空");
					return;
				}
				if(email.length<=0){
					alert("邮箱不能为空");
					return;
				}
				if(imei.length<=0){
					alert("IMEI不能为空");
					return;
				}
				$.getJSON("manager/user!adduser.action",{'user.username':username,'user.password':password,'user.realname':realname,'user.phonenum':phonenum,'user.email':email,'user.imei':imei},function(msg){
					alert(msg.msg);
					window.location.reload();
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
		<form action="manager/user!save.action" method="post" id="form">
			<table width="99%" cellspacing="0" cellpadding="2" border="0"
				align="center" class="userlist">
				<tbody>
					<tr>
						<td style="width: 20%;">
							用户名<input type="hidden" name="user.id" value="${map.id }">
						</td>
						<td>
							<input type="text" name="user.username" value="${map.username }" id="username">
						</td>
					</tr>
					<tr>
						<td style="width: 20%;">
							密码
						</td>
						<td>
							<input type="text" name="user.password" value="${map.password }" id="password">
						</td>
					</tr>
					<tr>
						<td>
							真实姓名
						</td>
						<td>
							<input type="text" name="user.realname" value="${map.realname }" id="realname">
						</td>
					</tr>
					<tr>
						<td>
							手机号
						</td>
						<td>
							<input type="text" name="user.phonenum" value="${map.phonenum }" id="phonenum">
						</td>
					</tr>
					<tr>
						<td>
							邮箱
						</td>
						<td>
							<input type="text" name="user.email" value="${map.email }" id="email">
						</td>
					</tr>
					<tr>
						<td>
							IMEI
						</td>
						<td>
							<input type="text" name="user.imei" value="${map.imei }" id="imei">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<table width="99%" cellspacing="0" cellpadding="2" border="0"
				align="center" class="userlist">
				<tr>
					<td>
						<button onclick="save()">保存</button>&nbsp;&nbsp;&nbsp;<button onclick="quxiao()">取消</button>
					</td>
				</tr>
		</table>
	</body>
</html>
