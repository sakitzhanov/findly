package kz.asset.findly.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryTranslationDto {
	private CategoryDto category;
	private LanguageDto language;
	private String name;
}
