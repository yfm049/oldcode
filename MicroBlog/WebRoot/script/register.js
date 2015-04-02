// JavaScript Document
function checkemail(){
	var email = document.getElementById("email");
	var emailmsg = document.getElementById("emailmsg");
	if(email.value.indexOf("@")==-1){
		emailmsg.innerHTML = "<img src='icon/err.png' align='absmiddle'> <font color='red'>请填写正确的邮箱地址</font>";
	}else{
		emailmsg.innerHTML = "<img src='icon/ok.png' align='absmiddle'> <font color='green'>邮箱地址正确</font>";
	}
}