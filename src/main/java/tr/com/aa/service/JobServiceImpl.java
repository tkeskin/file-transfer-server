package tr.com.aa.service;

import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.aa.dal.entity.JobDestinationEntity;
import tr.com.aa.dal.entity.JobEntity;
import tr.com.aa.dal.repository.JobDestinationRepository;
import tr.com.aa.dal.repository.JobRepository;
import tr.com.aa.exception.EntityNotfoundException;
import tr.com.aa.mapper.JobMapper;
import tr.com.aa.models.FileList;
import tr.com.aa.models.FtpServerDestination;
import tr.com.aa.models.JobDto;
import tr.com.aa.models.JobList;
import tr.com.aa.models.JobsDto;

@Service
@Transactional
public class JobServiceImpl implements JobService {

  @Autowired
  JobRepository jobRepository;

  @Autowired
  JobDestinationRepository jobDestinationRepository;

  @Override
  public JobDto findById(UUID id) throws EntityNotfoundException {

    Optional<JobEntity> byId = Optional
        .ofNullable(jobRepository.findById(id)
            .orElseThrow(() -> new EntityNotfoundException(id.toString())));

    return JobMapper.INSTANCE.toFtpServerDto(byId.get());
  }

  @Override
  public JobDto findByAutoStartAndPending(Integer jobStatus) {

    return JobMapper.INSTANCE
        .toFtpServerDto(jobRepository.findByAutoStartTrueAndJobStatusIs(jobStatus));
  }

  @Override
  public JobList findAll() {

    JobList jobList = new JobList();
    jobList.setJobList(JobMapper.INSTANCE
        .toJobList(jobRepository.findAll()));
    return jobList;
  }

  @Override
  public Boolean saveJob(JobsDto jobsDto) {

    JobEntity jobEntity = jobRepository.save(JobMapper.INSTANCE.toJobEntity(jobsDto.getJobDto()));
    for (FtpServerDestination ftpServerDestination : jobsDto.getFtpServerDestination()) {
      for (FileList fileList : jobsDto.getFileList()) {
        JobDestinationEntity jobDestinationEntity = new JobDestinationEntity();
        jobDestinationEntity.setFtpServerId(ftpServerDestination.getId());
        jobDestinationEntity.setDownloadUrl(fileList.getDownloadUrl());
        jobDestinationEntity.setUploadPath(fileList.getUploadPath());
        jobDestinationEntity.setJobEntity(jobEntity);
        jobDestinationRepository.save(jobDestinationEntity);
      }
    }
    return true;
  }

  @Override
  public Boolean deleteJob(UUID id) {

    jobRepository.deleteById(id);
    return true;
  }
}