<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache,must-revalidate">
		<title>注册-个人用户</title>
		<link type="text/css" rel="stylesheet" href="/css/regist.personal.css" />
		<link type="text/css" rel="stylesheet" href="/css/passport.base.css" />
		<link type="text/css" rel="stylesheet" href="/libs/particles/css/style.css">
		<link type="text/css" rel="stylesheet" href="/libs/sweetalert2/sweetalert2.min.css">
		<link type="text/css" rel="stylesheet" href="/css/base.css">
		<link type="text/css" rel="stylesheet" href="/css/login.css">
		<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
</head>

<body>
	<jsp:include page="author.jsp" />
	<div id="particles-js"></div>
	<div id="wrapper">
		<div>
			<img src="/images/login-logo.png" />
			<h2>与世界分享你的知识、经验和见解</h2>
		</div>
		<nav class="switch_nav"> <a href="/user/showLogin"
			id="switch_login" class="switch_btn">登陆</a> <a href="javascript:;"
			id="switch_signup" class="switch_btn on">注册</a>
		<div class="switch_bottom" id="switch_bottom"></div>
		</nav>

		<form id="personRegForm" method="post" onsubmit="return false;">
			<div onselectstart="return false;">
				<ul class="group_input">
					<div id="select-regName">
						<div class="o-intelligent-regName">
							<input type="text" id="regName" name="username" class="text"
								tabindex="1" autoComplete="off" placeholder="用户名"
								onpaste="return false;" value=""
								onfocus="if(this.value=='') this.value='';this.style.color='#333'"
								onblur="if(this.value=='') {this.value='';this.style.color='#999999'}" />
							<i class="i-name"></i>
							<ul id="intelligent-regName" class="hide"></ul>
							<label id="regName_succeed" class="blank"></label> <label
								id="regName_error" class="hide"></label>
						</div>
					</div>

					<div id="o-password">
						<div class="fl item-ifo">
							<input type="text" id="phone" maxlength="11" name="phone"
								class="text" tabindex="4" autocomplete="off"
								placeholder="请输入手机号码" /> <i class="i-phone"></i> <label
								id="phone_succeed" class="blank"> </label> <label
								id="phone_error"></label>
						</div>

						<div class="fl item-ifo">
							<input type="password" id="pwd" name="password" class="text"
								tabindex="2" style="ime-mode: disabled;" onpaste="return  false"
								autocomplete="off" placeholder="请设置密码" /> <i class="i-pass"></i>
							<label id="pwd_succeed" class="blank"></label> <label
								id="pwd_error"></label> <span class="clr"></span>
						</div>

						<div class="fl item-ifo">
							<input type="password" id="pwdRepeat" name="pwdRepeat"
								class="text" tabindex="3" onpaste="return  false"
								autocomplete="off" placeholder="请确认密码" /> <i class="i-pass"></i>
							<label id="pwdRepeat_succeed" class="blank"> </label> <label
								id="pwdRepeat_error"></label>
						</div>



					</div>
			</div>
			<span class="agreement-tip">点击「注册」按钮，即代表你同意<a href="javascript:;">《广财书苑商城用户注册协议》</a></span>
			<div class="item">
					<span class="label">&nbsp;</span> <input type="button"
					class="submit_btn" id="registsubmit" value="立即注册" tabindex="8"
					clstag="regist|keycount|personalreg|07" onclick="REGISTER.reg();" />
			</div>
			</ul>
	</div>
	<span class="clr"></span>
	</form>
		<div id="footer">
			<span>&copy;2019 Powered By Wuky . All Rights Reserved</span>
		</div>
	</div>

	<script type="text/javascript">
		var REGISTER = {
			param : {
				//单点登录系统的url
				surl : ""
			},
			inputcheck : function() {
				//不能为空检查
				if ($("#regName").val() == "") {
					alert("用户名不能为空");
					$("#regName").focus();
					return false;
				}
				if ($("#pwd").val() == "") {
					alert("密码不能为空");
					$("#pwd").focus();
					return false;
				}
				if ($("#phone").val() == "") {
					alert("手机号不能为空");
					$("#phone").focus();
					return false;
				}
				//密码检查
				if ($("#pwd").val() != $("#pwdRepeat").val()) {
					alert("确认密码和密码不一致，请重新输入！");
					$("#pwdRepeat").select();
					$("#pwdRepeat").focus();
					return false;
				}
				return true;
			},
			beforeSubmit : function() {
				//检查用户是否已经被占用
				$.ajax({
					url : REGISTER.param.surl + "/user/check/"
							+ escape($("#regName").val()) + "/1?r="
							+ Math.random(),
					success : function(data) {
						if (data.data) {
							//检查手机号是否存在
							$.ajax({
								url : REGISTER.param.surl + "/user/check/"
										+ $("#phone").val() + "/2?r="
										+ Math.random(),
								success : function(data) {
									if (data.data) {
										REGISTER.doSubmit();
									} else {
										alert("此手机号已经被注册！");
										$("#phone").select();
									}
								}
							});
						} else {
							alert("此用户名已经被占用，请选择其他用户名");
							$("#regName").select();
						}
					}
				});

			},
			doSubmit : function() {
				$.post("/user/register", $("#personRegForm").serialize(),
						function(data) {
							if (data.status == 200) {
								alert('用户注册成功，请登录！');
								REGISTER.login();
							} else {
								alert("注册失败！");
							}
						});
			},
			login : function() {
				location.href = "/user/showLogin";
				return false;
			},
			reg : function() {
				if (this.inputcheck()) {
					this.beforeSubmit();
				}
			}
		};
	</script>
</body>
</html>
