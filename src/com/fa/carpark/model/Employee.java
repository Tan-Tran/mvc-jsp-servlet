package com.fa.carpark.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EMPLOYEE")
public class Employee {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="ID")
		private Integer id;
		
		@Column(name="FIRST_NAME")
		private String firstName;
		
		@Column(name="LAST_NAME")
		private String lastName;
		
		@Column(name="GENDER")
		private Integer gender;
		
		@Column(name="DATE_OF_BIRTH")
		@Temporal(TemporalType.DATE)
		private Date dateOfBirth;
		
		@Column(name="PHONE", length = 20)
		private String phone;
		
		@Column(name="ADDRESS")
		private String address;
		
		@Column(name="DEPARTMENT_NAME")
		private String departmentName;
		
		@Column(name="REMARK", length = 1000)
		private String remark;
		
//		@OneToOne(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//		private Account account;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public int getGender() {
			return gender;
		}

		public void setGender(int gender) {
			this.gender = gender;
		}

		public Date getDateOfBirth() {
			return dateOfBirth;
		}

		public void setDateOfBirth(Date dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getDepartmentName() {
			return departmentName;
		}

		public void setDepartmentName(String departmentName) {
			this.departmentName = departmentName;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

//		public Account getAccount() {
//			return account;
//		}
//
//		public void setAccount(Account account) {
//			this.account = account;
//		}
}
