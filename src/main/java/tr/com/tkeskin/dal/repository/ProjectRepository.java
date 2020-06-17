package tr.com.tkeskin.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import tr.com.tkeskin.dal.entity.ProjectEntity;

import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, UUID> {

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<ProjectEntity> findAll();

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Optional<ProjectEntity> findById(UUID id);
}
