package tr.com.tkeskin.models;

import lombok.Data;

import java.util.List;

@Data
public class FtpServerList {

    private List<FtpServerDto> ftpServerList;
}