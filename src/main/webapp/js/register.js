			function submit(){
				var username=$('#username').val();
				var loginPwd=$('#loginPwd').val();
				var tel=$('#tel').val();
				var checktel = /^((\(\d{3}\))|(\d{3}\-))?13\d{9}|14[57]\d{8}|15\d{9}|18\d{9}$/ ; 
				
				var passwordRepeat = $('#passwordRepeat').val();
				var ok1=false;
                var ok2=false;
                var ok3=false;
                
				if(username !="" && loginPwd !="" && loginPwd==passwordRepeat){
					ok1 =true;
					
		
				}else {
					layer.msg('填写内容为空或两次密码输入不一致!',{icon:5,time:1500});
					ok1 = false;
				}
				
				if(loginPwd.length >= 6 &&loginPwd.length <=15){
					ok2=true;		
				}else{
					layer.msg('密码应该为6-15位之间',{icon:5,time:1500});
					ok2 = false;
				}
				if(tel.length!=11||!tel.match(checktel)){
					layer.msg('请输入正确的手机号码!',{icon:5,time:1500});
				}else{
					 ok3=true;
				}
				       
				
				
				
				if(ok1 && ok2&&ok3){
					$.post(
									_basePath+"/user_web/user/registerjson.do",
									{ 
										"tel":tel,
										"username":username,
										"loginPwd":loginPwd,
									},function(data){
										if(data.msg >0){
										layer.msg('注册成功!',{icon:5,time:1500},function(){
                            				window.location.href="${path}/login_web/login.do"});
											
										}else{
										layer.msg('注册失败!',{icon:5,time:1500});
											
										}
									},"json"
							);

				}
			}			