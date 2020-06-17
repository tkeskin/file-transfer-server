package tr.com.tkeskin.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.tkeskin.dal.entity.JobStatusEntity;

public interface JobStatusRepository extends JpaRepository<JobStatusEntity,
    Integer> {

}