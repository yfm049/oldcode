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
			function update(){
				var json='{"id":"${id}",';
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
					$.getJSON("manager/out!saveupdate.action",{json:json},function(msg){
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
				<button style="float: right" onclick="update()">
					更改出货单
				</button>
				客户：
				<input type="text" name="name" id="name" value="${map.username }">
				电话：
				<input type="text" name="phone" id="phone" value="${map.phonenum }">
				日期：
				<input type="text" name="riqi"onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="riqi"value="${map.date }">
			</div>
			<table class="table" style="margin-top: 10px">
				<tbody id="data">
					<tr>
						<th>
							序号
						</th>
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
					<c:forEach items="${lmo}" var="mo" varStatus="status">
						<tr>
							<td style="text-align: center" ondblclick="delerow(this)">${status.index+1 }</td>
							<td>
								<input type="text" style="width: 100px;" class="username"value="${mo.name }">
							</td>
							<td>
								<input type="text" style="width: 100px;" class="pinpai"value="${mo.pinpai }">
							</td>
							<td>
								<input type="text" style="width: 100px;" class="guige"value="${mo.guige }">
							</td>
							<td style="text-align: center" class="danwei">${mo.danwei }</td>
							<td>
								<input type="text" style="width: 100px;" class="shuliang"value="${mo.shuliang }">
							</td>
							<td class="mishu">${mo.mishu }</td>
							<td>
								<input type="text" style="width: 100px;" class="danjia"value="${mo.danjia }">
							</td>
							<td class="price">${mo.money }</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="total">
			<table>
				<tr>
					<td style="width: 80px; background-color: #EEF2FB;">应收金额:</td>
					<td id="total" style="width: 150px;">${map.price }</td>
					<td style="width: 80px; background-color: #EEF2FB;">人民币大写</td>
					<td id="dxtotal">${map.dxprice }</td>
					<td style="width: 20%; text-align: right;">
						<button onclick="addrow()">
							添加
						</button>
					</td>
				</tr>
			</table>
		</div>


	</body>
	<script type="text/javascript">
<!--
	$(function() {
		updaterow();
		$( ".username" ).autocomplete({
			source: function( request, response ){
				$.getJSON("manager/auto!name.action",request,function(msg){
					response(msg);
				});
			}
		});
		$( ".pinpai" ).autocomplete({
			source: function( request, response ){
				$.getJSON("manager/auto!pinpai.action",request,function(msg){
					response(msg);
				});
			}
		});
		$( ".guige" ).autocomplete({
			source: function( request, response ){
				$.getJSON("manager/auto!guige.action",request,function(msg){
					response(msg);
				});
			},
		}).focusout(function(){
			var sl=$(this).val();
			var re=/^[a-zA-Z]\d{4}$/;
			if(!re.test(sl)){
				alert("规格格式不正确");
				$(this).val("");
			}else{
				jisuan(this);
			}
		});
		
		$(".shuliang").keyup(function(){
			var sl=$(this).val();
			var re=/^[0-9]*[1-9][0-9]*$/;
			if(!re.test(sl)){
				alert("数量必须为正整数");
				$(this).val("");
			}
			jisuan(this);
		});
		$(".danjia").keyup(function(){
			var sl=$(this).val();
			var re=/\d+.?\d*/;
			if(!re.test(sl)){
				alert("数据必须是小数");
				$(this).val("");
			}
			jisuan(this);
		});
	});
	function jisuan(t){
		var p=$(t).parents("tr");
		var dj=$(".danjia",$(p)).val();
		var gg=$( ".guige",$(p)).val();
		var sl=$(".shuliang",$(p)).val();
		
		var mishu;
		if(gg){
			gg=gg.substr(1,gg.length)/1000;
		}else{
			return;
		}
		if(sl){
			$(".mishu",$(p)).html(Math.floor(gg*sl * 1000)/1000);
		}else{
			$(".mishu",$(p)).html("");
			return;
		}
		if(dj){
			var mishu=$(".mishu",$(p)).html();
			$(".price",$(p)).html(Math.floor(mishu*dj*100)/100);
		}else{
			$(".price",$(p)).html("");
			return;
		}
		total();
	}
	
//-->
</script>
</html>
