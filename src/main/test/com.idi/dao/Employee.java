/*
 * User: eladh
 * Date: 18/01/2018 
 *
 * Copyright (2005) IDI. All rights reserved.
 * This software is a proprietary information of Israeli Direct Insurance.
 * Created by IntelliJ IDEA. 
 */
package com.idi.dao;

import org.javers.core.metamodel.annotation.TypeName;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 */
@Entity
@TypeName("Employee")
public class Employee {

	@Id
	private String name;
	private Integer age;
	private Integer salary;

	public Employee(String name, Integer age, Integer salary) {
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}
}