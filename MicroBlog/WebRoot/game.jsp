<%@ page language="java" import="java.util.*,com.microblog.po.*,com.microblog.dao.*" import= "java.sql.*" import= "com.microblog.dbutil.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="Content-Language" content="zh-CN" />
<meta content="all" name="robots" />
<title>微博 - ${sessionScope.user.u_account}的主页</title>
<script type="text/javascript" src="script/home.js"></script>
</head>
  <body background="images/gamebg.png">
  <br/><br/><br/>
   <% if (request.getParameter("msg").equals("1")){%>

  <center><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="550" height="420" title="s">
    <param name="movie" value="file:///E|/flash/暴力摩托.swf" />
    <param name="quality" value="high" />
    <embed src="file:///E|/flash/暴力摩托.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="550" height="420"></embed>
  </object></center>
  <%  } %>
  
  
  <% if (request.getParameter("msg").equals("2")){%>

  <center><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="740" height="510" title="zcx">
  <param name="movie" value="file:///E|/flash/宠物连连看.swf" />
  <param name="quality" value="high" />
  <embed src="file:///E|/flash/宠物连连看.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="740" height="510"></embed>
</object></center>
  <%  } %>
  
  
   <% if (request.getParameter("msg").equals("3")){%>
<center>
 <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="600" height="300">
  <param name="movie" value="file:///E|/flash/电眼美少女2.swf" />
  <param name="quality" value="high" />
  <embed src="file:///E|/flash/电眼美少女2.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="600" height="300"></embed>
</object></center>
  <%  } %>
  
   <% if (request.getParameter("msg").equals("4")){%>

 <center><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="550" height="400" title="as">
  <param name="movie" value="file:///E|/flash/黄金矿工双人版.swf" />
  <param name="quality" value="high" />
  <embed src="file:///E|/flash/黄金矿工双人版.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="550" height="400"></embed>
</object></center>
  <%  } %>
  </body>
 <SCRIPT language="JavaScript">
//符合web标准且可关闭的多幅对联广告
//Modified By 在远方 www.jscode.cn

lastScrollY = 0;
function heartBeat(){
var diffY;
if (document.documentElement && document.documentElement.scrollTop)
diffY = document.documentElement.scrollTop;
else if (document.body)
diffY = document.body.scrollTop
else
{/*Netscape stuff*/}
//alert(diffY);
percent=.1*(diffY-lastScrollY);
if(percent>0)percent=Math.ceil(percent);
else percent=Math.floor(percent);
document.getElementById("leftDiv").style.top = parseInt(document.getElementById("leftDiv").style.top)+percent+"px";
document.getElementById("rightDiv").style.top = parseInt(document.getElementById("rightDiv").style.top)+percent+"px";
lastScrollY=lastScrollY+percent;
//alert(lastScrollY);
}
//下面这段删除后，对联将不跟随屏幕而移动。
window.setInterval("heartBeat()",1);
//-->
//关闭按钮
function close_left1(){
    left1.style.visibility='hidden';
}
function close_left2(){
    left2.style.visibility='hidden';
}
function close_right1(){
    right1.style.visibility='hidden';
}
function close_right2(){
    right2.style.visibility='hidden';
}
//显示样式
document.writeln("<style type=\"text\/css\">");
document.writeln("#leftDiv,#rightDiv{width:100px;height:100px;background-color:#fff;position:absolute;}");
document.writeln(".itemFloat{width:100px;height:auto;line-height:5px}");
document.writeln("<\/style>");
//以下为主要内容
document.writeln("<div id=\"leftDiv\" style=\"top:40px;left:5px\">");
//------左侧各块开始
//---L1
document.writeln("<div id=\"left1\" class=\"itemFloat\">");
document.writeln("<a target=_blank href=http://www.jscode.cn><img height=150 width=120 border=0 src=images/mei.png></a>");
document.writeln("<br><a href=\"javascript:close_left1();\" title=\"关闭上面的广告\"><center>风尚美容<center><\/a><br>");
document.writeln("<\/div>");
//---L2
document.writeln("<div id=\"left2\" class=\"itemFloat\">");
document.writeln("<a target=_blank href=http://www.jscode.cn><img height=150 width=120 border=0 src=images/bg.png></a>");
document.writeln("<br><a href=\"javascript:close_left2();\" title=\"关闭上面的广告\"><center>仙剑十年<center><\/a><br>");
document.writeln("<\/div>");
//------左侧各块结束
document.writeln("<\/div>");
document.writeln("<div id=\"rightDiv\" style=\"top:40px;right:5px\">");
//------右侧各块结束
//---R1
document.writeln("<div id=\"right1\" class=\"itemFloat\">");
document.writeln("<a target=_blank href=http://www.jscode.cn><img height=150 width=120 border=0 src=images/yu.png></a>");
document.writeln("<br><a href=\"javascript:close_right1();\" title=\"关闭上面的广告\"><center>海底总动员<center><\/a><br>");
document.writeln("<\/div>");
//---R2
document.writeln("<div id=\"right2\" class=\"itemFloat\">");
document.writeln("<a target=_blank href=http://www.jscode.cn><img height=150 width=120 border=0 src=images/yu2.jpg></a>");
document.writeln("<br><a href=\"javascript:close_right2();\" title=\"关闭上面的广告\"><center>京京历险记<center><\/a><br>");
document.writeln("<\/div>");
//------右侧各块结束
document.writeln("<\/div>");
</SCRIPT>
<p>  </p>
<p>　</p>
<p>　</p>
<p>　</p>

  
  <a href="home.jsp">返回首页</a>
  
  </body>
</html>
