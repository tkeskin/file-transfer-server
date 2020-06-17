package tr.com.tkeskin.service;

import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.tkeskin.dal.entity.ProjectEntity;
import tr.com.tkeskin.dal.repository.ProjectRepository;
import tr.com.tkeskin.exception.EntityNotfoundException;
import tr.com.tkeskin.mapper.ProjectMapper;
import tr.com.tkeskin.models.ProjectDto;
import tr.com.tkeskin.models.ProjectList;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

  @Autowired
  ProjectRepository projectRepository;

  @Override
  public ProjectDto findById(UUID id) {

    Optional<ProjectEntity> byId = Optional.ofNullable(
        projectRepository.findById(id).stream().findAny()
            .orElseThrow(() -> new EntityNotfoundException(id.toString())));

    return ProjectMapper.INSTANCE.toProjectDto(byId.get());
  }

  @Override
  public ProjectList findAll() {

    ProjectList projectList = new ProjectList();
    projectList.setProjectList(ProjectMapper.INSTANCE
        .toProjectDtoList(projectRepository.findAll()));
    return projectList;
  }

  @Override
  public Boolean saveProject(ProjectDto projectDto) {

    projectRepository
        .save(ProjectMapper.INSTANCE.toProjectEntity(projectDto));
    return true;
  }

  @Override
  public Boolean deleteProject(UUID id) {

    projectRepository.deleteById(id);
    return true;
  }
}