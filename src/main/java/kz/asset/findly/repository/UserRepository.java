package kz.asset.findly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kz.asset.findly.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUsername(String username);
	@Query("SELECT COUNT(u) FROM User u WHERE u.username=:username")
	public long countUsersByUsername(@Param("username") String username);
	@Query("SELECT COUNT(u) FROM User u WHERE u.phoneNumber=:phoneNumber")
	public long countUsersByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
