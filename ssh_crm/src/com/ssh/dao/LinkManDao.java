package com.ssh.dao;

import java.util.List;

import com.ssh.entity.LinkMan;

public interface LinkManDao {

	void add(LinkMan linkMan);

	List<LinkMan> list();

	LinkMan findOne(int lid);

	void update(LinkMan linkMan);

	List<LinkMan> findCondition(LinkMan linkMan);

}
