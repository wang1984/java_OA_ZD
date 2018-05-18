package cn.wang.oaA.test;

import org.junit.Test;

public class SessionFactoryTest extends SpringUtils{
	@Test
	public void testSessionFactory(){
		/*
		 * Spring得到sessionFactory后说明hibernate加载成功domain的映射文件就会生成数据表
		 */
		context.getBean("sessionFactory1");
	}
}
