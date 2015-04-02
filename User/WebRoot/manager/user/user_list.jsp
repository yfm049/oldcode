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
			function gopage(p){
				var url="manager/user!list.action?page.cpage="+p
				alert(url);
				window.open(url,"_self");
			}
			function deleted(id){
				$.getJSON("manager/user!delete.action",{'user.id':id},function(msg){
					alert(msg.msg);
					window.location.reload();
				});
			}
			function shouquan(id,d){
				$.getJSON("manager/user!shouquan.action",{'user.id':id,'user.state':d},function(msg){
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
						当前位置: 用户管理
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
					<th>用户名</th><th>真实姓名</th><th>手机号</th><th>邮箱</th><th>IMEI</th><th>状态</th><th>操作&nbsp;<a href="manager/user/user_add.jsp" target="main">添加用户</a></th>
				</tr>
				<c:forEach items="${lmo}" var="mo">
				<tr>
					<td>
						${mo.username }
					</td>
					<td>
						${mo.realname }
					</td>
					<td>
						${mo.phonenum }
					</td>
					<td>
						${mo.email }
					</td>
					<td align="center">
						${mo.imei }
					</td>
					<td align="center">
						${mo.zt }
					</td>
					<td align="center" style="width: 20%">
						<a href="manager/user!update.action?user.id=${mo.id }">修改</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="deleted('${mo.id }')">删除</a>&nbsp;&nbsp;&nbsp;
						<c:if test="${mo.state==0}">
						<a href="javascript:void(0)" onclick="shouquan('${mo.id }','1')">授权</a>
						</c:if>
						<c:if test="${mo.state==1}">
						<a href="javascript:void(0)" onclick="shouquan('${mo.id }','0')">取消授权</a>
						</c:if>
						
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div style="margin-top: 10px;text-align: right;">
			<y:ypage cpage="${page.cpage}" tsize="${page.tsize}"/>
		</div>
	</body>
</html>
