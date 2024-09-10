package kz.asset.findly.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import kz.asset.findly.model.entity.User;

public interface UserService extends UserDetailsService {
	public User findByUsername(String username);
	public User save(User user);
}
