<%@ page language="java" import="java.util.*,com.microblog.po.*,com.microblog.dao.*" import= "java.sql.*" import= "com.microblog.dbutil.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" type="text/css" href="css/global.css" />
<link rel="stylesheet" type="text/css" href="css/home.css" />
<title>微博 - ${sessionScope.user.u_account}的主页</title>
<script type="text/javascript" src="script/home.js"></script>
</head>
<body>
 <!-- 记录被修改输的标识 -->
  
	
<!-- header开始-->
<table id="header" align="center" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="20%" align="center"><img src="images/logo.png" width="178" height="62" /></td>
    <td width="55%" align="right">
      <table border="0" align="right" cellpadding="0" cellspacing="0" id="daohang">
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
          <td width="30" height="30" rowspan="2" class="userface_bg"><img src="${sessionScope.user.u_pic}" border="0" width="44" height="44"/></td>
          <td>欢迎您，${sessionScope.user.u_account}！</td>
        </tr>
        <tr>
          <td><a href="servlet/RemoveSessionServlet">[ 退出 ]</a></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<!-- header结束-->
<!-- container 开始-->
<table border="0" align="center" cellpadding="0" cellspacing="0" id="container">
  <tr>
    <td width="670" height="600" valign="top"><form action="servlet/AddBlogServlet" method="get">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" id="input">
      <tr>
        <td width="160" height="48">&nbsp;</td>
        <td width="479">&nbsp;</td>
        <td width="31">&nbsp;</td>
      </tr>
     <tr>
        <td height="84">&nbsp;</td>
        <td><textarea id="inputbox" name="blogtext" cols="45" rows="5"></textarea>
        <!-- 记录被修改输的标识 -->
			<input type="hidden" id="" name="blogunick" value="${sessionScope.user.u_nickname}" />
			<input type="hidden" id="" name="w_id" value="${sessionScope.user.u_id}" />
			<input type="hidden" id="" name="w_upic" value="${sessionScope.user.u_pic}" /></td>
        
        <td>&nbsp;</td>
      </tr>
      <tr><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td></tr><tr>
        <td>&nbsp;</td>
        <td align="right" valign="top"><a href="#">
        <input name="dosubmit" type="submit" id="dosubmit" value="" style="background:url(images/btn_input.png); border-style:none; width:100px; height:26px; background-repeat:no-repeat;" />
       </a></td>
        <td>&nbsp;</td>
      </tr>
    </table></form>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="menu">
          <tr>
            <td width="33%" align="center"><table width="165" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                 <td align="center" ><a href="servlet/AllServlet?u_id=${sessionScope.user.u_id}">全部</a></td>
                <td align="center" class="menu_btn"><a href="servlet/AttentionSevlet?u_id=${sessionScope.user.u_id}">关注</a>
                <td align="center"><a href="servlet/FansServlet?u_id=${sessionScope.user.u_id}">粉丝</a>
              </tr>
            </table></td>
            <td width="18%" align="right">&nbsp;</td>
            <td width="49%" align="center">
								<form id="" name="" method="post" action="servlet/SearchServlet">
									搜索方式：<select name="searchtype">
										<option value="content">内容</option>
										<option value="user">用户</option>
									</select><input name="searchtxt" type="text" class="input"
										id="textfield" />
									<input name="button" type="submit" class="btnsearch"
										id="button" value="搜索" />
								</form></td>
          </tr>
      </table>
        <!-- weibo 开始-->
   <c:forEach items="${fanslist}" var="obj2" >  
    <form action="servlet/DelConcerServlet" method="post">
    <input type="hidden" name="u_id" value="${sessionScope.user.u_id}" />
  	<input type="hidden" name="g_id" value="${obj2.u_id}" />
          <table id="weibo" width="90%" border="0" align="center" cellpadding="3" cellspacing="0">
          <tr>
            <td colspan="2"><table border="0" cellpadding="0" cellspacing="0" class="userdetail">
            
              <tr>
              <!-- 记录被修改输的标识 -->
	            
                <td width="26%">
               <a href="servlet/ShowUserServlet?u_id=${obj2.u_id}"><img src="${obj2.u_pic}" width="50" height="50" border="0" /></a></td>
               <td width="74%"><a href="servlet/ShowUserServlet?u_id=${obj2.u_id}">${obj2.u_nickname}</a> 
               <input name="" type="submit" class="btnguanzhu" id="" value="取消关注" />
                <br />
               ${obj2.u_city}<br />生日：${obj2.u_date}</td>
              </tr>
            </table></td>
          </tr>
              </table>
      </form>
   </c:forEach>
    <!-- weibo 结束-->
    </td>
	    <td width="280" align="center" valign="top" class="pageright">
        <!-- userinfo 开始-->
        <table align="center" id="userinfo">
          <tr>
            <td width="25%" rowspan="2"><img src="${sessionScope.user.u_pic}"  width="50" height="50" /></td>
            <td width="75%"><a href="profile.jsp">${sessionScope.user.u_nickname}<img src="icon/v.gif" width="11" height="10" align="middle" /></a></td>
          </tr>
          <tr>
            <td valign="top">${sessionScope.user.u_city}</td>
          </tr>
         <tr>
            <td colspan="2" align="left"><table width="80%" border="0" align="left" cellpadding="3" cellspacing="0">
              <tr>
                <td align="center" class="split2"><a href="servlet/AttentionSevlet?u_id=${sessionScope.user.u_id}">关注<br/></a>
                <input type="hidden" name="u_id" value="${sessionScope.user.u_id}" />${sessionScope.ConcersNum}
                <td align="center" class="split2"><a href="servlet/FansServlet?u_id=${sessionScope.user.u_id}">粉丝<br/></a>
                 <input type="hidden" name="u_id" value="${sessionScope.user.u_id}" />${sessionScope.FansNum}<br>
               </td>
                <td align="center"><a href="servlet/MyBlogServlet?u_id=${sessionScope.user.u_id}">微博</a><br>
                ${sessionScope.WeiboNum}</td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td colspan="2" class="split1"><a href="/MicroBlog/servlet/GetuserServlet?u_id=${sessionScope.user.u_id }">个人账户设置</a></td>
          </tr>
        </table>
        
         <!-- weibo 开始-->
       <%	
	  List<User> looklist=(List<User>)request.getAttribute("looklist"); 
	  pageContext.setAttribute("looklist",looklist); 
	  
 
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
          String sql1="select * from user where u_id!=? and u_id not in (select g_id from relation where  r_id=?)order by rand()";
          ResultSet rs1 = dbConn1.execQuery(sql1, new Object[]{u_id12,u_id12});
          rs1.last();               //光标指向查询结果集中的最后一条记录 
          intRowCount1=rs1.getRow();//获取 记录总数 
          intPageCount1=14000;//计算总也数 
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
            <a href ="attention.jsp">[换一换]</a>
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
		