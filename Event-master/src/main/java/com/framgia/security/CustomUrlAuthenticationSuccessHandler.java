package com.framgia.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 
 * @version 22/05/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
@Component
public class CustomUrlAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private static final Logger logger = Logger.getLogger(CustomUrlAuthenticationSuccessHandler.class);

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		logger.info("Login success");
		String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(Authentication authentication) {

		String url = "";

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		List<String> permission = new ArrayList<String>();

		for (GrantedAuthority a : authorities) {
			permission.add(a.getAuthority());
		}

		if (isAdmin(permission)) {
			logger.info("permission admin");
			url = "/admin";
		} else if (isAdmin(permission) || isManager(permission)) {
			logger.info("permission admin or manager");
			url = "/manager";
		} else if (isUser(permission) || isAdmin(permission) || isManager(permission)) {
			logger.info("permission admin or manager or user normal");
			url = "/user";
		} else {
			url = "/403";
		}

		return url;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	private boolean isUser(List<String> permission) {
		if (permission.contains(PermissionType.USER.getPermissionType())) {
			return true;
		}
		return false;
	}

	private boolean isManager(List<String> permission) {
		if (permission.contains(PermissionType.MANAGER.getPermissionType())) {
			return true;
		}
		return false;
	}

	private boolean isAdmin(List<String> permission) {
		if (permission.contains(PermissionType.ADMIN.getPermissionType())) {
			return true;
		}
		return false;
	}

}