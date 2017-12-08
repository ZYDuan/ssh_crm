package com.ssh.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;


public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	private Class tClass;
	//构造方法
	public BaseDaoImpl() {
		//得到当前运行到class
		Class cla = this.getClass();
		
		//得到运行类的父类到参数化类型BaseDaoImpl<T>
		//Type getGenericSuperClass得到父类（Type为接口）
		Type type = cla.getGenericSuperclass();
		
		//使用Type的子接口 ParamterizedType
		ParameterizedType pType = (ParameterizedType) type;
		
		//得到实际类型参数<t>中的T
		//Type[] getActualTypeArguments()
		Type[] types = pType.getActualTypeArguments();
		
		//Type的实现类就是class
		this.tClass = (Class) types[0];
	}

	@Override
	public void add(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void update(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(t);
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public T findOne(int i) {
		
		return (T) this.getHibernateTemplate().get(tClass, i);
	}

	@Override
	public List<T> findAll() {
		// 使用class里面的getSimpleName获得类的名字
		return (List<T>) this.getHibernateTemplate().find("from "+ tClass.getSimpleName());
	}

}
