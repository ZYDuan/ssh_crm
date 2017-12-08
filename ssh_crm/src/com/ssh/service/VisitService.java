package com.ssh.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ssh.dao.VisitDao;
import com.ssh.entity.Visit;

@Transactional
public class VisitService {
	private VisitDao visitDao;

	public void setVisitDao(VisitDao visitDao) {
		this.visitDao = visitDao;
	}

	public void addVisit(Visit visit) {
		visitDao.addVisit(visit);
		
	}

	public List<Visit> findAll() {
		
		return visitDao.findAll();
	}
}
