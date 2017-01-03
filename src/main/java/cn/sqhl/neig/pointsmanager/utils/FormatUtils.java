package cn.sqhl.neig.pointsmanager.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.StringUtils;


public class FormatUtils {
	 public static String getStringFromHttp(InputStream entity) {

		 StringBuilder buffer = new StringBuilder();
	     try {
	         // 获取输入流
//	         BufferedReader reader = new BufferedReader(new InputStreamReader(entity,"UTF-8"));
	        //
//	    	 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, CHARSET));
	    	 BufferedReader reader = new BufferedReader(new InputStreamReader(entity));
	      //   String aaa=new String(inputStream2String(entity).getBytes("GBK"), "UTF-8");
	       //  BufferedReader reader = new BufferedReader(new InputStreamReader(entity,"UTF-8"));
//	         reader = new BufferedReader(new InputStreamReader(final_request.getInputStream(),"UTF-8"));
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
	 public static String getStringFromHttpforCN(InputStream entity) {

		 StringBuilder buffer = new StringBuilder();
	     try {
	         // 获取输入流
//	         BufferedReader reader = new BufferedReader(new InputStreamReader(entity,"UTF-8"));
	        //
//	    	 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, CHARSET));
	    	 //BufferedReader reader = new BufferedReader(new InputStreamReader(entity));
//	    	 BufferedReader reader = new BufferedReader(new InputStreamReader(entity));
	    	 BufferedReader reader = new BufferedReader(new InputStreamReader(entity,"ISO-8859-1"));
//	         String aaa=new String(inputStream2String(entity).getBytes("GBK"), "UTF-8");
	       //  BufferedReader reader = new BufferedReader(new InputStreamReader(entity,"UTF-8"));
//	         reader = new BufferedReader(new InputStreamReader(final_request.getInputStream(),"UTF-8"));
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
	 public String dealNullStr(String str){
	     if(str == null || StringUtils.isEmpty(str)){
	         return "";
	     }
	     return str;
	 }
	 public String dealNullStr(Object o){
	     if(o == null) return "";
	     return dealNullStr(o.toString());
	 }

	 
	 public static String inputStream2String(InputStream in_st){
		    BufferedReader in;
		    StringBuffer buffer = new StringBuffer();
			try {
				in = new BufferedReader(new InputStreamReader(in_st, "UTF-8"));
			
		//    BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(in_st), "gbk"));  
		 
		    String line = "";
		   
				while ((line = in.readLine()) != null){
				  buffer.append(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return buffer.toString();
		}
	
	 
	 public static String CheckEnCoding(String str) {
		 
		 try {
			 List<String> list=new ArrayList<String>();
			 list.add("ISO-8859-1");
			 list.add("GBK");
			 list.add("UTF-8");
			 
			 for(String encode:list){
				 if(str.equals(new String(str.getBytes(encode), encode))){
					 return encode;
				 }
			 }
			 
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	 }
	 
	 public static List<String> testStringFromHttpforCN(InputStream entity) {

		 List<String> strlist=new ArrayList<String>();
		 StringBuilder buffer ;
	     try {
	    	 List<BufferedReader> bflist=new ArrayList<BufferedReader>();
	         // 获取输入流
//	         BufferedReader reader1 = new BufferedReader(new InputStreamReader(entity,"UTF-8"));
//	         bflist.add(reader1);
	         BufferedReader reader2 = new BufferedReader(new InputStreamReader(entity,"ISO-8859-1"));
	         bflist.add(reader2);
//	         BufferedReader reader3 = new BufferedReader(new InputStreamReader(entity,"GBK"));
//	         bflist.add(reader3);
//	         BufferedReader reader4 = new BufferedReader(new InputStreamReader(entity,"GB2312"));
//	         bflist.add(reader4);
	        for(int i=0;i<bflist.size();i++){
	        	 String temp = null;
	        	 buffer = new StringBuilder();
		         while ((temp = bflist.get(i).readLine()) != null) {
		        	 buffer.append(temp);
		         }
		         strlist.add(buffer.toString());
	         }
	         
	        //
//	    	 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, CHARSET));
	    	 //BufferedReader reader = new BufferedReader(new InputStreamReader(entity));
//	    	 BufferedReader reader = new BufferedReader(new InputStreamReader(entity));
//	         String aaa=new String(inputStream2String(entity).getBytes("GBK"), "UTF-8");
	       //  BufferedReader reader = new BufferedReader(new InputStreamReader(entity,"UTF-8"));
//	         reader = new BufferedReader(new InputStreamReader(final_request.getInputStream(),"UTF-8"));
	         // 将返回的数据读到buffer中
	         
	     } catch (IllegalStateException e) {
	         e.printStackTrace();
	     } catch (IOException e) {
	         e.printStackTrace();
	     }
	     return strlist;
	 }
	 public static String MaptoStringforUrl(Map map){
		
		 	StringBuffer sb=new StringBuffer();
			Set es = map.entrySet();
			Iterator it = es.iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				String k = (String) entry.getKey();
				String v = (String) entry.getValue();
				sb.append(k + "=" + v + "&");
			}
			
			return sb.toString().substring(0, sb.toString().length()-1);
	 }
}
