package app.repositories;

import app.entities.orm.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);

}
