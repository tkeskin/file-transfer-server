package tr.com.aa.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tr.com.aa.dal.entity.JobDestinationEntity;
import tr.com.aa.models.JobDestinationDto;

@Mapper(componentModel = "spring")
public interface JobDestinationMapper {

  JobDestinationMapper INSTANCE = Mappers.getMapper(JobDestinationMapper.class);

  List<JobDestinationDto> toJobDestinationrDto(List<JobDestinationEntity> jobDestinationEntities);
}