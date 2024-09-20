package kz.asset.findly.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import kz.asset.findly.model.dto.UserDto;
import kz.asset.findly.model.entity.User;

public interface UserService extends UserDetailsService {
	public UserDto findById(Integer id);
	public User findByUsername(String username);
	public User save(User user);
	public List<UserDto> findAll();
	public UserDto update(UserDto dto);
}
