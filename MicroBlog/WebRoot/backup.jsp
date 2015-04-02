<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" type="text/css" href="css/global.css" />
<link rel="stylesheet" type="text/css" href="css/index.css" />
<title>微博系统</title>    
<script type="text/javascript" src="script/global.js"></script>
<script type="text/javascript" src="script/index.js"></script>
</head>

<body>
<!--header 开始-->
<table id="header" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="right"><table border="0" align="right" cellpadding="0" cellspacing="0" id="daohang">
      <tr>
        <td align="center"><a href="user.html">随便看看</a>&nbsp; |&nbsp; <a href="about.html">关于微博</a></td>
        </tr>
    </table></td>
  </tr>
</table>
<!--header 结束-->
<!--container 开始-->
<table border="0" align="center" cellpadding="0" cellspacing="0" id="container">
  <tr>
    <td width="70%" valign="top">
    <!-- content开始 -->
    <table id="content" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td class="phototitle"><img src="icon/huangguan.gif" width="24" height="19" align="absmiddle" /> 他（她）们正在用微博</td>
     </tr>
     <tr>
       <td>
         <!-- photolist开始 -->
         <table id="photolist" border="0" cellspacing="0" cellpadding="0">
            <tr>			  	
             <td><MARQUEE scrollAmount=3 width="80"><INPUT style="FILTER: alpha(opacity=100,style=2) width="80"; HEIGHT="80" type=image src="face/1.jpg"></MARQUEE></td>
              <td><MARQUEE scrollAmount=3 width="80"><INPUT style="FILTER: alpha(opacity=100,style=2) width="80"; HEIGHT="80" type=image src="face/2.jpg"></MARQUEE></td>
              <td><MARQUEE scrollAmount=3 width="80"><INPUT style="FILTER: alpha(opacity=100,style=2) width="80"; HEIGHT="80" type=image src="face/3.jpg"></MARQUEE></td>
              <td><MARQUEE scrollAmount=3 width="80"><INPUT style="FILTER: alpha(opacity=100,style=2) width="80"; HEIGHT="80" type=image src="face/4.jpg"></MARQUEE></td>
              <td><MARQUEE scrollAmount=3 width="80"><INPUT style="FILTER: alpha(opacity=100,style=2) width="80"; HEIGHT="80" type=image src="face/5.jpg"></MARQUEE></td>
              <td><MARQUEE scrollAmount=3 width="80"><INPUT style="FILTER: alpha(opacity=100,style=2) width="80"; HEIGHT="80" type=image src="face/6.jpg"></MARQUEE></td>
              <td><MARQUEE scrollAmount=3 width="80"><INPUT style="FILTER: alpha(opacity=100,style=2) width="80"; HEIGHT="80" type=image src="face/7.jpg"></MARQUEE></td>
               <td><MARQUEE scrollAmount=3 width="80"><INPUT style="FILTER: alpha(opacity=100,style=2) width="80"; HEIGHT="80" type=image src="face/8.jpg"></MARQUEE></td>
             </tr>
           <tr>
           <td><MARQUEE scrollAmount=3 width="100%"><INPUT style="FILTER: alpha(opacity=100,style=2) width="80"; HEIGHT="80" type=image src="face/9.jpg"></MARQUEE></td>
              <td><MARQUEE scrollAmount=3 direction=right width="100%"><INPUT style="FILTER: alpha(opacity=100,style=2) width="80"; HEIGHT="80" type=image src="face/10.jpg"></MARQUEE></td>
              <td><MARQUEE scrollAmount=3 direction=right width="100%"><INPUT style="FILTER: alpha(opacity=100,style=2) width="80"; HEIGHT="80" type=image src="face/11.jpg"></MARQUEE></td>
              <td><MARQUEE scrollAmount=3 direction=right width="100%"><INPUT style="FILTER: alpha(opacity=100,style=2) width="80"; HEIGHT="80" type=image src="face/12.jpg"></MARQUEE></td>
              <td><MARQUEE scrollAmount=3 direction=right width="100%"><INPUT style="FILTER: alpha(opacity=100,style=2) width="80"; HEIGHT="80" type=image src="face/13.jpg"></MARQUEE></td>
              <td><MARQUEE scrollAmount=3 direction=right width="100%"><INPUT style="FILTER: alpha(opacity=100,style=2) width="80"; HEIGHT="80" type=image src="face/14.jpg"></MARQUEE></td>
              <td><MARQUEE scrollAmount=3  direction=right width="100%"><INPUT style="FILTER: alpha(opacity=100,style=2) width="80"; HEIGHT="80" type=image src="face/15.jpg"></MARQUEE></td>
               <td><MARQUEE scrollAmount=3 direction=right width="100%"><INPUT style="FILTER: alpha(opacity=100,style=2) width="80"; HEIGHT="80" type=image src="face/16.jpg"></MARQUEE></td>
            </tr>
          <tr>
       <td><MARQUEE scrollAmount=3 width="100%"><INPUT style="FILTER: alpha(opacity=100,style=2) width="80"; HEIGHT="80" type=image src="face/17.jpg"></MARQUEE></td>
              <td><MARQUEE scrollAmount=3  width="100%"><INPUT style="FILTER: alpha(opacity=100,style=2) width="80"; HEIGHT="80" type=image src="face/18.jpg"></MARQUEE></td>
              <td><MARQUEE scrollAmount=3  width="100%"><INPUT style="FILTER: alpha(opacity=100,style=2) width="80"; HEIGHT="80" type=image src="face/19.jpg"></MARQUEE></td>
              <td><MARQUEE scrollAmount=3  width="100%"><INPUT style="FILTER: alpha(opacity=100,style=2) width="80"; HEIGHT="80" type=image src="face/20.jpg"></MARQUEE></td>
              <td><MARQUEE scrollAmount=3  width="100%"><INPUT style="FILTER: alpha(opacity=100,style=2) width="80"; HEIGHT="80" type=image src="face/21.jpg"></MARQUEE></td>
              <td><MARQUEE scrollAmount=3  width="100%"><INPUT style="FILTER: alpha(opacity=100,style=2) width="80"; HEIGHT="80" type=image src="face/22.jpg"></MARQUEE></td>
              <td><MARQUEE scrollAmount=3   width="100%"><INPUT style="FILTER: alpha(opacity=100,style=2) width="80"; HEIGHT="80" type=image src="face/23.jpg"></MARQUEE></td>
               <td><MARQUEE scrollAmount=3  width="100%"><INPUT style="FILTER: alpha(opacity=100,style=2) width="80"; HEIGHT="80" type=image src="face/24.jpg"></MARQUEE></td>
           </tr>
         </table>
         <!-- photolist结束 -->
       </td>
     </tr>
     <tr>
       <td>
          <!--counter开始-->
          <table id="counter" border="0" cellpadding="0" cellspacing="0" align="center">
             <tr>
               <td valign="middle">
                 <table width="330" border="0" align="center" cellpadding="0" cellspacing="0">
                   <tr><br/><br/>
                     <td class="counternum">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;24</td>
                   </tr>
                 </table>
               </td>
             </tr>
          </table>
        </td>
      </tr>
    </table>
    <!-- content结束 -->
    </td>
    <td>
    <!-- pageright开始 -->
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center">
        <!--login开始-->
        <form  method="post" action="servlet/BackupServlet">
        <table id="login" width="220" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td colspan="2"><br /></td>
          </tr>
          <tr>
            <td colspan="2" align="left">请输入用户名：<br />
              <input name="userid" type="text"  />
            </td>
          </tr>
          <tr>
            <td colspan="2" align="left">请输入验证答案：<br />
              <input name="ques" type="text"  />
            </td>
          </tr>
          <tr>
            <td width="120"><a href="register.jsp"><img height="36" border="0" width="205" src="images/regbtn.png"/></a></td>
            <td width="100"><br /></td>
          </tr>
          <tr>
            <td colspan="2" align="center"><input name="Submit" type="submit" class="loginbtn" id="Submit" value="找回密码" /></td>
          </tr>
        </table>
        </form>
          <% 
  if (request.getParameter("msg")!=null){
    int res=Integer.parseInt(request.getParameter("msg"));
    if(res==1){
    out.print("<script>alert('对不起，您输入的用户名或验证信息错误，请重新输入');</script>");
    
    }
  } 
  
  %>
        <!--login结束-->
        </td>
      </tr>
	  <tr>
        <td height="20" valign="bottom"><hr color="#CCCCCC" width="97%" size="1" /></td>
      </tr>
      <tr>
        <td>
        <!-- messge 开始-->
          <table id="message" width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>              
              <td class="title">公告</td>
            </tr>
            <tr>
              <td>
                <ul type="disc">					
			      <li>微博系统开始测试火爆进行中</li>
                  <li>有啥新鲜事，跟大家说说</li>	
                  <li>完善资料寻找志同道合的朋友</li>	
                  <li>客服热线：022-88888888</li>						
			      <li><a href="#" target="_blank">更多历史公告...</a></li>
			    </ul>
              </td>
            </tr>
            <tr>
			  <td align="center">
				<a href="#" target="_blank"><img src="images/testad.jpg" height="60" width="270" /></a>
			  </td>
			</tr>
          </table>
        </td>
      </tr>
    </table>
    <!-- pageright结束 -->
    </td>
  </tr>
</table>
<!--container 结束-->
<!--footer开始-->
<table id="index_footer" border="0" align="center" cellpadding="5" cellspacing="0">
  <tr>
    <td width="304" height="50" align="left">&copy; 2011-2012 微博系统 版权所有</td>        
    <td width="501" align="right"><a href="#">帮助</a>&nbsp;&nbsp; <a href="#">意见反馈</a>&nbsp;&nbsp; <a href="#">微博认证及合作</a>&nbsp;&nbsp; <a href="#">开放平台</a>&nbsp;&nbsp; <a href="#">微博招聘</a>&nbsp;&nbsp; <a href="#">微博导航</a></td>
    <td width="166" align="right">语言：
      <select name="select" id="select">
        <option>中文(简体)</option>
        <option>中文(繁体)</option>
    </select></td>
  </tr>        
</table>
<!--footer结束-->
</body>
</html>