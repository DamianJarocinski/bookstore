package com.dejot.bookstore;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

       //warunek testowy, do usuniecia
        if(servletRequest.getContentType() != null ) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            servletResponse.getWriter().write("Odmowa dostepu");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
