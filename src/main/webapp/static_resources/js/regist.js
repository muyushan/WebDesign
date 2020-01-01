$(document).ready(function(){
	// 个人信息注册保存按钮处理
	$("#submitBtn").click(function(){
		var email_phone=$("#email_phone").val();
		var password=$("#password").val();
		var confirm_password=$("#confirm_password").val();
		var registInfo={};
		registInfo['username']=email_phone;
		registInfo['password']=password;
		$.ajax({
            url : "regist",  
            type : "POST",  
            datatype:"json",
            contentType: "application/json",
            data : JSON.stringify(registInfo),  
            success:function(data){
            	if(data=='success'){
            		// 注册成功，跳转到个人详情信息页面
            		var profileUrl=registInfo.username+"/";
            		location.href=profileUrl;
            	}else{
            		alert(data);
            	}
            	
            },
            error : function(data) {  
                alert("请求失败");  
            }  
        });  
	});
	
	// 邮箱手机焦点验证
	$("#email_phone").blur(function(){
		var email_phone=$("#email_phone").val();
		var reg_email = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
		var reg_phone = /^1\d{10}$/;
		                if (reg_email.test(email_phone)||reg_phone.test(email_phone)) {
		                	email_phoneValidation(email_phone);
		                	$("#email_phone").removeClass("valid_error");
		                	$("#email_phone").addClass("valid_pass");
		                	$("#email_phone").next("span").removeClass("valid_error_tip");
		                	$("#email_phone").next("span").text("");
		                } else {
		                	$("#email_phone").removeClass("valid_pass");
		                	$("#email_phone").addClass("valid_error");
		                	$("#email_phone").next("span").addClass("valid_error_tip");
		                	$("#email_phone").next("span").text("请输入正确的邮箱或手机号码");
		                }
	});
	
	function  email_phoneValidation(email_phone){
		var email_phone=$("#email_phone").val();
		$.ajax({
			url:email_phone+'/validate',
			type:'GET',
			success:function(data){
				if(eval("("+data+")")){
					$("#email_phone").removeClass("valid_pass");
	            	$("#email_phone").addClass("valid_error");
	            	$("#email_phone").next("span").addClass("valid_error_tip");
	            	$("#email_phone").next("span").text("该邮箱或手机已经被注册过了！");
				}else{
					$("#email_phone").removeClass("valid_error");
                	$("#email_phone").addClass("valid_pass");
                	$("#email_phone").next("span").removeClass("valid_error_tip");
                	$("#email_phone").next("span").text("");
				}
				
			}
		});
	}
	// 密码验证
	$("#password").blur(function(){
		var reg_pass=/(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,30}/;
		var password=$("#password").val();
		if(reg_pass.test(password)){
			$("#password").removeClass("valid_error");
        	$("#password").addClass("valid_pass");
        	$("#password").next("span").removeClass("valid_error_tip");
        	$("#password").next("span").text("");
		}else{
			$("#password").removeClass("valid_pass");
        	$("#password").addClass("valid_error");
        	$("#password").next("span").addClass("valid_error_tip");
        	$("#password").next("span").text("必须包含字母、数字、特殊字符，8-30个字符");
		}
	});
	// 重复密码验证
	$("#confirm_password").blur(function(){
		var password=$("#password").val();
		if(password==''){
			return;
		}
		var confirm_password=$("#confirm_password").val();
       if((password==confirm_password)&&(confirm_password!='')){
    	$("#confirm_password").removeClass("valid_error");
       	$("#confirm_password").addClass("valid_pass");
       	$("#confirm_password").next("span").removeClass("valid_error_tip");
       	$("#confirm_password").next("span").text("");
		}else{
			$("#confirm_password").removeClass("valid_pass");
        	$("#confirm_password").addClass("valid_error");
        	$("#confirm_password").next("span").addClass("valid_error_tip");
        	$("#confirm_password").next("span").text("" +
        			"确认密码要和密码一致");
		}
		
	});
	
})