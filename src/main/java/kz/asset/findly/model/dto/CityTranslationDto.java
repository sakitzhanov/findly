package kz.asset.findly.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityTranslationDto {
	private CityDto city;
	private LanguageDto language;
	private String name;
}
