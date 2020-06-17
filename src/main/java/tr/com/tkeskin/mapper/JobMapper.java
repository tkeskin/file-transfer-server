package tr.com.tkeskin.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tr.com.tkeskin.dal.entity.JobEntity;
import tr.com.tkeskin.models.JobDto;
import tr.com.tkeskin.models.JobViewDto;

@Mapper(uses = DateMapper.class)
public interface JobMapper {

  JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

  List<JobViewDto> toJobList(List<JobEntity> jobEntityList);

  JobEntity toJobEntity(JobDto jobDto);

  JobDto toFtpServerDto(JobEntity jobEntity);

  List<JobDto> topendingJobList(List<JobEntity> jobEntity);
}