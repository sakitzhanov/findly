package kz.asset.findly.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {
	private Integer id;
	private String name;
}
