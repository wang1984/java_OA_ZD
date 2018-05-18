package cn.wang.oaA.dao.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
/*
 * 对实体对象进行增删改查
 */
public interface BaseDao<E> {
	/**
	 * 得到E代表的所有的实体对象
	 * @return
	 */
	public Collection<E> getAllEntry();
	
	/**
	 * Type 和 Object都太大了
	 * Serializable该类型可以接受所有的基本类型和String类型
	 * 所有的基本类型的包装类全部实现了Serializable接口所以可以使用Serializable来接收
	 * 它可以接受所有的持久化类的主键
	 * @param id
	 */
	public E getEntryById(Serializable id);
	
	public void saveEntry(E e);
	
	public void deleteEntry(Serializable id);
	
	public void updateEntry(E e);
	
	/**
	 * 根据一个或者一个以上的id，返回一个Set<E>
	 */
	public Set<E> getEntrysByIDS(Serializable[] ids);
	public Set<E> getEntrysByIDS(String ids);
	
	public E getEntryByCondition(final String entityName,final String... objects);
}
