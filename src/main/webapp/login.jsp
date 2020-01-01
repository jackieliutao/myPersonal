<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="loginHtml">
<head>
	<meta charset="utf-8">
	<title>登录--后台管理</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="icon" href="${pageContext.request.contextPath}/static/favicon.ico">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/public.css" media="all" />
	<style>

		.loginBody .logTip{
			color: #fff9ec;
			position: absolute;
			left: 50%;
			transform: translateX(-50%);
			top: 12%;
		}
		.loginBody #loginForm{
			position: absolute;
			left: 62%;
			transform: translateX(-50%);
			padding-top: 20px;
			width: 400px;
			height: 300px;
		}
	</style>
</head>
<body class="loginBody">
	<h1 class="logTip">人事管理后台登录</h1>
	<form class="layui-form" id="loginForm" method="post" action="/login" lay-filter="loginForm">
		<div class="layui-form-item input-item">
			<label for="userName">用户名</label>
			<input type="text" placeholder="请输入用户名" name="username" autocomplete="off" id="userName" class="layui-input" lay-verify="required">
		</div>
		<div class="layui-form-item input-item">
			<label for="password">密码</label>
			<input type="password" placeholder="请输入密码" name="password" autocomplete="new-password" id="password" class="layui-input" lay-verify="required">
		</div>
		<div class="layui-form-item input-item" id="imgCode">
			<label for="code">验证码</label>
			<input type="text" style="width: 250px;height: 35px;margin:0 0 15px 0;display: inline-block" placeholder="请输入验证码" autocomplete="off" id="code" class="layui-input">
			<canvas id="canvas" width="100" style="display: inline-block;vertical-align: top" height="43"></canvas>
		</div>
		<div class="layui-form-item">
			<button class="layui-btn layui-block" id="login"  lay-filter="login" >登录</button>
		</div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/page/login/login.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/cache.js"></script>
	<script>
		layui.use(['form','layer'],function () {
			var $ = layui.jquery;
			var form = layui.form,
					layer = parent.layer === undefined ? layui.layer : top.layer;
			var show_num = [];
			draw(show_num);

			$("#canvas").on('click',function(){
				draw(show_num);
			});
			//登录按钮
			/*form.on("submit(login)",function(data){
				var val = $("#code").val().toLowerCase();
				var num = show_num.join("");
				if(val==''){
					layer.msg("请输入验证码");
				}else if(val == num){
					$.post("/login",data.field,function (res) {
						if(res.success){
							window.location.href='index.jsp';
						}else{
							form.val('loginForm',{
								username:'',
								password:'',
							});
							$("#code").val('');
							draw(show_num);
							layer.msg(res.msg);
						}
					});
				}else{
					layer.msg("验证码输入错误");
					$(".input-val").val('');
					draw(show_num);
				}
				return false;
			});*/
			$('#login').click(function () {
				var val = $("#code").val().toLowerCase();
				var num = show_num.join("");
				var name = $('#userName').val();
				var p1 = $('#password').val();
				if(name == '' || name== null){
					$('#userName').focus();
					layer.msg("请输入用户名");
					return false;
				}
				if(p1 == '' || p1== null){
					$('#password').focus();
					layer.msg("请输入密码");
					return false;
				}
				if(val == ''){
					layer.msg("请输入验证码");
					return false;
				}
				if(val == num){
					var data = form.val('loginForm');
					$.post("/login",data,function (res) {
						if(res.success){
							window.location.href = "index.jsp";
						}else{
							layer.msg(res.msg);
							$('#password').val('');
							$("#code").val('');
							draw(show_num);
						}
					});
				}else{
					layer.msg("验证码输入错误");
					$("#code").val('');
					draw(show_num);
				}
				return false;
			});
			//表单输入效果
			$(".loginBody .input-item").click(function(e){
				e.stopPropagation();
				$(this).addClass("layui-input-focus").find(".layui-input").focus();
			})
			$(".loginBody .layui-form-item .layui-input").focus(function(){
				$(this).parent().addClass("layui-input-focus");
			})
			$(".loginBody .layui-form-item .layui-input").blur(function(){
				$(this).parent().removeClass("layui-input-focus");
				if($(this).val() != ''){
					$(this).parent().addClass("layui-input-active");
				}else{
					$(this).parent().removeClass("layui-input-active");
				}
			})
			//验证码
			function draw(show_num) {
				var canvas_width=$('#canvas').width();
				var canvas_height=$('#canvas').height();
				var canvas = document.getElementById("canvas");//获取到canvas的对象，演员
				var context = canvas.getContext("2d");//获取到canvas画图的环境，演员表演的舞台
				canvas.width = canvas_width;
				canvas.height = canvas_height;
				var sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
				var aCode = sCode.split(",");
				var aLength = aCode.length;//获取到数组的长度

				for (var i = 0; i <= 3; i++) {
					var j = Math.floor(Math.random() * aLength);//获取到随机的索引值
					var deg = Math.random() * 30 * Math.PI / 180;//产生0~30之间的随机弧度
					var txt = aCode[j];//得到随机的一个内容
					show_num[i] = txt.toLowerCase();
					var x = 10 + i * 20;//文字在canvas上的x坐标
					var y = 20 + Math.random() * 8;//文字在canvas上的y坐标
					context.font = "bold 23px 微软雅黑";

					context.translate(x, y);
					context.rotate(deg);

					context.fillStyle = randomColor();
					context.fillText(txt, 0, 0);

					context.rotate(-deg);
					context.translate(-x, -y);
				}
				for (var i = 0; i <= 5; i++) { //验证码上显示线条
					context.strokeStyle = randomColor();
					context.beginPath();
					context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
					context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
					context.stroke();
				}
				for (var i = 0; i <= 30; i++) { //验证码上显示小点
					context.strokeStyle = randomColor();
					context.beginPath();
					var x = Math.random() * canvas_width;
					var y = Math.random() * canvas_height;
					context.moveTo(x, y);
					context.lineTo(x + 1, y + 1);
					context.stroke();
				}
			}

			function randomColor() {//得到随机的颜色值
				var r = Math.floor(Math.random() * 256);
				var g = Math.floor(Math.random() * 256);
				var b = Math.floor(Math.random() * 256);
				return "rgb(" + r + "," + g + "," + b + ")";
			}
		})
	</script>
</body>
</html>