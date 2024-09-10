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

import kz.asset.findly.model.dto.SubcategoryDto;
import kz.asset.findly.service.SubcategoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/subcategories")
@RequiredArgsConstructor
public class SubcategoryController {
	private final SubcategoryService service;
	
	@GetMapping
	public ResponseEntity<List<SubcategoryDto>> all() {	
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SubcategoryDto> one(@PathVariable(name = "id") Integer id) {
		SubcategoryDto subcategoryDto = service.findById(id);
		
		if (subcategoryDto == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.status(HttpStatus.OK).body(subcategoryDto);
	}
	
	@PostMapping
	public ResponseEntity<SubcategoryDto> create(@RequestBody SubcategoryDto dto) {
		SubcategoryDto subcategoryDto = service.save(dto);			
		
		return ResponseEntity.status(HttpStatus.CREATED).body(subcategoryDto);
	}
	
	@PutMapping
	public ResponseEntity<SubcategoryDto> edit(@RequestBody SubcategoryDto dto) {
		SubcategoryDto subcategoryDto = service.save(dto);			
		
		return ResponseEntity.status(HttpStatus.OK).body(subcategoryDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
		if (!service.delete(id))
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
