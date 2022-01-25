package kr.or.spring.instagram_clone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	@GetMapping(path="/login")
	public String login() {
		return "login";
	}

}
