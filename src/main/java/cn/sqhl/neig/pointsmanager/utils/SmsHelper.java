package cn.sqhl.neig.pointsmanager.utils;

import java.util.HashMap;
import java.util.Map;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;

import net.sf.json.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class SmsHelper {
	

	public static void sendSms(String phoneNum,String message) {   
		
		PropKit.use("config.properties");
        
        try {
        	
        	IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", PropKit.get("aliyun.sms.accessKey"), PropKit.get("aliyun.sms.accessSecret"));
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms",  "sms.aliyuncs.com");
            IAcsClient client = new DefaultAcsClient(profile);
            SingleSendSmsRequest request = new SingleSendSmsRequest();
            
            Map param = new HashMap();
            param.put("name", "用户");
            param.put("code", message);
            
            
            request.setSignName(PropKit.get("aliyun.sms.signName"));
            request.setTemplateCode(PropKit.get("aliyun.sms.templateCode"));
            System.out.println(JSONObject.fromObject(param).toString());
            request.setParamString( JSONObject.fromObject(param).toString()  );
            request.setRecNum(phoneNum);
            
            SingleSendSmsResponse httpResponse = client.getAcsResponse(request);
            
            
            
        } catch (ServerException e) {
            e.printStackTrace();
        }
        catch (ClientException e) {
            e.printStackTrace();
        }
    }
	
	public static void main(String[] args) {
		SmsHelper.sendSms("15697313599", "623480");
	}
	
}
