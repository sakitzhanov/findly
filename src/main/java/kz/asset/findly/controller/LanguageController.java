package kz.asset.findly.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kz.asset.findly.model.dto.LanguageDto;
import kz.asset.findly.service.LanguageService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/languages")
@RequiredArgsConstructor
public class LanguageController {
	private final LanguageService service;
	
	@GetMapping
	public ResponseEntity<List<LanguageDto>> all() {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
}
