package tr.com.tkeskin.service;

import java.util.UUID;
import tr.com.tkeskin.models.JobDestinationList;
import tr.com.tkeskin.models.JobDto;
import tr.com.tkeskin.models.JobList;
import tr.com.tkeskin.models.JobsDto;
import tr.com.tkeskin.models.PendingJobList;

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
