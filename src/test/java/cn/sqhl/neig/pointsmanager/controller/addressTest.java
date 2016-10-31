package cn.sqhl.neig.pointsmanager.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import junit.framework.TestCase;

/**
 * @author Freedom
 *
 */
public class addressTest extends TestCase {
	
	private static HttpClient client;
	
	
	public void testUrl() throws ClientProtocolException, IOException
	{
		
		
		   DefaultHttpClient client = new DefaultHttpClient();
		   
//		   HttpPost httpost = new HttpPost("http://127.0.0.1:8080/pointsmanager/address/search.do");
//		   HttpPost httpost = new HttpPost("http://127.0.0.1:8080/pointsmanager/address/manager.action");
		   HttpPost httpost = new HttpPost("http://127.0.0.1:8080/pointsmanager/goods/search.action");
//		   HttpPost httpost = new HttpPost("http://127.0.0.1:8080/pointsmanager/goods/searchlist.action");
//		   HttpPost httpost = new HttpPost("http://127.0.0.1:8080/pointsmanager/goods/searchcategory.action");
//		   HttpPost httpost = new HttpPost("http://127.0.0.1:8080/pointsmanager/adevent/searchad.action");
//		   HttpPost httpost = new HttpPost("http://127.0.0.1:8080/pointsmanager/adevent/searchevent.action");
		   httpost.setHeader("Content-Type", "application/json;charset=utf-8");
		  // 下面加入代码
		   Map map =new HashMap(); 
		   /***
		    * begin test
		    * /address/search.do
		    */
//		   map.put("userid", "1");
//		   map.put("isuse", "");
		   /**
		    * end test
		    */
		   
		   
		   /**
		    * begin test
		    * /address/manager.action
		    */
//		   map.put("uerid", "0002");
//		   map.put("address", "长沙市芙蓉区");
//		   map.put("name", "cc陈");
//		   map.put("post", "222222");
//		   map.put("phonenumb", "22222222222");
//		   map.put("telnumb", "123123123");
//		   map.put("isuse", "1");
//		   map.put("type", "0");
//		   map.put("addressid", "");
		   

		   /**
		    * end test
		    */
		   
		   /***
		    * begin test
		    * /goods/search.action
		    */
		   map.put("goodsid", "1");
		   /**
		    * end test
		    */
		   
			
		   JSONObject jsonpk=JSONObject.fromObject(map); 
		   //加密 
		   //DESUtil des=new DESUtil(); 
		   //String pk=des.encrypt(jsonpk.toString()); 
		   //参数 
		   StringEntity se = new StringEntity(jsonpk.toString()); 
		   httpost.setEntity(se); 
		   
		   System.out.println("JSONObject:"+jsonpk);
		   System.out.println("StringEntity:"+se);
		   
		   HttpResponse response = client.execute(httpost);
           String locationsJSONString = getStringFromHttp(response.getEntity());
           System.out.println(locationsJSONString);
           //DES解密
         //  DESUtil des=new DESUtil();
           //locationsJSONString=des.decrypt(locationsJSONString);
           //System.out.println(locationsJSONString);
           //读取json
          //JSONArray
           JSONObject json=JSONObject.fromObject(locationsJSONString);
           //String fundaccount= (String) json.get("value");
           System.out.println("return values:"+json);
      }
	  private static String getStringFromHttp(HttpEntity entity) {

	        StringBuffer buffer = new StringBuffer();

	        try {
	            // 获取输入流
	            BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"));

	            // 将返回的数据读到buffer中
	            String temp = null;

	            while ((temp = reader.readLine()) != null) {
	                buffer.append(temp);
	            }
	        } catch (IllegalStateException e) {
	            
	            //e.printStackTrace();
	        } catch (IOException e) {
	            //e.printStackTrace();
	        }
	        return buffer.toString();
	    }

	public static HttpClient getClient() {
		return client;
	}



	public static void setClient(HttpClient client) {
		addressTest.client = client;
	}
}