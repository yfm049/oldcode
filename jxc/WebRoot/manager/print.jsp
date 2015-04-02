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

		<title>出库打印页面</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="manager/js/jquery.js"></script>
		<link rel="stylesheet" type="text/css" href="manager/css/jxc.css">
	</head>

	<body style="padding-left: 40px;padding-right: 40px;padding-top: 20px;padding-bottom: 20px;">
		<div class="info">
			<table>
				<tr>
					
					<td colspan="8" style="text-align: center;">
						<h2>销售出库单</h2>
					</td>
				</tr>
				<tr>
					<td style="width: 35px;">
						客户:
					</td>
					<td>
						${map.username }
					</td>
					<td  style="width: 35px;">
						单号:
					</td>
					<td>
						${map.danhao }
					</td>
					<td  style="width: 35px;">
						电话:
					</td>
					<td>
						${map.phonenum }
					</td>
					<td  style="width: 35px;">
						时间:
					</td>
					<td>
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
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>
