package fi.haagahelia.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import fi.haagahelia.demo.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);

}
