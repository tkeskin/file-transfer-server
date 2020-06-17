package tr.com.tkeskin.service;

import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.tkeskin.dal.entity.JobDestinationEntity;
import tr.com.tkeskin.dal.entity.JobEntity;
import tr.com.tkeskin.dal.repository.JobDestinationRepository;
import tr.com.tkeskin.dal.repository.JobRepository;
import tr.com.tkeskin.exception.EntityNotfoundException;
import tr.com.tkeskin.mapper.JobDestinationMapper;
import tr.com.tkeskin.mapper.JobMapper;
import tr.com.tkeskin.models.FileList;
import tr.com.tkeskin.models.FtpServerDestination;
import tr.com.tkeskin.models.JobDestinationList;
import tr.com.tkeskin.models.JobDto;
import tr.com.tkeskin.models.JobList;
import tr.com.tkeskin.models.JobsDto;
import tr.com.tkeskin.models.PendingJobList;
import tr.com.tkeskin.util.Const;

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
  public PendingJobList findByAutoStartAndPending(Integer jobStatus) {

    PendingJobList pendingJobList = new PendingJobList();
    pendingJobList.setPendingJobList(JobMapper.INSTANCE
        .topendingJobList(jobRepository.findByAutoStartTrueAndJobStatusIs(jobStatus)));

    return pendingJobList;
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

  @Override
  public JobDestinationList findByJobId(UUID jobId) {

    JobDestinationList jobDestinationList = new JobDestinationList();
    jobDestinationList.setJobDestinationViewList(JobDestinationMapper.INSTANCE
        .toJobDestinationViewDto(jobDestinationRepository.findByJobEntityId(jobId)));
    return jobDestinationList;
  }

  @Override
  public JobList findBycreatedById(UUID createdById) {

    JobList jobList = new JobList();
    jobList.setJobList(JobMapper.INSTANCE
        .toJobList(jobRepository.findByCreatedById(createdById)));
    return jobList;
  }

  @Override
  public Boolean updateJobStatus(UUID id) {

    Optional<JobEntity> byId = jobRepository.findById(id);
    byId.get().setJobStatus(Const.COMPLETED_JOB);
    return true;
  }
}