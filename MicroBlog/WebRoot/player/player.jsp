<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>播放器 </title>
</head>

<body background="/MicroBlog/images/playerbg.jpg"><br/><br/><br/><br/><br/>
<center><font size="200" color="white">MUSIC</font></center><br/><br/><br/>
<div align="center">
<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"  width="300" height="320" id="mp3player"
		codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" >
	<param name="movie" value="mp3player.swf?config=config_2.xml&file=playlist_2.xml" />
	<param nam="allowScriptAccess" value="always">
	<embed src="mp3player.swf?config=config_2.xml&file=playlist_2.xml" allowScriptAccess="always" width="299" height="319" name="mp3player"
		type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
</object>
<br/><br/><br/><br/><br/><br/><br/><br/>
<a href="../home.jsp">返回首页</a>
</div>
</body>
</html>
