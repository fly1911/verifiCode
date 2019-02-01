<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>code</title>
<script type="text/javascript" src="jquery-1.7.2.js"></script>	
<style>
	.circle {
		background-color: white;
		position: absolute;
		font-size: 5px;
	}
</style>
</head>
<body onload="showWords()" style="margin:0;padding:0">
	<div id="imgDiv">
		<img id="codeImg" src="/verifiCodeDemo/verifiCodeServlet"/>
		<a href="#" onclick="switchVerifiCode()">换一张</a><br/>
	</div>
	<span id="words"></span><br/>
	<span id="checkVerifiCodeRs"></span>
	
</body>
<script>
	function switchVerifiCode() {
		document.getElementById("codeImg").src = "/verifiCodeDemo/verifiCodeServlet?t="+new Date().getTime();
		setTimeout(function() {
			showWords();
		}, 100);
		removeLittleCircle();
		$("#checkVerifiCodeRs").text("");
	}
	
	function showWords() {
		$.ajax({
			url:"/verifiCodeDemo/getVerifiWordsServlet",
			success:function (data) {
				if (data != null && data != "") {
					$("#words").text("请依次输入以下字符：" + data);
				}
			}
		});
	}
	
	function onMD(e) {
		var xz = e.clientX;
		var yz = e.clientY;
		return {
			x:xz,
			y:yz
		}
	}
	
	var g = null;
	var clickNum = 0;
	$(function() {
		var points = "";
		$("#codeImg").on("click", function(e) {
			clickNum++;
			var zb = onMD(e);
			//drawCircle
			drawCircle(e);
			
			var point = zb.x + "," + zb.y;
			console.log("point:" + point);
			points += (point + ";");
			if (8 == clickNum) {
				$.ajax({
					url:"/verifiCodeDemo/checkVerifiCodeServlet",
					data:"points=" + points,
					method:"POST",
					success:function(data) {
						if ("1" == data) {
							$("#checkVerifiCodeRs").css("color", "green");
							$("#checkVerifiCodeRs").text("校验通过！");
							/* removeLittleCircle(); */
						} else {
							$("#checkVerifiCodeRs").css("color", "red");
							$("#checkVerifiCodeRs").text("校验失败！");
						}
						clickNum = 0;
						points = "";
					}
				});	
			}
		});
		
		/* var container = $("#codeImg");
		g = new jmGraph(container, {
	        width: 300,
	        height: 200,
	        //样式，规则请参照样式说明
	        style: {
	            fill: '#F4A460' //指定背景色
	        }
	    }); */
	});
	
	function drawCircle(event) {
		var $circle = $('<div class="circle">'+clickNum+'</div>');
		var $img = $("#imgDiv");
		var centerX = event.pageX - $img.offset().left;
		var centerY = event.pageY - $img.offset().top;
		var radius = 5;
		
		// 设置圆的大小和位置
		$circle.css("left", centerX - radius + "px");
		$circle.css("top", centerY - radius + "px");
		$circle.css("width", 2 * radius + "px");
		$circle.css("height", 2 * radius + "px");
		$circle.css("border-radius", radius + "px");
		$img.append($circle);
 		event.preventDefault();
	}
	
	function removeLittleCircle(){
		$(".circle").each(function() {
			$(this).remove();
		});
	}
</script>

</html>