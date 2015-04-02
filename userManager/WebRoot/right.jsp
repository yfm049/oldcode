<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="images/main.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.STYLE1 {font-size: 12px}
.STYLE3 {color: #707070; font-size: 12px; }
.STYLE5 {color: #0a6e0c; font-size: 12px; }
a{text-decoration: none;}
body {
	margin-top: 0px;
	margin-bottom: 0px;
}
.STYLE7 {font-size: 12}
-->
</style>
  </head>
  
  <body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>&nbsp;</td>
        <td style="padding-right:10px;"><div align="right">
          <table border="0" align="right" cellpadding="0" cellspacing="0">
            <tr>
              <td width="60"><table width="87%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    
                  </tr>
              </table></td>
              <td width="60"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center"><img src="images/001.gif" width="14" height="14" /></div></td>
                    <td class="STYLE1"><div align="center"><a href="add.jsp">新增</a></div></td>
                  </tr>
              </table></td>
              
            </tr>
          </table>
        </div></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#c9c9c9">
      <tr>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">用户</span></strong></div></td>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">IMEI</span></strong></div></td>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">修改</span></strong></div></td>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">删除</span></strong></div></td>
      </tr>
      <%
      	List<Map<String, Object>> lmo=(List<Map<String, Object>>)request.getAttribute("listdate");
      	for(Map<String, Object> mo:lmo){
      	
       %>
      <tr>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3"><%=mo.get("username") %></span></div></td>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3"><%=mo.get("IMEI") %></span></div></td>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><a href="servlet/Update?id=<%=mo.get("id") %>" target="right"><span class="STYLE5">修改</span></a></div></td>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><a href="servlet/Delete?id=<%=mo.get("id") %>"><span class="STYLE5">删除</span></a></div></td>
      </tr>
      <%} %>
      
    </table></td>
  </tr>
  
</table>
  </body>
</html>
