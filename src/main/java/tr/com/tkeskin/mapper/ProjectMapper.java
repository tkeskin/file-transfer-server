package tr.com.tkeskin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tr.com.tkeskin.dal.entity.ProjectEntity;
import tr.com.tkeskin.models.ProjectDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectEntity toProjectEntity(ProjectDto projectDto);

    List<ProjectDto> toProjectDtoList(List<ProjectEntity> projectEntities);

    ProjectDto toProjectDto(ProjectEntity projectEntity);
}