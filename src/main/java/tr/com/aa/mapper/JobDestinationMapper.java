package tr.com.aa.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tr.com.aa.dal.entity.JobDestinationEntity;
import tr.com.aa.models.JobDestinationDto;
import tr.com.aa.models.JobDestinationViewDto;

@Mapper(uses = DateMapper.class)
public interface JobDestinationMapper {

  JobDestinationMapper INSTANCE = Mappers.getMapper(JobDestinationMapper.class);

  List<JobDestinationDto> toJobDestinationDto(List<JobDestinationEntity> jobDestinationEntities);

  List<JobDestinationViewDto> toJobDestinationViewDto(
      List<JobDestinationEntity> jobDestinationEntities);
}