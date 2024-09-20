package kz.asset.findly.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kz.asset.findly.model.dto.UserDto;
import kz.asset.findly.model.entity.User;
import kz.asset.findly.repository.UserRepository;
import kz.asset.findly.util.MappingUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repository;
	@Autowired
	private MappingUtil mappingUtil;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUsername(username);
		
		if (user == null)
			throw new UsernameNotFoundException(String.format("%s is not found...", username));
		
		return user;
	}

	@Override
	public User findByUsername(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public User save(User user) {
		return repository.save(user);
	}
	
	@Override
	public List<UserDto> findAll() {
		List<UserDto> result =  repository.findAll().stream()
				.map(mappingUtil::convertToUserDto)
				.collect(Collectors.toList());
		
		result.forEach(user -> user.setPassword(null));
		
		return result;
	}
	
	@Override
	public UserDto findById(Integer id) {
		User user = repository.findById(id).orElse(null);
		
		if (user != null) {
			return mappingUtil.convertToUserDto(user);
		}
		
		return null;		
	}
	
	@Override
	public UserDto update(UserDto dto) {
		User user = repository.save(mappingUtil.convertToUser(dto));
		UserDto result = mappingUtil.convertToUserDto(user);
		
		result.setPassword(null);
		
		return result;
	}
	
//	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
//		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//	}	
}
