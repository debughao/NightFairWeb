$(function() {	
	//邮箱栏获得焦点
	$("#username").click(function(){
		$('.error1').hide();
	});
	$("#password").click(function(){
		$('.error1').hide();
	});
	$(".fullBtnBlue").click(function() {	
		var $username = $('#username');
		var	$password = $("#password");		
		if ($username.val() == '') {	
			$(".error1").html("用户名不能为空！");
			$(".error1").css("display", "block");
			return;
		}else if ($password.val() == '') {	
			$(".error1").html("密码不能为空！");
			$(".error1").css("display", "block");
			return;
		} else{
		var	username=$username.val();
		var	password=$password.val();
	
			$.ajax({
				url : "/NightFair/Register",
				data : {
					"validate" : "login", "username" : username,
					 "password":password,
				},
				type : "post",
				success : function(data) {
					var dataObj = JSON.parse(data);
					if (dataObj.values == true) {
						window.location="seller/update.html";
					}else{
						$(".error1").html("用户名或密码错误！");
						$(".error1").css("display", "block");
					}
				},
				error : function(error) {
					console.log(error);
					alert('网络异常，稍后再试!');
				}
			});
		}
	});
	
});

