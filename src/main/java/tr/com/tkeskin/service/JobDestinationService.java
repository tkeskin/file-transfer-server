package tr.com.tkeskin.service;

import tr.com.tkeskin.dal.entity.JobDestinationEntity;
import tr.com.tkeskin.models.JobDestinationDto;
import tr.com.tkeskin.models.JobDestinationList;

import java.util.List;
import java.util.UUID;

public interface JobDestinationService {

    List<JobDestinationDto> findByDownloadFalseAndJobEntityId(UUID id);

    List<JobDestinationDto> findByDownloadTrueAndSendFalseAndJobEntityId(UUID id);

    JobDestinationList findByDownloadFalse();

    JobDestinationEntity findByDownloadFalseAndId(JobDestinationDto jobDestinationDto);

    String updateSend(JobDestinationDto jobDestinationDto);

    Boolean findAllfileSend(UUID id);

    JobDestinationDto findById(UUID id);

}
