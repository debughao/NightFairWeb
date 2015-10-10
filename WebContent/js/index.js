$(function(){
	//文本框失去焦点
	$(".mainForm input").blur(function(){
		$("#mz_Float").hide();
	});
	$(".mainForm input").click(function(){
		$("#mz_Float").show();
	});
	
	//mainform1
	//密码是否可见(mainform1)
	$(".pwdBtnShow").click(function(){
		if($(".pwdBtnShow").attr("isshow")=="false")
		{
			$(".pwdBtnShow i").css("background-position","-60px -93px");
			$(".password").hide();
			$(".password1").val($(".password").val());
			$(".password1").show();
			$(".pwdBtnShow").attr("isshow","true");
		}
		else
		{
			$(".pwdBtnShow i").css("background-position","-30px -93px");
			$(".password1").hide();
			$(".password").val($(".password1").val());
			$(".password").show();
			$(".pwdBtnShow").attr("isshow","false");
		}
		
	});
	
	//手机号栏失去焦点
	$(".phone").blur(function(){
		reg=/^1[3|4|5|8][0-9]\d{4,8}$/i;//验证手机正则(输入前7位至11位)

		if( $(".phone").val()=="")
		{ 
			$(".phone").parent().addClass("errorC");
			$(".error4").html("请输入手机号");
			$(".error4").css("display","block");
		}
		else if($(".phone").val().length<11)
        {   
        	$(".phone").parent().addClass("errorC");
            $(".error4").html("手机号长度有误！");
            $(".error4").css("display","block");
        }
        else if(!reg.test($(".phone").val()))
        {   
        	$(".phone").parent().addClass("errorC");
            $(".error4").html("逗我呢吧，你确定这是你的手机号!!");
            $(".error4").css("display","block");
        }
        else
        {var phone=$(".phone").val();
        	$.ajax({
			url : "/NightFair/Register",
			data : {
				"validate" : "phone", "parameter" : phone				 
			},
			type : "post",
			success : function(data) {
				var dataObj = JSON.parse(data);
				if (dataObj.values == false) {
					$(".phone").parent().addClass("errorC");
					$(".error4").html("此手机号已被注册！");
					$(".error4").css("display","block");
				}
			},
			error : function(error) {
				console.log(error);
				alert('网络异常，稍后再试!');
			}
		});
        	$(".phone").parent().addClass("checkedN");
        }
	});

	//验证码栏失去焦点
	$(".kapkey").blur(function(){
		reg=/^.*[\u4e00-\u9fa5]+.*$/;
		if( $(".kapkey").val()=="")
		{
			$(".kapkey").parent().addClass("errorC");
			$(".error5").html("请填写验证码");
			$(".error5").css("display","block");
		}
        else if($(".kapkey").val().length<6)
        {   
        	$(".kapkey").parent().addClass("errorC");
            $(".error5").html("验证码长度有误！");
            $(".error5").css("display","block");
        }
        else if(reg.test($(".kapkey").val()))
        {
        	$(".kapkey").parent().addClass("errorC");
            $(".error5").html("验证码里无中文！");
            $(".error5").css("display","block");
        }
        else 
        {
        	$(".kapkey").parent().addClass("checkedN");
        }
	});

	//密码栏失去焦点(mainform1)
	$(".password,.password1").blur(function(){
		reg1=/^.*[\d]+.*$/;
		reg2=/^.*[A-Za-z]+.*$/;
		reg3=/^.*[_@#%&^+-/*\/\\]+.*$/;//验证密码
		if($(".pwdBtnShow").attr("isshow")=="false")
		{
			var Pval = $(".password").val();
		}
		else
		{
			var Pval = $(".password1").val();
		}
		
		if( Pval =="")
		{
			$(".password").parent().addClass("errorC");
			$(".error3").html("请填写密码");
			$(".error3").css("display","block");
		}
        else if(Pval.length>16 || Pval.length<8)
        {   
        	$(".password").parent().addClass("errorC");
            $(".error3").html("密码应为8-16个字符，区分大小写");
            $(".error3").css("display","block");
        }
        else if(!((reg1.test(Pval) && reg2.test(Pval)) || (reg1.test(Pval) && reg3.test(Pval)) || (reg2.test(Pval) && reg3.test(Pval)) ))
        {
        	$(".password").parent().addClass("errorC");
            $(".error3").html("需至少包含数字、字母和符号中的两种类型");
            $(".error3").css("display","block");
        }
        else
        {
        	$(".password").parent().addClass("checkedN");
        }
	});

	//手机号栏获得焦点
	$(".phone").focus(function(){
		$(".phone").parent().removeClass("errorC");
		$(".phone").parent().removeClass("checkedN");
		$(".error4").hide();
		if($(".error1").css("display")=="block" && $(".error3").css("display")=="block"&& $(".error2").css("display")=="block"){
		$("#mz_Float").css("top","205px");
		}else if($(".error1").css("display")=="block" || $(".error3").css("display")=="block"|| $(".error2").css("display")=="block"){
        $("#mz_Float").css("top","180px");
        }else if($(".error1").css("display")=="none" && $(".error3").css("display")=="none"&& $(".error2").css("display")=="none"){
        	$("#mz_Float").css("top","160px");
        }else{
        	$("#mz_Float").css("top","175px");
        }
		$("#mz_Float").find(".bRadius2").html("输入11位手机号码，可用于登录和找回密码");
	});
	//验证码栏获得焦点
	$(".kapkey").focus(function(){
		$(".kapkey").parent().removeClass("errorC");
		$(".kapkey").parent().removeClass("checkedN");
		$(".error5").hide();
		if($(".error1").css("display")=="block"&&$(".error2").css("display")=="block"&&$(".error3").css("display")=="block"&&$(".error4").css("display")=="block")
		{
			$("#mz_Float").css("top","275px");
		}
		else if($(".error1").css("none")=="block"&&$(".error2").css("none")=="block"&&$(".error3").css("none")=="block"&&$(".error4").css("display")=="none")
		{
			$("#mz_Float").css("top","245px");
		}else if($(".error1").css("none")=="block"&&$(".error2").css("none")=="block"||$(".error3").css("none")=="block"||$(".error4").css("display")=="none")
		{
			$("#mz_Float").css("top","230px");
		}else{
			$("#mz_Float").css("top","240px");		
		}
		$("#mz_Float").find(".bRadius2").html("请输入手机收到的验证码");
	});
	//密码栏获得焦点(mainform1)
	$(".password,.password1").focus(function(){
		$(".password").parent().removeClass("errorC");
		$(this).parent().removeClass("checkedN");
		$(".error3").hide();
		if($(".error1").css("display")=="block")
		{
			$("#mz_Float").css("top","60px");
		}
		else
		{
			$("#mz_Float").css("top","65px");
		}
		
		$("#mz_Float").find(".bRadius2").html("长度为8-16个字符，区分大小写，至少包含两种类型");
	});


	//mainform1end



	//mainForm2
	//密码是否可见(mainform2)
	$(".pwdBtnShowN").click(function(){
		if($(".pwdBtnShowN").attr("isshow")=="false")
		{
			$(".pwdBtnShowN i").css("background-position","-60px -93px");
			$(".passwordN").hide();
			$(".password1N").val($(".passwordN").val());
			$(".password1N").show();
			$(".pwdBtnShowN").attr("isshow","true");
		}
		else
		{
			$(".pwdBtnShowN i").css("background-position","-30px -93px");
			$(".password1N").hide();
			$(".passwordN").val($(".password1N").val());
			$(".passwordN").show();
			$(".pwdBtnShowN").attr("isshow","false");
		}
		
	});


	//账户名栏获得焦点
	$(".username").focus(function(){
		$(".username").parent().removeClass("errorC");
		$(".username").parent().removeClass("checkedN");
		$(".error1").hide();
		$("#mz_Float").css("top","0px");
		$("#mz_Float").find(".bRadius2").html("长度为2-10个字符支持数字、字母、下划线、中文，字母或中文开头");
	});
	//邮箱栏获得焦点
	$(".email").focus(function(){
		$(".email").parent().removeClass("errorC");
		$(".email").parent().removeClass("checkedN");
		$(".error2").hide();
		if($(".error1").css("display")=="block" && $(".error3").css("display")=="block")
		{
			$("#mz_Float").css("top","160px");
		}
		else if($(".error1").css("display")=="block" || $(".error3").css("display")=="block")
		{
			$("#mz_Float").css("top","135px");
		}
		else
		{
			$("#mz_Float").css("top","120px");
		}
		
		$("#mz_Float").find(".bRadius2").html("用于找回密码，提高账户安全等级");
	});
	//密码栏获得焦点(mainform2)
	$(".passwordN,.password1N").focus(function(){
		$(".passwordN").parent().removeClass("errorC");
		$(this).parent().removeClass("checkedN");
		$(".error3").hide();
		if($(".error1").css("display")=="block")
		{
			$("#mz_Float").css("top","8px");
		}
		else
		{
			$("#mz_Float").css("top","304px");
		}
		
		$("#mz_Float").find(".bRadius2").html("长度为8-16个字符，区分大小写，至少包含两种类型");
	});

	//账户名栏失去焦点
	$(".username").blur(function(){
		reg=/^[a-zA-Z\u4e00-\u9fa5][a-zA-Z0-9_\u4E00-\u9FA5]{2,10}$/;//验证用户名正则

		if( $(".username").val()=="")
		{ 
			$(".username").parent().addClass("errorC");
			$(".error1").html("请输入账户名");
			$(".error1").css("display","block");
		}
		else if($(".username").val().length>32 || $(".username").val().length<4)
        {   
        	$(".username").parent().addClass("errorC");
            $(".error1").html("账户名长度有误！");
            $(".error1").css("display","block");
        }
        else if(!reg.test($(".username").val()))
        {   
        	$(".username").parent().addClass("errorC");
            $(".error1").html("账户名格式有误!!");
            $(".error1").css("display","block");
        }
        else
        { var username=$(".username").val();
        	$.ajax({
			url : "/NightFair/Register",
			data : {
				"validate" : "username", "parameter" : username				 
			},
			type : "post",
			success : function(data) {
				var dataObj = JSON.parse(data);
				if (dataObj.values == false) {
					$(".username").parent().addClass("errorC");
					$(".error1").html("此用户名已被注册！");
					$(".error1").css("display","block");
				}
			},
			error : function(error) {
				console.log(error);
				alert('网络异常，稍后再试!');
			}
		});
        	$(".username").parent().addClass("checkedN");
        }
	});
	//密码栏失去焦点(mainform2)
	$(".passwordN,.password1N").blur(function(){
		reg1=/^.*[\d]+.*$/;
		reg2=/^.*[A-Za-z]+.*$/;
		reg3=/^.*[_@#%&^+-/*\/\\]+.*$/;//验证密码
		if($(".pwdBtnShow").attr("isshow")=="false")
		{
			var Pval = $(".passwordN").val();
		}
		else
		{
			var Pval = $(".password1N").val();
		}
		
		if( Pval =="")
		{
			$(".passwordN").parent().addClass("errorC");
			$(".error3").html("请填写密码");
			$(".error3").css("display","block");
		}
        else if(Pval.length>16 || Pval.length<8)
        {   
        	$(".passwordN").parent().addClass("errorC");
            $(".error3").html("密码应为8-16个字符，区分大小写");
            $(".error3").css("display","block");
        }
        else if(!((reg1.test(Pval) && reg2.test(Pval)) || (reg1.test(Pval) && reg3.test(Pval)) || (reg2.test(Pval) && reg3.test(Pval)) ))
        {
        	$(".passwordN").parent().addClass("errorC");
            $(".error3").html("需至少包含数字、字母和符号中的两种类型");
            $(".error3").css("display","block");
        }
        else
        {
        	$(".passwordN").parent().addClass("checkedN");
        }
	});
	

	//邮箱栏键盘操作
	$(".email").keyup(function(){//键盘监听keyup,keydown,keypress
		var emailVal = $(".email").val();
		emailValN = emailVal.replace(/\s/g,'');//去空
		emailValN = emailValN.replace(/[\u4e00-\u9fa5]/g,'');//屏蔽中文
		if(emailValN!=emailVal)
		{
			$(".email").val(emailValN);
		}
		
		var mailVal = emailValN.split("@");
		var mailHtml=mailVal[0];
		if(mailHtml.length>15)
		{
			mailHtml=mailHtml.slice(0,15)+"...";//字数超加省略
		}
		
		for(var i=1;i<6;i++)
		{
			var M = $(".item"+i).attr("data-mail");
			$(".item"+i).html(mailHtml+M);
		}
	});

	//邮箱提示
	$(".item").click(function(){
		var a= $(".email").val();
		var b= $(this).attr("data-mail");
		$(".email").val(a+b);
		$(".email").trigger("focus");//setTimeout($(".email").("focus") );效果同，时间设多少无所谓
	});


	$(".email").click(function(){
		if($(".error1").css("display")=="block" && $(".error3").css("display")=="block")
		{
			$(".mail").css("top","35px");
		}
		else if($(".error1").css("display")=="block" || $(".error3").css("display")=="block")
		{
			$(".mail").css("top","38px");
		}
		else
		{
			$(".mail").css("top","32px");
		}
		$(".mail").show(); 
		return false;
	});
	$(document).click(function(){
		$(".mail").hide();
	});

	//邮箱栏失去焦点
	$(".email").blur(function(){
		reg=/^\w+[@]\w+((.com)|(.net)|(.cn)|(.org)|(.gmail))$$/;
		if( $(".email").val()=="")
		{
			$(".email").parent().addClass("errorC");
			$(".error2").html("邮箱不能为空!");
			$(".error2").css("display","block");
		}
        else if(!reg.test($(".email").val()))
        {
        	$(".email").parent().addClass("errorC");
            $(".error2").html("邮箱格式错误！");
            $(".error2").css("display","block");
        }
        else 
        {var email=$(".email").val();
        	$.ajax({
			url : "/NightFair/Register",
			data : {
				"validate" : "email", "parameter" : email				 
			},
			type : "post",
			success : function(data) {
				var dataObj = JSON.parse(data);
				if (dataObj.values == false) {
					$(".email").parent().addClass("errorC");
					$(".error2").html("此邮箱已被注册！");
					$(".error2").css("display","block");
				}
			},
			error : function(error) {
				console.log(error);
				alert('网络异常，稍后再试!');
			}
		});
        	$(".email").parent().addClass("checkedN");
        }
	});

	$(".fullBtnBlue").click(function() {
		var $username = $('.username');
		var $password;
		if ($(".pwdBtnShow").attr("isshow") == "false") {
			$password = $(".password");
		} else {
			$password = $(".password1");
		}
		var $email = $('.email');
		var $phone = $('.phone');
		var $kapkey = $('.kapkey');
		if ($username.val() == '') {
			$(".username").parent().addClass("errorC");
			$(".error1").html("用户名不能为空！");
			$(".error1").css("display", "block");
			return;
		}else if($(".username").parent().hasClass('errorC')){
			$(".error1").html("用户名格式错误！");
			$(".error1").css("display", "block");
			return;
		}else if ($password.val() == '') {
			$(".password").parent().addClass("errorC");
			$(".error3").html("密码不能为空！");
			$(".error3").css("display", "block");
			return;
		} else if($(".password").parent().hasClass('errorC')){
			$(".error3").html("密码格式错误！");
			$(".error3").css("display", "block");
			return;
		}else if ($email.val() == '') {
			$(".email").parent().addClass("errorC");
			$(".error2").html("邮箱不能为空！");
			$(".error2").css("display", "block");
			return;
		}
		else if($(".email").parent().hasClass('errorC')){
			$(".error2").html("邮箱格式错误！");
			$(".error2").css("display", "block");
			return;
		}else if ($phone.val() == '') {
			$(".phone").parent().addClass("errorC");
			$(".error4").html("手机号码不能为空！");
			$(".error4").css("display", "block");
			return;
		} else if($(".phone").parent().hasClass('errorC')){
			$(".error4").html("手机号码错误！");
			$(".error4").css("display", "block");
			return;
		}else if ($kapkey.val() == '') {
			$(".kapkey").parent().addClass("errorC");
			$(".error5").html("验证码不能为空！");
			$(".error5").css("display", "block");
			return;
		} else if($(".kapkey").parent().hasClass('errorC')){
			$(".error5").html("验证码错误！");
			$(".error5").css("display", "block");
			return;
		}else{
		var	username=$username.val();
		var	password=$password.val();
		var	email=$email.val();
		var	phone=$phone.val();
			$.ajax({
				url : "/NightFair/Register",
				data : {
					"validate" : "register", "username" : username,
					 "password":password,"email":email,"phone":phone,
				},
				type : "post",
				success : function(data) {
					var dataObj = JSON.parse(data);
					if (dataObj.values == true) {
						window.location="success.html";
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