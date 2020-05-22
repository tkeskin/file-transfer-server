package tr.com.aa.service;

import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.aa.client.Client;
import tr.com.aa.client.ClientFactory;
import tr.com.aa.client.auth.UserCredentials;
import tr.com.aa.dal.entity.FtpServerEntity;
import tr.com.aa.dal.repository.FtpServerRepository;
import tr.com.aa.exception.EntityNotfoundException;
import tr.com.aa.mapper.FtpServerMapper;
import tr.com.aa.models.FtpServerDto;
import tr.com.aa.models.FtpServerList;

@Slf4j
@Transactional
@Service
public class FtpServerServiceImpl implements FtpServerService {

  @Autowired
  FtpServerRepository ftpServerRepository;

  @Override
  public FtpServerDto findById(UUID id) {

    Optional<FtpServerEntity> byId = Optional.ofNullable(
        ftpServerRepository.findById(id).stream().findAny()
            .orElseThrow(() -> new EntityNotfoundException(id.toString())));

    return FtpServerMapper.INSTANCE.toFtpServerDto(byId.get());
  }

  @Override
  public FtpServerList findAll() {

    FtpServerList ftpServerList = new FtpServerList();
    ftpServerList.setFtpServerList(FtpServerMapper.INSTANCE
        .toFtpServerDtoList(ftpServerRepository.findAll()));
    return ftpServerList;
  }

  @Override
  public Boolean saveFtpServer(FtpServerDto ftpServerDto) {

    ftpServerRepository
        .save(FtpServerMapper.INSTANCE.toFtpServerEntity(ftpServerDto));
    return true;
  }

  @Override
  public Boolean deleteFtpServer(UUID id) throws EntityNotfoundException {

    ftpServerRepository.deleteById(id);
    return true;
  }

  @Override
  public Boolean testConnection(FtpServerDto ftpServerDto) {

    log.info("{} attempting to test connection to host {}", ftpServerDto.getUsername(),
        ftpServerDto.getHostAdress());
    Client client = new ClientFactory().createClient(ClientFactory.Protocol.FTP);
    client.setHost(ftpServerDto.getHostAdress());
    client.setPort(ftpServerDto.getPort());
    client.setCredentials(
        new UserCredentials(ftpServerDto.getUsername(), ftpServerDto.getPassword()));
    log.debug("Making connection on port {}", ftpServerDto.getPort());
    client.connect();
    log.info("Connection successful.");
    client.disconnect();
    log.debug("Disconnected");
    return true;
  }
}