package tr.com.aa.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tr.com.aa.dal.entity.JobEntity;
import tr.com.aa.models.JobDto;
import tr.com.aa.models.JobViewDto;

@Mapper(uses = DateMapper.class)
public interface JobMapper {

  JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

  List<JobViewDto> toJobList(List<JobEntity> jobEntityList);

  JobEntity toJobEntity(JobDto jobDto);

  JobDto toFtpServerDto(JobEntity jobEntity);

  List<JobDto> topendingJobList(List<JobEntity> jobEntity);
}