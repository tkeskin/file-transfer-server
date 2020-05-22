package tr.com.aa.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.aa.dal.entity.JobStatusEntity;

public interface JobStatusRepository extends JpaRepository<JobStatusEntity,
    Integer> {

}