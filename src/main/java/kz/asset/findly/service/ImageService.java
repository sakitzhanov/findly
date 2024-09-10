package kz.asset.findly.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kz.asset.findly.model.dto.ImageDto;
import kz.asset.findly.model.entity.Image;
import kz.asset.findly.repository.ImageRepository;
import kz.asset.findly.util.MappingUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {
	@Value("${image-storage.directory.name}")
	private String location;
	private final ImageRepository repository;
	private final MappingUtil mappingUtil;
	
	public ImageDto upload(MultipartFile file) throws IOException {		
		String originalName = file.getOriginalFilename();
		UUID uuid = UUID.randomUUID();
		String name = uuid.toString() + originalName.substring(originalName.lastIndexOf("."));
		
		Path parentFolder = Paths.get(location);
		
		if (!Files.exists(parentFolder))
			Files.createDirectory(parentFolder);
		
		Path path = parentFolder.resolve(name);
		
		Image image = repository.save(Image.builder()
				.name(name)
				.type(file.getContentType())
				.path(path.toString())
				.build());
		
		file.transferTo(path);
		
		return mappingUtil.convertToImageDto(image);
	}
	
	public Image download(String name) throws IOException {
		Image image = repository.findByName(name).orElse(null);
		
		return image;
	}
}
