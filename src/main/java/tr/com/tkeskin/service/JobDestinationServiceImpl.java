package tr.com.tkeskin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.tkeskin.dal.entity.JobDestinationEntity;
import tr.com.tkeskin.dal.repository.JobDestinationRepository;
import tr.com.tkeskin.exception.EntityNotfoundException;
import tr.com.tkeskin.mapper.JobDestinationMapper;
import tr.com.tkeskin.models.JobDestinationDto;
import tr.com.tkeskin.models.JobDestinationList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class JobDestinationServiceImpl implements JobDestinationService {

    @Autowired
    JobDestinationRepository jobDestinationRepository;

    @Override
    public List<JobDestinationDto> findByDownloadFalseAndJobEntityId(UUID jobId) {

        return JobDestinationMapper.INSTANCE
                .toJobDestinationDto(jobDestinationRepository.findByDownloadFalseAndJobEntityId(jobId));
    }

    @Override
    public List<JobDestinationDto> findByDownloadTrueAndSendFalseAndJobEntityId(UUID jobId) {

        return JobDestinationMapper.INSTANCE
                .toJobDestinationDto(
                        jobDestinationRepository.findByDownloadTrueAndSendFalseAndJobEntityId(jobId));
    }

    @Override
    public JobDestinationList findByDownloadFalse() {

        JobDestinationList jobDestinationList = new JobDestinationList();
        jobDestinationList.setJobDestinationList(JobDestinationMapper.INSTANCE
                .toJobDestinationDto(
                        jobDestinationRepository.findByDownloadFalse()));
        return jobDestinationList;
    }

    @Override
    public JobDestinationEntity findByDownloadFalseAndId(JobDestinationDto jobDestinationDto) {

        JobDestinationEntity byDownload = jobDestinationRepository
                .findByDownloadFalseAndId(jobDestinationDto.getId());
        if (jobDestinationDto.getDownload()) {
            byDownload.setDownload(true);
            byDownload.setDownloadDateTime(jobDestinationDto.getDownloadDateTime());
            byDownload.setDownloadPath(jobDestinationDto.getDownloadPath());
            jobDestinationRepository.save(byDownload);
        }
        return byDownload;
    }

    @Override
    public String updateSend(JobDestinationDto jobDestinationDto) {

        JobDestinationEntity byUpload = jobDestinationRepository
                .getById(jobDestinationDto.getId());
        if (jobDestinationDto.getSend()) {
            byUpload.setSend(jobDestinationDto.getSend());
            byUpload.setSendDateTime(jobDestinationDto.getSendDateTime());
            jobDestinationRepository.save(byUpload);
        }

        return "aa";
    }

    @Override
    public Boolean findAllfileSend(UUID id) {

        return jobDestinationRepository.findByJobEntityId(id)
                .stream()
                .allMatch(JobDestinationEntity::getSend);
    }

    @Override
    public JobDestinationDto findById(UUID id) {

        Optional<JobDestinationEntity> byId = Optional
                .ofNullable(jobDestinationRepository.findById(id)
                        .orElseThrow(() -> new EntityNotfoundException(id.toString())));

        return JobDestinationMapper.INSTANCE.toJobDestination(byId.get());
    }
}