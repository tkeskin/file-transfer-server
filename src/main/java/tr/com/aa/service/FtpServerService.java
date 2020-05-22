package tr.com.aa.service;

import java.util.UUID;
import tr.com.aa.models.FtpServerDto;
import tr.com.aa.models.FtpServerList;

public interface FtpServerService {

  FtpServerDto findById(UUID id);

  FtpServerList findAll();

  Boolean saveFtpServer(FtpServerDto ftpServerDto);

  Boolean deleteFtpServer(UUID id);

  Boolean testConnection(FtpServerDto ftpServerDto);
}
