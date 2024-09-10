package kz.asset.findly.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "region_translation")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "region")
@IdClass(RegionTranslationId.class)
public class RegionTranslation {
	@Id
	@ManyToOne
	@JoinColumn(name = "region_id")
	private Region region;
	@Id
	@ManyToOne
	@JoinColumn(name = "language_id")
	private Language language;
	@Column(name = "name")
	private String name;	
}
