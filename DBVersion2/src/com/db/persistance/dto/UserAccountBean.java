package com.db.persistance.dto;

import java.util.Set;


public class UserAccountBean extends AbstractDTO{
	
	
	private Long accountID;
	private String userName;
	private String password;
	private String personType;
	
	
	/**
	 * @return the personType
	 */
	public String getPersonType() {
		return personType;
	}
	/**
	 * @param personType the personType to set
	 */
	public void setPersonType(String personType) {
		this.personType = personType;
	}
	/**
	  * @return the accountID
	 */
	public Long getAccountID() {
		return accountID;
	}
	/**
	 * @param accountID the accountID to set
	 */
	public void setAccountID(Long accountID) {
		this.accountID = accountID;
	}
		/**
		 * @return the userName
		 */
		public String getUserName() {
			return userName;
		}
		/**
		 * @param userName the userName to set
		 */
		public void setUserName(String userName) {
			this.userName = userName;
		}
		/**
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}
		/**
		 * @param password the password to set
		 */
		public void setPassword(String password) {
			this.password = password;
		}
}
