package tr.com.aa.service;

import java.util.concurrent.CompletableFuture;
import tr.com.aa.models.JobDestinationDto;

public interface FileUploadService {

  CompletableFuture<JobDestinationDto> uploadFile(JobDestinationDto file);
}
