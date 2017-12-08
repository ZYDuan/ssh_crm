package com.ssh.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ssh.entity.Customer;
import com.ssh.entity.Dict;
import com.ssh.entity.PageBean;
import com.ssh.service.CustomerService;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	private CustomerService customerService;
	private Customer customer = new Customer();

	private Integer currentPage;
	
	@Override
	public Customer getModel() {
		return customer;
		
	}
	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	
	private List<Customer> list;
	public List<Customer> getList() {
		return list;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	public String toAddPage() {
		List<Dict> listDict = customerService.findAllDict();
		ServletActionContext.getRequest().setAttribute("listDict", listDict);
		return "toAddPage";
	}
	
//	添加客户
	public String add() {
		customerService.add(customer);
		return "add";
	}

	
//	获取客户信息形成表单
	public String list() {
//		List<Customer> list = customerService.findAll();
//		//域对象
//		ServletActionContext.getRequest().setAttribute("list",list);
		//放在值栈
		list = customerService.findAll();
		return "list";
	}
	
	//删除表单内容
	public String delete() {
//		使用模型驱动中获取表单提交的cid
		int cid = customer.getCid();
		Customer c = customerService.findOne(cid);
		if(c != null) {
			customerService.delete(c);
		}
		return "delete";
	}
	
//	修改表单内容
	public String showCustomer() {
		int cid = customer.getCid();
		
		Customer c = customerService.findOne(cid);
		if(c != null) {
			ServletActionContext.getRequest().setAttribute("customer", c);
		}
		return "show";
	}
	
	//修改表单
	public String update() {
		customerService.update(customer);
		return "update";
	}
	
	//分页
	public String listpage() {
		PageBean pageBean = customerService.listPage(currentPage);
		ServletActionContext.getRequest().setAttribute("pageBean",pageBean);
		return "listpage";
		
	}
	
	//条件查询
	public String listcondition() {
		if(customer.getCustName() !=null && !("").equals(customer.getCustName())) {
			//输入不为空
			List<Customer> list = customerService.findCondition(customer);
			ServletActionContext.getRequest().setAttribute("list", list);
		}else {
//			不输入内容，显示全部
			list();
		}
		return "listcondition";
	}
	
//	到客户综合查询页面
	public String toSelectCustomerPage() {
		return "toSelectCustomerPage";
	}
	
	public String moreCondition() {
		List<Customer> list = customerService.findMoreCondition(customer);
		ServletActionContext.getRequest().setAttribute("list", list);
		return "moreCondition";
	}
	
	public String countSource() {
		List list = customerService.findCountSource();
		
		ServletActionContext.getRequest().setAttribute("list", list);
		return "countSource";
	}
	
	public String countLevel() {
		List list = customerService.findCountLevel();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "countLevel";
	}
}
