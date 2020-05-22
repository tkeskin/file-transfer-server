package tr.com.aa.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tr.com.aa.dal.entity.ProjectEntity;
import tr.com.aa.models.ProjectDto;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

  ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

  ProjectEntity toProjectEntity(ProjectDto projectDto);

  List<ProjectDto> toProjectDtoList(List<ProjectEntity> projectEntities);

  ProjectDto toProjectDto(ProjectEntity projectEntity);
}