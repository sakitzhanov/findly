package kz.asset.findly.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageDto {
	private Integer id;
	private String url;
}
