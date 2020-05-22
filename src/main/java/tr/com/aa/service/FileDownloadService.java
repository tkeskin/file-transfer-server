package tr.com.aa.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import tr.com.aa.models.JobDestinationDto;

public interface FileDownloadService {

  CompletableFuture<List<JobDestinationDto>> downloadFile(String downloadPath,
                                                          List<JobDestinationDto> jobDestinationDto);
}
