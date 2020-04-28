package com.gautam.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gautam.model.Customer;
import com.gautam.service.LoanService;


@RestController
@RequestMapping(value="/loans")
public class LoanAPI {

	@Autowired
	private LoanService loanService;

	@Autowired
	public Environment environment;
	
	@PostMapping(value="/")
	public ResponseEntity<String> addCustomer(@RequestBody Customer customer) 
			throws Exception {
		try {
			Integer custId=loanService.addCustomer(customer);
			String message=environment.getProperty("API.CUSTOMER_ADDED")+custId;
			ResponseEntity<String> response=new ResponseEntity<String>(message, HttpStatus.CREATED);
			return response;
		} catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()), e);
		}
	}
	
	@PostMapping(value="/loan")
	public ResponseEntity<String> sanctionLoan(@RequestBody Customer customer)
			throws Exception {
		try {
			Integer loanId=loanService.sanctionLoan(customer);
			String message=environment.getProperty("API.SANCTION_SUCCESS")+loanId;
			ResponseEntity<String> response=new ResponseEntity<String>(message, HttpStatus.CREATED);
			return response;
		} catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()), e);
		}
	}

	@GetMapping(value="/{loanType}")
	public ResponseEntity<List<Customer>> getReportByLoanType(@PathVariable String loanType) throws Exception {
		try {
			List<Customer> custList=loanService.getReportByLoanType(loanType);
			ResponseEntity<List<Customer>> response=new ResponseEntity<List<Customer>>(custList, HttpStatus.OK);
			return response;
		} catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()), e);
		}
	}

}
