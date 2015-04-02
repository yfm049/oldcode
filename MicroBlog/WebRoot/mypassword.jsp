<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" type="text/css" href="css/global.css" />
<link rel="stylesheet" type="text/css" href="css/mypassword.css" />
<title>微博 - 账号管理</title>

<script type="text/javascript"  language="javascript" >

function CheckFrom()
{if(frm.newpwd1.value!=frm.newpwd2.value){
alert("您两次输的密码不一样!请重新输入.");
return false;}
else{
return true;}

}

</script>
<script type="text/javascript">
var xhr;
	function checkpassword() {
		var password = document.frm.old_pwd.value;
		if(password == "" || password == null) {
		
			var passwordmsg = document.getElementById("passwordmsg");
			passwordmsg.innerHTML = '<span><font color="red">密码为空</font></span>';
				   
			
			return ;
		}else {
		

			xhr = new XMLHttpRequest();

			xhr.open("get" , "/MicroBlog/servlet/CheckServlet1?password="+password ,true);
		
			xhr.onreadystatechange = handler;
		
			xhr.send(null);
		}
	}
	
	function handler() {
		if(xhr.status == 200 && xhr.readyState == 4)  {
			
			
			var result = document.getElementById("passwordmsg");
			
			var res = eval('('+xhr.responseText+')');
			
			if(res) {
				result.innerHTML = '<span><font color="green">密码正确</font></span>';
			}else {
				result.innerHTML = '<span><font color="red">密码错误</font></span>';
			}
		}
	
		
	}
</script>
</head>
<body>
<!-- header开始-->
<table id="header" align="center" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="20%" align="center"><img src="images/logo2.png" width="178" height="62" /></td>
    <td width="55%" align="left">账号设置</td>
    <td width="25%" align="right">&nbsp;</td>
  </tr>
</table>
<!-- header结束-->
<!-- container 开始-->
<table border="0" align="center" cellpadding="0" cellspacing="0" id="container">
  <tr>
    <td width="670" valign="top">
    <form action="servlet/UpdatepwdServelet" method="get" name="frm" onsubmit="return CheckFrom()">
      <input type="hidden" id="u_id" name="u_id"  value="${sessionScope.user.u_id }" />
      <input type="hidden" id="u_pwd" name="u_pwd"  value="${sessionScope.user.u_pwd }" />
      <table border="0" align="center" cellpadding="0" cellspacing="0" id="userinfo">
        <tr>
          <td class="title">个人资料 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <a href = "home.jsp">返回主页</a></td>
          </tr>
        <tr>
          <td class="menu"><a href="userinfo.jsp">基本资料</a> | 修改密码 | <a href="myface.jsp">修改头像</a></td>
        </tr>
        <tr>
          <td align="center"><table width="90%" border="0" cellpadding="5" cellspacing="0" id="userinfo_content">
            <tr onmouseover="tagscheck(this);">
              <td width="20%" align="right">当前密码：</td>
              <td width="53%"><input name="old_pwd" type="password" class="input1" id="password"   onblur="checkpassword()"/></td>
               <td width="27%"><span id="passwordmsg"><a href="#">请输入旧密码</a></span></td>
            </tr>
            <tr onmouseover="tagscheck(this);">
              <td align="right">新密码：</td>
              <td><input name="newpwd1" type="password" class="input1" id="rpassword" /></td>
              </tr>
            <tr onmouseover="tagscheck(this);">
              <td align="right" >确认密码：</td>
              <td><input name="newpwd2" type="password" class="input1" id="nickname"  /></td>
              </tr>
             <tr>
              <td align="right">&nbsp;</td>
              <td align="left">
               <input name="dosubmit" type="submit" id="dosubmit" value="" style="background:url(images/editbtn.png); border-style:none; width:150px; height:37px; background-repeat:no-repeat;" />
               </td>
            </tr>
            </table></td>
          </tr>
        </table>
      </form>
      <% 
  if (request.getParameter("msg")!=null){
    int res=Integer.parseInt(request.getParameter("msg"));
    if(res==1){
    out.print("<script>alert('修改成功');</script>");
    
    }else{out.print("<script>alert('当前密码与用户密码不一致，请重新输入');</script>");
    }
  } 
  
  %>
    </td>
  </tr>
	</table>
<!-- container 结束-->

<!--footer开始-->
<table id="footer" border="0" align="center" cellpadding="3" cellspacing="0">
  <tr>
    <td width="534" align="left"><a href="#">帮助</a>&nbsp;&nbsp; <a href="#">意见反馈</a>&nbsp;&nbsp; <a href="#">微博认证及合作</a>&nbsp;&nbsp; <a href="#">开放平台</a>&nbsp;&nbsp; <a href="#">微博招聘</a>&nbsp;&nbsp; <a href="#">微博导航</a></td>        
    <td width="447" align="right">Copyright: 2011-2015<a href="#"> 微博系统 版权所有</a></td>
  </tr>
  <tr>
    <td align="left">客服电话：400 123 12345（按当地市话标准收费）</td>
    <td align="right">语言：
      <select name="select" id="select">
        <option>中文(简体)</option>
        <option>中文(繁体)</option>
    </select></td>
  </tr>        
</table>
<!--footer结束-->
</body>
</html>
