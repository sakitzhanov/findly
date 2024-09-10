package kz.asset.findly.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kz.asset.findly.model.dto.SubcategoryDto;
import kz.asset.findly.model.entity.Subcategory;
import kz.asset.findly.model.entity.SubcategoryTranslation;
import kz.asset.findly.repository.LanguageRepository;
import kz.asset.findly.repository.SubcategoryRepository;
import kz.asset.findly.repository.SubcategoryTranslationRepository;
import kz.asset.findly.util.MappingUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubcategoryService {
	private final SubcategoryRepository repository;
	private final LanguageRepository languageRepository;
	private final SubcategoryTranslationRepository subcategoryTranslationRepository;
	private final MappingUtil mappingUtil;
	
	public List<SubcategoryDto> findAll() {
		return repository.findAll().stream()
				.map(mappingUtil::convertToSubcategoryDto)
				.collect(Collectors.toList());
	}
	
	public SubcategoryDto findById(Integer id) {
		Subcategory subcategory = repository.findById(id).orElse(null);
		
		if (subcategory != null)
			return mappingUtil.convertToSubcategoryDto(subcategory);
		
		return null;
	}
	
	public SubcategoryDto save(SubcategoryDto dto) {
		Subcategory subcategory = repository.save(mappingUtil.convertToSubcategory(dto));
		
		List<SubcategoryTranslation> translations = dto.getTranslations().stream()
				.map(item -> {
					item.setSubcategory(dto);
					return item;
				})
				.map(item -> {
					SubcategoryTranslation result = mappingUtil.convertToSubcategoryTranslation(item);
					
					result.setSubcategory(subcategory);
					result.setLanguage(languageRepository.findById(item.getLanguage().getId()).get());
					
					return result;
				})
				.collect(Collectors.toList());
		
		subcategoryTranslationRepository.saveAll(translations);
		
		return mappingUtil.convertToSubcategoryDto(repository.findById(subcategory.getId()).get());
	}
	
	public SubcategoryDto update(SubcategoryDto dto) {
		Subcategory subcategory = repository.save(mappingUtil.convertToSubcategory(dto));
		
		List<SubcategoryTranslation> translations = dto.getTranslations().stream()
				.map(item -> {
					item.setSubcategory(dto);
					return item;
				})
				.map(item -> {
					SubcategoryTranslation result = mappingUtil.convertToSubcategoryTranslation(item);
					
					result.setSubcategory(subcategory);
					result.setLanguage(languageRepository.findById(item.getLanguage().getId()).get());
					
					return result;
				})
				.collect(Collectors.toList());
		
		subcategoryTranslationRepository.saveAll(translations);
		
		return mappingUtil.convertToSubcategoryDto(repository.findById(subcategory.getId()).get());
	}
	
	public boolean delete(Integer id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		
		return false;
	}
}
