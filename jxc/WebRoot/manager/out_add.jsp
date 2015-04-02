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
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>My97DatePicker/WdatePicker.js"></script>

		<base href="<%=basePath%>">

		<title>My JSP 'right.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="manager/js/jquery.js"></script>
		<script type="text/javascript" src="manager/js/jqueryui.js"></script>
		<script type="text/javascript" src="manager/js/formatmoney.js"></script>
		<link rel="stylesheet" type="text/css" href="manager/css/jxc.css">
		<link rel="stylesheet" type="text/css"
			href="manager/css/ui-lightness/jqueryui.css">
		<script type="text/javascript">
			var tot=0;	
			function addrow(){
				$.get("manager/out_moban.jsp",function(msg){
					$("#data").append(msg);
					updaterow();
					shijian();
				});
			}
			
			
			function delerow(t){
				$(t).parent().remove();
				updaterow();
				jisuan(t);
			}
			function updaterow(){
				var u=$(".table").find("tr");
				for(var j=1;j<u.length;j++){
					var tds=$(u[j]).children();
					$(tds[0]).html(j);
				}
			}
			$(document).ready(function(){
				 addrow();
			});
			function save(){
				var json='{';
				var name=$("#name").val();
				var phone=$("#phone").val();
				var riqi=$("#riqi").val();
				var total=$("#total").html();
				var dxtotal=$("#dxtotal").html();
				if(name.length<=0){
					alert("客户名不能为空");
					return;
				}
				if(phone.length<=0){
					alert("电话不能为空");
					return;
				}
				if(riqi.length<=0){
					alert("日期不能为空");
					return;
				}
				json=json+'"username":"'+name+'","phonenum":"'+phone+'","date":"'+riqi+'","total":"'+total+'","dxtotal":"'+dxtotal+'"';
				var child=$(".table").find("tr");
				if(child.size()>1){
					var data="[";
					for(var i=1;i<child.size();i++){
						var tr=$(child[i]);
						var username=$(".username",$(tr)).val();
						var pinpai=$(".pinpai",$(tr)).val();
						var guige=$(".guige",$(tr)).val();
						var shuliang=$(".shuliang",$(tr)).val();
						var danjia=$(".danjia",$(tr)).val();
						
						var danwei=$(".danwei",$(tr)).html();
						var mishu=$(".mishu",$(tr)).html();
						var price=$(".price",$(tr)).html();
						if(username.length<=0){
							alert("商品名称不能为空");
							return;
						}
						if(pinpai.length<=0){
							alert("品牌名称不能为空");
							return;
						}
						if(guige.length<=0){
							alert("规格不能为空");
							return;
						}
						if(shuliang.length<=0){
							alert("数量不能为空");
							return;
						}
						if(danjia.length<=0){
							alert("单价不能为空");
							return;
						}
						var u='{"username":"'+username+'","pinpai":"'+pinpai+'","guige":"'+guige+'","danwei":"'+danwei+'","mishu":"'+mishu+'","price":"'+price+'","shuliang":"'+shuliang+'","danjia":"'+danjia+'"}';
						data=data+u+",";
					}
					data=data.substring(0,data.length-1);
					json=json+',"data":'+data+']}';
					$.getJSON("manager/out!save.action",{json:json},function(msg){
						alert(msg.msg);
						window.open(msg.url,"_self");
					});
				}
			}
			
			function total(){
				tot=0;
				$(".price").each(function(){
					var p=$(this).html();
					var m=/\d+.?\d*/;
					if(m.test(p)){
						tot=tot+parseFloat(p);
					}
				});
				tot=Math.round(tot*100)/100;
				$("#total").html(tot);
				$("#dxtotal").html(DX(tot));
			}
		</script>
	</head>

	<body>
		<div>

			<div class="menu">
				<button style="float: right" onclick="save()">
					保存出货单
				</button>
				客户：
				<input type="text" name="name" id="name">
				电话：
				<input type="text" name="phone" id="phone">
				日期：
				<input type="text" name="riqi"
					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="riqi" readonly="readonly">
			</div>
			<table class="table" style="margin-top: 10px">
				<tbody id="data">
					<tr>
						<th style="width: 5%">
							序号
						</th>
						<th style="width: 20%">
							商品名称
						</th>
						<th style="width: 15%">
							品牌
						</th>
						<th style="width: 10%">
							规格
						</th>
						<th style="width: 10%">
							单位
						</th>
						<th style="width: 10%">
							数量
						</th>
						<th style="width: 10%">
							米数
						</th>
						<th style="width: 10%">
							单价
						</th>
						<th style="width: 10%">
							金额
						</th>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="total">
			<table>
				<tr>
					<td style="width: 80px; background-color: #EEF2FB;">
						应收金额:
					</td>
					<td id="total" style="width: 150px;"></td>
					<td style="width: 80px; background-color: #EEF2FB;">
						人民币大写
					</td>
					<td id="dxtotal"></td>
					<td style="width: 20%; text-align: right;">
						<button onclick="addrow()">
							添加
						</button>
					</td>
				</tr>
			</table>
		</div>


	</body>
</html>
