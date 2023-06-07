package dev.isaac.springbootrestapp.controller;

import dev.isaac.springbootrestapp.annotation.TokenRequired;
import dev.isaac.springbootrestapp.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {

    private final JwtService jwtService;

    @TokenRequired
    @GetMapping("/")
    public String index() {
        return "Hello, Boot";
    }

    @GetMapping("/jwt/generate")
    public Map<String, Object> createJwt(@RequestParam String subject) {
        String jwt = jwtService.createToken(subject, 1000 * 60 * 60 * 24);
        Map<String, Object> map = new HashMap<>();
        map.put("subject", subject);
        map.put("jwt", jwt);
        return map;
    }

    @GetMapping("/jwt/get-subject")
    public String getSubject(@RequestParam String jwt) {
        String subject = jwtService.getSubject(jwt);
        return subject;
    }
}
