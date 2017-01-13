
	$(function(){
		$('#mobile_code').focus(function(){
            $('#msg').text('');
        }).blur(function(){
        	var mobilecode=$('#mobile_code').val();
 
        	if(mobilecode==""){
        		$('#msg').text('*验证码不能为空');
        	}else {
        		$.post(
						_basePath+"/user_web/user/checkcode.do",
						{ 
							"mobilecode":mobilecode		
						},function(data){
							if(data.msg==1){
								
							}else{
								$('#msg').text('*输入的验证码有误');
								
								
							}
						},"json"
				);
        	}
        });
		
		$('#sendMobileCode').click(function(){
				var tel= $('#tel').val();
					$.post(
								_basePath+"/user_web/user/sendcode.do",
								{ 
									"tel":tel	
								},function(data){
									if(data.msg==0){
										layer.msg('短信已发送，请在30秒内进行验证!',{icon:5,time:1500});
										$('#sendMobileCode').attr('disabled','disabled');
													
									}else{
										layer.msg('短信发送失败!',{icon:5,time:1500});
									}
								},"json"
					);
				
				
		});
		
		
		
	});	
	
	

