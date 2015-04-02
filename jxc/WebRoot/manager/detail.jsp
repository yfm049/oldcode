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
			function print(){
				window.open("<%=basePath%>manager/out!print.action?id=${id}","_blank");
			}
		</script>
	</head>

	<body>
		<div class="info">
			<table>
				<tr>
					<td style="width: 35px;">
						客户:
					</td>
					<td style="width: 20%">
						${map.username }
					</td>
					<td  style="width: 35px;">
						单号:
					</td>
					<td style="width: 20%">
						${map.danhao }
					</td>
					<td  style="width: 35px;">
						电话:
					</td>
					<td style="width: 20%">
						${map.phonenum }
					</td>
					<td  style="width: 35px;">
						时间:
					</td>
					<td style="width: 20%">
						${map.date }
					</td>
				</tr>
			</table>
		</div>
		<div>
			<table class="table" style="margin-top: 5px">
				<tr>
					<th>
						商品名称
					</th>
					<th>
						品牌
					</th>
					<th>
						规格
					</th>
					<th>
						单位
					</th>
					<th>
						数量
					</th>
					<th>
						米数
					</th>
					<th>
						单价
					</th>
					<th>
						金额
					</th>
				</tr>
				<c:forEach items="${lmo}" var="mo">
					<tr>
						<td>
							${mo.name }
						</td>
						<td>
							${mo.pinpai }
						</td>
						<td>
							${mo.guige }
						</td>
						<td>
							${mo.danwei }
						</td>
						<td>
							${mo.shuliang }
						</td>
						<td>
							${mo.mishu }
						</td>
						<td>
							${mo.danjia }
						</td>
						<td>
							${mo.money }
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="total">
			<table>
				<tbody>
					<tr>
						<td style="width: 70px;">
							应收金额:
						</td>
						<td style="width: 150px;" id="total">
							${map.price }
						</td>
						<td style="width: 80px;">
							人民币大写:
						</td>
						<td id="dxtotal">
							${map.dxprice }
						</td>
						<td style="width: 20%; text-align: right;">
							<button style="float: right;" onclick="print();">打印</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>
