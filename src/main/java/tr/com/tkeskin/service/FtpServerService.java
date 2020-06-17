package tr.com.tkeskin.service;

import java.util.UUID;
import tr.com.tkeskin.models.FtpServerDto;
import tr.com.tkeskin.models.FtpServerList;

public interface FtpServerService {

  FtpServerDto findById(UUID id);

  FtpServerList findAll();

  Boolean saveFtpServer(FtpServerDto ftpServerDto);

  Boolean deleteFtpServer(UUID id);

  Boolean testConnection(FtpServerDto ftpServerDto);
}
