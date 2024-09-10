package kz.asset.findly.model.entity;

import java.sql.Timestamp;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lost_properties")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LostProperty {
	@Id
	@Column(name = "id")
	@GeneratedValue	
	private Integer id;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "latitude")
	private Double latitude;
	@Column(name = "longitude")
	private Double longitude;
	@Column(name = "person")
	private String person;
	@Column(name = "phone_number")
	private String phoneNumber;
	@Enumerated(EnumType.STRING)
	private LostPropertyStatus status;
	@Column(name = "is_archived")
	private Boolean isArchived;
	@Column(name = "is_owner_found")
	private Boolean isOwnerFound;
	@Column(name = "created_at")
	private Timestamp createdAt;
	@Column(name = "deleted_at")
	private Timestamp deletedAt;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;
	@OneToMany(mappedBy = "lostProperty")
	private Collection<Image> images;
}
