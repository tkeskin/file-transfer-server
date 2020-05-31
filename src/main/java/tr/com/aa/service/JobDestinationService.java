package tr.com.aa.service;

import java.util.List;
import java.util.UUID;
import tr.com.aa.models.JobDestinationDto;

public interface JobDestinationService {

  List<JobDestinationDto> findByDownloadFalseAndJobEntityId(UUID id);

  List<JobDestinationDto> findByDownloadTrueAndSendFalseAndJobEntityId(UUID id);

  String findByDownloadFalseAndId(List<JobDestinationDto> jobDestinationDto);

  String updateSend(List<JobDestinationDto> jobDestinationDto);

  Boolean findAllfileSend(UUID id);

}
