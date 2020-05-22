package tr.com.aa.service;

import java.util.List;
import java.util.UUID;
import tr.com.aa.models.JobDestinationDto;

public interface JobDestinationService {

  List<JobDestinationDto> findByJobEntityId(UUID id);

  String findByDownloadFalseAndId(List<JobDestinationDto> jobDestinationDto);

  String updateSend(List<JobDestinationDto> jobDestinationDto);

}
