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
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subcategory_translation")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(SubcategoryTranslationId.class)
public class SubcategoryTranslation {
	@Id
	@ManyToOne
	@JoinColumn(name = "subcategory_id")
	private Subcategory subcategory;
	@Id
	@ManyToOne
	@JoinColumn(name = "language_id")
	private Language language;
	@Column(name = "name")
	private String name;
}
