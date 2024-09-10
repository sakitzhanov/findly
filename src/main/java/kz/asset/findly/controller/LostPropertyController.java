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

import kz.asset.findly.model.dto.LostPropertyDto;
import kz.asset.findly.service.LostPropertyService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/lost-properties")
@RequiredArgsConstructor
public class LostPropertyController {
	private final LostPropertyService service;
	
	@GetMapping
	public ResponseEntity<List<LostPropertyDto>> all() {	
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LostPropertyDto> one(@PathVariable(name = "id") Integer id) {
		LostPropertyDto lostPropertyDto = service.findById(id);
		
		if (lostPropertyDto == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.status(HttpStatus.OK).body(lostPropertyDto);
	}
	
	@PostMapping
	public ResponseEntity<LostPropertyDto> create(@RequestBody LostPropertyDto dto) {
		LostPropertyDto lostPropertyDto = service.save(dto);			
		
		return ResponseEntity.status(HttpStatus.CREATED).body(lostPropertyDto);
	}
	
	@PutMapping
	public ResponseEntity<LostPropertyDto> edit(@RequestBody LostPropertyDto dto) {
		LostPropertyDto lostPropertyDto = service.save(dto);			
		
		return ResponseEntity.status(HttpStatus.OK).body(lostPropertyDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
		if (!service.delete(id))
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}
