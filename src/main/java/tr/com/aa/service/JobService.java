package tr.com.aa.service;

import java.util.UUID;
import tr.com.aa.models.JobDto;
import tr.com.aa.models.JobList;
import tr.com.aa.models.JobsDto;

public interface JobService {

  JobDto findById(UUID id);

  JobDto findByAutoStartAndPending(Integer jobStatus);

  JobList findAll();

  Boolean saveJob(JobsDto jobsDto);

  Boolean deleteJob(UUID id);
}
