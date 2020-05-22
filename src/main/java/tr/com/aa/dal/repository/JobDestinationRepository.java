package tr.com.aa.dal.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import tr.com.aa.dal.entity.JobDestinationEntity;

public interface JobDestinationRepository extends
    RevisionRepository<JobDestinationEntity, UUID, Integer>, JpaRepository<JobDestinationEntity,
    UUID> {

  List<JobDestinationEntity> findByJobEntityId(UUID jobId);

  JobDestinationEntity findByDownloadFalseAndId(UUID id);

  JobDestinationEntity getById(UUID id);
}