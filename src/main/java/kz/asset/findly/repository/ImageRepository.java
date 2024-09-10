package kz.asset.findly.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kz.asset.findly.model.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
	public Optional<Image> findByName(String name);
}
