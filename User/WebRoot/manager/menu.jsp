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

		<title>My JSP 'menu.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/admin.css">
<SCRIPT language=javascript>
	function expand(el)
	{
		childObj = document.getElementById("child" + el);

		if (childObj.style.display == 'none')
		{
			childObj.style.display = 'block';
		}
		else
		{
			childObj.style.display = 'none';
		}
		return;
	}
</SCRIPT>
	</head>

	<body>
		<table height="100%" cellspacing=0 cellpadding=0 width=170
			background=images/menu_bg.jpg border=0>
			<tr>
				<td valign=top align=middle>
					<table cellspacing=0 cellpadding=0 width="100%" border=0>

						<tr>
							<td height=10></td>
						</tr>
					</table>
					<table cellspacing=0 cellpadding=0 width=150 border=0>

						<tr height=22>
							<td style="padding-left: 30px" background=images/menu_bt.jpg>
								<a class=menuparent onclick=expand(1) href="javascript:void(0);">基本功能</a>
							</td>
						</tr>
						<tr height=4>
							<td></td>
						</tr>
					</table>
					<table id=child1 style="display: block" cellspacing=0 cellpadding=0
						width=150 border=0>
						<tr height=20>
							<td align=middle width=30>
								<img height=9 src="images/menu_icon.gif" width=9>
							</td>
							<td>
								<a class=menuchild href="manager/user!list.action" target=main>用户管理</a>
							</td>
						</tr>
						<tr height=4>
							<td colspan=2></td>
						</tr>
					</table>
				</td>
				<td width=1 bgcolor=#d1e6f7></td>
			</tr>
		</table>
	</body>
</html>
