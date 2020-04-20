package com.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "banji")
@Entity
public class Banji {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private  Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "banji")
	private String banji;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "jiazhang")
	private String jiazhang;
    
	@Column(name = "remark")
	private String remark;
 }
