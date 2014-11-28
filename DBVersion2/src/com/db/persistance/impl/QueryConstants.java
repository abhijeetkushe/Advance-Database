package com.db.persistance.impl;

public class QueryConstants {

	public static StringBuilder UPDATE_PERSON_INFO=new StringBuilder(100);
	public static StringBuilder GET_PATIENT_INFO=new StringBuilder(100);
	public static StringBuilder GET_PHYSICIAN_INFO=new StringBuilder(100);
	static
	{
		UPDATE_PERSON_INFO.append("UPDATE PERSON SET Person_First_Name = ?,Person_Last_Name = ?,Person_Address = ?,Person_City = ?,");
		UPDATE_PERSON_INFO.append("Person_State = 'e',Person_Zip = 'f',Person_Phone = 'g',Person_Email = 'h',Person_Username = 'i',Person_Password = 'j',WHERE Person_ID = 123");

		GET_PATIENT_INFO.append("Select Person.Person_First_Name,Person.Person_Last_Name,Person.Person_Address,Person.Person_City,Person.Person_State,Person.Person_Zip,Person.Person_Phone, ");
		GET_PATIENT_INFO.append("Person.Person_Email,Patient.Patient_Type, PATIENT_EMERG_CONTACT.FIRST_NAME,PATIENT_EMERG_CONTACT.LAST_NAME,PATIENT_EMERG_CONTACT.RELATION, ");
		GET_PATIENT_INFO.append(" PATIENT_EMERG_CONTACT.ADDRESS,PATIENT_EMERG_CONTACT.city,PATIENT_EMERG_CONTACT.state, PATIENT_EMERG_CONTACT.zip,PATIENT_EMERG_CONTACT.PHONE, PATIENT_EMERG_CONTACT.email, ");
		GET_PATIENT_INFO.append(" PATIENT_INS.POLICY_NBR,PATIENT_INS.COMPANY_NAME,PATIENT_INS.GROUP_NBR,PATIENT_INS.Relation,decode(PERSON.Person_Gender,'M','Male','F','Female'),CASE PERSON.Person_Type WHEN 'PA' THEN 'PATIENT'WHEN 'PH' THEN 'PHYSICIAN' WHEN 'EM' THEN 'EMPLOYEE' WHEN 'VO' THEN 'VOLUNTEER' END from PERSON,PATIENT,PATIENT_EMERG_CONTACT,PATIENT_INS where Person.Person_ID = Patient.Patient_ID ");
		GET_PATIENT_INFO.append(" and PATIENT_EMERG_CONTACT.Patient_ID = Patient.Patient_ID and PATIENT_INS.Patient_ID = Patient.Patient_ID and Patient.Patient_ID = ? and Person.Person_Type = 'PATIENT'");
		
		GET_PHYSICIAN_INFO.append("Select Person_First_Name, Person_Last_Name, Person_Address,Person_City,Person_State,Person_Zip,Person_Phone,Person_Email,");
		GET_PHYSICIAN_INFO.append(" DEA_ID,Pager_Nbr,Speciality,decode(PERSON.Person_Gender, 'M', 'Male', 'F', 'Female'), CASE PERSON.Person_Type WHEN 'PA' THEN 'PATIENT' WHEN 'PH' THEN 'PHYSICIAN' WHEN 'EM' THEN 'EMPLOYEE' WHEN 'VO' THEN 'VOLUNTEER' END,decode(PERSON.Person_Gender, 'M', 'Male', 'F', 'Female') from Person, Physician where Person_ID = Physician_ID  and Physician_ID = ? and Person_Type = 'PHYSICIAN'"); 
		
		
	}
}
