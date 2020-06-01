package tr.com.aa.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tr.com.aa.artemis.JmsProducer;
import tr.com.aa.models.JobDto;
import tr.com.aa.models.PendingJobList;
import tr.com.aa.service.JobDestinationService;
import tr.com.aa.service.JobService;

@Slf4j
@Component
public class ScheduledTasks {

  @Autowired
  JmsProducer jmsProducer;

  @Autowired
  JobService jobService;

  @Autowired
  JobDestinationService jobDestinationService;

  private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter
      .ofPattern("HH:mm:ss");

  @Scheduled(cron = "0 */2 * ? * *")
  public void pendingJobScheduleTask() {

    log.info("Pending Job Task :: {}",
        dateTimeFormatter.format(LocalDateTime.now()));

    try {
      for (JobDto pendingJob : getByAutoStartAndPending().getPendingJobList()) {
        jmsProducer.autoStart(pendingJob.getId());
      }
    } catch (Exception e) {
      log.info("", e);
    }
  }

  @Scheduled(cron = "0 1/2 * ? * *")
  public void updateJobStatusScheduleTask() {

    log.info("Update Job Task :: {}",
        dateTimeFormatter.format(LocalDateTime.now()));

    try {
      for (JobDto pendingJob : getByAutoStartAndPending().getPendingJobList()) {
        if (jobDestinationService.findAllfileSend(pendingJob.getId())) {
          updateJobStatus(pendingJob.getId());
        }
      }
    } catch (Exception e) {
      log.info("", e);
    }
  }

  private void updateJobStatus(UUID id) {

    log.info("Update job status {}", dateTimeFormatter.format(LocalDateTime.now()));
    jobService.updateJobStatus(id);
  }

  private PendingJobList getByAutoStartAndPending() {

    log.info("Get pending job {}", dateTimeFormatter.format(LocalDateTime.now()));
    return jobService.findByAutoStartAndPending(Const.PENDING_JOB);
  }

}