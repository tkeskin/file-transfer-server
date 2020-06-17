package tr.com.tkeskin.service;

import tr.com.tkeskin.models.FtpServerDto;
import tr.com.tkeskin.models.FtpServerList;

import java.util.UUID;

public interface FtpServerService {

    FtpServerDto findById(UUID id);

    FtpServerList findAll();

    Boolean saveFtpServer(FtpServerDto ftpServerDto);

    Boolean deleteFtpServer(UUID id);

    Boolean testConnection(FtpServerDto ftpServerDto);
}
