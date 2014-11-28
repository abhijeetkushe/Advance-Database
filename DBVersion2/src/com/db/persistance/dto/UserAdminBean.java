package com.db.persistance.dto;



public class UserAdminBean extends AbstractDTO {
	
	private long personid;
	/**
	 * @return the personid
	 */
	public long getPersonid() {
		return personid;
	}
	/**
	 * @param personid the personid to set
	 */
	public void setPersonid(long personid) {
		this.personid = personid;
	}
	private String firstName;
	private String lastName;
	private boolean checked;
	private String personType;
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the checked
	 */
	public boolean isChecked() {
		return checked;
	}
	/**
	 * @param checked the checked to set
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
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
}
