package tr.com.tkeskin.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.tkeskin.models.JobDestinationDto;
import tr.com.tkeskin.util.FileUtil;

@Slf4j
@Transactional
@Service
public class FileDownloadServiceImpl implements FileDownloadService {

  @Autowired
  ProjectService projectService;

  @Async("downloadTaskExecutor")
  @Override
  public CompletableFuture<JobDestinationDto> downloadFile(String downloadPath,
                                                           JobDestinationDto jobDestinationDto) {

    log.info(
        "Start download thread: {} for url {}", Thread.currentThread().getName(),
        jobDestinationDto.getDownloadUrl());
    InputStream inputStream;
    FileOutputStream outputStream;
    String targetDirectory = getTodayFolder(downloadPath);
    FileUtil.createLocalDirectory(targetDirectory);
    String fileName = jobDestinationDto.getDownloadUrl()
        .substring(jobDestinationDto.getDownloadUrl().lastIndexOf('/') + 1);
    long start = System.currentTimeMillis();
    try {
      inputStream = new URL(jobDestinationDto.getDownloadUrl()).openStream();
      outputStream = new FileOutputStream(targetDirectory + File.separator
          + FilenameUtils.getName(fileName));
      int i = IOUtils.copy(inputStream, outputStream);
      if (i == -1) {
        IOUtils.copyLarge(inputStream, outputStream);
      }
      jobDestinationDto.setDownload(true);
      jobDestinationDto.setDownloadPath(targetDirectory + File.separator
          + FilenameUtils.getName(fileName));
      jobDestinationDto.setDownloadDateTime(new Date());
    } catch (IOException e) {
      log.error("{} : ", jobDestinationDto.getDownloadUrl(), e);
    }
    long end = System.currentTimeMillis();
    log.info("{} Total time {} for file name {}", Thread.currentThread().getName(), (end - start),
        targetDirectory + File.separator
            + FilenameUtils.getName(fileName));
    return CompletableFuture.completedFuture(jobDestinationDto);
  }

  private String getTodayFolder(String downloadPath) {

    String format = "yyyy-MM-dd-HH";
    DateFormat dateFormatter = new SimpleDateFormat(format);
    String date = dateFormatter.format(new Date());
    return FileUtils.getUserDirectoryPath() + downloadPath + "/" + date;
  }
}