package com.ssh.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ssh.dao.CustomerDao;
import com.ssh.entity.Customer;
import com.ssh.entity.Dict;
import com.ssh.entity.PageBean;

@Transactional
public class CustomerService {
	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public void add(Customer customer) {
		customerDao.add(customer);
		
	}

	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return customerDao.findAll();
		
	}

	public Customer findOne(int cid) {
		// TODO Auto-generated method stub
		return customerDao.findOne(cid);
	}

	public void delete(Customer c) {
		// TODO Auto-generated method stub
		customerDao.delete(c);
	}

	public void update(Customer customer) {
		// TODO Auto-generated method stub
		customerDao.update(customer);
	}

	public PageBean listPage(Integer currentPage) {
		PageBean pageBean = new PageBean();
		//每页显示数量
		int pageSize = 3;
		pageBean.setCurrentPage(currentPage);
		
		int totalCount = customerDao.findCount();
		pageBean.setTotalCount(totalCount);
		
		int totalPage = 0;
		if(totalCount % pageSize  == 0) {
			totalPage = totalCount / pageSize;
		}else {
			totalPage = totalCount / pageSize + 1;
		}
		
		pageBean.setTotalPage(totalPage);
		
		//起始数据位置
		int begin = (currentPage-1)*pageSize;
		pageBean.setBegin(begin);
		
		List<Customer> list = customerDao.findPage(begin,pageSize); 
		pageBean.setList(list);
		
		return pageBean;
		
	}

	public List<Customer> findCondition(Customer customer) {
		List<Customer> list = customerDao.findCondition(customer);
		return list;
	}

	public List<Customer> findMoreCondition(Customer customer) {
		return customerDao.findMoreCondition(customer);
		
	}

	public List<Dict> findAllDict() {
		// TODO Auto-generated method stub
		return customerDao.findDict();
	}

	public List findCountSource() {
		// TODO Auto-generated method stub
		return customerDao.findCountSource();
	}

	public List findCountLevel() {
		// TODO Auto-generated method stub
		return customerDao.findCountLevel();
	}
}
