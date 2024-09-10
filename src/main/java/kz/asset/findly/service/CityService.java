package kz.asset.findly.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kz.asset.findly.model.dto.CityDto;
import kz.asset.findly.model.entity.City;
import kz.asset.findly.model.entity.CityTranslation;
import kz.asset.findly.repository.CityRepository;
import kz.asset.findly.repository.CityTranslationRepository;
import kz.asset.findly.repository.LanguageRepository;
import kz.asset.findly.util.MappingUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityService {	
	private final CityRepository repository;
	private final LanguageRepository languageRepository;
	private final CityTranslationRepository cityTranslationRepository;
	private final MappingUtil mappingUtil;
	
	public List<CityDto> findAll() {
		return repository.findAll().stream()
				.map(mappingUtil::convertToCityDto)
				.collect(Collectors.toList());
	}
	
	public CityDto findById(Integer id) {
		City city = repository.findById(id).orElse(null);
		
		if (city != null)
			return mappingUtil.convertToCityDto(city);
		
		return null;
	}
	
	public CityDto save(CityDto dto) {
		City city = repository.save(mappingUtil.convertToCity(dto));
		
		List<CityTranslation> translations = dto.getTranslations().stream()
				.map(item -> {
					item.setCity(dto);
					return item;
				})
				.map(item -> {
					CityTranslation result = mappingUtil.convertToCityTranslation(item);
					
					result.setCity(city);
					result.setLanguage(languageRepository.findById(item.getLanguage().getId()).get());
					
					return result;
				})
				.collect(Collectors.toList());
		
		cityTranslationRepository.saveAll(translations);
		
		return mappingUtil.convertToCityDto(repository.findById(city.getId()).get());
	}
	
	public CityDto update(CityDto dto) {
		City city = repository.save(mappingUtil.convertToCity(dto));
		
		List<CityTranslation> translations = dto.getTranslations().stream()
				.map(item -> {
					item.setCity(dto);
					return item;
				})
				.map(item -> {
					CityTranslation result = mappingUtil.convertToCityTranslation(item);
					
					result.setCity(city);
					result.setLanguage(languageRepository.findById(item.getLanguage().getId()).get());
					
					return result;
				})
				.collect(Collectors.toList());
		
		cityTranslationRepository.saveAll(translations);
		
		return mappingUtil.convertToCityDto(repository.findById(city.getId()).get());
	}
	
	public boolean delete(Integer id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		
		return false;
	}
}
