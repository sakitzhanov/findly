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

import kz.asset.findly.model.dto.CategoryDto;
import kz.asset.findly.service.CategoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
	private final CategoryService service;
	
	@GetMapping
	public ResponseEntity<List<CategoryDto>> all() {	
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> one(@PathVariable(name = "id") Integer id) {
		CategoryDto categoryDto = service.findById(id);
		
		if (categoryDto == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.status(HttpStatus.OK).body(categoryDto);
	}
	
	@PostMapping
	public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto dto) {
		CategoryDto categoryDto = service.save(dto);			
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryDto);
	}
	
	@PutMapping
	public ResponseEntity<CategoryDto> edit(@RequestBody CategoryDto dto) {
		CategoryDto categoryDto = service.save(dto);			
		
		return ResponseEntity.status(HttpStatus.OK).body(categoryDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
		if (!service.delete(id))
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
