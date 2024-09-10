package kz.asset.findly.service;

import kz.asset.findly.model.request.LoginRequest;
import kz.asset.findly.model.request.RegistrationRequest;
import kz.asset.findly.model.response.JwtAuthenticationResponse;

public interface AuthenticationService {
	public JwtAuthenticationResponse login(LoginRequest request);
	public JwtAuthenticationResponse registration(RegistrationRequest request);
}
