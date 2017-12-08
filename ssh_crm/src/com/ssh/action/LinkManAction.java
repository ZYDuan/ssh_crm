package com.ssh.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ssh.entity.Customer;
import com.ssh.entity.LinkMan;
import com.ssh.service.CustomerService;
import com.ssh.service.LinkManService;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{
	private LinkManService linkManService;
	private LinkMan linkMan =  new LinkMan();
	
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
//	到添加页面
	public String toAddPage() {
		List<Customer> listCustomer = customerService.findAll();
		ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
		return "toAddPage";
	}
	
	/*
	 * 需要上传文件流
	 * 文件名称
	 * （1）在action里面成员变量位置定义变量（命名规范）：一个代表上传文件；一个代表文件名称
	 * （2）生成getset
	 * （还有一个变量，上传文件的mime类型：不需要自己设置；tomcat对正常后缀名自己的配置）
	 */
	//上传文件：变量名称是表单内容中上传项的name值
	private File upload;
	//文件名称：表单内容中name值+FileName
	private String uploadFileName;
	
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	//	完成添加动作
	public String addLinkMan() {
//		判断是否上传文件
		if(upload != null){
			File serviceFile = new File("/Users/zyd/serviceFile/"+uploadFileName);
			try {
				FileUtils.copyFile(upload, serviceFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*
		 * 可以封装联系人到基本信息
		 * 但是有cid是客户到id不能直接封装,把cid封装到customer属性中
		 * */
//		方法一：原始方式实现
//		String cString = ServletActionContext.getRequest().getParameter("cid");
//		int cid = Integer.parseInt(cString);
//		Customer cus = new Customer();
//		cus.setCid(cid);
//		linkMan.setCustomer(cus);
		
		linkManService.addLinkMan(linkMan);
		return "addLinkMan";
	}
	
//	联系人列表
	public String list() {
		List<LinkMan> list = linkManService.listLinkMan();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
	
//	到修改页面
	public String showLinkMan() {
		int lid = linkMan.getLinkid();
		LinkMan l = linkManService.findOne(lid);
		if(l != null) {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("linkman", l);
		}
		
		List<Customer> listCustomer = customerService.findAll();
		ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
		return "showLinkMan";
	}
	
	//修改数据
	public String updateLinkMan() {
		linkManService.updateLink(linkMan);
		return "updateLinkMan";
	}
	
	public String toSelectPage() {
		List<Customer> list = customerService.findAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "toSelectPage";
	}
	
	public String moreCondition() {
		List<LinkMan> list = linkManService.findCondition(linkMan);
		ServletActionContext.getRequest().setAttribute("list", list);
		return "moreCondition";
	}
@Override
public LinkMan getModel() {
	return linkMan;
}
}
