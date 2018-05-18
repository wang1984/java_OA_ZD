package cn.wang.oaA.annotation.test;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Target  ElementType.TYPE该注解只能标注在类上
 * @Retention  生命周期 
 * @Documented  该注解是否可以出现在帮助文档中
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ClassAnnotation {
	/**
	 * 该注解有一个属性的名称为name,默认值为""
	 * @return
	 */
	String name() default "";
}
