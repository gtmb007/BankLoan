package com.gautam.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class CustomerEntity {
	
	@Id
	@Column(name="customer_id")
	private Integer customerId;
	
	@Column(name="name")
	private String customerName;
	
	@Column(name="mobile_no")
	private Long mobileNo;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="loan_id", unique=true)
	private LoanEntity loan;
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public LoanEntity getLoan() {
		return loan;
	}
	public void setLoan(LoanEntity loan) {
		this.loan = loan;
	}
	
}
