package cn.wang.oaA.dao.impl;


import org.springframework.stereotype.Repository;

import cn.wang.oaA.dao.FormDao;
import cn.wang.oaA.dao.base.impl.BaseDaoImpl;
import cn.wang.oaA.domain.workflow.Form;

@Repository("formDao")
public class FormDaoImpl extends BaseDaoImpl<Form> implements FormDao{

}
