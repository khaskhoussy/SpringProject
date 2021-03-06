package com.example.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;


@Configuration
public class CustomLoginSeccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
	
		String targetUrl= determineTargerUrl(authentication) ;
		
		if(response.isCommitted())
		{
			return  ;
		}
		RedirectStrategy redirectStrategy =new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	protected String determineTargerUrl(Authentication authentication)
	{
		String targetUrl="";
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for(GrantedAuthority a : authorities){
			roles.add(a.getAuthority());
		}
		if(roles.contains("ROLE_USER"))
		
			return  "/user/Home" ;
		if(roles.contains("ROLE_ADMIN"))
			return "/admin/Home";
		//////
		if(roles.contains("ROLE_EXPERT"))
			
			return  "/expert/Home" ;
		
		if(roles.contains("ROLE_IEXPERT"))
			
			return  "/expert_insurance/Home" ;			
		return "";
		
		 
	}

	

}
