package cn.sqhl.neig.pointsmanager.controller;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringUtils;

import cn.sqhl.neig.pointsmanager.utils.DateHelper;

import com.alibaba.fastjson.JSONObject;

@Configuration
@PropertySource("classpath:config.properties")
public class ContextInfo {
	private Logger logger = LogManager.getLogger(ContextInfo.class);
	public static final Level ERROR = Level.ERROR;
	public static final Level INFO = Level.INFO;
	public static final Level DEBUG = Level.DEBUG;

	 @Value("${ns.version}")
	 protected String ver;
	 protected String result;
	 protected String message;
	 protected Object data;
	 
	 
	/***
	 * restr 为空 json不为空 则从json对象中获取 str的值 类型需要自行转换
	 * 
	 * @param restr
	 * @param str
	 * @param obj
	 * @return
	 */
	protected Object getString(String restr, String str, JSONObject obj) {
		Object inf = "";
		if (StringUtils.isEmpty(restr) && obj != null) {
			inf = obj.get(str);
		}
		return inf;
	}

	/***
	 * restr 为空 json不为空 则从json对象中获取 str的值 值不为空 进行obj的get set 操作
	 * 
	 * @param restr
	 * @param str
	 * @param json
	 * @param obj
	 * @return Object
	 */
	protected Object getValue(Class<?> obj, String str, JSONObject json) {
		boolean inf = false;
		boolean status = false;
		Field fieldname = null;
		PropertyDescriptor pd = null;
		String type = null;
		Method readmethod = null;
		Method writemethod = null;
		try {
			if (obj != null) {
				fieldname = obj.getClass().getField(str);
				// 获取字段数据类型
				type = fieldname.getGenericType().toString();
				// 获取参数描述
				pd = new PropertyDescriptor(fieldname.getName(), obj.getClass());
				// 从描述中获取 read方法 即 getXXX
				readmethod = pd.getReadMethod();
				// 从描述中获取 write方法 即 setXXX
				writemethod = pd.getWriteMethod();

				if (readmethod.invoke(obj) != null) {
					return false;
				} else {
					status = true;
				}

			} else {
				obj = obj.getClass().newInstance();
				status = true;
			}

			if (status && json != null) {
				String adds = (String) json.get(str);
				if (!StringUtils.isEmpty(adds)) {
					// 取得字段名称
					if (fieldname == null) {
						fieldname = obj.getClass().getField(str);
					}// 获取字段数据类型
					if (type == null) {
						type = fieldname.getGenericType().toString();
					}// 获取参数描述
					if (pd == null) {
						pd = new PropertyDescriptor(fieldname.getName(),
								obj.getClass());
					}// 从描述中获取 read方法 即 getXXX
					if (readmethod == null) {
						readmethod = pd.getReadMethod();
					}// 从描述中获取 write方法 即 setXXX
					if (readmethod == null) {
						writemethod = pd.getWriteMethod();
					}
					// 调用写入插入值
					Object now = null;
					if (type.equals("class java.util.Date")) {
						writemethod.invoke(obj, DateHelper.stringToDate(adds));
						// 调用读取获取值
						now = readmethod.invoke(obj);
						// 比对 插入后的值 跟 adds的值是否一致 一致则表示插入成功..
						if (DateHelper.stringToDate(adds).getTime() == ((java.util.Date) now)
								.getTime()) {
							inf = true;
						}
					} else {
						if (type.equals("class java.lang.Integer")) {
							writemethod.invoke(obj, Integer.parseInt(adds));
							// 调用读取获取值
							now = readmethod.invoke(obj);
						} else if (type.equals("class java.lang.Short")) {
							writemethod.invoke(obj, Short.parseShort(adds));
							// 调用读取获取值
							now = readmethod.invoke(obj);
						} else if (type.equals("class java.lang.Double")) {
							writemethod.invoke(obj, Double.parseDouble(adds));
							// 调用读取获取值
							now = readmethod.invoke(obj);
						} else if (type.equals("class java.lang.Boolean")) {
							writemethod.invoke(obj, Boolean.parseBoolean(adds));
							// 调用读取获取值
							now = readmethod.invoke(obj);
						}
						// 比对 插入后的值 跟 adds的值是否一致 一致则表示插入成功..
						if (now.toString().equals(adds)) {
							inf = true;
						}
					}

				}
			}
		} catch (Exception e) {
			logger.log(ERROR, e.getMessage());
			e.printStackTrace();
		}finally{
			if(inf){
				return obj;
			}else{
				return null;
			}
		}
	}
	
	
	protected Object autoLoad(Object obj, String str, JSONObject json) {
		boolean inf = true;
		boolean status = false;
		Field fieldname = null;
		try {
			if (obj != null) {
				fieldname = getField(obj, str);

				if (getFieldValue(obj, str) != null) {
					return obj;
				} else {
					status = true;
				}

			} else {
				if(json != null && json.size() >0){
					obj = obj.getClass().newInstance();
					status = true;
				}else{
					obj=null;
				}
			}

			if (status && json != null) {
				String adds = (String) json.get(str);
				if (!StringUtils.isEmpty(adds)) {
					// 取得字段名称
					if (fieldname == null) {
						fieldname = getField(obj, str);
					}// 获取字段数据类型
					String type = fieldname.getGenericType().toString();
					// 获取参数描述
					
					// 调用写入插入值
					Object now = null;
					if (type.equals("class java.util.Date")) {
						setFieldValue(obj, str, adds,type);
					} else {
						if (type.equals("class java.lang.Integer")) {
							setFieldValue(obj, str, adds,type);
						} else if (type.equals("class java.lang.Short")) {
							setFieldValue(obj, str, adds,type);
						} else if (type.equals("class java.lang.Double")) {
							setFieldValue(obj, str, adds,type);
						} else if (type.equals("class java.lang.Boolean")) {
							setFieldValue(obj, str, adds,type);
						}else if (type.equals("class java.lang.Long")) {
							setFieldValue(obj, str, adds,type);
						}else if (type.equals("class java.math.BigDecimal")) {
							setFieldValue(obj, str, adds,type);
						}else{
							setFieldValue(obj, str, adds,type);
						}
					}

				}
			}else{
				obj=null;
			}
		} catch (Exception e) {
			logger.log(ERROR, e.getMessage());
			e.printStackTrace();
		}finally{
			return obj;
		}
	}
	 /** 
     * 利用反射获取指定对象里面的指定属性 
     * @param obj 目标对象 
     * @param fieldName 目标属性 
     * @return 目标字段 
     */  
    private static Field getField(Object obj, String fieldName) {  
        Field field = null;  
        for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz  
                .getSuperclass()) {  
            try {  
                field = clazz.getDeclaredField(fieldName);  
                break;  
            } catch (NoSuchFieldException e) {  
                // 这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。  
            }  
        }  
        return field;  
    }  

    /** 
     * 利用反射设置指定对象的指定属性为指定的值 
     * @param obj 目标对象 
     * @param fieldName 目标属性 
     * @param fieldValue 目标值 
     */  
    public static void setFieldValue(Object obj, String fieldName,String fieldValue,String typename) {  
        Field field = ContextInfo.getField(obj, fieldName);
        if (field != null) {  
            try {  
                field.setAccessible(true);
                System.out.println("this is sysType:"+typename);
                if (typename.equals("class java.lang.Integer") ) {
                	field.set(obj, Integer.parseInt(fieldValue)); 
				} else if (typename.equals("class java.lang.Short")) {
					field.set(obj,Short.parseShort(fieldValue));
				} else if (typename.equals("class java.lang.Double")) {
					field.set(obj,Double.parseDouble(fieldValue));
				} else if (typename.equals("class java.lang.Boolean")) {
					field.set(obj,Boolean.parseBoolean(fieldValue));
				}else if (typename.equals("class java.lang.Long")) {
					field.set(obj,Long.parseLong(fieldValue));
				}else if (typename.equals("class java.math.BigDecimal")) {
					field.set(obj,BigDecimal.valueOf(Double.parseDouble(fieldValue)));
				}else{
					field.set(obj,fieldValue);
				}
            } catch (IllegalArgumentException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            } catch (IllegalAccessException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
    }  
    
    /** 
     * 利用反射获取指定对象的指定属性 
     * @param obj 目标对象 
     * @param fieldName 目标属性 
     * @return 目标属性的值 
     */  
    public static Object getFieldValue(Object obj, String fieldName) {  
        Object result = null;  
        Field field = ContextInfo.getField(obj, fieldName);  
        if (field != null) {  
            field.setAccessible(true);  
            try {  
                result = field.get(obj);  
            } catch (IllegalArgumentException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            } catch (IllegalAccessException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
        return result;  
    }  
    
}
