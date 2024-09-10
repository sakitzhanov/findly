package kz.asset.findly.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubcategoryTranslationDto {
	private SubcategoryDto subcategory;
	private LanguageDto language;
	private String name;
}
