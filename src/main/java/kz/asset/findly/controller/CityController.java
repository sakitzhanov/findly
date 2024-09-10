package kz.asset.findly.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kz.asset.findly.model.dto.CityDto;
import kz.asset.findly.service.CityService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {
	private final CityService service;
	
	@GetMapping
	public ResponseEntity<List<CityDto>> all() {	
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CityDto> one(@PathVariable(name = "id") Integer id) {
		CityDto cityDto = service.findById(id);
		
		if (cityDto == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.status(HttpStatus.OK).body(cityDto);
	}
	
	@PostMapping
	public ResponseEntity<CityDto> create(@RequestBody CityDto dto) {
		CityDto cityDto = service.save(dto);			
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cityDto);
	}
	
	@PutMapping
	public ResponseEntity<CityDto> edit(@RequestBody CityDto dto) {
		CityDto cityDto = service.save(dto);			
		
		return ResponseEntity.status(HttpStatus.OK).body(cityDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
		if (!service.delete(id))
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
