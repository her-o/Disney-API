package arg.hero.challenge.service;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import arg.hero.challenge.auth.AppUser;
import arg.hero.challenge.auth.dto.JwtDto;
import arg.hero.challenge.auth.dto.LoginRequest;
import arg.hero.challenge.auth.dto.RegisterRequest;
import arg.hero.challenge.auth.jwt.JWTProvider;
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

	public void signUp(RegisterRequest registerRequest) {
			AppUser appUser = buildAppUserFromRegisterRequest(registerRequest);
			this.repository.save(appUser);
			try {
				sendEmail(appUser);
			} catch (UnsupportedEncodingException | MessagingException e) {
				e.printStackTrace();
			}
	}
	
	

	public JwtDto signIn(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), 
																				   loginRequest.getPassword()));
	
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return convertToJwtDto(authentication);
	}

	private AppUser buildAppUserFromRegisterRequest(RegisterRequest registerRequest) {
		AppUser appUser = new AppUser();
		appUser.setEmail(registerRequest.getEmail());
		appUser.setPassword(encodePassword(registerRequest.getPassword()));
		return appUser;
	}
	
	private JwtDto convertToJwtDto(Authentication authentication) {
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		return new JwtDto(jwt, userDetails.getUsername(), 
				userDetails.getAuthorities());
	}
	
	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}

	public boolean emailAlreadyExists(String email) {
		return repository.existsByEmail(email);
	}
	
	private void sendEmail(AppUser appUser) throws MessagingException, UnsupportedEncodingException {
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("hernan.olmos11@gmail.com");
		mailSender.setPassword("wuoxxpwfgusgului");
		
		Properties mailProperties = new Properties();
		mailProperties.setProperty("mail.smtp.auth", "true");
		mailProperties.setProperty("mail.smtp.starttls.enable", "true");
		
		mailSender.setJavaMailProperties(mailProperties);
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom("hernan.olmos11@gmail.com", "Disney API - Alkemy Challenge");
		helper.setTo(appUser.getEmail());
		helper.setSubject("Registration Successful");
		
		String content = "Thanks for signing up for this API. You can now start using it!.";
		helper.setText(content, true);
		
		mailSender.send(message);
	}
}
