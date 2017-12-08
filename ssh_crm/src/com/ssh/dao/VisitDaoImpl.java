package com.ssh.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.ssh.entity.Visit;

public class VisitDaoImpl extends HibernateDaoSupport implements VisitDao {

	@Override
	public void addVisit(Visit visit) {
		this.getHibernateTemplate().save(visit);
		
	}

	@Override
	public List<Visit> findAll() {
		
		return (List<Visit>) this.getHibernateTemplate().find("from Visit");
	}

}
