package tr.com.tkeskin.service;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.aa.client.Client;
import tr.com.aa.client.ClientFactory;
import tr.com.aa.client.auth.UserCredentials;
import tr.com.aa.connection.Connection;
import tr.com.tkeskin.models.FtpServerDto;
import tr.com.tkeskin.models.JobDestinationDto;

@Slf4j
@Transactional
@Service
public class FileUploadServiceImpl implements FileUploadService {

  @Autowired
  FtpServerService ftpServerService;

  @Async("uploadTaskExecutor")
  @Override
  public CompletableFuture<JobDestinationDto> uploadFile(JobDestinationDto file) {

    log.info("{} Start upload thread : {}", file.getFtpServerId(),
        Thread.currentThread().getName());
    long start = System.currentTimeMillis();
    try {
      if (connectUpload(file.getFtpServerId(), file)) {
        file.setSend(true);
        file.setSendDateTime(new Date());
      }
    } catch (Exception e) {
      log.error("{} : ", file.getDownloadPath(), e);
    }
    long end = System.currentTimeMillis();
    log.info("{} Total time {}", Thread.currentThread().getName(), (end - start));
    return CompletableFuture.completedFuture(file);
  }

  public Boolean connectUpload(UUID uuid,
                               JobDestinationDto jobDestinationDto) {

    FtpServerDto ftpServerDto = ftpServerService.findById(uuid);
    log.info("{} connect&upload thread : {}", ftpServerDto.getUsername(),
        Thread.currentThread().getName());
    Client client = new ClientFactory().createClient(ClientFactory.Protocol.FTP);
    client.setHost(ftpServerDto.getHostAdress());
    client.setPort(ftpServerDto.getPort());
    client.setCredentials(
        new UserCredentials(ftpServerDto.getUsername(), ftpServerDto.getPassword()));
    Connection connection = client.connect();
    connection.uploadFile(jobDestinationDto.getDownloadPath(), jobDestinationDto.getUploadPath(),
        true);
    client.disconnect();
    return true;
  }
}