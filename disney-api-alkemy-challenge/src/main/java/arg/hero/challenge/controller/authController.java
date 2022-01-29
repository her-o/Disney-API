package arg.hero.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import arg.hero.challenge.dto.LoginRequest;
import arg.hero.challenge.dto.RegisterRequest;
import arg.hero.challenge.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class authController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/signin")
	public String login(@RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
	}
	
	@PostMapping("/signup")
	public  ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
		authService.signup(registerRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
