package com.ssh.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.Query;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.ssh.entity.Customer;
import com.ssh.entity.Dict;
import com.sun.org.apache.xalan.internal.xsltc.cmdline.Transform;

import sun.net.www.content.text.plain;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

//	public void add(Customer customer) {
//		this.getHibernateTemplate().save(customer);
//		
//	}
//
//	@Override
//	public List<Customer> findAll() {
//		List<Customer> list = (List<Customer>) this.getHibernateTemplate().find("from Customer");
//		return list;
//		
//	}
//
//	@Override
//	public Customer findOne(int cid) {
//		Customer c = (Customer) this.getHibernateTemplate().get(Customer.class, cid);
//		return c;
//	}
//
//	@Override
//	public void delete(Customer c) {
//		// TODO Auto-generated method stub
//		this.getHibernateTemplate().delete(c);
//	}
//
//	@Override
//	public void update(Customer customer) {
//		this.getHibernateTemplate().update(customer);
//		
//	}

	

	@Override
	public int findCount() {
		List<Object> list = (List<Object>) this.getHibernateTemplate().find("select count(*) from Customer");
		if(list != null && list.size() != 0) {
			Object obj = list.get(0);
			Long lobj = (Long) obj;
			int Count = lobj.intValue();
			return Count;
		}else {
			return 0;
		}
		
	}

	@Override
	public List<Customer> findPage(int begin, int pageSize) {
//		SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
//		org.hibernate.Query query = session.createQuery("from Customer");
//		query.setFirstResult(begin);
//		query.setMaxResults(pageSize);
		
		//使用离线对象实现	
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
		return list;
	}

	@Override
	public List<Customer> findCondition(Customer customer) {
//		方法一，底层方法   (?是模糊查询)
//		SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
//		org.hibernate.Query query = session.createQuery("from Customer where custName like ?");
//		query.setParameter(0, "%"+customer.getCustName()+"%");
//		List<Customer> list = query.list();
		
		//方法二：调用hibernatetemplate到find方法
//		List<Customer> list = (List<Customer>) this.getHibernateTemplate().find("from Customer where custName like ?",  "%"+customer.getCustName()+"%");
		
		//方法三：调用离线对象
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		detachedCriteria.add(Restrictions.like("custName", "%"+customer.getCustName()+"%"));
		List<Customer> list = (List<Customer>) (this.getHibernateTemplate().findByCriteria(detachedCriteria));
		return list;
	}

	@Override
	public List<Customer> findMoreCondition(Customer customer) {
//		String hql="from Customer where 1=1";
//		List<Object> pArrayList = new ArrayList<>();
//		if(customer.getCustName()!=null && !"".equals(customer.getCustName())) {
//			hql += " and CUSTNAME=?";
//			pArrayList.add(customer.getCustName());
//		}
//		if(customer.getCustLevel()!=null && !"".equals(customer.getCustLevel())) {
//			hql += " and CUSTLEVEL=?";
//			pArrayList.add(customer.getCustLevel());
//		}
//		if(customer.getCustSource()!=null && !"".equals(customer.getCustSource())) {
//			hql += " and CUSTSOURCE=?";
//			pArrayList.add(customer.getCustSource());
//		}
//		return (List<Customer>) this.getHibernateTemplate().find(hql, pArrayList.toArray());
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		if(customer.getCustName()!=null && !"".equals(customer.getCustName())) {
			criteria.add(Restrictions.eq("custName", customer.getCustName()));
		}
		if(customer.getDictCustLevel().getDname()!=null && !"".equals(customer.getDictCustLevel().getDname())) {
			criteria.add(Restrictions.eq("custLevel", customer.getDictCustLevel()));
		}
		if(customer.getCustSource()!=null && !"".equals(customer.getCustSource())) {
			
			criteria.add(Restrictions.eq("custSource", customer.getCustSource()));
		}
		
		return (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public List<Dict> findDict() {
		
		return (List<Dict>) this.getHibernateTemplate().find("from Dict");
	}

	@Override
	public List findCountSource() {
		Session session = this.getSessionFactory().getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery("SELECT COUNT(*) AS num, CUSTSOURCE FROM t_customer GROUP BY CUSTSOURCE");
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		List list = sqlQuery.list();
		return list;
	}

	@Override
	public List findCountLevel() {
		Session session = this.getSessionFactory().getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery("SELECT c.num, d.dname FROM (SELECT COUNT(*) AS num, CUSTLEVEL FROM t_customer GROUP BY CUSTLEVEL) c, t_dict d WHERE c.CUSTLEVEL = d.DID");
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		List list = sqlQuery.list();
		return list;
	} 

	
}
