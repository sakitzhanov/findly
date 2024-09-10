package kz.asset.findly.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kz.asset.findly.model.entity.User;
import kz.asset.findly.model.request.LoginRequest;
import kz.asset.findly.model.request.RegistrationRequest;
import kz.asset.findly.model.response.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;

	@Override
	public JwtAuthenticationResponse login(LoginRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		User user = userService.findByUsername(request.getUsername());
		
		if (user == null)
			throw new IllegalArgumentException("Invalid username or password");
		
		String jwt = jwtService.generateToken(user);
		
		return JwtAuthenticationResponse.builder().token(jwt).build();		
	}

	@Override
	public JwtAuthenticationResponse registration(RegistrationRequest request) {
		User user = User.builder()
				.username(request.getUsername())
				.password(passwordEncoder.encode(request.getPassword()))
				.phoneNumber(request.getPhoneNumber())
				.build();
		
		userService.save(user);
		
		String jwt = jwtService.generateToken(user);
		
		return JwtAuthenticationResponse.builder().token(jwt).build();
	}
}
