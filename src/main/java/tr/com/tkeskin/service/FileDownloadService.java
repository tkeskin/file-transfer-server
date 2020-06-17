package tr.com.tkeskin.service;

import tr.com.tkeskin.models.JobDestinationDto;

import java.util.concurrent.CompletableFuture;

public interface FileDownloadService {

    CompletableFuture<JobDestinationDto> downloadFile(String downloadPath,
                                                      JobDestinationDto jobDestinationDto);
}