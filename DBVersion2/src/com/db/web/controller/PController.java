package com.db.web.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.db.persistance.dto.Administrator;
import com.db.persistance.dto.Emergency;
import com.db.persistance.dto.Insurance;
import com.db.persistance.dto.Patient;
import com.db.persistance.dto.PersonBean;
import com.db.persistance.dto.UserAccountBean;
import com.db.persistance.impl.LoginDAOImpl;

@Controller
public class PController {

@RequestMapping("/registerNewPatient.html")
		public ModelAndView newPatient(){
	Patient patient = new Patient();
	return (new ModelAndView("newPatient","patient",patient));
}

@RequestMapping("/addNewPatient")
public ModelAndView addPatient(@ModelAttribute Patient p,HttpSession session) throws ClassNotFoundException, SQLException{
	//session.setAttribute("patient",p);
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection conn = DriverManager.getConnection(LoginDAOImpl.URL,LoginDAOImpl.USERNAME,LoginDAOImpl.PASSWORD);
	Statement sta = conn.createStatement();
	ResultSet rs = sta.executeQuery("Select Person_seq.NextVal From dual");
	rs.next();
	long id = rs.getLong("NextVal");
	sta.close();
	
	p.setPersonId(id);
	PreparedStatement st = conn.prepareStatement("Insert Into Person(Person_Id,Person_first_name,person_last_name,person_gender,person_BirthDate,person_Address,person_city,person_state,person_zip,person_country,person_phone,person_email,person_type,Person_UserName,Person_Password)" +
			                                      "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,fin_proj_pkg2.encryptPassword('"+p.getAccountBean().getPassword()+"'))");
	st.setLong(1,id);
	st.setString(2,p.getFname());
	st.setString(3,p.getLname());
	st.setString(4,p.getGender());
	st.setString(5,p.getBirthdate());
	st.setString(6,p.getAddress());
	st.setString(7,p.getCity());
	st.setString(8,p.getState());
	st.setString(9,p.getZip());
	st.setString(10,p.getCountry());
	st.setString(11,p.getPhone());
	st.setString(12,p.getEmail());
	st.setString(13,"PA");
	st.setString(14,p.getAccountBean().getUserName());
	//st.setString(15,p.getUserAccountBean().getPassword());
	st.executeUpdate();
	st.close();
	
	PreparedStatement st1 = conn.prepareStatement("Insert Into PATIENT_EMERG_CONTACT(patient_Id,First_Name,Last_Name,relation,Address,city,state,zip,phone,email)" +
    "values(?,?,?,?,?,?,?,?,?,?)");
st1.setLong(1,id);
st1.setString(2,p.getEmergency().getFname());
st1.setString(3,p.getEmergency().getLname());
st1.setString(4,p.getEmergency().getRelation());
st1.setString(5,p.getEmergency().getAddress());
st1.setString(6,p.getEmergency().getCity());
st1.setString(7,p.getEmergency().getState());
st1.setString(8,p.getEmergency().getZip());
st1.setString(9,p.getEmergency().getPhone());
st1.setString(10,p.getEmergency().getEmail());

st1.executeUpdate();
st1.close();

PreparedStatement st2 = conn.prepareStatement("Insert Into PATIENT_INS(patient_Id,Policy_Nbr,company_name,group_nbr,relation)" +
"values(?,?,?,?,?)");
st2.setLong(1,id);
st2.setString(2,p.getInsurance().getPolicyNbr());
st2.setString(3,p.getInsurance().getCompany());
st2.setString(4,p.getInsurance().getGroupId());
st2.setString(5,p.getInsurance().getRelation());

st2.executeUpdate();
st2.close();

//PreparedStatement st3 = conn.prepareStatement("Insert Into UserAccount(Account_Id,User_Name,Password,Person_Type) Values(UserAccount_Seq.NextVal,?,?,?)");
//st3.setString(1,p.getUserAccountBean().getUserName());
//st3.setString(2,p.getUserAccountBean().getPassword());
//st3.setString(3,"PATIENT");
//
//st3.executeUpdate();
//st3.close();
conn.close();

return (new ModelAndView("registerSuccess","patient",p));
	
}
//**patient directory
@RequestMapping("/adminUser.html")
public ModelAndView persondirectory(HttpServletRequest req, HttpServletResponse res) throws ClassNotFoundException, SQLException{
	Administrator admin=new Administrator();
	
	LoginDAOImpl loginDAOimpl=new LoginDAOImpl();
	List<PersonBean> userList=loginDAOimpl.getUserInfo("ALL","SELECT","");
	admin.setUserList(userList);
	
	req.getSession().setAttribute("userList", userList);
	
	return (new ModelAndView("adminUser","userInfo",admin));
}
/*//**for delete patient
@RequestMapping("/deletePatient.html")
public ModelAndView deletePatient(HttpServletRequest req, HttpServletResponse res) throws ClassNotFoundException, SQLException{
	long patientId = Long.parseLong(req.getParameter("patientId"));
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","adb","000786");
	PreparedStatement st = conn.prepareStatement("Delete From Person Where Person_Id=?");
	st.setLong(1,patientId);
	st.executeUpdate();
	st.close();
	conn.close();
	
	ArrayList<Patient> patientList = patientDirectory();
	return (new ModelAndView("personDirectory","patientList",patientList));
}*/
/*//**search patient
@RequestMapping("/searchPatient")
public ModelAndView searchPatient(HttpServletRequest req, HttpServletResponse res) throws ClassNotFoundException, SQLException{
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","adb","000786");
	String sql;
	PreparedStatement st;
	//String searchType = req.getParameter("type");
	String value = req.getParameter("value");
//	if(searchType.equals("Person_Id")){
//		sql = "Select Person_Id,Person_First_Name,Person_Last_Name,Person_Gender,Person_Phone,Person_Email FROM Person Where Person_Id = ?";
//		//sql="Select Person_Id,Person_First_Name,Person_Last_Name,Person_Gender,Person_Phone,Person_Email,Patient_Id,First_Name,Last_Name,Relation,Address,City,Phone,Email FROM Person p,PATIENT_EMERG_CONTACT em WHERE p.Person_Id = em.Patient_ID AND Person_Id = ?";
//		st = conn.prepareStatement(sql);
//		st.setLong(1,Long.parseLong(value));
//	}
//		else{
			//sql = "Select Person_Id,Person_First_Name,Person_Last_Name,Person_Gender,Person_Phone,Person_Email FROM Person Where Person_Last_Name = ?";
			sql="Select Person_Id,Person_First_Name,Person_Last_Name,Person_Gender,Person_Phone,Person_Email,Patient_Id,First_Name,Last_Name,Relation,Address,City,Phone,Email FROM Person p,PATIENT_EMERG_CONTACT em WHERE p.Person_Id = em.Patient_ID AND Person_Last_Name = ?";
		    st = conn.prepareStatement(sql);
			st.setString(1,value);
	//}
	
	ResultSet rs = st.executeQuery();
	ArrayList<Patient> patientList = new ArrayList<Patient>();
	while (rs.next()){
		Patient p = new Patient();
		Emergency em = new Emergency();
		p.setPatientId(rs.getLong(1));
		p.setFname(rs.getString(2));
		p.setLname(rs.getString(3));
		p.setGender(rs.getString(4));
		p.setPhone(rs.getString(5));
		p.setEmail(rs.getString(6));
		//**Emergency
		
		em.setPatientId(rs.getLong(7));
		em.setFname(rs.getString(8));
		em.setLname(rs.getString(9));
		em.setRelation(rs.getString(10));
		em.setAddress(rs.getString(11));
		em.setCity(rs.getString(12));
		em.setPhone(rs.getString(13));
		em.setEmail(rs.getString(14));
		p.setEmergency(em);
		
		patientList.add(p);
	}
	st.close();
	conn.close();
	return (new ModelAndView("personDirectory","patientList",patientList));
}*/

@RequestMapping("/updatePatient.html")
public ModelAndView updatePatient(HttpServletRequest req,HttpServletResponse res) throws ClassNotFoundException, SQLException{
	long pId = ((Long)req.getSession().getAttribute("selectedPersonId")).longValue();
	Patient p = new Patient();
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection conn = DriverManager.getConnection(LoginDAOImpl.URL,LoginDAOImpl.USERNAME,LoginDAOImpl.PASSWORD);
	PreparedStatement st = conn.prepareStatement("Select * FROM Person Where Person_Id = ?");
	st.setLong(1,pId);
	ResultSet rs = st.executeQuery();
	while(rs.next()){
		p.setPersonId(rs.getLong(1));
		p.setFname(rs.getString(2));
		p.setLname(rs.getString(3));
		p.setGender(rs.getString(4));
		p.setCountry(rs.getString(5));
		p.setBirthdate(rs.getString(6));
		p.setAddress(rs.getString(7));
		p.setCity(rs.getString(8));
		p.setState(rs.getString(9));
		p.setZip(rs.getString(10));
		p.setPhone(rs.getString(11));
		p.setEmail(rs.getString(12));
		
	}
	st.close();
	
	Insurance ins = new Insurance();
	PreparedStatement st1 = conn.prepareStatement("Select * FROM PATIENT_INS Where Patient_Id = ?");
	st1.setLong(1,pId);
	ResultSet rs1 = st1.executeQuery();
	while(rs1.next()){
		ins.setPolicyNbr(rs1.getString(2));
		ins.setCompany(rs1.getString(3));
		ins.setGroupId(rs1.getString(4));
		ins.setRelation(rs1.getString(5));
	}
	st1.close();
	p.setInsurance(ins);
	
	Emergency em = new Emergency();
	PreparedStatement st2 = conn.prepareStatement("Select * FROM PATIENT_EMERG_CONTACT Where Patient_Id = ?");
	st2.setLong(1,pId);
	ResultSet rs2 = st2.executeQuery();
	while(rs2.next()){
	    em.setFname(rs2.getString(2));
	    em.setLname(rs2.getString(3));
	    em.setRelation(rs2.getString(4));
	    em.setAddress(rs2.getString(5));
	    em.setCity(rs2.getString(6));
	    em.setState(rs2.getString(7));
	    em.setZip(rs2.getString(8));
	    em.setPhone(rs2.getString(9));
	    em.setEmail(rs2.getString(10));
	}
	st2.close();
	p.setEmergency(em);
	
	
	return (new ModelAndView("updatePatient","patient",p));
}
@RequestMapping("/update")
public ModelAndView update(@ModelAttribute Patient p,HttpServletRequest request) throws ClassNotFoundException, SQLException{
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection conn = DriverManager.getConnection(LoginDAOImpl.URL,LoginDAOImpl.USERNAME,LoginDAOImpl.PASSWORD);
	PreparedStatement st = conn.prepareStatement("UPdate Person SET Person_first_name = ?,person_last_name= ?,person_gender=?,person_BirthDate=?,person_Address=?,person_city=?,person_state=?,person_zip=?,person_country=?,person_phone=?,person_email=? Where Person_Id = ?");
    
	st.setString(1, p.getFname());
	st.setString(2,p.getLname());
	st.setString(3,p.getGender());
	st.setString(4,p.getBirthdate());
	st.setString(5,p.getAddress());
	st.setString(6,p.getCity());
	st.setString(7,p.getState());
	st.setString(8,p.getZip());
	st.setString(9,p.getCountry());
	st.setString(10,p.getPhone());
	st.setString(11,p.getEmail());
	st.setLong(12, p.getPersonId());
	st.executeUpdate();
	st.close();
	
	Emergency em = p.getEmergency();
	PreparedStatement st1 = conn.prepareStatement("UPdate PATIENT_EMERG_CONTACT SET First_Name=?,Last_Name=?,relation=?,Address=?,city=?,state=?,zip=?,phone=?,email=? Where Patient_Id = ?");
	st1.setString(1, em.getFname());
	st1.setString(2,em.getLname());
	st1.setString(3,em.getRelation());
	st1.setString(4,em.getAddress());
	st1.setString(5,em.getCity());
	st1.setString(6,em.getState());
	st1.setString(7,em.getZip());
	st1.setString(8,em.getPhone());
	st1.setString(9,em.getEmail());
	st1.setLong(10,p.getPersonId());
	st1.executeUpdate();
	conn.close();

	Administrator admin=new Administrator();
	LoginDAOImpl loginDAOimpl=new LoginDAOImpl();
	List<PersonBean> userList=loginDAOimpl.getUserInfo("ALL","SELECT","");
	admin.setUserList(userList);

	request.getSession().setAttribute("userList", userList);
	
	return (new ModelAndView("adminUser","userInfo",admin));
}

////get Patient
public ArrayList<Patient> patientDirectory() throws ClassNotFoundException, SQLException{
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection conn = DriverManager.getConnection(LoginDAOImpl.URL,LoginDAOImpl.USERNAME,LoginDAOImpl.PASSWORD);
	Statement st = conn.createStatement();
	//ResultSet rs = st.executeQuery("Select Person_Id,Person_First_Name,Person_Last_Name,Person_Gender,Person_Phone,Person_Email FROM Person");
	ResultSet rs = st.executeQuery("Select Person_Id,Person_First_Name,Person_Last_Name,Person_Gender,Person_Phone,Person_Email,Patient_Id,First_Name,Last_Name,Relation,Address,City,Phone,Email FROM Person p,PATIENT_EMERG_CONTACT em WHERE p.Person_Id = em.Patient_ID");
	
	ArrayList<Patient> patientList = new ArrayList<Patient>();
	while (rs.next()){
		Patient p = new Patient();
		Emergency em = new Emergency();
		p.setPersonId(rs.getLong(1));
		p.setFname(rs.getString(2));
		p.setLname(rs.getString(3));
		p.setGender(rs.getString(4));
		p.setPhone(rs.getString(5));
		p.setEmail(rs.getString(6));
		//**Emergency contact
		em.setPatientId(rs.getLong(7));
		em.setFname(rs.getString(8));
		em.setLname(rs.getString(9));
		em.setRelation(rs.getString(10));
		em.setAddress(rs.getString(11));
		em.setCity(rs.getString(12));
		em.setPhone(rs.getString(13));
		em.setEmail(rs.getString(14));
		p.setEmergency(em);
		
		patientList.add(p);
	}
	st.close();
	conn.close();
	return patientList;
}


}
