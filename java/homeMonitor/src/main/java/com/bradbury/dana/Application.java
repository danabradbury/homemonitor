package com.bradbury.dana;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

@SpringBootApplication
public class Application implements CommandLineRunner{

	@Autowired
	private CustomerRepository repository;
	
	//api key from google app, get from dev console - console.developers.google.com
	private final String GCM_API_KEY = "AIzaSyCET8o3rFTzSt8y8Qdli8PcENpaNGnp7-c"; 
	//Registration key from an app running on a phone, this is hardcoded now, but would in practice come from 
	//a device and be stored in a DB
	private final String REGISTRATION_ID = "APA91bENtnLbamGMQa9I7AqB28svmttUz-YTFtEGpqTBV6S_k83I6XR5Ma1DeLUU_o-xUHd_Sow3uZi4ixXIiEaCjz4uLMMuix1yHaCNY7gvQhne8d37YSWn0gN9gsrg8tjF3GGcBTob";
	
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Override
	public void run(String... args) throws Exception {

    	System.out.println("app started, setup the repo");
    	//clear out the repo and populate some dummy users
		repository.deleteAll();

		// save a couple of customers
		repository.save(new Customer("Alice", "Smith"));
		repository.save(new Customer("Bob", "Smith"));
		System.out.println("repo setup, try to fire a GCM message");
		
		//test consuming GA data
		GAUtility gaUtil = new GAUtility();
		gaUtil.test();
		
		//test the push service
		Sender sender = new Sender(GCM_API_KEY);

		final String messageBody = "home monitor server started at " + new Date();
		Message message = new Message.Builder()
				 .addData("key1", "value1")
				 .addData("message", "Home Monitor")
				 .addData("title", messageBody)
				 .build();
		
		Result result = sender.send(message, REGISTRATION_ID, 3);
		
		System.out.println("GCM message sent, " + result + "\r\nsetup complete");
		
	}
}
