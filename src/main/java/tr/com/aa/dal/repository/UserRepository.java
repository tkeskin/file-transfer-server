package tr.com.aa.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.aa.dal.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
