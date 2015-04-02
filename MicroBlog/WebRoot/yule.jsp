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
<link rel="stylesheet" type="text/css" href="css/yule.css" />
<title>微博 - ${sessionScope.user.u_account}的主页</title>
<script type="text/javascript" >
var no = 6; // snow number
var speed = 12; // smaller number moves the snow faster
var snowflake = "images/meiyuan.png";

var ns4up = (document.layers) ? 1 : 0;  // browser sniffer
var ie4up = (document.all) ? 1 : 0;
var dx, xp, yp;    // coordinate and position variables
var am, stx, sty;  // amplitude and step variables
var i, doc_width = 400, doc_height = 500;
if (ns4up) {
  doc_width = self.innerWidth;
  doc_height = self.innerHeight;
} else if (ie4up) {
  doc_width = 500;
  doc_height = 500;
}
dx = new Array();
xp = new Array();
yp = new Array();
am = new Array();
stx = new Array();
sty = new Array();
for (i = 0; i < no; ++ i) {  
  dx[i] = 0;                        // set coordinate variables
  xp[i] = Math.random()*(doc_width-50);  // set position variables
  yp[i] = Math.random()*doc_height;
  am[i] = Math.random()*20;         // set amplitude variables
  stx[i] = 0.02 + Math.random()/10; // set step variables
  sty[i] = 0.7 + Math.random();     // set step variables
  if (ns4up) {                      // set layers
    if (i == 0) {
      document.write("<layer name=\"dot"+ i +"\" left=\"15\" ");
      document.write("top=\"15\" visibility=\"show\"><img src=\"");
      document.write(snowflake + "\" border=\"0\"></layer>");
    } else {
      document.write("<layer name=\"dot"+ i +"\" left=\"15\" ");
      document.write("top=\"15\" visibility=\"show\"><img src=\"");
      document.write(snowflake + "\" border=\"0\"></layer>");
    }
  } else if (ie4up) {
    if (i == 0) {
      document.write("<div id=\"dot"+ i +"\" style=\"POSITION: ");
      document.write("absolute; Z-INDEX: "+ i +"; VISIBILITY: ");
      document.write("visible; TOP: 15px; LEFT: 15px;\"><img src=\"");
      document.write(snowflake + "\" border=\"0\"></div>");
    } else {
      document.write("<div id=\"dot"+ i +"\" style=\"POSITION: ");
      document.write("absolute; Z-INDEX: "+ i +"; VISIBILITY: ");
      document.write("visible; TOP: 15px; LEFT: 15px;\"><img src=\"");
      document.write(snowflake + "\" border=\"0\"></div>");
    }
  }
}
function snowNS() {  // Netscape main animation function
  for (i = 0; i < no; ++ i) {  // iterate for every dot
    yp[i] += sty[i];
    if (yp[i] > doc_height-50) {
      xp[i] = Math.random()*(doc_width-am[i]-30);
      yp[i] = 0;
      stx[i] = 0.02 + Math.random()/10;
      sty[i] = 0.7 + Math.random();
      doc_width = self.innerWidth;
      doc_height = self.innerHeight;
    }
    dx[i] += stx[i];
    document.layers["dot"+i].top = yp[i];
    document.layers["dot"+i].left = xp[i] + am[i]*Math.sin(dx[i]);
  }
  setTimeout("snowNS()", speed);
}

function snowIE() {  // IE main animation function
  for (i = 0; i < no; ++ i) {  // iterate for every dot
    yp[i] += sty[i];
    if (yp[i] > doc_height-50) {
      xp[i] = Math.random()*(doc_width-am[i]-30);
      yp[i] = 0;
      stx[i] = 0.02 + Math.random()/10;
      sty[i] = 0.7 + Math.random();
      doc_width = document.body.clientWidth;
      doc_height = document.body.clientHeight;
    }
    dx[i] += stx[i];
    document.all["dot"+i].style.pixelTop = yp[i];
    document.all["dot"+i].style.pixelLeft = xp[i] + am[i]*Math.sin(dx[i]);
  }
  setTimeout("snowIE()", speed);
}

if (ns4up) {
  snowNS();
} else if (ie4up) {
  snowIE();
}



</script>
</head>
<body><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
<font size="100">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="player/player.jsp">影音娱乐</a></font><br/><br/>
<font size="100">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="gamelist.jsp">经典游戏</a></font>
</body>
</html>