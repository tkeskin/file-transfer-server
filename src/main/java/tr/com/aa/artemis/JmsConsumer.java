package tr.com.aa.artemis;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import tr.com.aa.models.JobDestinationDto;
import tr.com.aa.models.JobDto;
import tr.com.aa.models.ProjectDto;
import tr.com.aa.service.FileDownloadService;
import tr.com.aa.service.FileTransferService;
import tr.com.aa.service.FileUploadService;
import tr.com.aa.service.JobDestinationService;
import tr.com.aa.service.JobService;
import tr.com.aa.service.ProjectService;

@Slf4j
@Service
public class JmsConsumer {

  @Autowired
  JobDestinationService jobDestinationService;

  @Autowired
  FileTransferService fileTransferService;

  @Autowired
  FileDownloadService fileDownloadService;

  @Autowired
  FileUploadService fileUploadService;

  @Autowired
  ProjectService projectService;

  @Autowired
  JobService jobService;

  @JmsListener(destination = "${aa.download.queue}")
  public Boolean receiveDownload(UUID id) {

    log.info("Received download job id= {}", id);
    log.info("download edilecek dosyalarin listesi kontrol ediliyor");
    Map<String, List<JobDestinationDto>> collect = jobDestinationService
        .findByDownloadFalseAndJobEntityId(id)
        .stream()
        .collect(Collectors.groupingBy(JobDestinationDto::getDownloadUrl));
    log.info("download edilecek dosya sayisi : {}", collect.size());

    JobDto byId = jobService.findById(id);
    ProjectDto downloadPath = projectService.findById(byId.getCreatedById());

    collect.entrySet().stream()
        .map(c -> fileDownloadService.downloadFile(downloadPath.getDownloadPath(), c.getValue()))
        .map(dl -> dl
            .thenApply(c -> jobDestinationService.findByDownloadFalseAndId(c)))
        .map(CompletableFuture::join)
        .collect(Collectors.toList());
    return true;
  }

  @JmsListener(destination = "${aa.upload.queue}")
  public Boolean receiveUpload(UUID id) {

    log.info("Received upload job id= {}", id);
    log.info("upload edilecek dosyalarin listesi kontrol ediliyor");
    Map<UUID, List<JobDestinationDto>> destinationByFtpServer = jobDestinationService
        .findByDownloadTrueAndSendFalseAndJobEntityId(id).stream()
        .collect(Collectors.groupingBy(JobDestinationDto::getFtpServerId));
    log.info("baglanilacak ftp server sayisi : {}", destinationByFtpServer.size());

    destinationByFtpServer.entrySet().stream()
        .map(c -> fileUploadService.uploadFile(c))
        .map(dl -> dl.thenApply(x -> jobDestinationService.updateSend(x)))
        .map(CompletableFuture::join)
        .collect(Collectors.toList());
    return true;
  }

  @JmsListener(destination = "${aa.autostart.queue}")
  public void receiveAutoStart(UUID id) {

    log.info("Received autostart job id= {}", id);
    if (receiveDownload(id)) {
      receiveUpload(id);
    }
  }
}