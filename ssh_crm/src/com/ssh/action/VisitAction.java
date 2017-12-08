package com.ssh.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ssh.entity.Customer;
import com.ssh.entity.User;
import com.ssh.entity.Visit;
import com.ssh.service.CustomerService;
import com.ssh.service.UserService;
import com.ssh.service.VisitService;

public class VisitAction extends ActionSupport implements ModelDriven<Visit>{
	private VisitService visitService;
	private Visit visit = new Visit();
	
	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}
	
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	//到添加页面
	public String toAddPage() {
		List<Customer> listCustomer = customerService.findAll();
		List<User> listUser = userService.findAll();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listCustomer", listCustomer);
		request.setAttribute("listUser", listUser);
		
		return "toAddPage";
	}
	
//	添加visit
	public String addVisit() {
		visitService.addVisit(visit);
		return "addVisit";
	}

//	列表
	public String list() {
		List<Visit> listVisit = visitService.findAll();
		ServletActionContext.getRequest().setAttribute("list", listVisit);
		return "list";
	}
@Override
public Visit getModel() {
	
	return visit;
}
}
