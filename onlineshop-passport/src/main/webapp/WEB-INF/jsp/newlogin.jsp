<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  	 <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	 <title>登录广财书苑</title>
	 <link rel="stylesheet" href="/libs/particles/css/style.css">
	 <link type="text/css" rel="stylesheet" href="/css/base.css">
	 <link type="text/css" rel="stylesheet" href="/css/login.css" />
	 <script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
</head>
<body>
<jsp:include page="author.jsp"/>
	<!-- particles.js container -->
	<div id="particles-js"></div>
	<div id="wrapper">
		<div>
			<img src="/images/login-logo.png" />
			<h2>网罗天下图书 传承中华文明</h2>
		</div>
		<nav class="switch_nav"> 
		   <a href="javascript:;" id="switch_login" class="switch_btn on">登陆</a>
			<a href="/user/showRegister" id="switch_signup" class="switch_btn">注册</a> 
		<div class="switch_bottom" id="switch_bottom"></div>
		</nav>

		<form id="formlogin" method="post" onsubmit="return false;">
			<div class="mc " id="bgDiv">
				<div>
					<ul class="group_input">
						<div class="item-ifo">
							<input type="text" id="loginname" name="username" class="text"
								tabindex="1" autocomplete="off" placeholder="用户名" />
							<div class="i-name ico"></div>
							<label id="loginname_succeed" class="blank invisible"></label> <label
								id="loginname_error" class="hide"><b></b></label>
						</div>
					
					</ul>
					<script type="text/javascript">
						setTimeout(function() {
							if (!$("#loginname").val()) {
								$("#loginname").get(0).focus();
							}
						}, 0);
					</script>
					<!-- <div id="capslock">
						<i></i><s></s>键盘大写锁定已打开，请注意大小写
					</div> -->
					<ul class="group_input">
					
						<div class="item-ifo">
							<input type="password" id="nloginpwd" name="password"
								class="text" tabindex="2" autocomplete="off" placeholder="密码" />
							<div class="i-pass ico"></div>
							<label id="loginpwd_succeed" class="blank invisible"></label> <label
								id="loginpwd_error" class="hide"></label>
						</div>
						
					</ul>
					<div class="item login-btn2013">
						<input type="button" class="submit_btn" id="loginsubmit"
							value="登录" tabindex="8" clstag="passport|keycount|login|06" />
					</div>
				</div>
			</div>
			<!-- <div class="free-regist">
					<span><a href="/user/showRegister"
						clstag="passport|keycount|login|08">免费注册&gt;&gt;</a></span>
				</div> -->
		</form>
		<div class="states">
			<span class="left"><a href="javascript:;">手机验证码登录</a></span> <span
				class="right"><a href="javascript:;">无法登录？</a></span>
		</div>
		<div class="states">
			<a href="javascript:;" class="social_account">社交账号登录</a>
			<div class="states three_MinIcon">
				<a href="javascript:;" class="MinIcon_weixin"><img
					src="/images/icon_weixin.jpg" style="width: 20px; height: 18px" /></a>
				<a href="javascript:;" class="MinIcon_weibo"><img
					src="/images/icon_weibo.jpg" style="width: 20px; height: 18px" /></a>
				<a href="javascript:;" class="MinIcon_qq"><img
					src="/images/icon_qq.jpg" style="width: 20px; height: 18px" /></a>
			</div>
		</div>
		<div id="footer">
			<span>&copy;2019 Powered By Wuky . All Rights Reserved</span>
		</div>
	</div>
	</div>

	<script type="text/javascript">
		var redirectUrl = "${redirect}";
		var LOGIN = {
			checkInput : function() {
				if ($("#loginname").val() == "") {
					alert("用户名不能为空");
					$("#loginname").focus();
					return false;
				}
				if ($("#nloginpwd").val() == "") {
					alert("密码不能为空");
					$("#nloginpwd").focus();
					return false;
				}
				return true;
			},
			doLogin : function() {
				$.post("/user/login", $("#formlogin").serialize(), function(
						data) {
					if (data.status == 200) {
						alert("登录成功！");
						if (redirectUrl == "") {
							location.href = "http://localhost:8082";
						} else {
							location.href = redirectUrl;
						}
					} else {
						alert("登录失败，原因是：" + data.msg);
						$("#loginname").select();
					}
				});
			},
			login : function() {
				if (this.checkInput()) {
					this.doLogin();
				}
			}

		};
		$(function() {
			$("#loginsubmit").click(function() {
				LOGIN.login();
			});
		});
	</script>

   <script>
		$(function(){
		//为表单的必填文本框添加提示信息（选择form中的所有后代input元素）
        // $("form :input.required").each(function () {
        //     //通过jquery api：$("HTML字符串") 创建jquery对象
        //     var $required = $("<strong class='high'>*</strong>");
        //     //添加到this对象的父级对象下
        //     $(this).parent().append($required);
        // });
			// var errorMsg = $(".error-msg").text();
		//为表单元素添加失去焦点事件
		$("form :input").blur(function(){
			var $parent = $(this).parent();
			$parent.find(".msg").remove(); //删除以前的提醒元素（find()：查找匹配元素集中元素的所有匹配元素）		
			//验证手机号
			if($(this).is("#loginname")){
				var mobileVal = $.trim(this.value);
				// var regMobile = /^1[3|4|5|7|8][0-9]{9}$/;
				if(mobileVal == ""){
					var errorMsg = " 请输入用户名！";
					$parent.append("<span class='msg onError'>" + errorMsg + "</span>");
				} else{
					var okMsg=" 输入正确";
                    $parent.append("<span class='msg onSuccess'>" + okMsg + "</span>");
				}
			}
			//验证密码
            if($(this).is("#nloginpwd")){
                var psdVal = $.trim(this.value);
                var regPsd = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;
                if(psdVal== "" || !regPsd.test(psdVal)){
                    var errorMsg = " 密码为6-20位字母、数字的组合！";
                    $parent.append("<span class='msg onError'>" + errorMsg + "</span>");
                }
                else{
                    var okMsg=" 输入正确";
                    $parent.append("<span class='msg onSuccess'>" + okMsg + "</span>");
                }
            }
		}).keyup(function(){
			//triggerHandler 防止事件执行完后，浏览器自动为标签获得焦点
			$(this).triggerHandler("blur"); 
		}).focus(function(){
			$(this).triggerHandler("blur");
		});

				//点击重置按钮时，通过trigger()来触发文本框的失去焦点事件
		$("#btnSubmit").click(function(){
    		//trigger 事件执行完后，浏览器会为submit按钮获得焦点
    		$("form .required:input").trigger("blur"); 
    		var numError = $("form .onError").length;
    		if(numError){
    			return false;
    		}
    		alert('登陆成功！')
    	});
		})
		
	</script>
</body>