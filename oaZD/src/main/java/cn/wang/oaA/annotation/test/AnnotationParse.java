package cn.wang.oaA.annotation.test;

import java.lang.reflect.Method;

import org.junit.Test;

/**
 * 注解解析器
 *    1、如果要获取类上的注解，那么首先必须获取Class
 *    2、如果要获取方法上的注解，首先必须获取Method
 *
 */
public class AnnotationParse {
	@Test
	public void parse(){
		Class classt = SH05Java.class;
		/**
		 * 该类上是否标注有该注解
		 */
		if(classt.isAnnotationPresent(ClassAnnotation.class)){
			ClassAnnotation classAnnotation = (ClassAnnotation)classt.getAnnotation(ClassAnnotation.class);
			System.out.println(classAnnotation.name());
		}
		
		Method[] methods = classt.getMethods();
		for(Method method:methods){
			/**
			 * 判断正在遍历的方法上面是否存在MethodAnnotation注解
			 */
			if(method.isAnnotationPresent(MethodAnnotation.class)){
				MethodAnnotation methodAnnotation =  method.getAnnotation(MethodAnnotation.class);
				System.out.println(methodAnnotation.name());
			}
		}
	}
}
