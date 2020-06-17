package tr.com.tkeskin.models;

import java.util.Date;
import java.util.UUID;
import lombok.Data;

@Data
public class JobDestinationDto {

  private UUID id;
  private String downloadPath;
  private Boolean download;
  private Date downloadDateTime;
  private String downloadUrl;
  private String uploadPath;
  private Boolean send;
  private Date sendDateTime;
  private UUID ftpServerId;
}
