<%@ page language="java" import="java.util.*,com.microblog.dao.*,com.microblog.po.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 自动登录  -->
<%  
       
            Cookie[]   cookie   =   request.getCookies(); 
              String userid = null;   
              String password = null;   
            if(cookie!=null){  
            for(int   i   =   0;   i   <   cookie.length;   i++){  
             Cookie   myCookie   =   cookie[i];  
            
             if(myCookie.getName().equals( "autologinname")){  
                userid = myCookie.getValue();
                        }
             if(myCookie.getName().equals( "autologinpwd")){
               	password =  myCookie.getValue();       	
                 }
                  AdminDao adminDao = new AdminDao();
		        User user = adminDao.check(userid, password);
		    try{    
              if (userid != null && password!= null) { // 如果存在   
		        HttpSession session2 = request.getSession();
				session2.setAttribute("user", user);
				int u_id = user.getU_id();// 通过u_account拿到u_id
				
				WeiboDao weiboDao = new WeiboDao();
				List<Weibo> lstPro = new ArrayList<Weibo>();
				lstPro = weiboDao.getAllWeibobyU_id(u_id, u_id,1);
				session.setAttribute("lstPro", lstPro);
		
				//获取好友信息
				RelaDao relaDao = new RelaDao();
				List<User> looklist = relaDao.getLook(u_id);				
				
				HttpSession session1 = request.getSession();
				session1.setAttribute("looklist", looklist);	
				
 
				session.setAttribute("ConcersNum", relaDao.showConcersNum(u_id));
				session.setAttribute("FansNum", relaDao.showFansNum(u_id));
				session.setAttribute("WeiboNum",weiboDao.showWeiboNum(u_id));
		      request.getRequestDispatcher("/home.jsp").forward(request,
						response);
			
			}
		      } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		      
             }   	
            }%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" type="text/css" href="css/global.css" />
<link rel="stylesheet" type="text/css" href="css/index.css" />
<title>微博系统</title>    
<script type="text/javascript" src="script/global.js"></script>
<script type="text/javascript" src="script/index.js"></script>
    <script type="text/javascript">
   
	function init() {
	   //LoginServlet
	   var s=document.forms['first'];  		  
	   s.submit();	 
	   
	   //alert(s.id);
	}
	</script>
</head>

<body>
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

<!--header 开始-->
<table id="header" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="right"><table border="0" align="right" cellpadding="0" cellspacing="0" id="daohang">
      <tr>
        <td align="center"><a href="into.jsp">企业微博</a></td>
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
         <form action="/MicroBlog/servlet/LoginImgServlet" name="first" method="post" id="fir">
        
        <table id="photolist" border="0" cellspacing="0" cellpadding="0">
         <tr>             
           <c:forEach var="stu" varStatus="su" items="${applicationScope.students}">
           <td><MARQUEE scrollAmount=2 direction=right width="80"><a href="register.jsp" target="_blank"><img src="${stu.u_pic}" id="${stu.u_id}" alt="这里是描述" title="这里是描述" onmouseover="xianshi(this)" onmouseout="huifu(this)" /></a></MARQUEE> </td>          
           <c:if test="${su.count%8 == 0 }">
      </tr><tr>
			</c:if>
			
           </c:forEach>
        
           </tr>
           
         </table>
         </form>
         <!-- photolist结束 -->
       </td>
     </tr>
     <tr>
       <td>
          <!--counter开始-->
          <table id="counter" border="0" cellpadding="0" cellspacing="0" align="center">
             <tr>
               <td valign="middle">
                 <table width="330" border="0" align="center" cellpadding="0" cellspacing="0" height="88">
                   <tr><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td></tr><tr><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td></tr><tr>
                     <td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td class="counternum" align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${applicationScope.info}</td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td>
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
        <form id="form1" name="form1" method="post" action="servlet/LoginServlet" onsubmit="return checkForm()">
        <table id="login" width="220" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td colspan="2"><a href="register.jsp"><img src="images/regbtn.png" width="200" height="37" border="0" /></a></td>
          </tr>
          <tr>
            <td colspan="2" align="left">邮箱/会员账号/手机号：<br />
              <input name="userid" type="text" class="logininput" id="userid" onmouseover="this.focus()" />
            </td>
          </tr>
          <tr>
            <td colspan="2" align="left">输入你的登录密码：<br />
              <input name="password" type="password" class="logininput" id="password" onmouseover="this.focus()"/>
            </td>
          </tr>
          <tr>
            <td width="120"><input name="autologin" type="checkbox" value="on" />下次自动登录</td>
            <td width="100"><a href="backup.jsp" style="text-decoration:none"><font color="#0066CC">找回登录密码</font></a></td>
          </tr>
          <tr>
            <td colspan="2" align="center"><input name="Submit" type="submit" class="loginbtn" id="Submit" value="  登录微博  " /></td>
          </tr>
        </table>
        </form>
        <!--login结束-->
        
         <% 
  if (request.getParameter("msg")!=null){
    int res=Integer.parseInt(request.getParameter("msg"));
    if(res==1){
    out.print("<script>alert('修改成功');</script>");
    
    }else{out.print("<script>alert('登陆失败，请重新登陆');</script>");
    }
  } 
  
  %>
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
