package cn.sqhl.neig.pointsmanager.chat;

import java.util.ArrayList;
import java.util.List;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Userinfos;
import com.taobao.api.request.OpenimUsersAddRequest;
import com.taobao.api.response.OpenimUsersAddResponse;

import cn.sqhl.neig.pointsmanager.pay.util.PropKit;
import cn.sqhl.neig.pointsmanager.po.NsUser;

public class IChat {
	
	
	private static IChat ichat = new IChat();
	
    private IChat (){
    	PropKit.use("config.properties");
    }
    
    public static IChat getInstance() {
    	return ichat;
    }

    
    
    
	public  void reg(NsUser nsuser) throws ApiException {
		
		
		TaobaoClient client = new DefaultTaobaoClient(PropKit.get("alichat.addUserURL"), PropKit.get("alichat.appKey"), PropKit.get("alichat.appSecret"));
		OpenimUsersAddRequest req = new OpenimUsersAddRequest();
		List<Userinfos> userlist = new ArrayList<Userinfos>();
		Userinfos userInfo = new Userinfos();
		userInfo.setNick(nsuser.getNickName());
		userInfo.setIconUrl("");
		userInfo.setMobile(nsuser.getUserPhone());
		userInfo.setUserid("bxuserid_"+nsuser.getId());
		userInfo.setPassword("123456");
		userInfo.setName(nsuser.getTrueName());
		userInfo.setAge(30L);
		userlist.add(userInfo);
		req.setUserinfos(userlist);
		OpenimUsersAddResponse rsp = client.execute(req);
		System.out.println(rsp.getBody());


	}

}
