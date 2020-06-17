package tr.com.tkeskin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tr.com.tkeskin.dal.entity.JobEntity;
import tr.com.tkeskin.models.JobDto;
import tr.com.tkeskin.models.JobViewDto;

import java.util.List;

@Mapper(uses = DateMapper.class)
public interface JobMapper {

    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

    List<JobViewDto> toJobList(List<JobEntity> jobEntityList);

    JobEntity toJobEntity(JobDto jobDto);

    JobDto toFtpServerDto(JobEntity jobEntity);

    List<JobDto> topendingJobList(List<JobEntity> jobEntity);
}