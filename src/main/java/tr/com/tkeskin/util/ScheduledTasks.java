package tr.com.tkeskin.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tr.com.tkeskin.activemq.Producer;
import tr.com.tkeskin.models.JobDestinationDto;
import tr.com.tkeskin.models.JobDestinationList;
import tr.com.tkeskin.models.JobDto;
import tr.com.tkeskin.models.PendingJobList;
import tr.com.tkeskin.service.JobDestinationService;
import tr.com.tkeskin.service.JobService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Component
public class ScheduledTasks {

    @Autowired
    Producer producer;

    @Autowired
    JobService jobService;

    @Autowired
    JobDestinationService jobDestinationService;

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter
            .ofPattern("HH:mm:ss");

    @Scheduled(cron = "0 */2 * ? * *")
    public void pendingFileScheduleTask() {

        log.info("Pending Job Task :: {}",
                dateTimeFormatter.format(LocalDateTime.now()));

        try {
            for (JobDestinationDto jobDestinationDto : getPendingFile().getJobDestinationList()) {
                for (int i = 0; i < 10; i++) {
                    producer.sendDownload(jobDestinationDto);
                }
            }
        } catch (Exception e) {
            log.info("", e);
        }
    }

    //@Scheduled(cron = "0 1/2 * ? * *")
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

    private JobDestinationList getPendingFile() {

        log.info("Get pending file {}", dateTimeFormatter.format(LocalDateTime.now()));
        return jobDestinationService.findByDownloadFalse();
    }

}