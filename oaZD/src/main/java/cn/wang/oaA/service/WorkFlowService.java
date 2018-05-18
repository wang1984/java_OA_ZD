package cn.wang.oaA.service;
 
import java.io.File;
import java.util.Collection;

import cn.wang.oaA.domain.workflow.Form;

public interface WorkFlowService {
	public void submit(File resource,Long ftid,String processKey);
	
	public Collection<Form> myTaskList();
	
	//public Collection<TaskView> myTaskList2();
	
	public void approve();
}
