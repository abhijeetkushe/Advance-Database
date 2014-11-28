package com.db.persistance.dto;

public class Emergency {

	private long patientId;
	private String relation;
	private String fname;
	private String lname;
	private String address;
	private String city;
	private String country;
	private String zip;
	/**
	 * @return the patientId
	 */
	public long getPatientId() {
		return patientId;
	}
	private String phone;
	private String email;
	private String state;


	public String getState() {
	    return state;
	}
	public void setState(String state) {
	    this.state = state;
	}
	public String getRelation() {
	    return relation;
	}
	public void setRelation(String relation) {
	    this.relation = relation;
	}
	public String getFname() {
	    return fname;
	}
	public void setFname(String fname) {
	    this.fname = fname;
	}
	public String getLname() {
	    return lname;
	}
	public void setLname(String lname) {
	    this.lname = lname;
	}
	public String getAddress() {
	    return address;
	}
	public void setAddress(String address) {
	    this.address = address;
	}
	public String getCity() {
	    return city;
	}
	public void setCity(String city) {
	    this.city = city;
	}
	public String getCountry() {
	    return country;
	}
	public void setCountry(String country) {
	    this.country = country;
	}
	public String getZip() {
	    return zip;
	}
	public void setZip(String zip) {
	    this.zip = zip;
	}
	public String getPhone() {
	    return phone;
	}
	public void setPhone(String phone) {
	    this.phone = phone;
	}
	public String getEmail() {
	    return email;
	}
	public void setEmail(String email) {
	    this.email = email;
	}
	public void setPatientId(long long1) {
		// TODO Auto-generated method stub
		
	}


	}
