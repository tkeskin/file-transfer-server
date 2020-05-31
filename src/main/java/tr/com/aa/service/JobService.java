package tr.com.aa.service;

import java.util.UUID;
import tr.com.aa.models.JobDestinationList;
import tr.com.aa.models.JobDto;
import tr.com.aa.models.JobList;
import tr.com.aa.models.JobsDto;
import tr.com.aa.models.PendingJobList;

public interface JobService {

  JobDto findById(UUID id);

  PendingJobList findByAutoStartAndPending(Integer jobStatus);

  JobList findAll();

  Boolean saveJob(JobsDto jobsDto);

  Boolean deleteJob(UUID id);

  JobDestinationList findByJobId(UUID jobId);

  JobList findBycreatedById(UUID createdById);

  Boolean updateJobStatus(UUID id);
}
