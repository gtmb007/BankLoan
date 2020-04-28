package com.gautam.validator;

import com.gautam.model.Loan;

public class Validator {
	
	public static void validate(Loan loan) throws Exception{
		if(! validateLoanType(loan.getLoanType())){
			throw new Exception("Validator.INVALID_LOANTYPE");
		}


	}

	public static Boolean validateLoanType(String loanType) {
		if(loanType.equals("HomeLoan") || loanType.equals("CarLoan")){
			return true;
		}
		return false;
	}
	
}
