package kz.asset.findly.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kz.asset.findly.model.dto.ImageDto;
import kz.asset.findly.model.dto.LostPropertyDto;
import kz.asset.findly.model.entity.Image;
import kz.asset.findly.model.entity.LostProperty;
import kz.asset.findly.repository.ImageRepository;
import kz.asset.findly.repository.LostPropertyRepository;
import kz.asset.findly.util.MappingUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LostPropertyService {
	private final LostPropertyRepository repository;
	private final ImageRepository imageRepository;
	private final MappingUtil mappingUtil;
	
	public List<LostPropertyDto> findAll() {
		return repository.findAll().stream()
				.map(mappingUtil::convertToLostPropertyDto)
				.collect(Collectors.toList());
	}
	
	public LostPropertyDto findById(Integer id) {
		LostProperty lostProperty = repository.findById(id).orElse(null);
		
		if (lostProperty != null)
			return mappingUtil.convertToLostPropertyDto(lostProperty);
		
		return null;
	}
	
	public LostPropertyDto save(LostPropertyDto dto) {
		LostProperty lostProperty = repository.save(mappingUtil.convertToLostProperty(dto));
		
		List<Image> images = imageRepository.findAllById(dto.getImages().stream()
				.map(ImageDto::getId)
				.toList());
		
		images.forEach(item -> item.setLostProperty(lostProperty));
		
		imageRepository.saveAll(images);
		
		return mappingUtil.convertToLostPropertyDto(lostProperty);
	}
	
	public LostPropertyDto update(LostPropertyDto dto) {
		LostProperty lostProperty = repository.save(mappingUtil.convertToLostProperty(dto));
		
		List<Image> images = imageRepository.findAllById(dto.getImages().stream()
				.map(ImageDto::getId)
				.toList());
		
		images.forEach(item -> item.setLostProperty(lostProperty));
		
		imageRepository.saveAll(images);
		
		return mappingUtil.convertToLostPropertyDto(lostProperty);
	}
	
	public boolean delete(Integer id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		
		return false;
	}
}
