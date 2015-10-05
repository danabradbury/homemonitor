package com.bradbury.dana;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	private CustomerRepository repository;
	
	
	@RequestMapping("/create")
    public Customer createUser(@RequestParam(value="firstname") String firstName, 
    							@RequestParam(value="lastname") String lastName,
    							@RequestParam(value="registrationId") String registrationId) {
	
		Customer newCustomer = new Customer(firstName, lastName);
		if(StringUtils.isNotBlank(registrationId)){
			newCustomer.setRegistrationId(registrationId);
			newCustomer.setRegistrationDate(new Date());
		}
		Customer savedCustomer = repository.save(newCustomer);
		return savedCustomer;
	}

	@RequestMapping("/get")
    public List<Customer> getUser(@RequestParam(value="firstname") String firstName, @RequestParam(value="lastname") String lastName) {
	
		return repository.findByLastName(lastName);
	}
	
	@RequestMapping("/update")
    public Customer updateUser(@RequestParam(value="firstname") String firstName, @RequestParam(value="lastname") String lastName) {
	
		Customer newCustomer = new Customer(firstName, lastName);
		repository.save(newCustomer);
		return newCustomer;
	}
	
	@RequestMapping("/delete")
    public Customer deleteUser(@RequestParam(value="firstname") String firstName, @RequestParam(value="lastname") String lastName) {
	
		Customer foundCustomer = repository.findByFirstName(firstName);
		repository.delete(foundCustomer);
		return foundCustomer;
	}
	
	@RequestMapping("/list")
	public List<Customer> getCustomers(){
		
		List<Customer> customers = repository.findAll();
		return customers;
	}
}
