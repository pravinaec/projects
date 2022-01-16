package com.example.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

@Component
public class RouteFilter extends ZuulFilter {
    @Override
    public Object run( ) throws ZuulException {
        System.out.println( "going through router filter" );
        return null;
    }
    @Override
    public boolean shouldFilter( ) {
        return true;
    }
    @Override
    public int filterOrder( ) {
        return 1;
    }
    @Override
    public String filterType( ) {
        return "router";
    }

}
