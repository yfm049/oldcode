
function DX(n) { //金额大写转换函数
	if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(n)) {
		return "\u6570\u636e\u975e\u6cd5";
	}
	var unit = "\u5343\u767e\u62fe\u4ebf\u5343\u767e\u62fe\u4e07\u5343\u767e\u62fe\u5143\u89d2\u5206", str = "";
	n += "00";
	var p = n.indexOf(".");
	if (p >= 0) {
		n = n.substring(0, p) + n.substr(p + 1, 2);
	}
	unit = unit.substr(unit.length - n.length);
	for (var i = 0; i < n.length; i++) {
		str += "\u96f6\u58f9\u8d30\u53c1\u8086\u4f0d\u9646\u67d2\u634c\u7396".charAt(n.charAt(i)) + unit.charAt(i);
	}
	return str.replace(/零(千|百|拾|角)/g, "\u96f6").replace(/(零)+/g, "\u96f6").replace(/零(万|亿|元)/g, "$1").replace(/(亿)万|壹(拾)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g, "\u5143\u6574");
}
function shijian() {
	$(".username").autocomplete({source:function (request, response) {
		$.getJSON("manager/auto!name.action", request, function (msg) {
			response(msg);
		});
	}});
	$(".pinpai").autocomplete({source:function (request, response) {
		$.getJSON("manager/auto!pinpai.action", request, function (msg) {
			response(msg);
		});
	}});
	$(".guige").autocomplete({source:function (request, response) {
		$.getJSON("manager/auto!guige.action", request, function (msg) {
			response(msg);
		});
	}}).keyup(function () {
		var sl = $(this).val();
		var re = /^[a-zA-Z]{1}\d{0,5}$/;
		if (!re.test(sl)) {
			alert("\u89c4\u683c\u683c\u5f0f\u4e0d\u6b63\u786e");
			$(this).val("");
		} else {
			jisuan(this);
		}
	});
	$(".shuliang").keyup(function () {
		var sl = $(this).val();
		var re = /^[0-9]*[1-9][0-9]*$/;
		if (!re.test(sl)) {
			alert("\u6570\u91cf\u5fc5\u987b\u4e3a\u6b63\u6574\u6570");
			$(this).val("");
		}
		jisuan(this);
	});
	$(".danjia").keyup(function () {
		var sl = $(this).val();
		var re = /\d+.?\d*/;
		if (!re.test(sl)) {
			alert("\u6570\u636e\u5fc5\u987b\u662f\u5c0f\u6570");
			$(this).val("");
		}
		jisuan(this);
	});
}
function jisuan(t){
				var p=$(t).parents("tr");
				var dj=$(".danjia",$(p)).val();
				var gg=$( ".guige",$(p)).val();
				var sl=$(".shuliang",$(p)).val();
				
				var mishu;
				if(gg){
					gg=gg.substr(1,gg.length)/1000;
				}else{
					return;
				}
				if(sl){
					$(".mishu",$(p)).html(Math.floor(gg*sl * 1000)/1000);
				}else{
					$(".mishu",$(p)).html("");
					return;
				}
				if(dj){
					var mishu=$(".mishu",$(p)).html();
					$(".price",$(p)).html(Math.floor(mishu*dj*100)/100);
				}else{
					$(".price",$(p)).html("");
					return;
				}
				total();
			}

