package kz.asset.findly.model.dto;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
	private Integer id;
	private String username;
	private String password;
	private String phoneNumber;
	private Boolean isActive;
	private Timestamp createdAt;
	private Timestamp deletedAt;
	private List<LostPropertyDto> lostProperties;
	private Set<RoleDto> roles;
}
