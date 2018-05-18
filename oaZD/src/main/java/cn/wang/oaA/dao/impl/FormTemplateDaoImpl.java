package cn.wang.oaA.dao.impl;
 
import org.springframework.stereotype.Repository;

import cn.wang.oaA.dao.FormTemplateDao;
import cn.wang.oaA.dao.base.impl.BaseDaoImpl;
import cn.wang.oaA.domain.workflow.FormTemplate;

@Repository("formTemplateDao")
public class FormTemplateDaoImpl extends BaseDaoImpl<FormTemplate> implements FormTemplateDao{

}
