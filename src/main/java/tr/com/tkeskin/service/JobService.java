package tr.com.tkeskin.service;

import tr.com.tkeskin.models.*;

import java.util.UUID;

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
