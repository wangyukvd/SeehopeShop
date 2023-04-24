<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>砺锋商城——会员注册</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link href="css/css/home_header.css" rel="stylesheet" type="text/css">
<link href="css/css/home_login.css" rel="stylesheet" type="text/css">
<link href="css/css/font-awesome.min.css" rel="stylesheet">
<link href="css/css/dialog.css" rel="stylesheet" type="text/css">
<script src="js/jquery-1.12.4.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<!-- 表单校验插件 -->
<style>
.container {
	background-color: white;
}

.container-fluid {
	border-bottom: 2px solid red;
}

#myform .form-group {
	padding: 10px;
	margin-left: 200px;
}
</style>
<!-- 表单校验 -->
<style>
label.error {
	width: 200px;
	color: red;
	position: absolute;
	padding-left: 10px;
	right: -200px;
	top: 5px
}
</style>
<%--	做Register.jsp页面，主要做js代码，各种验证表单，素材老师提供。其中用户名要用ajax验证是否重复。参考代码：--%>
<script>
	function checkUserName(){
		$("#userNameMsg").html("");
		var username=$("#username").val();

		if (username == ""){
			$('#userNameMsg').html("用户名不能为空！");
			return false;
		}


		$.ajax({
			// async:false,
			url:"checkUsername",
			data :{
				"username":username
			},
			type:"POST",
			success:function (data){

				if(data == "true"){
					$("#userNameMsg").html("用户名已存在！")
					return false;
				}
				return true;
			}
		});
		return true;
	}

	/*验证密码*/
	function checkPwd(){
		$("#pwdMsg").html("");
		var pwd= $("#password").val();
		if(pwd == ""){
			$("#pwdMsg").html("密码不能为空！");
			return false;
		}
		var regex=/^[a-zA-Z0-9]{6,10}$/;
		if(regex.test(pwd)==false){
			$("#pwdMsg").html("密码为6-10位字母或数字！");
			return false;
		}
		return true;
	}

	/*验证确认密码*/
	function checkRepwd(){
		$("#repwdMsg").html("");
		var repwd = $("#confirmpwd").val();
		if(repwd == ""){
			$("#repwdMsg").html("确认密码不能为空");
			return false;
		}
		var pwd = $("#password").val();
		if(pwd!=repwd){
			$("#repwdMsg").html("确认密码不正确");
			return false;
		}
		return true;
	}

	//验证手机
	function checkTelNum(){
		$('#phoneMsg').html("");
		var telephone = $("#telephone").val();
		if(telephone == ""){
			$("#phoneMsg").html("电话号码不能为空！");
			return false;
		}
		var regex=/^1[34578]\d{9}$/;
		var flag=regex.test(telephone);
		if(flag==false){
			$("#phoneMsg").html("电话号码不正确");
		}
		return flag;

	}

	//向手机发送验证码
	function  checktelephone() {
		$("#msg").html("");//清空验证码的提示！

		if(checkTelNum()==false){
			return;
		}
		var telephone = $("#telephone").val();
		//alert(telephone);
		$.ajax({
			type:"post",
			url:"send",
			data:"telephone="+telephone,

			success : function (data) {
				if (data=='0'){
					$("#phoneMsg").html("短信已发送到你手机！<span>60</span>秒有效");
					$("#validPhone").attr("disabled","disabled");
					var time=60;
					var timer=setInterval(function (){
						$("#phoneMsg span").text(time--);
						if(time==0){
							clearInterval(timer);
							$("#validPhone").removeAttr("disabled");
							$("#phoneMsg").html("验证码超时！");
							//异步删除存储短信的Session
							$.ajax({
								url: "removeMsg",
								type:"get"
							});
						}
					},1000);
				}else {
					$("#phoneMsg").html("电话号码有误,发送失败！");
				}
				return true;
			}
		});
	}

	/* 检查验证码是否为空*/
	function checknumber(){
		$("#msg").text("");
		var num=$("#number").val();
		if(num==""){
			$("#msg").text("验证码不能为空！");
			return false;
		}
		return true;
	}


	$(document).ready(function (){  //一开就读完整个页面
		$("#username").keyup(checkUserName); //使用这个方法的过程
		$("#password").blur(checkPwd);//使用这个方法的过程
		$("#confirmpwd").blur(checkRepwd);//使用这个方法的过程
		$("#telephone").blur(checkTelNum);//使用这个方法的过程

		$("#myform").submit(function (e){
			//使用这个方法的过程的结果 是带括号
			if(checknumber()&checkUserName()&checkPwd()&checkRepwd()&checkTelNum()){
				return true;
			}

			return false;
		});

	});


