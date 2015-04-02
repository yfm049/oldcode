<%@ page language="java" import="java.util.*,com.microblog.po.*,com.microblog.dao.*" import= "java.sql.*" import= "com.microblog.dbutil.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<STYLE>
A.menuitem {
COLOR: menutext; TEXT-DECORATION: none
}
A.menuitem:hover {
COLOR: highlighttext; BACKGROUND-COLOR: highlight
}
DIV.contextmenu {
BORDER-RIGHT: 2px outset; BORDER-TOP: 2px outset; Z-INDEX: 999; VISIBILITY: hidden; BORDER-LEFT: 2px outset; BORDER-BOTTOM: 2px outset; POSITION: absolute; BACKGROUND-COLOR: buttonface
}
</STYLE> 
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" type="text/css" href="css/global.css" />
<link rel="stylesheet" type="text/css" href="css/home.css" />
<title>微博 - ${sessionScope.user.u_account}的主页</title>
<script type="text/javascript" src="script/home.js"></script>

<script language="javascript"> 
 
function countChar(textareaName,spanName)
{  
 
document.getElementById(spanName).innerHTML = 140 - document.getElementById(textareaName).value.length;
}  

function show(id){

	var obj=document.getElementById(id);
	obj.style.display="block";
}
function hide(id){
	var obj=document.getElementById(id);
	obj.style.display="none";
}
</script>  


</head>
<body>
<div id="staResult" style="display:none;width:100%;height:1000px;position:absolute;top:0;left:0;background-image:url(images/white_trans.png); ">
<iframe src="servlet/ShowBallotServlet" scrolling="no" frameborder="0" style=" width:600px;height:400px;position:absolute;left:200px;top:100px;" name="showResult"></iframe>
<button onclick="hide('staResult')" style="position:absolute;left:710px;top:110px;">关闭</button>
</div>
<div id="staResult2" style="display:none;width:100%;height:1000px;position:absolute;top:0;left:0;background-image:url(images/white_trans.png); ">
<iframe src="servlet/ShowBallot2Servlet" scrolling="no" frameborder="0" style=" width:600px;height:400px;position:absolute;left:200px;top:100px;" name="showResult"></iframe>
<button onclick="hide('staResult2')" style="position:absolute;left:710px;top:110px;">关闭</button>
</div>
<layer NAME="a0" LEFT=10 TOP=10 VISIBILITY=SHOW BGCOLOR="#ffff00" CLIP="0,0,3,3"></layer> <layer NAME="a1" LEFT=10 TOP=10 VISIBILITY=SHOW BGCOLOR="#ffff00" CLIP="0,0,3,3"></layer > <layer NAME="a2" LEFT=10 TOP=10 VISIBILITY=SHOW BGCOLOR="#ffff00" CLIP="0,0,3,3"></layer> <layer NAME="a3" LEFT=10 TOP=10 VISIBILITY=SHOW BGCOLOR="#ffff00" CLIP="0,0,3,3"></layer> <layer NAME="a4" LEFT=10 TOP=10 VISIBILITY=SHOW BGCOLOR="#ffff00" CLIP="0,0,3,3"></layer> <laye rNAME="a5" LEFT=10 TOP=10 VISIBILITY=SHOW BGCOLOR="#ffff00" CLIP="0,0,3,3"></layer> <layer NAME="a6" LEFT=10 TOP=10 VISIBILITY=SHOW BGCOLOR="#ffff00" CLIP="0,0,3,3"></layer>
<script language="JavaScript" >
<!--

/*
by www.qpsh.com
*/

if (document.all){
document.write('<div id="starsDiv" style="position:absolute;top:0px;left:0px">')
for (xy=0;xy<7;xy++)
document.write('<div style="position:relative;width:3px;height:3px;background:#FFFF00;font-size:2px;visibility:visible"></div>')
document.write('</div>')
}

if (document.layers)
{window.captureEvents(Event.MOUSEMOVE);}
var yBase = 200;
var xBase = 200;
var yAmpl = 10;
var yMax = 40;
var step = .2;
var ystep = .5;
var currStep = 0;
var tAmpl=1;
var Xpos = 1;
var Ypos = 1;
var i = 0;
var j = 0;

