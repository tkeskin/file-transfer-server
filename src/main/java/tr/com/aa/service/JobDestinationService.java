package tr.com.aa.service;

import java.util.List;
import java.util.UUID;
import tr.com.aa.dal.entity.JobDestinationEntity;
import tr.com.aa.models.JobDestinationDto;
import tr.com.aa.models.JobDestinationList;

public interface JobDestinationService {

  List<JobDestinationDto> findByDownloadFalseAndJobEntityId(UUID id);

  List<JobDestinationDto> findByDownloadTrueAndSendFalseAndJobEntityId(UUID id);

  JobDestinationList findByDownloadFalse();

  JobDestinationEntity findByDownloadFalseAndId(JobDestinationDto jobDestinationDto);

  String updateSend(JobDestinationDto jobDestinationDto);

  Boolean findAllfileSend(UUID id);

  JobDestinationDto findById(UUID id);

}
