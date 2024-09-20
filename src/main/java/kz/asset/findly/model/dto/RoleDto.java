package kz.asset.findly.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RoleDto {
	@EqualsAndHashCode.Include
	private Integer id;
	private String name;
}
