package tr.com.aa.service;

import java.util.UUID;
import tr.com.aa.models.ProjectDto;
import tr.com.aa.models.ProjectList;

public interface ProjectService {

  ProjectDto findById(UUID id);

  ProjectList findAll();

  Boolean saveProject(ProjectDto projectDto);

  Boolean deleteProject(UUID id);
}