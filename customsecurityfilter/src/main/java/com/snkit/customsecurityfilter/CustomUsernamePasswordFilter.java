package com.snkit.customsecurityfilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.filter.GenericFilterBean;

public class CustomUsernamePasswordFilter extends GenericFilterBean {

	public CustomUsernamePasswordFilter() {

	}

	private AuthenticationManager authenticationManager;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;

		String url = request.getRequestURI();

		if (url.contains("/api/rest/")) {

			if (!("/".equalsIgnoreCase(url) || "/login".equalsIgnoreCase(url))) {

				try {

					final HttpServletResponse response = (HttpServletResponse) res;

					String header = request.getHeader("Authorization");

					if (header != null && header.startsWith("Basic ")) {

						String[] tokens = extractAndDecodeHeader(header, request);

						UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(tokens[0],
								tokens[1]);

						Authentication auth = this.getAuthenticationManager().authenticate(token);

						/*
						 * if (auth.isAuthenticated()) { return; }
						 */
					}
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}

		chain.doFilter(req, res);

	}

	private String[] extractAndDecodeHeader(String header, HttpServletRequest request) throws IOException {

		byte[] base64Token = header.substring(6).getBytes("UTF-8");
		byte[] decoded;
		try {
			decoded = Base64.decode(base64Token);
		} catch (IllegalArgumentException e) {
			throw new BadCredentialsException("Failed to decode basic authentication token");
		}
		String token = new String(decoded, "UTF-8");
		int delim = token.indexOf(":");

		if (delim == -1) {
			throw new BadCredentialsException("Invalid basic authentication token");
		}
		return new String[] { token.substring(0, delim), token.substring(delim + 1) };
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

}
