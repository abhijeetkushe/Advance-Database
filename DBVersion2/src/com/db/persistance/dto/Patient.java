package com.db.persistance.dto;

public class Patient extends PersonBean{
        
    private Emergency emergency;
    private Insurance insurance;
    private String patientType;
    
    /**
	 * @return the patientType
	 */
	public String getPatientType() {
		return patientType;
	}
	/**
	 * @param patientType the patientType to set
	 */
	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}
	public Emergency getEmergency() {
        return emergency;
    }
    public void setEmergency(Emergency emergency) {
        this.emergency = emergency;
    }
    public Insurance getInsurance() {
        return insurance;
    }
    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }
    
    

}
