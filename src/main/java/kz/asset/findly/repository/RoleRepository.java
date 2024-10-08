package kz.asset.findly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kz.asset.findly.model.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
