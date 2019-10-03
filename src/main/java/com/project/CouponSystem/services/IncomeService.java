package com.project.CouponSystem.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.project.CouponSystem.beans.Company;
import com.project.CouponSystem.beans.Customer;
import com.project.CouponSystem.beans.Income;
import com.project.CouponSystem.repo.CompanyRepo;
import com.project.CouponSystem.repo.CustomerRepo;
import com.project.CouponSystem.repo.IncomeRepo;

@Transactional
@Service
public class IncomeService {

	
	@Autowired
	private IncomeRepo incomeRepo;
	
	@Autowired
	private CompanyRepo companyRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	
	public ResponseEntity<?> storeIncome (Income income) {
		if (income!=null) {
			return ResponseEntity.ok(incomeRepo.save(income));
		}
		return ResponseEntity.badRequest().body("Something went wrong");
	}
	

	public ResponseEntity<?> viewAllIncome () {
		
	List<Income> incomesList=new ArrayList<Income>();
	incomesList=incomeRepo.findAll();
	if (incomesList.size()>0) {
		return ResponseEntity.ok(incomesList);
	}
		return ResponseEntity.badRequest().body("No income reports in db");
	}
	

	public ResponseEntity<?> viewIncomeByCustomer (long customerId) {
		Optional<Customer> customer=customerRepo.findById(customerId);
		if (customer.isPresent()) {
			customer.get().getIncomeCollection();
			if (customer.get().getIncomeCollection() !=null && customer.get().getIncomeCollection().size()>0) {
				return ResponseEntity.ok(customer.get().getIncomeCollection());
			}
		}
		return ResponseEntity.badRequest().body("No customer with id "+customerId);
	}
	

	public ResponseEntity<?> viewIncomeByCompany (long companyId) {
		Optional<Company> company=companyRepo.findById(companyId);
		if (company.isPresent()) {
			company.get().getIncomeCollection();
			if (company.get().getIncomeCollection() !=null && company.get().getIncomeCollection().size()>0) {
				return ResponseEntity.ok(company.get().getIncomeCollection());
			}
		}
		return ResponseEntity.badRequest().body("No company with id "+companyId);
	}
	
}
