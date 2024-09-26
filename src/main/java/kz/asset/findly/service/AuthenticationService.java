package kz.asset.findly.service;

import kz.asset.findly.model.request.CheckPhoneNumberRequest;
import kz.asset.findly.model.request.CheckUsernameRequest;
import kz.asset.findly.model.request.LoginRequest;
import kz.asset.findly.model.request.RegistrationRequest;
import kz.asset.findly.model.response.CredentialAvailabilityResponse;
import kz.asset.findly.model.response.JwtAuthenticationResponse;

public interface AuthenticationService {
	public JwtAuthenticationResponse login(LoginRequest request);
	public JwtAuthenticationResponse registration(RegistrationRequest request);
	public CredentialAvailabilityResponse checkUsername(CheckUsernameRequest request);
	public CredentialAvailabilityResponse checkPhoneNumber(CheckPhoneNumberRequest request);
}
