package com.gautam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gautam.dao.LoanDAO;
import com.gautam.model.Customer;
import com.gautam.validator.Validator;


@Service(value="loanService")
@Transactional
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanDAO loanDAO;

	public Integer addCustomer(Customer customer) throws Exception {
		Integer custId=loanDAO.addCustomer(customer);
		if(custId==null) throw new Exception("Service.CUSTOMER_AVAILABLE");
		return custId;
	}
	
	public Integer sanctionLoan(Customer customer) throws Exception {
		Validator.validate(customer.getLoan());
		Integer loanId=loanDAO.checkLoanAllotment(customer.getCustomerId());
		if(loanId==0) return loanDAO.sanctionLoan(customer);
		if(loanId==-1) throw new Exception("Service.CUSTOMER_UNAVAILABLE");
		throw new Exception("Service.LOAN_ALREADY_TAKEN");
	}

	
	public List<Customer> getReportByLoanType(String loanType) throws Exception {
		List<Customer> custList=loanDAO.getReportByLoanType(loanType);
		if(custList.isEmpty()) throw new Exception("Service.NO_LOAN_FOUND");
		return custList;
	}

}