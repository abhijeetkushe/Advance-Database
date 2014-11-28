package com.db.persistance.dto;

public class Insurance {
    private String status;
    private String relation;
    private String company;
    private String policyNbr;
    private String groupId;
    
    
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getRelation() {
        return relation;
    }
    public void setRelation(String relation) {
        this.relation = relation;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getPolicyNbr() {
        return policyNbr;
    }
    public void setPolicyNbr(String policyNbr) {
        this.policyNbr = policyNbr;
    }
    public String getGroupId() {
        return groupId;
    }
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    
    

}
