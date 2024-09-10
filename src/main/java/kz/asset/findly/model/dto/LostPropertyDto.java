package kz.asset.findly.model.dto;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LostPropertyDto {
	private Integer id;
	private String title;
	private String description;
	private Double latitude;
	private Double longitude;
	private String person;
	private String phoneNumber;
	private String status;
	private Boolean isArchived;
	private Boolean isOwnerFound;
	private Timestamp createdAt;
	private Timestamp deletedAt;
	private UserDto user;
	private CategoryDto category;
	private CityDto city;
	private List<ImageDto> images;
}
