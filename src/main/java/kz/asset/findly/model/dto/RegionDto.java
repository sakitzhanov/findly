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
public class RegionDto {
	private Integer id;
	private String code;
	private List<RegionTranslationDto> translations;
}
