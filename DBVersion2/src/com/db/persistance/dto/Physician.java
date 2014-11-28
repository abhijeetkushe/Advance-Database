package com.db.persistance.dto;

public class Physician extends PersonBean{

	private String deaID;
	private String speciality;
	private String pagerNumber;
	/**
	 * @return the deaID
	 */
	public String getDeaID() {
		return deaID;
	}
	/**
	 * @param deaID the deaID to set
	 */
	public void setDeaID(String deaID) {
		this.deaID = deaID;
	}
	/**
	 * @return the speciality
	 */
	public String getSpeciality() {
		return speciality;
	}
	/**
	 * @param speciality the speciality to set
	 */
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	/**
	 * @return the pagerNumber
	 */
	public String getPagerNumber() {
		return pagerNumber;
	}
	/**
	 * @param pagerNumber the pagerNumber to set
	 */
	public void setPagerNumber(String pagerNumber) {
		this.pagerNumber = pagerNumber;
	}
}
