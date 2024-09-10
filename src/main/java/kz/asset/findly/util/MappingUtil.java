package kz.asset.findly.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import kz.asset.findly.model.dto.CategoryDto;
import kz.asset.findly.model.dto.CategoryTranslationDto;
import kz.asset.findly.model.dto.CityDto;
import kz.asset.findly.model.dto.CityTranslationDto;
import kz.asset.findly.model.dto.ImageDto;
import kz.asset.findly.model.dto.LanguageDto;
import kz.asset.findly.model.dto.LostPropertyDto;
import kz.asset.findly.model.dto.RegionDto;
import kz.asset.findly.model.dto.RegionTranslationDto;
import kz.asset.findly.model.dto.RoleDto;
import kz.asset.findly.model.dto.SubcategoryDto;
import kz.asset.findly.model.dto.SubcategoryTranslationDto;
import kz.asset.findly.model.dto.UserDto;
import kz.asset.findly.model.entity.Category;
import kz.asset.findly.model.entity.CategoryTranslation;
import kz.asset.findly.model.entity.City;
import kz.asset.findly.model.entity.CityTranslation;
import kz.asset.findly.model.entity.Image;
import kz.asset.findly.model.entity.Language;
import kz.asset.findly.model.entity.LostProperty;
import kz.asset.findly.model.entity.LostPropertyStatus;
import kz.asset.findly.model.entity.Region;
import kz.asset.findly.model.entity.RegionTranslation;
import kz.asset.findly.model.entity.Role;
import kz.asset.findly.model.entity.Subcategory;
import kz.asset.findly.model.entity.SubcategoryTranslation;
import kz.asset.findly.model.entity.User;

@Service
public class MappingUtil {
	public LostPropertyDto convertToLostPropertyDto(LostProperty lostProperty) {
		return LostPropertyDto.builder()
				.id(lostProperty.getId())
				.title(lostProperty.getTitle())
				.description(lostProperty.getDescription())
				.latitude(lostProperty.getLatitude())
				.longitude(lostProperty.getLongitude())
				.person(lostProperty.getPerson())
				.phoneNumber(lostProperty.getPhoneNumber())
				.status(lostProperty.getStatus().name())
				.isArchived(lostProperty.getIsArchived())
				.isOwnerFound(lostProperty.getIsOwnerFound())
				.createdAt(lostProperty.getCreatedAt())
				.deletedAt(lostProperty.getDeletedAt())
				.user(convertToUserDto(lostProperty.getUser()))
				.category(convertToCategoryDto(lostProperty.getCategory()))
				.city(convertToCityDto(lostProperty.getCity()))
				.images(lostProperty.getImages().stream()
						.map(item -> convertToImageDto(item))
						.collect(Collectors.toList())
				).build();
	}
	
	public LostProperty convertToLostProperty(LostPropertyDto lostPropertyDto) {
		return LostProperty.builder()
				.id(lostPropertyDto.getId())
				.title(lostPropertyDto.getTitle())
				.description(lostPropertyDto.getDescription())
				.latitude(lostPropertyDto.getLatitude())
				.longitude(lostPropertyDto.getLongitude())
				.person(lostPropertyDto.getPerson())
				.phoneNumber(lostPropertyDto.getPhoneNumber())
				.status(LostPropertyStatus.valueOf(lostPropertyDto.getStatus()))
				.isArchived(lostPropertyDto.getIsArchived())
				.isOwnerFound(lostPropertyDto.getIsOwnerFound())
				.createdAt(lostPropertyDto.getCreatedAt())
				.deletedAt(lostPropertyDto.getDeletedAt())
				.user(convertToUser(lostPropertyDto.getUser()))
				.category(convertToCategory(lostPropertyDto.getCategory()))
				.city(convertToCity(lostPropertyDto.getCity()))
				.build();
	}
	
	public CategoryDto convertToCategoryDto(Category category) {
		List<CategoryTranslationDto> translations = category.getTranslations().stream()
				.map(item -> CategoryTranslationDto.builder()
						.language(convertToLanguageDto(item.getLanguage()))
						.name(item.getName())
						.build())
				.collect(Collectors.toList());				
		
		return CategoryDto.builder()
				.id(category.getId())
				.translations(translations)
				.build();
	}
	
	public Category convertToCategory(CategoryDto categoryDto) {
		Category category = Category.builder()
				.id(categoryDto.getId())
				.build();
		
		List<CategoryTranslation> translations = categoryDto.getTranslations().stream()
				.map(item -> CategoryTranslation.builder()
						.category(category)
						.language(convertToLanguage(item.getLanguage()))
						.name(item.getName())
						.build())
				.collect(Collectors.toList());
		
		category.setTranslations(translations);
		
		return category;
	}
	
