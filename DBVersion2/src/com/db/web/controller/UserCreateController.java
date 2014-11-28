package com.db.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.db.persistance.dto.Administrator;
import com.db.persistance.dto.Patient;
import com.db.persistance.dto.PersonBean;
import com.db.persistance.dto.UserAccountBean;
import com.db.persistance.dto.UserAdminBean;
import com.db.persistance.exception.LoginFailureException;
import com.db.persistance.impl.LoginDAOImpl;


@Controller
public class UserCreateController {

	

	@RequestMapping(method=RequestMethod.GET,value="/login.html")
	public String loginPage(Map model)
	{
		model.put("userAccount",new UserAccountBean());
		return "loginUser";
	}
		
	
	@RequestMapping(method=RequestMethod.POST,value="/login.html")
	public String loginUser(Map model,UserAccountBean ubean,HttpServletRequest request)
	{
		String returnURL=null;
		LoginDAOImpl loginDAOimpl=null;
		try
		{			
			if("PATIENT".equals(ubean.getPersonType().toUpperCase()))
			{
				loginDAOimpl=new LoginDAOImpl();
				UserAccountBean accountBean=loginDAOimpl.verifyLogin(ubean.getUserName(), ubean.getPassword());
				PersonBean person=loginDAOimpl.getUserDetails(accountBean);
				request.getSession().setAttribute("selectedPersonId",accountBean.getAccountID());
				return "redirect:updatePatient.html";
			}
			else if("PHYSICIAN".equals(ubean.getPersonType().toUpperCase()))
			{
				model.put("userAccount", ubean);
				returnURL="physicianUser";
			}
			else if("ADMIN".equals(ubean.getPersonType().toUpperCase()))
			{
				Administrator admin=new Administrator();
				loginDAOimpl=new LoginDAOImpl();
				List<PersonBean> userList=loginDAOimpl.getUserInfo("ALL","SELECT","");
				admin.setUserList(userList);
				model.put("userInfo", admin);
				request.getSession().setAttribute("userList", userList);		
				returnURL="adminUser";
				return returnURL;
			}	

			loginDAOimpl=new LoginDAOImpl();
			UserAccountBean accountBean=loginDAOimpl.verifyLogin(ubean.getUserName(), ubean.getPassword());
			PersonBean person=loginDAOimpl.getUserDetails(accountBean);
			model.put("userInfo", person);
			
		}
		catch(LoginFailureException exception)
		{
			request.setAttribute("invalidLogin","true");
			model.put("userAccount", ubean);
			returnURL="loginUser";
			return returnURL;
		}
		return returnURL;
	}
	@RequestMapping(method=RequestMethod.POST,value="/processAdmin.html")
	public String processAdminRequest(Map model,Administrator admin,HttpServletRequest request)
	{
		LoginDAOImpl loginDAOimpl=null;
		String returnURL=null;
		String goButton=request.getParameter("Go");
		String deleteButton=request.getParameter("Delete");
		String personType=admin.getPersonType();
		String lastName=admin.getSearchName();
		String searchButton=request.getParameter("search");
		String register=request.getParameter("register");
		String update=request.getParameter("update");
		List<PersonBean> userList=null;
		if(register!=null)
		{
			
			return "redirect:registerNewPatient.html";
		}
		else if(update!=null)
		{
			List<PersonBean> userAdminList=(List<PersonBean>)request.getSession().getAttribute("userList");
			for(int i=0;i<admin.getUserList().size();i++)
			{
				PersonBean temp=admin.getUserList().get(i);
				if(temp!=null && temp.isChecked())
				{					
					request.getSession().setAttribute("selectedPersonId",userAdminList.get(i).getPersonId());
					break;
				}	
			}
			return "redirect:updatePatient.html";
		}
		else if(searchButton!=null && lastName!=null && !"".equals(lastName.trim()))
		{
			loginDAOimpl=new LoginDAOImpl();
			userList=loginDAOimpl.getUserInfo(personType,"SEARCH",lastName.trim());
		}
		else if(goButton!=null)
		{
			loginDAOimpl=new LoginDAOImpl();
			userList=loginDAOimpl.getUserInfo(personType,"SELECT","");
		}	
		else if(deleteButton!=null)
		{
			loginDAOimpl=new LoginDAOImpl();
			List<PersonBean> userAdminList=(List<PersonBean>)request.getSession().getAttribute("userList");
			List<Long> personIDList=new ArrayList<Long>();
			for(int i=0;i<admin.getUserList().size();i++)
			{
				PersonBean temp=admin.getUserList().get(i);
				if(temp!=null && temp.isChecked())
				{					
					personIDList.add(userAdminList.get(i).getPersonId());
				}	
			}	
			Long[] longArray=new Long[personIDList.size()];
			longArray=personIDList.toArray(longArray);
			loginDAOimpl.deleteUser(longArray);
			userList=loginDAOimpl.getUserInfo(personType,"SELECT","");
		}	
		request.getSession().setAttribute("userList", userList);
		admin.setUserList(userList);
		model.put("userInfo", admin);		
		return "adminUser";
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/*/logout.html")
	public String logoutUser(Map model,HttpServletRequest request)
	{
		request.getSession().invalidate();
		return "loginConfirm";
	}
	
		
	
}
