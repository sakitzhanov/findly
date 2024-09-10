package kz.asset.findly.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import kz.asset.findly.model.request.LoginRequest;
import kz.asset.findly.model.request.RegistrationRequest;
import kz.asset.findly.model.response.JwtAuthenticationResponse;
import kz.asset.findly.service.AuthenticationService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {
	private final AuthenticationService service;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(service.login(request));
	}
	
	@PostMapping("/registration")
	public ResponseEntity<JwtAuthenticationResponse> registration(@RequestBody RegistrationRequest request) {
		return ResponseEntity.ok(service.registration(request));
		
	}
}
