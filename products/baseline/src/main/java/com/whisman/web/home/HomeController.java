package com.whisman.web.home;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

	@RequestMapping(method = RequestMethod.GET)
	public String dashboard() {
		return "home/dashboard";
	}

}
