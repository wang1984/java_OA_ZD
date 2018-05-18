package cn.wang.oaA.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.springframework.stereotype.Service;

import cn.wang.oaA.service.PDManager;

@Service("pdManager")
public class PDManagerImpl implements PDManager{
	@Resource(name="processEngine")
	private ProcessEngine processEngine;
	
	//找到每个流程定义中的最高版本
	@Override
	public Collection<ProcessDefinition> getLasterVersion() {
		/* 把所有的流程定义全部查询出来，并且按照版本的从低到高排序*/
		Collection<ProcessDefinition> pdList = this.processEngine
		.getRepositoryService()
		.createProcessDefinitionQuery()
		.orderAsc(ProcessDefinitionQuery.PROPERTY_VERSION)
		.list();
		
//		Map<String, List<ProcessDefinition>> map = new HashMap<String, List<ProcessDefinition>>();
//		for(ProcessDefinition pd:pdList){
//			if(map.containsKey(pd.getKey())){//map中存在该 pd.getKey() 0 1 2 3
//				List<ProcessDefinition> pd2 = map.get(pd.getKey());//从map中将该key对应的值查出来
//				pd2.add(pd);
//			}else{//不存在
//				List<ProcessDefinition> pd3 = new ArrayList<ProcessDefinition>();
//				pd3.add(pd);
//				map.put(pd.getKey(), pd3);
//			}
//		}
		
		/* 遍历每一个流程定义，如果存在相同的key值，后者覆盖前者，也就意味着高版本的覆盖低版本的 */
		Map<String, ProcessDefinition> map = new HashMap<String, ProcessDefinition>();
		for(ProcessDefinition pd:pdList){
			map.put(pd.getKey(), pd);
		}
		  		 
		for (ProcessDefinition pd : map.values()) {
			 System.out.println("deploymentId:" + pd.getDeploymentId());
			 System.out.println("key:" + pd.getKey());
			 System.out.println("version:" + pd.getVersion());
			 System.out.println("imageResourceName:" + pd.getImageResourceName());
			 System.out.println("description:" + pd.getDescription());
			 }	
		 
		return map.values();
	}
	//部署
	@Override
	public void deploy(File resource) throws Exception{
		InputStream inputStream = new FileInputStream(resource);
		ZipInputStream zipInputStream = new ZipInputStream(inputStream);
		this.processEngine
		.getRepositoryService()
		.createDeployment()
		.addResourcesFromZipInputStream(zipInputStream)
		.deploy();
	}
	//删除流程定义
	@Override
	public void deletePD(String key) {
		//根据key获取该key下面的所有的流程定义
		List<ProcessDefinition> pdList = this.processEngine
		.getRepositoryService()
		.createProcessDefinitionQuery()
		.processDefinitionKey(key)
		.list();
		for(ProcessDefinition pd:pdList){
			/**
			 * 根据每一个流程定义获取到deploymentID,然后进行删除
			 */
			this.processEngine.getRepositoryService().deleteDeploymentCascade(pd.getDeploymentId());
		}
	}
	//显示流程图
	@Override
	public InputStream showImage(String deploymentId) {
		/**
		 * 1、根据deploymentID和图片的路径把图片加载出来
		 * 2、把图片加载出来以后，产生输入流
		 * 3、把输入流的内容输出到界面上
		 */
		//1、根据deploymentID获取流程定义
		ProcessDefinition pd = this.processEngine.getRepositoryService()
		.createProcessDefinitionQuery()
		.deploymentId(deploymentId)
		.uniqueResult();
		//2、获取图片的输入流
		return this.processEngine.getRepositoryService().getResourceAsStream(deploymentId, pd.getImageResourceName());
	}
}
