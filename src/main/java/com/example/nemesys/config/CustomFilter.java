package com.example.nemesys.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class CustomFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Custom logic before the request is processed
        System.out.println("Custom filter triggered for: " + request.getRequestURI());
        System.out.println("Authorization header: " + request.getHeader("Authorization"));

        // Continue with the next filter in the chain
        filterChain.doFilter(request, response);

        // Custom logic after the response is processed
        System.out.println("Custom filter completed for: " + request.getRequestURI());
    }
}
