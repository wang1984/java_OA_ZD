package cn.wang.oaA.service.impl;



import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wang.oaA.dao.FormTemplateDao;
import cn.wang.oaA.domain.workflow.FormTemplate;
import cn.wang.oaA.service.FormTemplateService;

import com.opensymphony.xwork2.ActionContext;

@Service("formTemplateService")
public class FormTemplateServiceImpl implements FormTemplateService{
	@Resource(name="formTemplateDao")
	private FormTemplateDao formTemplateDao;
   /*
     	查看模板
    */
	@Override
	public Collection<FormTemplate> getAllFormTemplate() { 
		return this.formTemplateDao.getAllEntry();
	}
    /*
     	保存模板
     */
	@Transactional(readOnly=false)
	public void saveFormTemplate(FormTemplate formTemplate) {
		 
		this.formTemplateDao.saveEntry(formTemplate);
	}
    /*
     	下载模板
     */
	@Override
	public InputStream download(Long ftid) throws Exception{
		FormTemplate formTemplate = this.formTemplateDao.getEntryById(ftid);
		String url = formTemplate.getUrl();
		//因为在struts配置文件中也要使用 fileName，所以要将fileName放到值栈中
		String fileName = formTemplate.getName();
		fileName = URLEncoder.encode(fileName, "utf-8");
		ActionContext.getContext().put("fileName", fileName);
		return new FileInputStream(new File(url));
	}
}
