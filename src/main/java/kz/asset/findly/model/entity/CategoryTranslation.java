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
@Table(name = "category_translation")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CategoryTranslationId.class)
public class CategoryTranslation {
	@Id
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	@Id
	@ManyToOne
	@JoinColumn(name = "language_id")
	private Language language;
	@Column(name = "name")
	private String name;	

}
