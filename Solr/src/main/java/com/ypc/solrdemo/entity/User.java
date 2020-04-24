package com.ypc.solrdemo.entity;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.solr.core.mapping.SolrDocument;


@SolrDocument(solrCoreName = "custom_core")
public class User {
	
	@Id
    @Field
    private int id;
    @Field
    private String userName;
    @Field
    private String sex;
    @Field
    private String address;
    @Field
    private String description;
    @Field
    private int age;
    @Field
    private String mobile;
    @Field
    private String period;
    
    @Transient
    private Long _version_;
    
    
    
    public Long get_version_() {
		return _version_;
	}
	public void set_version_(Long _version_) {
		this._version_ = _version_;
	}
	// ����ʡ��
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
    
    

}
