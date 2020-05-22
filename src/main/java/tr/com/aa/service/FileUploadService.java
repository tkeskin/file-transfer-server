package tr.com.aa.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import tr.com.aa.models.JobDestinationDto;

public interface FileUploadService {

  CompletableFuture<List<JobDestinationDto>> uploadFile(
      Map.Entry<UUID, List<JobDestinationDto>> file);
}
