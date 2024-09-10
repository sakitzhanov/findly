package kz.asset.findly.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kz.asset.findly.model.dto.RegionDto;
import kz.asset.findly.model.entity.Region;
import kz.asset.findly.model.entity.RegionTranslation;
import kz.asset.findly.repository.LanguageRepository;
import kz.asset.findly.repository.RegionRepository;
import kz.asset.findly.repository.RegionTranslationRepository;
import kz.asset.findly.util.MappingUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegionService {
	private final RegionRepository repository;
	private final LanguageRepository languageRepository;
	private final RegionTranslationRepository regionTranslationRepository;
	private final MappingUtil mappingUtil;
	
	public List<RegionDto> findAll() {
		return repository.findAll().stream()
				.map(mappingUtil::convertToRegionDto)
				.collect(Collectors.toList());
	}
	
	public RegionDto findById(Integer id) {
		Region region = repository.findById(id).orElse(null);
		
		if (region != null)
			return mappingUtil.convertToRegionDto(region);
		
		return null;
	}
	
	public RegionDto save(RegionDto dto) {
		Region region = repository.save(mappingUtil.convertToRegion(dto));
		
		List<RegionTranslation> translations = dto.getTranslations().stream()
				.map(item -> {
					item.setRegion(dto);
					return item;
				})
				.map(item -> {
					RegionTranslation result = mappingUtil.convertToRegionTranslation(item);
					
					result.setRegion(region);
					result.setLanguage(languageRepository.findById(item.getLanguage().getId()).get());
					
					return result;
				})
				.collect(Collectors.toList());
		
		regionTranslationRepository.saveAll(translations);
		
		return mappingUtil.convertToRegionDto(repository.findById(region.getId()).get());
	}
	
	public RegionDto update(RegionDto dto) {
		Region region = repository.save(mappingUtil.convertToRegion(dto));
		
		List<RegionTranslation> translations = dto.getTranslations().stream()
				.map(item -> {
					item.setRegion(dto);
					return item;
				})
				.map(item -> {
					RegionTranslation result = mappingUtil.convertToRegionTranslation(item);
					
					result.setRegion(region);
					result.setLanguage(languageRepository.findById(item.getLanguage().getId()).get());
					
					return result;
				})
				.collect(Collectors.toList());
		
		regionTranslationRepository.saveAll(translations);
		
		return mappingUtil.convertToRegionDto(repository.findById(region.getId()).get());
	}
	
	public boolean delete(Integer id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		
		return false;
	}
}
