package codehouse.assignment.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import codehouse.assignment.entities.concretes.User;

public interface UserDao extends JpaRepository<User, Integer>
{
	Optional<User> findByPhone(String phone);
	
	Boolean existsByPhone(String phone);
	
	@Modifying
	@Transactional
	@Query("update User u "
			+ "set u.firstName=:firstName,"
			+ "u.lastName=:lastName,"
			+ "u.phone=:phone "
			+ "where u.id=:id")
	void updateUserById(String firstName, String lastName, String phone, int id);
}