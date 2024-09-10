package kz.asset.findly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kz.asset.findly.model.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
