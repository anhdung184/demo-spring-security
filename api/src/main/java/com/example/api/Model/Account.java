package com.example.api.Model;

import jakarta.persistence.*;

@Entity
@Table(name="ACCOUNT")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ACC_ID")
    private long id;
    
    @Column(name = "ACC_USERNAME")
    private String accusername;

    @Column(name = "ACC_PASSWORD")
    private String accpassword;
    
    @Column(name = "ACC_PERMISSIONS")
    private String accpermissions;
    
    public Account(){

    }

    public Account(String accusername,String accpassword,String accpermissions){
    this.accusername = accusername;
    this.accpassword = accpassword;
    this.accpermissions = accpermissions;
    }

    public long getId() {
		return id;
	}

    public String getAccusername(){
        return accusername;
    }
    public void setAccusername(String accusername){
        this.accusername = accusername;
    }

    public String getAccpassword(){
        return accpassword;
    }
    public void setAccpassword(String accpassword){
        this.accpassword = accpassword;
    }

    public String getAccpermissions(){
        return accpermissions;
    }

    public void setAccpermissions(String accpermissions){
        this.accpermissions = accpermissions;
    }

    @Override
    public String toString(){
        return "Account [id=" + id + ", accusername=" + accusername +", accpassword=" + accpassword + ", accpermissions=" + accpermissions + "]";
    }
}