</script>
</head>

<body>
	<div class="header-wrap">
		<header class="public-head-layout wrapper">
		<h1 class="site-logo">
			<a href="index.jsp"><img
				style="position: relative; top: -10px; width: 140px; height: 70px;"
				src="images/paimeng.jpg" class="pngFix"></a>
		</h1>
		<div class="nc-regist-now">
			<span class="avatar"><img src="images/66.gif"></span> <span>您好，欢迎来到砺锋商城<br>已注册的会员请<a
				title="" href="login.jsp" class="register">登录</a></span>
		</div>
		</header>
	</div>
	<!-- 注册 -->
	<div class="container" style="margin-top: 20px; height: 550px">
		<div class="row" style="padding-top: 10px; margin-right: 0px;">
			<div class="col-md-9" style="padding-left: 45px">
				<h3
					style="font-weight: bold; border-left: 3px solid red; padding: 0px 10px; display: inline-block">新用户注册</h3>
				<span style="color: #e60000">注册即送300元优惠券，首单成功后再返一张50优惠券 </span>
			</div>
		</div>
		<form id="myform" class="form-horizontal"
			action="register" method="post"
			style="margin-top: 15px;">
			<div class="form-group">
				<label for="username" class="col-sm-2 control-label">用户名:</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="username"
						name="username" placeholder="请输入用户名" value="${username }">
				</div>
				<span class="col-sm-3" id="userNameMsg" style="color: red;"></span>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-2 control-label">密码:</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" id="password"
						name="password" placeholder="请输入密码" value="${password }">
				</div>
				<span class="col-sm-3" id="pwdMsg" style="color: red;"></span>
			</div>
			<div class="form-group">
				<label for="confirmpwd" class="col-sm-2 control-label">确认密码:</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" id="confirmpwd"
						name="repassword" placeholder="请输入确认密码" value="${password }">
				</div>
				<span class="col-sm-3" id="repwdMsg" style="color: red;"></span>
			</div>

			<div class="form-group">
				<label for="telephone" class="col-sm-2 control-label">手机号码:</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="telephone"
						name="telephone" placeholder="请输入手机号码" value="${telephone }"/> <input type="button"
						onclick="checktelephone()" value="验证"
						style="color: white; background-color: red; font-weight: 200; height: 35px; width: 50px;; position: relative; left: 230px; top: -35px;" />
					
	
				</div>
				<span class="col-sm-3" id="phoneMsg" style="color: red;"></span>
			</div>

			<div class="form-group" style="position: relative; top: -35px;">
				<label for="checkCode" class="col-sm-2 control-label">验证码:</label>
				<div class="col-sm-4">
					<input type="text" id="number" class="form-control" name="number">
					
				</div>
				<p id="msg"  class="col-sm-3"
						style="color: red;">${msg}</p>
			</div>

			<div class="form-group" style="position: relative; top: -50px;">
				<!-- style="position: relative;top: -50px;" -->
				<div class="col-sm-offset-2 col-sm-10">
					<input id="btn" type="submit" width="100" value="立即注册"
						name="submit"
						style="padding: 5px; font-family: 'Microsoft YaHei UI'; border: 0; height: 35px; width: 290px; color: white; background-color: #e60000">
				</div>
			</div>
		</form>
	</div>

	<div class="footers"
		style="position: relative; top: 20px; left: 500px;">
		<p style="position: relative; left: 50px;">
			<a href="index.jsp">返回首页</a> <span>|</span> <a
				href="javascript:void(0)"> 特卖 </a> <span>|</span> <a
				href="javascript:void(0)"> 闪购 </a> <span>|</span> <a
				href="javascript:void(0)"> 关于我们 </a> <span>|</span> <a
				target="_blank" href="javascript:void(0)"> 店铺 </a> <span>|</span> <a
				href="javascript:void(0)">友情链接</a> <span>|</span> <a
				href="http://m.kuaidi100.com/" target="_blank">快递查询</a>
		</p>
		<p>
			<!-- Copyright -->
			Copyright © 2018 Beijin YouLeGou.,Ltd. allright reserved. 北京优乐购版权所有<br>
			<a href="javascript:void(0)" target="_blank" style="color: #666"></a>
		</p>
	</div>
</body>
</html>
