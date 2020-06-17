package tr.com.tkeskin.service;

import tr.com.tkeskin.models.JobDestinationDto;

import java.util.concurrent.CompletableFuture;

public interface FileUploadService {

    CompletableFuture<JobDestinationDto> uploadFile(JobDestinationDto file);
}
