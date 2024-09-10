package kz.asset.findly.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kz.asset.findly.model.dto.CategoryDto;
import kz.asset.findly.model.entity.Category;
import kz.asset.findly.model.entity.CategoryTranslation;
import kz.asset.findly.repository.CategoryRepository;
import kz.asset.findly.repository.CategoryTranslationRepository;
import kz.asset.findly.repository.LanguageRepository;
import kz.asset.findly.util.MappingUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
	private final CategoryRepository repository;
	private final LanguageRepository languageRepository;
	private final CategoryTranslationRepository categoryTranslationRepository;
	private final MappingUtil mappingUtil;
	
	public List<CategoryDto> findAll() {
		return repository.findAll().stream()
				.map(mappingUtil::convertToCategoryDto)
				.collect(Collectors.toList());
	}
	
	public CategoryDto findById(Integer id) {
		Category category = repository.findById(id).orElse(null);
		
		if (category != null)
			mappingUtil.convertToCategoryDto(category);
		
		return null;
	}
	
	public CategoryDto save(CategoryDto dto) {
		Category category = repository.save(mappingUtil.convertToCategory(dto));
		
		List<CategoryTranslation> translations = dto.getTranslations().stream()
				.map(item -> {
					item.setCategory(dto);
					return item;
				})
				.map(item -> {
					CategoryTranslation result = mappingUtil.convertToCategoryTranslation(item);
					
					result.setCategory(category);
					result.setLanguage(languageRepository.findById(item.getLanguage().getId()).get());
					
					return result;
				})
				.collect(Collectors.toList());
		
		categoryTranslationRepository.saveAll(translations);
		
		return mappingUtil.convertToCategoryDto(repository.findById(category.getId()).get());
	}
	
	public CategoryDto update(CategoryDto dto) {
		Category category = repository.save(mappingUtil.convertToCategory(dto));
		
		List<CategoryTranslation> translations = dto.getTranslations().stream()
				.map(item -> {
					item.setCategory(dto);
					return item;
				})
				.map(item -> {
					CategoryTranslation result = mappingUtil.convertToCategoryTranslation(item);
					
					result.setCategory(category);
					result.setLanguage(languageRepository.findById(item.getLanguage().getId()).get());
					
					return result;
				})
				.collect(Collectors.toList());
		
		categoryTranslationRepository.saveAll(translations);
		
		return mappingUtil.convertToCategoryDto(repository.findById(category.getId()).get());
	}
	
	public boolean delete(Integer id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		
		return false;
	}
}
