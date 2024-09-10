package kz.asset.findly.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "regions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Region {
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer id;
	@Column(name = "code")
	private String code;
	@OneToMany(mappedBy = "region")
	private List<RegionTranslation> translations;
}
