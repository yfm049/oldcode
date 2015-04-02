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

		<title>My JSP 'right.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="manager/js/jquery.js"></script>
		<link rel="stylesheet" type="text/css" href="manager/css/jxc.css">
		<script type="text/javascript">
			function addout(){
				window.open("<%=basePath%>manager/out_add.jsp","_self");
			}
			function dele(id){
				$.getJSON("<%=basePath%>manager/out!delete.action",{id:id},function(msg){
					alert(msg.msg);
					window.location.reload();
				});
			}
			function gopage(p){	
				window.open("manager/out!outlist.action?page.cpage="+p,"_self");
			}
		</script>
	</head>

	<body>
		<div class="menu">
			<button style="float: right" onclick="addout()">新建出货单</button>
			<form action="manager/out!outlist.action" method="post" style="margin: 0px;padding: 0px;" target="_self">
					客户：<input type="text" name="username" value="${username }">单号：<input type="text" name="danhao" value="${danhao }">电话：<input type="text" name="phonenum"  value="${phonenum }"><input type="submit" value="搜索">
			</form>
		</div>
		<div>
			<table class="table" style="margin-top: 10px">
				<tr>
					<th>
						客户
					</th>
					<th>
						单号
					</th>
					<th>
						时间
					</th>
					<th>
						电话
					</th>
					<th>
						价钱
					</th>
					<th>
						操作
					</th>
				</tr>
				<c:forEach items="${lmo}" var="mo">
				<tr>
					<td>
						${mo.username }
					</td>
					<td>
						${mo.danhao }
					</td>
					<td>
						${mo.date }
					</td>
					<td>
						${mo.phonenum }
					</td>
					<td>
						${mo.price }
					</td>
					<td style="text-align: center">
						<a href="manager/out!detail.action?id=${mo.id }">查看</a>&nbsp;&nbsp;<a href="manager/out!update.action?id=${mo.id }">修改</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="dele('${mo.id }')">删除</a>&nbsp;&nbsp;<a href="manager/out!print.action?id=${mo.id }" target="_blank">打印</a>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
		<div class="page">
			<y:ypage tsize="${page.tsize}" cpage="${page.cpage}" />
		</div>
	</body>
</html>
