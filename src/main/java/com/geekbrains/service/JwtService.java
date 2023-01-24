package com.geekbrains.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JwtService {



    public String generateToken(UserDetails userDetails) {
        String username = userDetails.getUsername();
        List<String> authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        Map<String, Object> claims = new HashMap<>(Map.of("authorities", authorities));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 6000000))
                .signWith(SignatureAlgorithm.HS256, "98fsd9v0dh23njnN#Nnoj309*(*7y4n")
                .compact();
    }

    public String getUsername(String header) {
        return parse(header).getSubject();
    }

    public List<GrantedAuthority> getAuthorities(String header) {
        return ((List<String>) parse(header).get("authorities"))
                .stream()
                .map(SimpleGrantedAuthority::new)
                .map(sga -> (GrantedAuthority) sga)
                .toList();
    }

    public Claims parse(String header) {
        return Jwts.parser()
                .setSigningKey("98fsd9v0dh23njnN#Nnoj309*(*7y4n")
                .parseClaimsJws(header)
                .getBody();
    }
}
