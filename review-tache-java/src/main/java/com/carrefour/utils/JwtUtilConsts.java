package com.carrefour.utils;

public class JwtUtilConsts {
	public static final String SECRET="sport2017";
	public static final String AUTH_HEADER="Authorization";
	public static final long EXPIRE_ACCESS_TOKEN=2*60*1000;
	public static final long EXPIRE_REFRESH_TOKEN=15*60*1000;
	public static final String PREFIX="Bearer ";
	
	public static final String[] AUTH_WHITELIST = {
			"/h2-console/**",
			"/users/refreshToken/**",
	        "/swagger-resources/**",
	        "/swagger-ui/**",
	        "/v3/api-docs",
	        "/webjars/**"
	};
}
