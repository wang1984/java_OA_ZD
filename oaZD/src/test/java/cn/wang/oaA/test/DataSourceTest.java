package cn.wang.oaA.test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;

public class DataSourceTest extends SpringUtils{
	@Test
	public void testDataSource() throws SQLException{
		/*
		 * 面向借口编程，不管datasource用什么方式传入的，最后用DataSource接口接收
		 */
		DataSource  datasource=(DataSource)context.getBean("dataSource");
		datasource.getConnection();
	}
}
