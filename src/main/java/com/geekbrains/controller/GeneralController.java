package com.geekbrains.controller;

import com.geekbrains.aspect.Timer;
import com.geekbrains.model.AuthRequest;
import com.geekbrains.model.AuthResponse;
import com.geekbrains.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Timer
@Controller
public class GeneralController {

    private AuthenticationManager authenticationManager;

    private JwtService jwtService;

    @Autowired
    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "redirect:/signin.html";
    }

    @PostMapping("/auth")
    @ResponseBody
    public AuthResponse auth(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);
        return new AuthResponse(token);
    }

//    @Timer
    @GetMapping("/wait")
    @ResponseBody
    public void waitRandom() throws InterruptedException {
        Thread.sleep((long) (Math.random() * 1000));
    }

    @GetMapping("/wait2")
    @ResponseBody
    public void waitRandom2() throws InterruptedException {
        Thread.sleep((long) (Math.random() * 1000));
        throw new InterruptedException();
    }


}
