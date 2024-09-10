package kz.asset.findly.controller;

import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kz.asset.findly.model.dto.ImageDto;
import kz.asset.findly.model.entity.Image;
import kz.asset.findly.service.ImageService;

@RestController
@RequestMapping("/api/images")
public class ImageController {
	@Autowired
	private ImageService service;
	
	@PostMapping
	public ResponseEntity<ImageDto> upload(@RequestParam("file") MultipartFile file) throws IOException {
		ImageDto imageDto = service.upload(file);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(imageDto);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<?> download(@PathVariable String name) throws IOException {
		Image image = service.download(name);
		
		if (image == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		String path = image.getPath();
		Resource data = new UrlResource(Paths.get(path).toUri());
		
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf(image.getType()))
				.body(data);		
	}
}
