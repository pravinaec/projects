package com.example.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;
@Component
public class PostFilter extends ZuulFilter {
	@Override
	public String filterType( ) {
		return "post";
	}
    @Override
    public Object run( ) throws ZuulException {
        System.out.println( "going through post filter" );
        return null;
    }
    @Override
    public boolean shouldFilter( ) {
        return true;
    }
    @Override
    public int filterOrder( ) {
        // TODO Auto-generated method stub
        return 1;
    }
}
