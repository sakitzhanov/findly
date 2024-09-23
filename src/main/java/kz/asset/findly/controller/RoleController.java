package kz.asset.findly.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kz.asset.findly.model.dto.RoleDto;
import kz.asset.findly.service.RoleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
	private final RoleService service;
	
	@GetMapping
	public ResponseEntity<List<RoleDto>> all() {	
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
}
