package tr.com.tkeskin.models;

import java.util.List;
import lombok.Data;

@Data
public class FtpServerList {

  private List<FtpServerDto> ftpServerList;
}