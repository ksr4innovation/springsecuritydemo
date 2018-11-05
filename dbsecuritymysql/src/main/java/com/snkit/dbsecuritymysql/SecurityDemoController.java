package com.snkit.dbsecuritymysql;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityDemoController {
	
	@GetMapping(value="/hi")
	public String sayHi() {
		
		
		return "Hello world this resource is protected  = "+SecurityContextHolder.getContext().getAuthentication().getName();
	}
	@GetMapping(value="/nonprotectedurl")
	public String nonprotectedurl() {
		
		
		return "nonprotectedurl ";
	}
	@GetMapping(value="/protectedurl")
	public String protectedurl() {
		
		
		return "protectedurl ";
	}

}
