<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title></title>
<style>
td{height:15px;}
</style>
<link rel="stylesheet" type="text/css" href="css/global.css" />
<link rel="stylesheet" type="text/css" href="css/userinfo.css" />
<title>微博 - 账号管理</title>

<script type="text/javascript" >

function init() {

		/////////填充Radio
		var hidden_sex = document.getElementById('u1_sex').value; //隐藏域中记录的值
		
		
		var frm_sex = document.frm.u_sex; //HTML 控件值

		//遍历Sex控件数组，如果某一个值和隐藏域中的值相等，则让此控件的Checked属性为true;
		for ( var i = 0; i < frm_sex.length; i++) {
			if (frm_sex[i].value == hidden_sex) {
				frm_sex[i].checked = true;
				break;
			}
		}

    
////////////填充Select
		//获取隐藏域中的选课控件值
		var hidden_subject = document.getElementById('city').value;
		//获取下拉选框
		var frm_city = document.frm.u_city;

		for ( var i = 0; i < frm_city.length; i++) {
			if (frm_city.options[i].value == hidden_subject) {
				frm_city.selectedIndex = i;
				break;
			}
		}
		
}

function tagscheck(a){
 var lng=document.all.tags(a.tagName).length;
 for(i=0;i<lng;i++){
  var temp=document.all.tags(a.tagName)[i];
  if(a==temp){
   //选中的标签样式
   temp.style.background="#ccc";
 
   }else{
   //恢复原状
   temp.style.background="";
 
   
   }
   
  
  }
 
 }
</script>
</head>
<body onload="init()">
	



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
    <td width="670" height="600" valign="top">
    	
    	<form name="frm" action="servlet/UpdateServlet">
			
			<!-- 记录被修改输的标识 -->
			<input type="hidden" id="u_id" name="u_id"  value="${sessionScope.user.u_id }" />
			<input type="hidden" id="ua_id" name="u_account"  value="${sessionScope.user.u_account }" />

			<!-- 记录Radio的值 -->
			<input type="hidden" id="u1_sex"  value="${sessionScope.user.u_sex }" />
              <input type="hidden" id="city" value="${sessionScope.user.u_city }" />
		

		
    
      <table border="0" align="center" cellpadding="0" cellspacing="0" id="userinfo">
        <tr>
          <td class="title">个人资料 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <a href = "home.jsp">返回主页</a></td>
          </tr>
        <tr>
          <td class="menu">基本资料 | <a href="mypassword.jsp">修改密码</a> | <a href="myface.jsp">修改头像</a></td>
        </tr>
        <tr>
          <td align="center"><table width="90%" border="0" cellpadding="5" cellspacing="0" id="userinfo_content">
            <tr>
              <td onmouseover="tagscheck(this);" width="20%" align="right">登录账号：</td>
              <td width="53%">${sessionScope.user.u_account}</td>
              </tr>
            <tr onmouseover="tagscheck(this);">
              <td  align="right">昵称：</td>
              <td><input name="u_nickname" type="text" class="input1" id="password" value="${sessionScope.user.u_nickname} " /></td>
              </tr>
            <tr onmouseover="tagscheck(this);">
              <td  align="right">真实姓名：</td>
              <td><input name="u_realname" type="text" class="input1" id="rpassword" value="${sessionScope.user.u_realname} " /></td>
              </tr>
           <tr>
              <td  align="right">所在城市：</td>
              <td><select name="u_city"   class="input1"     id='city' >
				<option value="北京">
					北京
				</option>
				<option value="天津">
					天津
				</option>
				<option value="上海">
					上海
				</option>
				<option value="山东">
					山东
				</option>
				<option value="长沙">
				         长沙
				</option>
			</select>
			</td>
              </tr>
            <tr>
              <td align="right">性别：</td>
              <td><input type="radio" name="u_sex"  value="male" />
                男
                &nbsp; &nbsp; &nbsp; &nbsp; <input type="radio" name="u_sex"  value="female" />
                女</td>
              </tr>
            <tr onmouseover="tagscheck(this);">
             <td   align="right">出生日期：</td>
              <td><input name="u_date" type="text" value="${sessionScope.user.u_date}"/></td>
              </tr>
            <tr onmouseover="tagscheck(this);">
              <td  align="right">QQ：</td>
              <td><input name="u_qq" type="text" class="input1" id="nickname2" value="${sessionScope.user.u_qq}"/></td>
              </tr>
            <tr onmouseover="tagscheck(this);">
              <td  align="right">MSN：</td>
              <td align="left"><input name="u_msn" type="text" class="input1" id="nickname3" value="${sessionScope.user.u_msn}" /></td>
              </tr>
            <tr onmouseover="tagscheck(this);">
              <td align="right">签名：</td>
              <td align="left"><input name="u_motto" type="text" class="input1" id="nickname4" value="${sessionScope.user.u_motto}" /></td>
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
    
    }else{out.print("<script>alert('修改失败');</script>");
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
