<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<html>
	<head>
    </head>
  
  <body>
    <form action="doLoginServlet" method="post">
    <table>
    <caption>用户登录</caption>
    <tr><td>登录名:</td>
    <td><input type="text" name="username"></td>
    </tr>
    <tr>
    <td>密码：</td>
    <td><input type="password" name="password"/></td>
    </tr>
    </table>
    <input type="submit" value="登陆">
    <input type="reset" value="重置"/>
    </form>
  </body>
</html>
