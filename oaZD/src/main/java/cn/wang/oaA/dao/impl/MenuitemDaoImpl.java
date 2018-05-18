package cn.wang.oaA.dao.impl;

import org.springframework.stereotype.Repository;

import cn.wang.oaA.dao.MenuitemDao;
import cn.wang.oaA.dao.base.impl.BaseDaoImpl;
import cn.wang.oaA.domain.menuitem.Menuitem;
 

@Repository("menuitemDao")
public class MenuitemDaoImpl extends BaseDaoImpl<Menuitem> implements MenuitemDao{

}
