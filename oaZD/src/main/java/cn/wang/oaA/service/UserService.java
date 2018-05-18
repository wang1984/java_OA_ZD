package cn.wang.oaA.service;

import java.io.Serializable;
import java.util.Collection;

import cn.wang.oaA.domain.system.User;



public interface UserService {
	/**
	 * 查询所有的用户
	 * @return
	 */
	public Collection<User> getAllUser();
	
	/**
	 * 保存用户
	 */
	public void saveUser(Long did,Long[] rids,User user);
	
	/**
	 * 修改用户
	 * @param user
	 */
	public void updateUser(Long did,Long[] rids,User user);
	public void updateUser(User user);
	/**
	 * 删除用户
	 */
	public void deleteUser(Serializable id);
	
	/**
	 * 根据id查询user
	 */
	public User getUserById(Serializable id);
}
