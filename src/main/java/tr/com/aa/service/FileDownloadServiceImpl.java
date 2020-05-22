package tr.com.aa.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.aa.models.JobDestinationDto;
import tr.com.aa.util.FileUtil;

@Slf4j
@Transactional
@Service
public class FileDownloadServiceImpl implements FileDownloadService {

  @Autowired
  ProjectService projectService;

  @Async
  @Override
  public CompletableFuture<List<JobDestinationDto>> downloadFile(String downloadPath,
                                                                 List<JobDestinationDto> jobDestinationDto) {

    List<JobDestinationDto> jobDestination = new ArrayList<>();
    for (JobDestinationDto jobDestinationDtos : jobDestinationDto) {
      log.info(
          "Start download thread: {} for url {}", Thread.currentThread().getName(),
          jobDestinationDtos.getDownloadUrl());
      InputStream inputStream;
      FileOutputStream outputStream;
      String targetDirectory = getTodayFolder(downloadPath);
      FileUtil.createLocalDirectory(targetDirectory);
      String fileName = jobDestinationDtos.getDownloadUrl()
          .substring(jobDestinationDtos.getDownloadUrl().lastIndexOf('/') + 1);
      long start = System.currentTimeMillis();
      try {
        inputStream = new URL(jobDestinationDtos.getDownloadUrl()).openStream();
        outputStream = new FileOutputStream(targetDirectory + File.separator
            + FilenameUtils.getName(fileName));
        int i = IOUtils.copy(inputStream, outputStream);
        if (i == -1) {
          IOUtils.copyLarge(inputStream, outputStream);
        }
        jobDestinationDtos.setDownload(true);
        jobDestinationDtos.setDownloadPath(targetDirectory + File.separator
            + FilenameUtils.getName(fileName));
        jobDestinationDtos.setDownloadDateTime(new Date());
      } catch (IOException e) {
        log.error("{} : ", jobDestinationDtos.getDownloadUrl(), e);
      }
      long end = System.currentTimeMillis();
      log.info("{} Total time {} for file name {}", Thread.currentThread().getName(), (end - start),
          targetDirectory + File.separator
              + FilenameUtils.getName(fileName));
      jobDestination.add(jobDestinationDtos);
    }
    return CompletableFuture.completedFuture(jobDestination);
  }

  private String getTodayFolder(String downloadPath) {

    String format = "yyyy-MM-dd-HH:mm";
    DateFormat dateFormatter = new SimpleDateFormat(format);
    String date = dateFormatter.format(new Date());
    return FileUtils.getUserDirectoryPath() + downloadPath + "/" + date;
  }
}