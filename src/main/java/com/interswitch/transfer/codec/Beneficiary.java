package com.interswitch.transfer.codec;

public class Beneficiary {
    
    private String phone;
    private String email;
    private String lastname;
    private String othernames;
    
    public Beneficiary(String phone, String email, String lastname, String othernames){
        this.phone = phone;
        this.email = email;
        this.lastname = lastname;
        this.othernames = othernames;
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
    public String getLastName() {
        return lastname;
    }
    public void setLastName(String lastName) {
        this.lastname = lastName;
    }
    public String getOtherNames() {
        return othernames;
    }
    public void setOtherNames(String otherNames) {
        this.othernames = otherNames;
    }
    
}
