package tr.com.tkeskin.service;

import tr.com.tkeskin.models.ProjectDto;
import tr.com.tkeskin.models.ProjectList;

import java.util.UUID;

public interface ProjectService {

    ProjectDto findById(UUID id);

    ProjectList findAll();

    Boolean saveProject(ProjectDto projectDto);

    Boolean deleteProject(UUID id);
}