package com.ssh.dao;
/**
 * 
 * @author zyd
 *
 * @param <T>
 */

import java.util.List;

public interface BaseDao <T>{
//	添加
	void add(T t);
	//修改
	void update(T t);
	//删除
	void delete(T t);
	//根据id查询
	T findOne(int i);
	//查询所有
	List<T> findAll();
}
