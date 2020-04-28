package com.gautam;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;

import com.gautam.dao.LoanDAO;
import com.gautam.model.Customer;
import com.gautam.model.Loan;
import com.gautam.service.LoanService;
import com.gautam.service.LoanServiceImpl;


@SpringBootTest
//@RunWith(SpringRunner.class)
@RunWith(MockitoJUnitRunner.class)
public class BankLoanApplicationTests {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Mock
	private LoanDAO loanDAO;
	
	@InjectMocks
	private LoanService loanService=new LoanServiceImpl();
	
	@Test
	public void addCustomerValidTest() throws Exception {
		Customer cust=new Customer();
		cust.setCustomerId(2001);
		cust.setCustomerName("Gautam");
		cust.setMobileNo(9123833866l);
		Double loanAmount=1200000d;
		Double interestRate=13d;
		Integer term=15;
		Double emi=Math.ceil((loanAmount+((loanAmount*term*interestRate)/100))/(term*12));
		cust.setEmi(emi);
		Loan loan=new Loan();
		loan.setLoanId(1001);
		loan.setLoanAmount(loanAmount);
		loan.setInterestRate(interestRate);
		loan.setTerm(term);
		loan.setLoanType("HomeLoan");
		cust.setLoan(loan);
		
		when(loanDAO.addCustomer(cust)).thenReturn(cust.getCustomerId());
		Integer actual=loanService.addCustomer(cust);
		Assert.assertEquals(cust.getCustomerId(), actual);
	}
	
	@Test
	public void addCustomerInValidTest() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("Service.CUSTOMER_AVAILABLE");
		
		Customer cust=new Customer();
		cust.setCustomerId(2001);
		cust.setCustomerName("Gautam");
		cust.setMobileNo(9123833866l);
		Double loanAmount=1200000d;
		Double interestRate=13d;
		Integer term=15;
		Double emi=Math.ceil((loanAmount+((loanAmount*term*interestRate)/100))/(term*12));
		cust.setEmi(emi);
		Loan loan=new Loan();
		loan.setLoanId(1001);
		loan.setLoanAmount(loanAmount);
		loan.setInterestRate(interestRate);
		loan.setTerm(term);
		loan.setLoanType("HomeLoan");
		cust.setLoan(loan);
		
		when(loanDAO.addCustomer(cust)).thenReturn(null);
		loanService.addCustomer(cust);
	}
	
	@Test
	public void sanctionLoanValidTest() throws Exception {
		Customer cust=new Customer();
		cust.setCustomerId(2001);
		cust.setCustomerName("Gautam");
		cust.setMobileNo(9123833866l);
		Double loanAmount=1200000d;
		Double interestRate=13d;
		Integer term=15;
		Double emi=Math.ceil((loanAmount+((loanAmount*term*interestRate)/100))/(term*12));
		cust.setEmi(emi);
		Loan loan=new Loan();
		loan.setLoanId(1001);
		loan.setLoanAmount(loanAmount);
		loan.setInterestRate(interestRate);
		loan.setTerm(term);
		loan.setLoanType("HomeLoan");
		cust.setLoan(loan);
		
		when(loanDAO.checkLoanAllotment(cust.getCustomerId())).thenReturn(0);
		when(loanDAO.sanctionLoan(cust)).thenReturn(cust.getLoan().getLoanId());
		Integer actual=loanService.sanctionLoan(cust);
		Assert.assertEquals(cust.getLoan().getLoanId(), actual);
	}
	
	@Test
	public void sanctionLoanCustomerNotAvailableTest() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("Service.CUSTOMER_UNAVAILABLE");
		
		Customer cust=new Customer();
		cust.setCustomerId(2001);
		cust.setCustomerName("Gautam");
		cust.setMobileNo(9123833866l);
		Double loanAmount=1200000d;
		Double interestRate=13d;
		Integer term=15;
		Double emi=Math.ceil((loanAmount+((loanAmount*term*interestRate)/100))/(term*12));
		cust.setEmi(emi);
		Loan loan=new Loan();
		loan.setLoanId(1001);
		loan.setLoanAmount(loanAmount);
		loan.setInterestRate(interestRate);
		loan.setTerm(term);
		loan.setLoanType("HomeLoan");
		cust.setLoan(loan);
		
		when(loanDAO.checkLoanAllotment(cust.getCustomerId())).thenReturn(-1);
		loanService.sanctionLoan(cust);
	}
	
	@Test
	public void sanctionLoanLoanAlreadyTakenTest() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("Service.LOAN_ALREADY_TAKEN");
		
		Customer cust=new Customer();
		cust.setCustomerId(2001);
		cust.setCustomerName("Gautam");
		cust.setMobileNo(9123833866l);
		Double loanAmount=1200000d;
		Double interestRate=13d;
		Integer term=15;
		Double emi=Math.ceil((loanAmount+((loanAmount*term*interestRate)/100))/(term*12));
		cust.setEmi(emi);
		Loan loan=new Loan();
		loan.setLoanId(1001);
		loan.setLoanAmount(loanAmount);
		loan.setInterestRate(interestRate);
		loan.setTerm(term);
		loan.setLoanType("HomeLoan");
		cust.setLoan(loan);
		
		when(loanDAO.checkLoanAllotment(cust.getCustomerId())).thenReturn(cust.getLoan().getLoanId());
		loanService.sanctionLoan(cust);
	}
	
	@Test
	public void getReportByLoanTypeValidTest() throws Exception {
		String loanType="HomeLoan";
		List<Customer> custList=new ArrayList<Customer>();
		Customer cust=new Customer();
		cust.setCustomerId(2001);
		cust.setCustomerName("Gautam");
		cust.setMobileNo(9123833866l);
		Double loanAmount=1200000d;
		Double interestRate=13d;
		Integer term=15;
		Double emi=Math.ceil((loanAmount+((loanAmount*term*interestRate)/100))/(term*12));
		cust.setEmi(emi);
		Loan loan=new Loan();
		loan.setLoanId(1001);
		loan.setLoanAmount(loanAmount);
		loan.setInterestRate(interestRate);
		loan.setTerm(term);
		loan.setLoanType(loanType);
		cust.setLoan(loan);
		custList.add(cust);
		
		when(loanDAO.getReportByLoanType(loanType)).thenReturn(custList);
		List<Customer> actual=loanService.getReportByLoanType(loanType);
		Assert.assertEquals(custList, actual);
	}
	
	@Test
	public void getReportByLoanTypeInValidTest() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("Service.NO_LOAN_FOUND");
		
		String loanType="PersonalLoan";
		
		when(loanDAO.getReportByLoanType(loanType)).thenReturn(new ArrayList<Customer>());
		loanService.getReportByLoanType(loanType);
	}

}
