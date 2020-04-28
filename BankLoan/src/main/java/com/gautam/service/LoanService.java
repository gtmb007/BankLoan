package com.gautam.service;

import java.util.List;

import com.gautam.model.Customer;


public interface LoanService {
	
	public List<Customer> getReportByLoanType(String loanType) throws Exception;
	public Integer sanctionLoan(Customer customer) throws Exception;
	public Integer addCustomer(Customer customer) throws Exception;
	
}
