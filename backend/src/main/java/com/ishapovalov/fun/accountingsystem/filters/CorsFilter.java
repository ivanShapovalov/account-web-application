package com.ishapovalov.fun.accountingsystem.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cors filter.
 */
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String origin = ((HttpServletRequest) request).getHeader("Origin");

        if (((HttpServletRequest) request).getMethod().equals("OPTIONS")) {
            HttpServletResponse resp = ((HttpServletResponse) response);
            addCorsHeaders(resp, origin);
            return;
        }

        if (response instanceof HttpServletResponse) {
            HttpServletResponse alteredResponse = ((HttpServletResponse) response);
            addCorsHeaders(alteredResponse, origin);
        }
        filterChain.doFilter(request, response);
    }

    private void addCorsHeaders(HttpServletResponse response, String origin) {
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "authorization,Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, HEAD, DELETE");
    }

    @Override
    public void destroy() {}

    @Override
    public void init(FilterConfig filterConfig) {}
}