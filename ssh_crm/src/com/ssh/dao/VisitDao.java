package com.ssh.dao;

import java.util.List;

import com.ssh.entity.Visit;

public interface VisitDao {

	void addVisit(Visit visit);

	List<Visit> findAll();

}
