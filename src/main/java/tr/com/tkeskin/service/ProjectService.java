package tr.com.tkeskin.service;

import java.util.UUID;
import tr.com.tkeskin.models.ProjectDto;
import tr.com.tkeskin.models.ProjectList;

public interface ProjectService {

  ProjectDto findById(UUID id);

  ProjectList findAll();

  Boolean saveProject(ProjectDto projectDto);

  Boolean deleteProject(UUID id);
}