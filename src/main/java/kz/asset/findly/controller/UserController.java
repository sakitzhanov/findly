package kz.asset.findly.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kz.asset.findly.model.dto.RoleDto;
import kz.asset.findly.model.dto.UserDto;
import kz.asset.findly.model.entity.Role;
import kz.asset.findly.model.entity.User;
import kz.asset.findly.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDto>> all() {	
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> one(@PathVariable(name = "id") Integer id) {
		UserDto userDto = service.findById(id);
		
		if (userDto == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		userDto.setPassword(null);
		
		return ResponseEntity.status(HttpStatus.OK).body(userDto);
	}
	
	@PutMapping
	public ResponseEntity<UserDto> edit(@RequestBody UserDto dto, Authentication authentication) {
		User authUser = (User) authentication.getPrincipal();
		Set<String> authRoleNames = authUser.getRoles().stream()
				.map(Role::getName)
				.collect(Collectors.toSet());
		
		if (!authUser.getId().equals(dto.getId()) && !(authRoleNames.contains("ADMIN") || authRoleNames.contains("MODER")))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		
		UserDto user = service.findById(dto.getId());
		
		if (!user.getRoles().equals(dto.getRoles()) && !authRoleNames.contains("ADMIN"))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		
		if (dto.getPassword() != null && !dto.getPassword().trim().isEmpty()) {
			Set<String> roleNames = dto.getRoles().stream()
					.map(RoleDto::getName)
					.collect(Collectors.toSet());
			
			if ((roleNames.contains("ADMIN") || roleNames.contains("MODER")) && !(authRoleNames.contains("ADMIN") || authUser.getId().equals(dto.getId())))
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();				
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(service.update(dto));
	}
}
