package tr.com.tkeskin.models;

import lombok.Data;

@Data
public class JobDestinationViewDto {

  private String downloadPath;
  private Boolean download;
  private String downloadDateTime;
  private String downloadUrl;
  private String uploadPath;
  private Boolean send;
  private String sendDateTime;
}