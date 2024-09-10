package kz.asset.findly.model.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {
	private Integer id;
	private List<CategoryTranslationDto> translations;
}
