package cn.wang.oaA.annotation.privilege;

import java.lang.reflect.Method;

public class PrivilegeInfoParse {
	public static String parse(Class actionClass,String methodName) throws Exception{
		String privilegeName = "";
		//得到访问action的方法的method内容
		Method method = actionClass.getMethod(methodName);
		/**
		 * 判断访问的action的方法上面是否存在PrivilegeInfo注解 
		 */
		if(method.isAnnotationPresent(PrivilegeInfo.class)){
			privilegeName = method.getAnnotation(PrivilegeInfo.class).name();
		}
		return privilegeName;
	}
}
