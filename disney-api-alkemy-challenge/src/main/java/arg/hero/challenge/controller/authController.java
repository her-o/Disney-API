package arg.hero.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import arg.hero.challenge.dto.JwtDto;
import arg.hero.challenge.dto.LoginRequest;
import arg.hero.challenge.dto.RegisterRequest;
import arg.hero.challenge.service.AuthService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@Api(tags = "Authentication")
public class authController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/signin")
	public ResponseEntity<?> login(@RequestParam("email") String email, 
									@RequestParam("password") String password) {
			
		return new ResponseEntity<JwtDto>(authService.signIn(new LoginRequest(email, password)), HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public  ResponseEntity<?> register(@RequestParam("email") String email, 
									@RequestParam("password") String password) {
		
		RegisterRequest registerRequest = new RegisterRequest(email, password);
		if(authService.emailAlreadyExists(registerRequest.getEmail())) {
			return new ResponseEntity<String>("There's already an user registered with that email.", HttpStatus.BAD_REQUEST);
		}
		
		authService.signUp(registerRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
