package cn.wang.oaA.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import cn.wang.oaA.dao.base.BaseDao;
/**
 * BaseDao<E> 的泛型需要 BaseDaoImpl<E>来传递
 */
public  class BaseDaoImpl<E> implements BaseDao<E>{
	/**
	 * 通过注解的方式引入hibernateTemplate
	 */
	@Resource(name="hibernateTemplate")
	public HibernateTemplate hibernateTemplate;
	
	/**
	 * 定义实体的实际类型
	 */
    private Class classt;
	
    /**
	 * 获取到E代表的持久化类的数据字典--->可以推导出来当前的实体bean的标示符的名称
	 */
	private ClassMetadata classMetadata;
	
	/**
	 *  当调用完成构造函数之后，立刻执行该init方法
	 */
	@PostConstruct
	public void init(){
		this.classMetadata = this.hibernateTemplate.getSessionFactory().getClassMetadata(this.classt);
	}
    
	public BaseDaoImpl(){
		/**
		 * 1、this可以代表子类或者本类
		 * 2、不能把BaseDaoImpl在spring容器中实例化，因为如果在spring容器中实例化，则就不是泛型了
		 *   ****
		 *    因为不能把BaseDaoImpl实例化 
		 *    所以 this 肯定代表的是 BaseDaoImpl的子类
		 *   ****
		 * 3、所以根据以上两点可以得出this应该代表子类（例如PersonDaoImpl）
		 *   ****
		 *     this 代表的是   BaseDaoImpl<E>的子类 例如 PersonDaoImpl
		 *     this.getClass().getGenericSuperclass()获取的就是 BaseDaoImpl<E>本身的ParameterizedType
		 *   **** 
		 * 4、this.getClass().getGenericSuperclass()：就是当前类的ParameterizedType
		 *   **** 
		 *    通过getGenericSuperclass方法可以获取当前对象的直接超类的 ParameterizedType
		 *    只有当  BaseDaoImpl<E> 是一个泛型类的时候才能利用 getGenericSuperclass方法 得到泛型
		 *    只有当  BaseDaoImpl<E> 创建实例对象的时候只有把实参传递给E ，BaseDaoImpl<E> 它才是泛型 
		 *   ****
		 * 5、注意：不能把BaseDaoImpl让spring容器实例化
		 */
		ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
		/**
		 * getActualTypeArguments()该方法告诉程序员E的实际类型
		 * 返回值是一个数组 因为 泛型可以写多个
		 */
		//因为将来E代表的是某一个持久化类，而该类型是class
		this.classt = (Class) type.getActualTypeArguments()[0];

		/*
		 * this.classt.getName():
				cn.wang.oaA.domain.system.Department
				cn.wang.oaA.domain.system.User
				cn.wang.oaA.domain.menuitem.Menuitem
				cn.wang.oaA.domain.Person
				cn.wang.oaA.domain.system.Privilege
				cn.wang.oaA.domain.system.Role
				cn.wang.oaA.domain.system.User
		 */
	}
	
	/**
	 * this.classt.getName() 代表持久化类
	 */
	@Override
	public Collection<E> getAllEntry() {
		
		return this.hibernateTemplate.find("from "+this.classt.getName());
	}

	@Override
	public E getEntryById(Serializable id) {
		/**
		 * classMetadata是持久化类的数据字典
		 * classMetadata.getIdentifierPropertyName() 得到标识符属性的名字
		 * 根据主键查
		 * 最后要强转成 E 当前的实体类
		 */
		return (E)this.hibernateTemplate.find("from "+this.classt.getName() 
				+" where " 
				+classMetadata.getIdentifierPropertyName() 
				+"=?",id).get(0);
	}

	@Override
	public void saveEntry(E e) {
		this.hibernateTemplate.save(e); 
	}

	@Override
	public void deleteEntry(Serializable id) {
		E e = this.getEntryById(id);
		this.hibernateTemplate.delete(e);
	}

	@Override
	public void updateEntry(E e) {
		this.hibernateTemplate.update(e);
	}
	
	@Override
	public Set<E> getEntrysByIDS(Serializable[] ids) {
		/**
		 * 1、如果数组只有一个元素
		 * 2、如果数组中有两个或者两个以上的元素
		 *    from Person where pid in(1,2,3,4)
		 */
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("from "+this.classt.getName());//this.classt.getName()持久化类的名字
		stringBuffer.append(" where "+this.classMetadata.getIdentifierPropertyName());
		stringBuffer.append(" in (");
		for(int i=0;i<ids.length;i++){
			if(i==ids.length-1){//如果是最后一个
				stringBuffer.append(ids[i]);
			}else{
				stringBuffer.append(ids[i]+",");
			}
		}
		stringBuffer.append(")");
		List<E> list = this.hibernateTemplate.find(stringBuffer.toString());
		return new HashSet<E>(list);//将 list转化成set
	}

	@Override
	public Set<E> getEntrysByIDS(String ids) {
		if(ids== null || ids.length() <= 0) 
		{
			return new HashSet<E>();
		}
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("from "+this.classt.getName());
		stringBuffer.append(" where "+this.classMetadata.getIdentifierPropertyName());
		stringBuffer.append(" in(");
		stringBuffer.append(ids);
		stringBuffer.append(")");
		List<E> list = this.hibernateTemplate.find(stringBuffer.toString());
		return new HashSet<E>(list);//将 list转化成set
		
	}
    
	/**
	 * final String... objects  可变参数
	 */
	@Override
	public E getEntryByCondition(final String hql,final String... objects) {
		return this.hibernateTemplate.execute(new HibernateCallback<E>() {
			@Override
			public E doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				int index = 0;
				for(String s:objects){
					query.setParameter(index, s);
					index++;
				}
				return (E)query.uniqueResult();
			}
		});
	}
}
