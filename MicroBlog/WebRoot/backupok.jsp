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
	{if(frm.newpwd3.value!=frm.newpwd4.value){
	alert("您两次输的密码不一样!请重新输入.");
	return false;}
	else{
	return true;}

}

</script>
</head>
<body>
<!-- header开始-->
<table id="header" align="center" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="20%" align="center"><img src="images/logo2.png" width="178" height="62" /></td>
    <td width="55%" align="left">密码找回</td>
    
    <td width="25%" align="right">&nbsp;</td>
  </tr>
</table>
<!-- header结束-->
<!-- container 开始-->
<table border="0" align="center" cellpadding="0" cellspacing="0" id="container">
  <tr>
    <td width="670" valign="top">
    <form action="servlet/MailServlet" method="post" name="frm" onsubmit="return CheckFrom()">
    <%
    String msg=request.getParameter("msg");
    pageContext.setAttribute("msg",msg);
     %>
     	<input type="hidden" name="userid"  value="${msg}" />
     	<input type="hidden" name="u_pwd" value="${sessionScope.u_pwd}">
     <table border="0" align="center" cellpadding="0" cellspacing="0" id="userinfo">
        <tr>
          <td class="title">您好,请设置您的新密码！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <a href="login.jsp">返回登录</a><br></td>
          </tr>
        <tr>
          <td class="menu"><br></td>
        </tr>
        <tr>
          <td align="center"><table width="90%" border="0" cellpadding="5" cellspacing="0" id="userinfo_content">

            <tr onmouseover="tagscheck(this);">
              <td align="right">请输入密保邮箱：</td>
              <td><input name="receive" type="text" class="input1" id="rpassword" /></td>
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

    </td>
  </tr>
	</table>
	<%
						if (request.getParameter("msg") != null) {

							int res = Integer.parseInt(request.getParameter("msg"));
							if (res == 1) {

								out.print("<script>alert('密码已成功发送至您的密保邮箱，请查收，谢谢使用!'); </script>");
							} else {

								out.print("<script>alert('修改失败!');</script>");

							}

						}
					%>
	
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


