package tr.com.aa.service;

import java.util.concurrent.CompletableFuture;
import tr.com.aa.models.JobDestinationDto;

public interface FileDownloadService {

  CompletableFuture<JobDestinationDto> downloadFile(String downloadPath,
                                                    JobDestinationDto jobDestinationDto);
}