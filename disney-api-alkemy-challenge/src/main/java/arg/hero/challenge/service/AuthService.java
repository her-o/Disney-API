package arg.hero.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import arg.hero.challenge.dto.LoginRequest;
import arg.hero.challenge.dto.RegisterRequest;
import arg.hero.challenge.jwt.JWTProvider;
import arg.hero.challenge.model.auth.AppUser;
import arg.hero.challenge.repository.AppUserRepository;

@Service
public class AuthService {
	
	@Autowired
	private AppUserRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JWTProvider jwtProvider;

	public void signup(RegisterRequest registerRequest) {
		System.out.println(registerRequest.getPassword());
		AppUser appUser = buildAppUserFromRegisterRequest(registerRequest);
		System.out.println(appUser.getPassword());
		this.repository.save(appUser);
	}

	private AppUser buildAppUserFromRegisterRequest(RegisterRequest registerRequest) {
		AppUser appUser = new AppUser();
		appUser.setUsername(registerRequest.getUsername());
		appUser.setEmail(registerRequest.getEmail());
		appUser.setPassword(encodePassword(registerRequest.getPassword()));
		return appUser;
	}
	
	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}

	public String login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), 
																				   loginRequest.getPassword()));
	
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return jwtProvider.generateToken(authentication);
	}
}
