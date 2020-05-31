package tr.com.aa.dal.repository;

import java.util.List;
import java.util.UUID;
import javax.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.history.RevisionRepository;
import tr.com.aa.dal.entity.JobEntity;

public interface JobRepository
    extends RevisionRepository<JobEntity, UUID, Integer>, JpaRepository<JobEntity,
    UUID> {

  @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
  List<JobEntity> findAll();

  List<JobEntity> findByCreatedById(UUID createdById);

  JobEntity getById(UUID id);

  List<JobEntity> findByAutoStartTrueAndJobStatusIs(Integer jobStatus);

}