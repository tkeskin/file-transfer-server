package tr.com.aa.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tr.com.aa.artemis.JmsProducer;
import tr.com.aa.models.JobDto;
import tr.com.aa.models.PendingJobList;
import tr.com.aa.service.JobService;

@Slf4j
@Component
public class ScheduledTasks {

  @Autowired
  JmsProducer jmsProducer;

  @Autowired
  JobService jobService;

  private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter
      .ofPattern("HH:mm:ss");

  @Scheduled(cron = "0 */10 * ? * *")
  public void scheduleTaskWithCronExpression() {

    log.info("AA Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));

    try {
      PendingJobList pendingJobList = jobService.findByAutoStartAndPending(0);
      for (JobDto pendingJob : pendingJobList.getPendingJobList()) {
        jmsProducer.autoStart(pendingJob.getId());
      }
    } catch (Exception e) {
      log.info("", e);
    }
  }

}