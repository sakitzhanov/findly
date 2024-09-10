package kz.asset.findly.model.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubcategoryDto {
	private Integer id;
	private CategoryDto category;
	private List<SubcategoryTranslationDto> translations;
}
