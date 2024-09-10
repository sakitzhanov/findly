package kz.asset.findly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kz.asset.findly.model.entity.CategoryTranslation;
import kz.asset.findly.model.entity.CategoryTranslationId;


@Repository
public interface CategoryTranslationRepository extends JpaRepository<CategoryTranslation, CategoryTranslationId> {
}
