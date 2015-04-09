<%@ page language="java"
	import="java.util.*,com.microblog.po.*,com.microblog.dao.*"
	import="java.sql.*" import="com.microblog.dbutil.*"
	pageEncoding="utf-8"%>
<%@ page language="java"
	import="com.microblog.po.*,com.microblog.po.Collection"%>
<%@ page language="java" import="com.microblog.dao.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<link rel="stylesheet" type="text/css" href="css/global.css" />
		<link rel="stylesheet" type="text/css" href="css/profile.css" />
		<title>微博 - 别来碰我的微博</title>
		<script type="text/javascript" src="script/profile.js"></script>
	</head>
	<body>

		<!-- header开始-->
		<table id="header" align="center" border="0" cellspacing="0"
			cellpadding="0">

			<tr>
				<td width="20%" align="center">
					<img src="images/logo2.png" width="178" height="62" />
				</td>
				<td width="55%" align="right">
					<table border="0" align="right" cellpadding="0" cellspacing="0"
						id="daohang">
						<tr>
							<td width="20%">
								<a href="servlet/AllServlet?u_id=${sessionScope.user.u_id}">我的首页</a>
							</td>
							<td width="20%">
								<a href="servlet/MyBlogServlet?u_id=${sessionScope.user.u_id}">我的博客</a>
							</td>
							<td width="20%">
								<a href="servlet/FriendSevlet?u_id=${sessionScope.user.u_id}">我的好友</a>
							</td>
							<td width="20%">
								<a href="yule.jsp">请休息吧</a>
							</td>
							<td width="20%">
								<a href="ballot.jsp">微博热议</a>
							</td>
						</tr>
					</table>
				</td>
				<td width="25%" align="right">
					<table id="welcome" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="30" height="30" rowspan="2" class="userface_bg">
								<img src="${sessionScope.user.u_pic}" border="0" width="20"
									height="20" />
							</td>
							<td>
								欢迎您，${sessionScope.user.u_nickname }
							</td>
							
							 <td><br/><STYLE>A.menuitem {
COLOR: menutext; TEXT-DECORATION: none
}
A.menuitem:hover {
COLOR: highlighttext; BACKGROUND-COLOR: highlight
}
DIV.contextmenu {
BORDER-RIGHT: 2px outset; BORDER-TOP: 2px outset; Z-INDEX: 999; VISIBILITY: hidden; BORDER-LEFT: 2px outset; BORDER-BOTTOM: 2px outset; POSITION: absolute; BACKGROUND-COLOR: buttonface
}</P><P></STYLE>
<SCRIPT language=JavaScript>
function Year_Month(){ 
var now = new Date(); 
var yy = now.getYear(); 
var mm = now.getMonth()+1; 
var cl = '<font color="#0000df">'; 
if (now.getDay() == 0) cl = '<font color="#c00000">'; 
if (now.getDay() == 6) cl = '<font color="#00c000">'; 
return(cl + yy + '年' + mm + '月</font>'); }
function Date_of_Today(){ 
var now = new Date(); 
var cl = '<font color="#ff0000">'; 
if (now.getDay() == 0) cl = '<font color="#c00000">'; 
if (now.getDay() == 6) cl = '<font color="#00c000">'; 
return(cl + now.getDate() + '</font>'); }
function Day_of_Today(){ 
var day = new Array(); 
day[0] = "星期日"; 
day[1] = "星期一"; 
day[2] = "星期二"; 
day[3] = "星期三"; 
day[4] = "星期四"; 
day[5] = "星期五"; 
day[6] = "星期六"; 
var now = new Date();
//作者:www.qpsh.com 网页特效
var cl = '<font color="#0000df">'; 
if (now.getDay() == 0) cl = '<font color="#c00000">'; 
if (now.getDay() == 6) cl = '<font color="#00c000">'; 
return(cl + day[now.getDay()] + '</font>'); }
function CurentTime(){ 
var now = new Date(); 
var hh = now.getHours(); 
var mm = now.getMinutes(); 
var ss = now.getTime() % 60000; 
ss = (ss - (ss % 1000)) / 1000; 
var clock = hh+':'; 
if (mm < 10) clock += '0'; 
clock += mm+':'; 
if (ss < 10) clock += '0'; 
clock += ss; 
return(clock); } 
function refreshCalendarClock(){ 
document.all.calendarClock1.innerHTML = Year_Month(); 
document.all.calendarClock2.innerHTML = Date_of_Today(); 
document.all.calendarClock3.innerHTML = Day_of_Today(); 
document.all.calendarClock4.innerHTML = CurentTime(); }
var webUrl = webUrl; 
document.write('<table border="0" cellpadding="0" cellspacing="0"><tr><td>'); 
document.write('<table id="CalendarClockFreeCode" border="0" cellpadding="0" cellspacing="0" width="60" height="70" ');
document.write('style="position:absolute;visibility:hidden" bgcolor="#eeeeee">');
document.write('<tr><td align="center"><font ');
document.write('style="cursor:hand;color:#ff0000;font-family:宋体;font-size:14pt;line-height:120%" ');
if (webUrl != 'netflower'){ 
document.write('</td></tr><tr><td align="center"><font ');
document.write('style="cursor:hand;color:#2000ff;font-family:宋体;font-size:9pt;line-height:110%" ');
} 
document.write('</td></tr></table>'); 
document.write('<table border="0" cellpadding="0" cellspacing="0" width="61" bgcolor="#C0C0C0" height="70">');
document.write('<tr><td valign="top" width="100%" height="100%">');
document.write('<table border="1" cellpadding="0" cellspacing="0" width="58" bgcolor="#FEFEEF" height="67">');
document.write('<tr><td align="center" width="100%" height="100%" >');
document.write('<font id="calendarClock1" style="font-family:宋体;font-size:7pt;line-height:120%"> </font><br>');
document.write('<font id="calendarClock2" style="color:#ff0000;font-family:Arial;font-size:14pt;line-height:120%"> </font><br>');
document.write('<font id="calendarClock3" style="font-family:宋体;font-size:9pt;line-height:120%"> </font><br>');
document.write('<font id="calendarClock4" style="color:#100080;font-family:宋体;font-size:8pt;line-height:120%"><b> </b></font>');
document.write('</td></tr></table>');
document.write('</td></tr></table>'); 
document.write('</td></tr></table>'); 
setInterval('refreshCalendarClock()',1000);
</SCRIPT></td>
						</tr>
						<tr>
							<td>
								<a href="servlet/RemoveSessionServlet">[ 退出 ]</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!-- header结束-->
		<!-- container 开始-->
		<table border="0" align="center" cellpadding="0" cellspacing="0"
			id="container">

			<tr>

				<td width="670" height="600" valign="top">
					<table width="90%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="curruser">
						<tr>
							<td width="24%">
								<img src="${sessionScope.user.u_pic}" width="120" height="120"
									class="userphoto" />
							</td>
							<td width="76%" valign="top">
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									id="curruserdetail">
									<tr>
										<td class="title">
											${sessionScope.user.u_nickname }
										</td>
									</tr>
									<tr>
										<td>
											<p>
												<a href="#">http://weibo.com/bielaipengwo<br /> </a>${sessionScope.city}
												<a href="#"> </a>
												<br />
												签名：${sessionScope.user.u_motto}
												<br />
												<a href="myface.jsp">修改头像</a> |
												<a href="userinfo.jsp">修改签名</a>
											</p>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="menu">
						<tr>
							<td width="33%" align="center">
								<table width="165" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td align="center" class="menu_btn">
											全部
										</td>
										<td align="center">
											关注
										</td>
										<td align="center">
											明星
										</td>
									</tr>
								</table>
							</td>
							<td width="18%" align="right">
								&nbsp;
							</td>
							<td width="49%" align="center">

								<form id="form1" name="form1" method="post" action="">
									<input name="textfield" type="text" class="input"
										id="textfield" />
									<input name="button" type="submit" class="btnsearch"
										id="button" value="搜索" />
								</form>
							</td>
						</tr>
					</table>
					<table id="weibo" width="90%" border="0" align="center"
						cellpadding="3" cellspacing="0">
						<%
							int intPageSize;//一页显示的记录数 
							int intRowCount;//记录总数 
							int intPageCount;//总页数 
							int intPage;//待显示页码 
							String strPage;
							int i;
							intPageSize = 6;//设置一页显示的记录数 
							strPage = request.getParameter("page");//取得待显示的页码 
							if (strPage == null) {
								//表明在QueryString中没有page这一个参数，此时显示第一页数据 
								intPage = 1;
							} else {
								//将字符串转换成整形  
								intPage = java.lang.Integer.parseInt(strPage);
								if (intPage < 1)
									intPage = 1;
							}
							User user123 = (User) session.getAttribute("user");
							int u_id123 = user123.getU_id();// 拿到u_id

							// out.print(u_id123+"--------------------------------");

							DBConn dbConn = new DBConn();
							String sql = "select * from collection where s_id=? order by s_date desc";

							ResultSet rs = dbConn.execQuery(sql, new Object[] { u_id123 });

							rs.last(); //光标指向查询结果集中的最后一条记录 
							intRowCount = rs.getRow();//获取 记录总数 
							intPageCount = (intRowCount + intPageSize - 1) / intPageSize;//计算总也数 
							if (intPage > intPageCount)
								intPage = intPageCount;//调整待显示页码 
							if (intPageCount > 0) {
								rs.absolute((intPage - 1) * intPageSize + 1);
								//将记录制针定位到待显示页的第一条记录上
								//显示数据
								i = 0;
								while (i < intPageSize && !rs.isAfterLast()) {
						%>


						<tr>
							<td width="88%" class="content">
								<%=rs.getString("s_content")%>
							</td>
						</tr>
						<tr>
							<td>
								<%
									if (rs.getString("s_image") != null) {
								%>
								<img src="<%=rs.getString("s_image")%>" width="120" height="120" />
							</td>
							<%
								}
							%>
						</tr>
						<tr>
							<td height="25">
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									id="weibo_status">
									<tr>
										<td>
											<%=rs.getString("s_date")%>
										</td>
										<td align="right">

											<a
												href="servlet/RemoveCollectionServlet?w_id=<%=rs.getString("s_id")%>&w_content=<%=rs.getString("id")%>">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>


						<%
							rs.next();
									i++;
								}
							}
						%>
					</table>

					<table align="center" id="page">
						<tr>
							<td align="right">
								第<%=intPage%>页 共<%=intPageCount%>页
								<%
								if (intPage > 1) {
							%>
								&nbsp; &nbsp;
								<a href="collection.jsp?page=<%=intPage - 1%>">上一页</a>
								<%
									}
									if (intPage < intPageCount) {
								%>
								<a href="collection.jsp?page=<%=intPage + 1%>">下一页</a>
							</td>
							<%
								}
								rs.close();
								dbConn.closeConn();
							%>


						</tr>
					</table>

					<!-- weibo 结束-->


					<%
						if (request.getParameter("msg") != null) {

							int res = Integer.parseInt(request.getParameter("msg"));
							if (res == 1) {

								out.print("<script>alert('收藏成功!'); </script>");
							} else {

								out.print("<script>alert('收藏失败!');</script>");

							}

						}
					%>


					<%
						if (request.getParameter("msg1") != null) {

							int res = Integer.parseInt(request.getParameter("msg1"));
							if (res == 1) {

								out.print("<script>alert('删除成功!'); </script>");
							} else {

								out.print("<script>alert('删除失败!');</script>");

							}

						}
					%>
					<!-- weibo 结束-->
				</td>
				<td width="280" align="center" valign="top" class="pageright">
					<!-- userinfo 开始-->


					<table align="center" id="userinfo">
						<tr>
							<td width="75%" align="left">
								<table width="80%" border="0" align="left" cellpadding="3"
									cellspacing="0">
									<tr>
										<td align="center" class="split2">
											<a
												href="servlet/AttentionSevlet?u_id=${sessionScope.user.u_id}">关注</a>
											<br>
											${sessionScope.ConcersNum}
										</td>
										<td align="center" class="split2">
											<a href="servlet/FansServlet?u_id=${sessionScope.user.u_id}">粉丝</a>
											<br>
											${sessionScope.FansNum}
										</td>
										<td align="center">
											<a
												href="servlet/MyBlogServlet?u_id=${sessionScope.user.u_id}">微博</a>
											<br>
											${sessionScope.WeiboNum}
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td class="split1">
								<a
									href="/MicroBlog/servlet/GetuserServlet?u_id=${sessionScope.user.u_id }">个人账户设置</a>
							</td>
						</tr>
					</table>


					<%
						List<User> looklist = (List<User>) request.getAttribute("looklist");
						pageContext.setAttribute("looklist", looklist);
					%>


					<table border="0" align="center" cellpadding="0" cellspacing="0" id="userlist">
           <tr>
            <td class="title" height="29">可能感兴趣的人</td>
          </tr>
          
      
          <%   
          int intPageSize1;//一页显示的记录数 
          int intRowCount1;//记录总数 
          int intPageCount1;//总页数 
          int intPage1;//待显示页码 
          String strPage1;
          int j;
          intPageSize1=6;//设置一页显示的记录数 
          strPage1=request.getParameter("page1");//取得待显示的页码 
          if(strPage1==null){
          //表明在QueryString中没有page这一个参数，此时显示第一页数据 
          intPage1=1;
          }else{
          //将字符串转换成整形  
          intPage1=java.lang.Integer.parseInt(strPage1);
          if(intPage1<1) intPage1=1;
          }

          DBConn dbConn1 = new DBConn();
          User	user12=(User)session.getAttribute("user");
			int u_id12 = user12.getU_id();// 拿到u_id
          
        // out.print(u_id12+"--------------------------------");
          String sql1="select * from user where u_id!=? and u_id not in (select g_id from relation where  r_id=?) order by rand()";
          ResultSet rs1 = dbConn1.execQuery(sql1, new Object[]{u_id12,u_id12});
          rs1.last();               //光标指向查询结果集中的最后一条记录 
          intRowCount1=rs1.getRow();//获取 记录总数 
          intPageCount1=10000000;//计算总也数 
          if(intPage1>intPageCount1)
            intPage1=intPageCount1;//调整待显示页码 
          if(intPageCount1>0){
            rs1.absolute((intPage1-1)*intPageSize1+1);
            //将记录制针定位到待显示页的第一条记录上
            //显示数据
            j=0;
          while(j<intPageSize1&&!rs1.isAfterLast()){%>
           <tr>
            
            <td colspan="2"> <form action="servlet/BefansServlet?g_id=<%=rs1.getString("u_id")%>&u_id=${sessionScope.user.u_id}" method="post"><table border="0" cellpadding="0" cellspacing="0" class="userdetail">
             <tr>
                <td width="26%"><a href="user.jsp"><img src="<%=rs1.getString("u_pic")%>" width="50" height="50" border="0" /></a></td>
                <td width="74%"><a href="user.jsp"><%=rs1.getString("u_nickname")%></a> <input name="" type="submit" class="btnguanzhu" id="" value="+关注" />
                <br />
              <%=rs1.getString("u_city")%><br />生日：<%=rs1.getString("u_date")%></td>
              </tr>
                
                      </table></form>
             </td>
          </tr>
         
          <% 
          rs1.next();
          j++;
          }
          }
          %>
          
     
       </table>
        <div>
         <%if(intPage1<intPageCount1){ %>
            <a href ="collection.jsp" class="huanyihuan " style="text-decoration: none"><font color="black"> 换一换</font></a>
             <%
          }
         rs1.close();
      
         dbConn1.closeConn();
          %>
            
        </div>
        <!-- userinfo 结束--></td>
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
		