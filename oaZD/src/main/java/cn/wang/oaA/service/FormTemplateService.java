package cn.wang.oaA.service;



import java.io.InputStream;
import java.util.Collection;

import cn.wang.oaA.domain.workflow.FormTemplate;

public interface FormTemplateService {
	public Collection<FormTemplate> getAllFormTemplate();
	
	public void saveFormTemplate(FormTemplate formTemplate);
	
	public InputStream download(Long ftid) throws Exception;
}
