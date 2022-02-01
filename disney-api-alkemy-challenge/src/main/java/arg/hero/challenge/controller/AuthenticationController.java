package arg.hero.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import arg.hero.challenge.auth.dto.JwtDto;
import arg.hero.challenge.auth.dto.LoginRequest;
import arg.hero.challenge.auth.dto.RegisterRequest;
import arg.hero.challenge.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@Api(tags = "Authentication")
public class AuthenticationController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/login")
	@ApiOperation(value = "Login into the API",
	notes = "Enter your email and password to log in.\nIn order to authorize your requests copy the token "
			+ "from the response's body and, in the 'Authorize' section on top, write the word 'Bearer' followed up by your token.")
	public ResponseEntity<?> login(@ApiParam(value="The email you used to register to the API.") 
								   @RequestParam("email") String email,
								   @ApiParam(value="The password you used to register to the API.") 
								   @RequestParam("password") String password) {
			
		return new ResponseEntity<JwtDto>(authService.signIn(new LoginRequest(email, password)), HttpStatus.OK);
	}
	
	@PostMapping("/register")
	@ApiOperation(value = "Sign up into the API",
	notes = "Provide a specific, unique email and a password to register into the API that you can later"
			+ " use to sign in.\n"
			+ "Upon succesful registration you should receive a welcome email.")
	public  ResponseEntity<?> register(@ApiParam(value="Your personal email.") 
									   @RequestParam("email") String email,
									   @ApiParam(value="Enter a password.") 
									   @RequestParam("password") String password) {
		
		RegisterRequest registerRequest = new RegisterRequest(email, password);
		if(authService.emailAlreadyExists(registerRequest.getEmail())) {
			return new ResponseEntity<String>("There's already an user registered with that email.", HttpStatus.BAD_REQUEST);
		}
		
		authService.signUp(registerRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
