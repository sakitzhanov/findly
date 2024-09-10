package kz.asset.findly.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityDto {
	private Integer id;
	private Double latitude;
	private Double longitude;
	private RegionDto region;
	private List<CityTranslationDto> translations;
}
