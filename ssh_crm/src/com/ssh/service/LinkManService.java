package com.ssh.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ssh.dao.LinkManDao;
import com.ssh.entity.LinkMan;

@Transactional
public class LinkManService {
	private LinkManDao linkManDao;

	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}

	public void addLinkMan(LinkMan linkMan) {
		linkManDao.add(linkMan);
		
	}

	public List<LinkMan> listLinkMan() {
		
		return linkManDao.list();
	}

	public LinkMan findOne(int lid) {
		return linkManDao.findOne(lid);
	}

	public void updateLink(LinkMan linkMan) {
		linkManDao.update(linkMan);
		
	}

	public List<LinkMan> findCondition(LinkMan linkMan) {
		// TODO Auto-generated method stub
		return linkManDao.findCondition(linkMan);
	}

}
