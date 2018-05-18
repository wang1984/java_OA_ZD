package cn.wang.oaA.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.wang.oaA.dao.MenuitemDao;
import cn.wang.oaA.domain.menuitem.Menuitem;
import cn.wang.oaA.service.MenuitemService;



@Service("menuitemService")
public class MenuitemServiceImpl implements MenuitemService{
	@Resource(name="menuitemDao")
	private MenuitemDao menuitemDao;

	@Override
	public Collection<Menuitem> getAllMenuitem() {
		return this.menuitemDao.getAllEntry();
	}
}
