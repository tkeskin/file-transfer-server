package tr.com.tkeskin.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.tkeskin.dal.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
