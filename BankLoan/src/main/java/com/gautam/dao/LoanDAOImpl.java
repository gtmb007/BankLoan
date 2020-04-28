package com.gautam.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.gautam.entity.CustomerEntity;
import com.gautam.entity.LoanEntity;
import com.gautam.model.Customer;
import com.gautam.model.Loan;


@Repository(value="loanDAO")
public class LoanDAOImpl implements LoanDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public Integer addCustomer(Customer customer) throws Exception {
		CustomerEntity custEntity=entityManager.find(CustomerEntity.class, customer.getCustomerId());
		if(custEntity!=null) return null;
		custEntity=new CustomerEntity();
		custEntity.setCustomerId(customer.getCustomerId());
		custEntity.setCustomerName(customer.getCustomerName());
		custEntity.setMobileNo(customer.getMobileNo());
		Loan loan=customer.getLoan();
		LoanEntity loanEntity=null;
		if(loan!=null) {
			loanEntity=new LoanEntity();
			loanEntity.setLoanAmount(loan.getLoanAmount());
			loanEntity.setInterestRate(loan.getInterestRate());
			loanEntity.setTerm(loan.getTerm());
			loanEntity.setLoanType(loan.getLoanType());
		}
		custEntity.setLoan(loanEntity);
		entityManager.persist(custEntity);
		return custEntity.getCustomerId();
	}
	
	public List<Customer> getReportByLoanType(String loanType) throws Exception {
		String queryString="SELECT c FROM CustomerEntity c WHERE c.loan.loanType=?1";
		Query query=entityManager.createQuery(queryString);
		query.setParameter(1, loanType);
		List<CustomerEntity> custEntityList=query.getResultList();
		List<Customer> custList=new ArrayList<Customer>();
		for(CustomerEntity custEntity : custEntityList) {
			Customer cust=new Customer();
			cust.setCustomerId(custEntity.getCustomerId());
			cust.setCustomerName(custEntity.getCustomerName());
			cust.setMobileNo(custEntity.getMobileNo());
			LoanEntity loanEntity=custEntity.getLoan();
			Double loanAmount=loanEntity.getLoanAmount();
			Double interestRate=loanEntity.getInterestRate();
			Integer term=loanEntity.getTerm();
			Double emi=Math.ceil((loanAmount+((loanAmount*term*interestRate)/100))/(term*12));
			cust.setEmi(emi);
			Loan loan=new Loan();
			loan.setLoanId(loanEntity.getLoanId());
			loan.setLoanAmount(loanAmount);
			loan.setInterestRate(interestRate);
			loan.setTerm(term);
			loan.setLoanType(loanEntity.getLoanType());
			cust.setLoan(loan);
			custList.add(cust);
		}
		return custList;
	}

	
	public Integer checkLoanAllotment(Integer customerId) throws Exception {
		CustomerEntity custEntity=entityManager.find(CustomerEntity.class, customerId);
		if(custEntity==null) return -1;
		LoanEntity loanEntity=custEntity.getLoan();
		if(loanEntity==null) return 0;
		return loanEntity.getLoanId();
	}
	
	public Integer sanctionLoan(Customer customer) throws Exception {
		CustomerEntity custEntity=entityManager.find(CustomerEntity.class, customer.getCustomerId());
		Loan loan=customer.getLoan();
		LoanEntity loanEntity=new LoanEntity();
		loanEntity.setLoanAmount(loan.getLoanAmount());
		String loanType=loan.getLoanType();
		loanEntity.setLoanType(loanType);
		if(loanType.equals("HomeLoan")) {
			loanEntity.setInterestRate(13.0);
			loanEntity.setTerm(15);
		} else {
			loanEntity.setInterestRate(9.0);
			loanEntity.setTerm(5);
		}
		entityManager.persist(loanEntity);
		custEntity.setLoan(loanEntity);
		return custEntity.getLoan().getLoanId();
	}
	
}
