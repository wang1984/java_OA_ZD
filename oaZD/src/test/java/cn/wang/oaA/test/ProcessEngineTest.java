package cn.wang.oaA.test;

import org.jbpm.api.ProcessEngine;
import org.junit.Test;

public class ProcessEngineTest extends SpringUtils{
	@Test
	public void testProcessEngine(){
		ProcessEngine processEngine = (ProcessEngine)context.getBean("processEngine");
		System.out.println(processEngine);
	}
}

