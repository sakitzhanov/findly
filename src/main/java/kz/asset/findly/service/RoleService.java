package kz.asset.findly.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kz.asset.findly.model.dto.RoleDto;
import kz.asset.findly.repository.RoleRepository;
import kz.asset.findly.util.MappingUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {
	private final RoleRepository repository;
	private final MappingUtil mappingUtil;
	
	public List<RoleDto> findAll() {
		return repository.findAll().stream()
				.map(mappingUtil::convertToRoleDto)
				.collect(Collectors.toList());
	}
}
