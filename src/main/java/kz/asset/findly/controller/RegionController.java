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

import kz.asset.findly.model.dto.RegionDto;
import kz.asset.findly.service.RegionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/regions")
@RequiredArgsConstructor
public class RegionController {
	private final RegionService service;
	
	@GetMapping
	public ResponseEntity<List<RegionDto>> all() {	
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RegionDto> one(@PathVariable(name = "id") Integer id) {
		RegionDto regionDto = service.findById(id);
		
		if (regionDto == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.status(HttpStatus.OK).body(regionDto);
	}
	
	@PostMapping
	public ResponseEntity<RegionDto> create(@RequestBody RegionDto dto) {
		RegionDto regionDto = service.save(dto);			
		
		return ResponseEntity.status(HttpStatus.CREATED).body(regionDto);
	}
	
	@PutMapping
	public ResponseEntity<RegionDto> edit(@RequestBody RegionDto dto) {
		RegionDto regionDto = service.update(dto);			
		
		return ResponseEntity.status(HttpStatus.OK).body(regionDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
		if (!service.delete(id))
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
