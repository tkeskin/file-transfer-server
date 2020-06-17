package tr.com.tkeskin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tr.com.tkeskin.dal.entity.JobDestinationEntity;
import tr.com.tkeskin.models.JobDestinationDto;
import tr.com.tkeskin.models.JobDestinationViewDto;

import java.util.List;

@Mapper(uses = DateMapper.class)
public interface JobDestinationMapper {

    JobDestinationMapper INSTANCE = Mappers.getMapper(JobDestinationMapper.class);

    List<JobDestinationDto> toJobDestinationDto(List<JobDestinationEntity> jobDestinationEntities);

    JobDestinationDto toJobDestination(JobDestinationEntity jobDestinationEntities);

    List<JobDestinationViewDto> toJobDestinationViewDto(
            List<JobDestinationEntity> jobDestinationEntities);
}