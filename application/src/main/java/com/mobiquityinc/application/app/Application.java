package com.mobiquityinc.application.app;

import com.mobiquityinc.packagelibrary.exception.APIException;
import com.mobiquityinc.packagelibrary.packer.Packer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mobiquityinc.packagelibrary"})
public class Application {
	
	public static void main(String[] args) {
		
		ApplicationContext  context = SpringApplication.run(Application.class, args);
		Packer service = (Packer) context.getBean("packer");
		
		try {
			if (args.length > 0){
				service.pack(args[0]);
			}
		} catch (APIException e) {
			e.printStackTrace();
		}
	}
}
