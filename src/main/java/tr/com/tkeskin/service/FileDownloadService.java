package tr.com.tkeskin.service;

import java.util.concurrent.CompletableFuture;
import tr.com.tkeskin.models.JobDestinationDto;

public interface FileDownloadService {

  CompletableFuture<JobDestinationDto> downloadFile(String downloadPath,
                                                    JobDestinationDto jobDestinationDto);
}