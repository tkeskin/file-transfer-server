package tr.com.tkeskin.service;

import java.util.concurrent.CompletableFuture;
import tr.com.tkeskin.models.JobDestinationDto;

public interface FileUploadService {

  CompletableFuture<JobDestinationDto> uploadFile(JobDestinationDto file);
}
