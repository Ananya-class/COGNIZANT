package com.cognizant.springlearn.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter { // [cite: 549]

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) { // [cite: 551]
        super(authenticationManager); // [cite: 552]
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException { // [cite: 559, 560]
        LOGGER.info("START: doFilterInternal");
        String header = req.getHeader("Authorization"); // [cite: 562]

        if (header == null || !header.startsWith("Bearer ")) { // [cite: 564]
            chain.doFilter(req, res); // [cite: 565]
            return; // [cite: 566]
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req); // [cite: 568]
        SecurityContextHolder.getContext().setAuthentication(authentication); // [cite: 569]
        chain.doFilter(req, res); // [cite: 570]
        LOGGER.info("END: doFilterInternal");
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) { // [cite: 575]
        String token = request.getHeader("Authorization"); // [cite: 576]
        if (token != null) {
            try {
                Jws<Claims> jws = Jwts.parser() // [cite: 581]
                        .setSigningKey("secretkey") // [cite: 582]
                        .parseClaimsJws(token.replace("Bearer ", "")); // [cite: 583]
                String user = jws.getBody().getSubject(); // [cite: 584]

                if (user != null) {
                    return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>()); // [cite: 587]
                }
            } catch (JwtException ex) { // [cite: 589]
                return null; // [cite: 590]
            }
        }
        return null;
    }
}
