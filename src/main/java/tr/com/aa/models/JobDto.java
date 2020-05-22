package tr.com.aa.models;

import java.util.UUID;
import lombok.Data;

@Data
public class JobDto {

  private UUID id;
  private UUID createdById;
  private String createdBy;
  private String createdDateTime;
  private Boolean autoStart;
}