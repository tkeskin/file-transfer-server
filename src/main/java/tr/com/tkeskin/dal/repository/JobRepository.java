package tr.com.tkeskin.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.history.RevisionRepository;
import tr.com.tkeskin.dal.entity.JobEntity;

import javax.persistence.QueryHint;
import java.util.List;
import java.util.UUID;

public interface JobRepository
        extends RevisionRepository<JobEntity, UUID, Integer>, JpaRepository<JobEntity,
        UUID> {

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<JobEntity> findAll();

    List<JobEntity> findByCreatedById(UUID createdById);

    JobEntity getById(UUID id);

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<JobEntity> findByAutoStartTrueAndJobStatusIs(Integer jobStatus);

}