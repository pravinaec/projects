package com.example.zuul.filter;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ErrorFilter extends ZuulFilter {
	@Override
	public Object run() throws ZuulException {
		System.out.println("going through error filter");
		return null;
	}
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 1;
	}
	@Override
	public String filterType() {
		return "error";
	}
}
