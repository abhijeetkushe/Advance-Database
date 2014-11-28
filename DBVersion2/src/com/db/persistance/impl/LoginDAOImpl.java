package com.db.persistance.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import java.sql.Types;


import com.db.persistance.dto.Emergency;
import com.db.persistance.dto.Insurance;
import com.db.persistance.dto.Patient;
import com.db.persistance.dto.PersonBean;
import com.db.persistance.dto.Physician;
import com.db.persistance.dto.UserAccountBean;
import com.db.persistance.dto.UserAdminBean;
import com.db.persistance.exception.LoginFailureException;
import com.db.persistance.log.LogMessage;

public class LoginDAOImpl {
	
	public static String URL="jdbc:oracle:thin:@abhijit:1521/orcl";
	public static String USERNAME="finProj";
	public static String PASSWORD="finProj";

	/*{
		try
		{
			Properties prop=new Properties();
			//InputStream is=new FileInputStream("/com/db/persistance/impl/db.properties");
			//prop.load(is);
			//URL=prop.getProperty("db.connection.url");
			//USERNAME=prop.getProperty("db.connection.username");
			//PASSWORD=prop.getProperty("db.connection.password");
		}
		catch(IOException e)
		{
			LogMessage.logStackTrace(e);
			LogMessage.logErrorMessage("Exception","ERROR");
		}
	}

	*/
	
	
	private Connection getConnection() throws SQLException
	{
		try
		{
		 // Load the Oracle JDBC driver"
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);	
			return conn;
		} 
		catch(SQLException e)
		{
			LogMessage.logStackTrace(e);
			throw e;
		}
	}
	
	public UserAccountBean verifyLogin(String username,String password) throws LoginFailureException
	{
		boolean result=false;
		Connection conn=null;
		CallableStatement cstmt=null;
		try
		{
			conn=getConnection();
			cstmt=conn.prepareCall("{? = call fin_proj_pkg2.verifyLogin(?,?,?)}");
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.registerOutParameter(2, Types.VARCHAR);
			cstmt.registerOutParameter(3, Types.VARCHAR);
			cstmt.registerOutParameter(4, Types.VARCHAR);
			cstmt.setString(2,username);
			cstmt.setString(3,password);
			cstmt.execute();
			long personid=cstmt.getLong(1);
			String personType=cstmt.getString(4);
			if(personid!=0)
			{
				UserAccountBean userAccount=new UserAccountBean();
				userAccount.setAccountID(personid);
				userAccount.setUserName(username);
				userAccount.setPassword(password);
				userAccount.setPersonType(personType);
				return userAccount;
			}
			else
			{
				throw new LoginFailureException("username/password in invalid");
			}	
			
		}
		catch(SQLException e)
		{
			LogMessage.logStackTrace(e);
			throw new LoginFailureException("username/password in invalid");
		}		
		finally
		{
			try
			{
				cstmt.close();
				conn.close();
			}
			catch(SQLException e)
			{
				LogMessage.logStackTrace(e);
			}
			
		}
	}
	
	public PersonBean getUserDetails(UserAccountBean accountBean)
	{
		PersonBean person=null;
		String personType=accountBean.getPersonType();
		if("PATIENT".equals(personType))
		{
			person=getPatientDetails(accountBean);
		}
		return person;
	}
	
	
	private Patient getPatientDetails(UserAccountBean accountBean)
	{
		Patient patient=new Patient();
		Connection conn=null;
		PreparedStatement preparedStmt=null;		
		try
		{
			conn=getConnection();
			preparedStmt=conn.prepareStatement(String.valueOf(QueryConstants.GET_PATIENT_INFO));
			LogMessage.logDebugMessage(QueryConstants.GET_PATIENT_INFO.toString(), "Debug");
			preparedStmt.setLong(1,accountBean.getAccountID());
			ResultSet resultSet=preparedStmt.executeQuery();
			while(resultSet.next())
			{
				patient.setAccountBean(accountBean);
				patient.setFname(resultSet.getString(1));
				patient.setLname(resultSet.getString(2));
				patient.setAddress(resultSet.getString(3));
				patient.setCity(resultSet.getString(4));
				patient.setState(resultSet.getString(5));
				patient.setZip(resultSet.getString(6));
				patient.setPhone(resultSet.getString(7));
				patient.setEmail(resultSet.getString(8));
				patient.setPatientType(resultSet.getString(9));
				
				Emergency emergency=new Emergency();
				emergency.setFname(resultSet.getString(10));
				emergency.setLname(resultSet.getString(11));
				emergency.setRelation(resultSet.getString(12));
				emergency.setAddress(resultSet.getString(13));
				emergency.setCity(resultSet.getString(14));
				emergency.setState(resultSet.getString(15));
				emergency.setZip(resultSet.getString(16));
				emergency.setPhone(resultSet.getString(17));
				emergency.setEmail(resultSet.getString(18));
				patient.setEmergency(emergency);
				
				Insurance insurance=new Insurance();
				insurance.setPolicyNbr(resultSet.getString(19));
				insurance.setCompany(resultSet.getString(20));
				insurance.setGroupId(resultSet.getString(21));
				insurance.setRelation(resultSet.getString(22));
				patient.setInsurance(insurance);
				patient.setGender(resultSet.getString(23));
				
				
			}
		}
		catch(SQLException e)
		{
			LogMessage.logErrorMessage("error","ERROR");
			
		}
		finally
		{
			try
			{
				preparedStmt.close();
				conn.close();
			}
			catch(SQLException e)
			{
				LogMessage.logStackTrace(e);
			}
			
		}
			return patient;
	}
	
	
	private Physician getPhysicianDetails(UserAccountBean accountBean)
	{
		Physician physician=new Physician();
		Connection conn=null;
		PreparedStatement preparedStmt=null;		
		try
		{
			conn=getConnection();
			preparedStmt=conn.prepareStatement(String.valueOf(QueryConstants.GET_PHYSICIAN_INFO));
			preparedStmt.setLong(1,accountBean.getAccountID());
			ResultSet resultSet=preparedStmt.executeQuery();
			while(resultSet.next())
			{
				physician.setAccountBean(accountBean);
				physician.setFname(resultSet.getString(1));
				physician.setLname(resultSet.getString(2));
				physician.setAddress(resultSet.getString(3));
				physician.setCity(resultSet.getString(4));
				physician.setState(resultSet.getString(5));
				physician.setZip(resultSet.getString(6));
				physician.setPhone(resultSet.getString(7));
				physician.setEmail(resultSet.getString(8));
				physician.setDeaID(resultSet.getString(9));		
				physician.setPagerNumber(resultSet.getString(10));
				physician.setSpeciality(resultSet.getString(11));
				physician.setGender(resultSet.getString(12));
				physician.setPersonType(resultSet.getString(13));
				
			}
		}
		catch(SQLException e)
		{
			LogMessage.logErrorMessage("error","ERROR");
			
		}
		finally
		{
			try
			{
				preparedStmt.close();
				conn.close();
			}
			catch(SQLException e)
			{
				LogMessage.logStackTrace(e);
			}
			
		}
			return physician;
	}			
	
	public List<PersonBean> getUserInfo(String personType,String mode,String lastName)
	{
		List<PersonBean> userAdminList=new ArrayList<PersonBean>(10);
		Connection conn=null;
		CallableStatement cstmt=null;
		try
		{
			conn=getConnection();
			cstmt=conn.prepareCall("{call getUserDetails(?,?,?,?)}");
			cstmt.setString(1,personType);
			cstmt.registerOutParameter(2,OracleTypes.CURSOR);
			cstmt.setString(3, mode);
			cstmt.setString(4, lastName);
			cstmt.execute();
			ResultSet rs=(ResultSet)cstmt.getObject(2);
			ResultSetMetaData metaData=rs.getMetaData();
			if(metaData.getColumnCount()>0)
			{	
				 while (rs.next()){
				        PersonBean p=null;
				        Emergency em =null;				        
					 	String type=rs.getString(15);
				        if("PA".equals(type))
				        {
				        	p= new Patient();
				        	em=new Emergency();
				        }
				        else
				        {
				        	p= new Physician();
				        }	
				        p.setPersonId(rs.getLong(1));
				        p.setFname(rs.getString(2));
				        p.setLname(rs.getString(3));
				        p.setGender(rs.getString(4));
				        p.setPhone(rs.getString(5));
				        p.setEmail(rs.getString(6));
				        //**Emergency contact

				        if(p instanceof Patient)
				        {
					        em.setPatientId(rs.getLong(7));
					        em.setFname(rs.getString(8));
					        em.setLname(rs.getString(9));
					        em.setRelation(rs.getString(10));
					        em.setAddress(rs.getString(11));
					        em.setCity(rs.getString(12));
					        em.setPhone(rs.getString(13));
					        em.setEmail(rs.getString(14));
				        	((Patient)p).setEmergency(em);
				        }	
				        if("PA".equals(type))
				        {	
				        	p.setPersonType("Patient");
				        }
				        else 
				        {
				        	p.setPersonType("Physician");				        	
				        }
				        userAdminList.add(p);
				    }
			}
		}
		catch(SQLException e)
		{
			LogMessage.logErrorMessage("error","ERROR");
			
		}
		finally
		{
			try
			{
				cstmt.close();
				conn.close();
			}
			catch(SQLException e)
			{
				LogMessage.logStackTrace(e);
			}
			
		}
		return userAdminList;
	}
	
	public void deleteUser(Long[] personids)
	{
		Connection conn=null;
		CallableStatement cstmt=null;
		try
		{
			conn=getConnection();
			ArrayDescriptor desc = ArrayDescriptor.createDescriptor("PERSON_ARRAY", conn);
		    ARRAY newArray = new ARRAY(desc, conn, personids);
			
			
			cstmt=conn.prepareCall("{call DELETE_PERSON(?)}");
			cstmt.setArray(1,newArray);
			cstmt.execute();
			
		}
		catch(SQLException e)
		{
			LogMessage.logErrorMessage("error","ERROR");
			
		}
		finally
		{
			try
			{
				cstmt.close();
				conn.close();
			}
			catch(SQLException e)
			{
				LogMessage.logStackTrace(e);
			}
			
		}
	}
}
