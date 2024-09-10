package kz.asset.findly.model.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegionTranslationId implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer region;
	private Integer language;
}
