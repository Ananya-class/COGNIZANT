package com.cognizant.springlearn.controller;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @GetMapping("/authenticate") // [cite: 481]
    public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) { // [cite: 482, 484, 485]
        LOGGER.info("START: authenticate");
        LOGGER.debug("Authorization Header Received: {}", authHeader); // [cite: 487]

        String user = getUser(authHeader); // [cite: 510]
        String token = generateJwt(user); // [cite: 537]

        Map<String, String> response = new HashMap<>(); // [cite: 488]
        response.put("token", token); // [cite: 538]

        LOGGER.info("END: authenticate");
        return response;
    }

    private String getUser(String authHeader) { // [cite: 502]
        String encodedCredentials = authHeader.replace("Basic ", ""); // [cite: 503]
        String decoded = new String(Base64.getDecoder().decode(encodedCredentials)); // [cite: 505, 506]
        return decoded.split(":")[0]; // [cite: 507]
    }

    private String generateJwt(String user) { // [cite: 522]
        JwtBuilder builder = Jwts.builder(); // [cite: 524]
        builder.setSubject(user); // [cite: 525]
        builder.setIssuedAt(new Date()); // [cite: 527]
        builder.setExpiration(new Date(System.currentTimeMillis() + 1200000)); // [cite: 529]
        builder.signWith(SignatureAlgorithm.HS256, "secretkey"); // [cite: 530]
        return builder.compact(); // [cite: 531]
    }
}