if (document.all)
{
  function MoveHandler(){
  Xpos = document.body.scrollLeft+event.x;
  Ypos = document.body.scrollTop+event.y;
  }
  document.onmousemove = MoveHandler; 
}

else if (document.layers)
{
  function xMoveHandler(evnt){
  Xpos = evnt.pageX;
  Ypos = evnt.pageY;
  }
  window.onMouseMove = xMoveHandler;
}



function animateLogo() {
if (document.all)
{
 yBase = window.document.body.offsetHeight/4;
 xBase = window.document.body.offsetWidth/4;
}
else if (document.layers)
{
 yBase = window.innerHeight/4 ;
 xBase = window.innerWidth/4;
}

if (document.all)
{
var totaldivs=document.all.starsDiv.all.length
 for ( i = 0 ; i < totaldivs ; i++ )
 {
var tempdiv=document.all.starsDiv.all[i].style
  tempdiv.top = Ypos + Math.cos((20*Math.sin(currStep/20))+i*70)*yBase*(Math.sin(10+currStep/10)+0.2)*Math.cos((currStep + i*25)/10);
  tempdiv.left = Xpos + Math.sin((20*Math.sin(currStep/20))+i*70)*xBase*(Math.sin(10+currStep/10)+0.2)*Math.cos((currStep + i*25)/10);
 }
}

else if (document.layers)
{
 for ( j = 0 ; j < 7 ; j++ ) 
 {
var templayer="a"+j
  document.layers[templayer].top = Ypos + Math.cos((20*Math.sin(currStep/20))+j*70)*yBase*(Math.sin(10+currStep/10)+0.2)*Math.cos((currStep + j*25)/10);
  document.layers[templayer].left =Xpos + Math.sin((20*Math.sin(currStep/20))+j*70)*xBase*(Math.sin(10+currStep/10)+0.2)*Math.cos((currStep + j*25)/10);
 }
}
currStep += step;
setTimeout("animateLogo()", 15);
}
animateLogo();
// -->
</script>

	
<!-- header开始-->
<table id="header" align="center" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="20%" align="center"><img src="images/logo2.png" width="178" height="62" /></td>
    <td width="55%" align="right">
      <table border="0" align="right" cellpadding="0" cellspacing="0" id="daohang">
        <tr>
          <td width="20%"><a href="servlet/AllServlet?u_id=${sessionScope.user.u_id}" >我的首页</a></td>
          <td width="20%"><a href="servlet/MyBlogServlet?u_id=${sessionScope.user.u_id}" >我的博客</a>
           <input type="hidden" name="u_id" value="${sessionScope.user.u_id}" /></td>
          <td width="20%"><a href="servlet/FriendSevlet?u_id=${sessionScope.user.u_id}">我的好友</a>
                <input type="hidden" name="u_id" value="${sessionScope.user.u_id}" /></td>
          <td width="20%"><a href="yule.jsp">请休息吧</a></td>
          <td width="20%"><a href="ballot.jsp" >微博热议</a></td>
        </tr>
      </table>
    </td>
    <td width="25%" align="right">
      <table id="welcome" border="0" cellspacing="0" cellpadding="0">
        <tr>          
          <td width="30" height="30" rowspan="2" class="userface_bg"><img src="${sessionScope.user.u_pic}" border="0" width="44" height="44"/></td>
          <td ><font size="3px">欢迎您，${sessionScope.user.u_nickname}！</font></td>
          <td><br><STYLE>A.menuitem {
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
          <td><a href="servlet/RemoveSessionServlet"  class="huanyihuan " ><font size="3px">退出</font> </a></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<!-- header结束-->
<!-- container 开始-->
<table border="0" align="center" cellpadding="0" cellspacing="0" id="container">
  <tr>
    <td width="670" height="600" valign="top">
    <form action="servlet/AddBlogServlet" enctype="multipart/form-data" method="post">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" id="input">
      <tr>
        <td width="165" height="32">&nbsp;</td>
        <td width="479">&nbsp;</td>
        <td width="31">&nbsp;</td>
      </tr>
     <tr>
        <td height="84">&nbsp;</td>
        <td align="right"><font size="4px" color="#ff2703" bold></>可以输入<span id="counter">140</span>字 </font><br/>

<textarea id="status"  name="blogtext" rows="10" cols="40" onkeydown='countChar("status","counter");' onkeyup='countChar("status","counter");'>
</textarea>s
         <!-- 记录被修改输的标识 -->
			
			<input type="hidden" id="" name="w_id" value="${sessionScope.user.u_id}" />
			<input type="hidden" id="" name="w_unick" value="${sessionScope.user.u_nickname}" />
			<input type="hidden" id="" name="w_upic" value="${sessionScope.user.u_pic}" /></td>
        
        <td>&nbsp;</td>
      </tr>
      <tr><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td></tr><tr><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td></tr><tr>
        <td>&nbsp;</td>
        <td align="right" valign="top"><input type="file" name ="upfile"/>
        <input name="dosubmit" type="submit" id="dosubmit" value="" style="background:url(images/btn_input.png); border-style:none; width:100px; height:26px; background-repeat:no-repeat;" />
       </a></td>
        <td>&nbsp;</td>
      </tr>
    </table></form>
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="menu">
          <tr>
            <td width="33%" align="center"><table width="165" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td align="center" class="menu_btn"><a href="servlet/AllServlet?u_id=${sessionScope.user.u_id}">全部</a></td>
                <td align="center"><a href="servlet/AttentionSevlet?u_id=${sessionScope.user.u_id}">关注</a>
                <td align="left"><a href="servlet/FansServlet?u_id=${sessionScope.user.u_id}">粉丝</a>
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
       <table width="709" border="0" cellpadding="0" >
  <tr>
    <td width="705" height="46"><span class="STYLE6"><h1>微博热议：</h1></span></td>
  </tr>
  <tr>
    <td height="166">
    <form id="form1" name="form1" method="post" action="servlet/BallotServlet">
    <table width="707" border="1" cellpadding="0">
      <tr>
        <td height="35" colspan="2">1、谁最有可能成为美网大满贯得主：</td>
        </tr>
      <tr>
        <td height="84" colspan="2" align="left"><label>
				<input type="radio" name="tennis" value="费德勒" />
				费德勒
				</label>
				<label>
					<input type="radio" name="tennis" value="纳达尔" />
					纳达尔
				</label>
				<label>
					<input type="radio" name="tennis" value="德约科维奇" />
					德约科维奇
				</label>
				<label>
					<input type="radio" name="tennis" value="罗迪克" />
					罗迪克
				</label>
				<label>
					<input type="radio" name="tennis" value="穆雷" />
					穆雷
		  </label></td>
        </tr>
    
      <tr>
        <td width="462" height="37" align="right"><input type="submit" value="投票" ></td>
        <td width="239" align="right"><input type="button" onclick="show('staResult')" value="查看投票结果" /></td>
      </tr>
      
    </table></form></td>
  </tr>
  <tr>
    <td height="114">
    <form id="form2" name="form2" method="post" action="servlet/Ballot2Servlet">
    <table width="706" border="1" cellpadding="0">
      <tr>
        <td height="42" colspan="2">2、您最喜爱的暑期档影视作品：</td>
        </tr>
      <tr>
        <td height="91" colspan="2"><label>
          <input type="radio" name="movie" value="加勒比海盗" />
          加勒比海盗</label>
          <label>
          <input type="radio" name="movie" value="功夫熊猫2" />
          功夫熊猫2</label>
          <label>
          <input type="radio" name="movie" value="哈利波特7" />
          哈利波特7
          <input type="radio" name="movie" value="变形金刚3" />
          变形金刚3
          <input type="radio" name="movie" value="不爱看电影……爱看裸婚时代" />
          不爱看电影……爱看裸婚时代</label></td>
        </tr>
      <tr>
       <td width="462" height="37" align="right"><input type="submit" value="投票" ></td>
         <td width="238" align="right"><input type="button" onclick="show('staResult2')" value="查看投票结果" /></td>
      </tr>
    </table>
    </form></td>
  </tr>
</table>
       
       <!-- weibo 开始-->
       
				

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
                <input type="hidden" name="u_id" value="${sessionScope.user.u_id}" />${ConcersNum} 
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
            <a href ="ballot.jsp" class="huanyihuan " style="text-decoration: none"><font color="black"> 换一换</font></a>
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
		