	public CategoryTranslationDto convertToCategoryTranslationDto(CategoryTranslation categoryTranslation) {		
		return CategoryTranslationDto.builder()
			.category(convertToCategoryDto(categoryTranslation.getCategory()))
			.language(convertToLanguageDto(categoryTranslation.getLanguage()))
			.name(categoryTranslation.getName())
			.build();
	}
	
	public CategoryTranslation convertToCategoryTranslation(CategoryTranslationDto categoryTranslationDto) {
		return CategoryTranslation.builder()
				.category(convertToCategory(categoryTranslationDto.getCategory()))
				.language(convertToLanguage(categoryTranslationDto.getLanguage()))
				.name(categoryTranslationDto.getName())
				.build();
	}
	
	public LanguageDto convertToLanguageDto(Language language) {
		return LanguageDto.builder()
				.id(language.getId())
				.code(language.getCode())
				.name(language.getName())
				.build();
	}
	
	public Language convertToLanguage(LanguageDto languageDto) {
		return Language.builder()
				.id(languageDto.getId())
				.code(languageDto.getCode())
				.name(languageDto.getName())
				.build();
	}
	
	public SubcategoryDto convertToSubcategoryDto(Subcategory subcategory) {
		List<SubcategoryTranslationDto> translations = subcategory.getTranslations().stream()
				.map(item -> SubcategoryTranslationDto.builder()
						.language(convertToLanguageDto(item.getLanguage()))
						.name(item.getName())
						.build())
				.collect(Collectors.toList());				
		
		return SubcategoryDto.builder()
				.id(subcategory.getId())
				.category(convertToCategoryDto(subcategory.getCategory()))
				.translations(translations)
				.build();
	}
	
	public Subcategory convertToSubcategory(SubcategoryDto subcategoryDto) {
		Subcategory subcategory = Subcategory.builder()
				.id(subcategoryDto.getId())
				.category(convertToCategory(subcategoryDto.getCategory()))
				.build();
		
		List<SubcategoryTranslation> translations = subcategoryDto.getTranslations().stream()
				.map(item -> SubcategoryTranslation.builder()
						.subcategory(subcategory)
						.language(convertToLanguage(item.getLanguage()))
						.name(item.getName())
						.build())
				.collect(Collectors.toList());
		
		subcategory.setTranslations(translations);
		
		return subcategory;	
	}
	
	public SubcategoryTranslationDto convertToSubcategoryTranslationDto(SubcategoryTranslation subcategoryTranslation) {		
		return SubcategoryTranslationDto.builder()
				.subcategory(convertToSubcategoryDto(subcategoryTranslation.getSubcategory()))
				.language(convertToLanguageDto(subcategoryTranslation.getLanguage()))
				.name(subcategoryTranslation.getName())
				.build();		
	}
	
	public SubcategoryTranslation convertToSubcategoryTranslation(SubcategoryTranslationDto subcategoryTranslationDto) {
		return SubcategoryTranslation.builder()
				.subcategory(convertToSubcategory(subcategoryTranslationDto.getSubcategory()))
				.language(convertToLanguage(subcategoryTranslationDto.getLanguage()))
				.name(subcategoryTranslationDto.getName())
				.build();
	}
	
	public RegionDto convertToRegionDto(Region region) {
		List<RegionTranslationDto> translations = region.getTranslations().stream()
				.map(item -> RegionTranslationDto.builder()
						.language(convertToLanguageDto(item.getLanguage()))
						.name(item.getName())
						.build())
				.collect(Collectors.toList());		
		
		return RegionDto.builder()
				.id(region.getId())
				.code(region.getCode())
				.translations(translations)
				.build();		
	}
	
	public Region convertToRegion(RegionDto regionDto) {
		Region region = Region.builder()
				.id(regionDto.getId())
				.code(regionDto.getCode())
				.build();
		
		List<RegionTranslation> translations = regionDto.getTranslations().stream()
				.map(item -> RegionTranslation.builder()
						.region(region)
						.language(convertToLanguage(item.getLanguage()))
						.name(item.getName())
						.build())
				.collect(Collectors.toList());
		
		region.setTranslations(translations);
		
		return region;
	}
	
	public RegionTranslationDto convertToRegionTranslationDto(RegionTranslation regionTranslation) {		
		return RegionTranslationDto.builder()
				.region(convertToRegionDto(regionTranslation.getRegion()))
				.language(convertToLanguageDto(regionTranslation.getLanguage()))
				.name(regionTranslation.getName())
				.build();		
	}
	
	public RegionTranslation convertToRegionTranslation(RegionTranslationDto regionTranslationDto) {
		return RegionTranslation.builder()
				.region(convertToRegion(regionTranslationDto.getRegion()))
				.language(convertToLanguage(regionTranslationDto.getLanguage()))
				.name(regionTranslationDto.getName())
				.build();
	}
	
