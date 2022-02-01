package arg.hero.challenge.auth.jwt;

import java.io.IOException;



import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	@Autowired
	private JWTProvider jwtProvider;
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			String token = getJwtFromRequest(request);
			
			if(StringUtils.hasText(token) && jwtProvider.validate(token)) {
				String username = jwtProvider.getUsernameFromToken(token);
				
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, 
																											 null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			
		} catch (Exception e) {
			logger.error("Fail while tryting to authenticate Token inside Filter");
		}
	
		
		filterChain.doFilter(request, response);
		
	}



	private String getJwtFromRequest(HttpServletRequest request) {
		
		String header = request.getHeader("Authorization");
		
		if(StringUtils.hasText(header) && header.startsWith("Bearer")) {
			return header.replace("Bearer ", "");
		}
		
		return null;
	}
	
	


}
