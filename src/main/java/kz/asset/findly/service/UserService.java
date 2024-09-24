package kz.asset.findly.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import kz.asset.findly.model.dto.UserDto;
import kz.asset.findly.model.entity.User;

public interface UserService extends UserDetailsService {
	public List<UserDto> findAll();
	public UserDto findById(Integer id);
	public User save(User user);
	public UserDto update(UserDto dto);
	public User loadUserByUsername(String username);
	public User loadUserById(Integer id);
}