	public CityDto convertToCityDto(City city) {
		List<CityTranslationDto> translations = city.getTranslations().stream()
				.map(item -> CityTranslationDto.builder()
						.language(convertToLanguageDto(item.getLanguage()))
						.name(item.getName())
						.build())
				.collect(Collectors.toList());		
		
		return CityDto.builder()
				.id(city.getId())
				.latitude(city.getLatitude())
				.longitude(city.getLongitude())
				.region(convertToRegionDto(city.getRegion()))
				.translations(translations)
				.build();		
	}
	
	public City convertToCity(CityDto cityDto) {
		City city = City.builder()
				.id(cityDto.getId())
				.latitude(cityDto.getLatitude())
				.longitude(cityDto.getLongitude())
				.region(convertToRegion(cityDto.getRegion()))
				.build();	
		
		List<CityTranslation> translations = cityDto.getTranslations().stream()
				.map(item -> CityTranslation.builder()
						.city(city)
						.language(convertToLanguage(item.getLanguage()))
						.name(item.getName())
						.build())
				.collect(Collectors.toList());
		
		city.setTranslations(translations);
		
		return city;		
	}
	
	public CityTranslationDto convertToCityTranslationDto(CityTranslation cityTranslation) {		
		return CityTranslationDto.builder()
				.city(convertToCityDto(cityTranslation.getCity()))
				.language(convertToLanguageDto(cityTranslation.getLanguage()))
				.name(cityTranslation.getName())
				.build();
		
	}
	
	public CityTranslation convertToCityTranslation(CityTranslationDto cityTranslationDto) {
		return CityTranslation.builder()
				.city(convertToCity(cityTranslationDto.getCity()))
				.language(convertToLanguage(cityTranslationDto.getLanguage()))
				.name(cityTranslationDto.getName())
				.build();		
	}
	
	public ImageDto convertToImageDto(Image image) {
		return ImageDto.builder()
				.id(image.getId())
				.url(ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/api/images/" + image.getName())
						.toUriString())
				.build();
	}
	
	public Image convertToImage(ImageDto imageDto) {
		return Image.builder()
				.id(imageDto.getId())
				.build();
	}
	
	public UserDto convertToUserDto(User user) {		
		List<LostPropertyDto> lostProperties = user.getLostProperties().stream()
				.map(item -> LostPropertyDto.builder()
						.id(item.getId())
						.title(item.getTitle())
						.description(item.getDescription())
						.latitude(item.getLatitude())
						.longitude(item.getLongitude())
						.person(item.getPerson())
						.phoneNumber(item.getPhoneNumber())
						.status(item.getStatus().name())
						.isArchived(item.getIsArchived())
						.isOwnerFound(item.getIsOwnerFound())
						.createdAt(item.getCreatedAt())
						.deletedAt(item.getDeletedAt())
						.category(convertToCategoryDto(item.getCategory()))
						.city(convertToCityDto(item.getCity()))
						.images(item.getImages().stream()
								.map(image -> convertToImageDto(image))
								.collect(Collectors.toList())
						).build())
				.collect(Collectors.toList());
		
		return UserDto.builder()
				.id(user.getId())
				.username(user.getUsername())
				.password(user.getPassword())
				.phoneNumber(user.getPhoneNumber())
				.isActive(user.getIsActive())
				.lostProperties(lostProperties)
				.roles(user.getRoles().stream()
						.map(item -> convertToRoleDto(item))
						.collect(Collectors.toSet()))
				.build();
	}
	
	public User convertToUser(UserDto userDto) {		
		User user = User.builder()
				.id(userDto.getId())
				.username(userDto.getUsername())
				.password(userDto.getPassword())
				.phoneNumber(userDto.getPhoneNumber())
				.isActive(userDto.getIsActive())
				.roles(userDto.getRoles().stream()
						.map(item -> convertToRole(item))
						.collect(Collectors.toSet()))
				.build();	
		
		List<LostProperty> lostProperties = userDto.getLostProperties().stream()
				.map(item -> LostProperty.builder()
						.id(item.getId())
						.title(item.getTitle())
						.description(item.getDescription())
						.latitude(item.getLatitude())
						.longitude(item.getLongitude())
						.person(item.getPerson())
						.phoneNumber(item.getPhoneNumber())
						.status(LostPropertyStatus.valueOf(item.getStatus()))
						.isArchived(item.getIsArchived())
						.isOwnerFound(item.getIsOwnerFound())
						.createdAt(item.getCreatedAt())
						.deletedAt(item.getDeletedAt())
						.user(user)
						.category(convertToCategory(item.getCategory()))
						.city(convertToCity(item.getCity()))
						.build())
				.collect(Collectors.toList());
		
		user.setLostProperties(lostProperties);
		
		return user;
	}
	
	public RoleDto convertToRoleDto(Role role) {
		return RoleDto.builder()
				.id(role.getId())
				.name(role.getName())
				.build();
	}
	
	public Role convertToRole(RoleDto roleDto) {
		return Role.builder()
				.id(roleDto.getId())
				.name(roleDto.getName())
				.build();
	}
}