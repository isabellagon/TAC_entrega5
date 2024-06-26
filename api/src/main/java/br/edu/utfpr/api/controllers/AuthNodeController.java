package br.edu.utfpr.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.api.service.ExternalApiService;

@RestController
@RequestMapping("/authNode")
public class AuthNodeController {
    @Autowired
    private ExternalApiService externalApiService;

    @GetMapping("/send-token")
    public String sendToken(@RequestHeader("Authorization") String token) {
        return externalApiService.sendPostRequestWithToken(token);
    }
}
