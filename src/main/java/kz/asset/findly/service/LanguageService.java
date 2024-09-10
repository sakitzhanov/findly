package kz.asset.findly.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kz.asset.findly.model.dto.LanguageDto;
import kz.asset.findly.repository.LanguageRepository;
import kz.asset.findly.util.MappingUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LanguageService {
	private final LanguageRepository repository;
	private final MappingUtil mappingUtil;
	
	public List<LanguageDto> findAll() {		
		return repository.findAll().stream()
				.map(mappingUtil::convertToLanguageDto)
				.collect(Collectors.toList());
	}
}
