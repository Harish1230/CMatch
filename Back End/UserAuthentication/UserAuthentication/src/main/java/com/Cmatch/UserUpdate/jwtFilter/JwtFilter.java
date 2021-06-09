package com.Cmatch.UserUpdate.jwtFilter;

import org.springframework.web.filter.GenericFilterBean;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/* This class implements the custom filter by extending org.springframework.web.filter.GenericFilterBean.  
 * Override the doFilter method with ServletRequest, ServletResponse and FilterChain.
 * This is used to authorize the API access for the application.
 */
public class JwtFilter extends GenericFilterBean {

	/*
	 * Override the doFilter method of GenericFilterBean. Retrieve the
	 * "authorization" header from the HttpServletRequest object. Retrieve the
	 * "Bearer" token from "authorization" header. If authorization header is
	 * invalid, throw Exception with message. Parse the JWT token and get claims
	 * from the token using the secret key Set the request attribute with the
	 * retrieved claims Call FilterChain object's doFilter() method
	 */

	@Override
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		final String Header = request.getHeader("Authorization");
		if (Header == null || !Header.startsWith("Bearer ")) {
			throw new ServletException("Missing or invalid Authorization header.");
		}
		final String compactJws = Header.substring(7);

		try {
			JwtParser jwtParser = Jwts.parser().setSigningKey("secretKey");
			Jwt jwt = jwtParser.parse(compactJws);
			Claims claims = (Claims) jwt.getBody();
			request.setAttribute("claims", claims);

			String userId = claims.getSubject();
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("LoggedInUserId", userId);

		} catch (SignatureException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServletException("Invalid Token");

		} catch (MalformedJwtException jwtException) {
			throw new ServletException("Jwt is malformed");
		}

		chain.doFilter(request, response);
	}
}
